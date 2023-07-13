package controller;

import dao.ProductoDAO;
import jakarta.validation.Valid;
import model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    @Qualifier("productosDAOMap")
    private ProductoDAO productoDAO;

    @PostMapping()
    public String alta(@ModelAttribute("producto") @Valid Producto l,
                       BindingResult result, //Justo después del bean validado
                       ModelMap model) {
        String vista = "productos/index";
        if (!result.hasErrors()) {
            productoDAO.guarda(new Producto(l.getId(), l.getNombre(), l.getDescripcion(), l.getPrecio()));
            vista = "redirect:/productos"; //PRG
        } else { //Mostrar formulario con errores
            model.addAttribute("productos", productoDAO.buscaTodos());
        }
        return vista;
    }

    @GetMapping("")
    public String listado(ModelMap model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", productoDAO.buscaTodos());
        return "productos/index";
    }

    @GetMapping("/alta")
    public String alta2(ModelMap model) {
        model.addAttribute("producto", new Producto());
        return "productos/alta";
    }

    @GetMapping("/paginaCRUD")
    public String paginaCRUD(ModelMap model) {
        model.addAttribute("producto", new Producto());
        return "productos/paginaCRUD";
    }

    @GetMapping("/listado")
    public String lista(ModelMap model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", productoDAO.buscaTodos());
        return "productos/listado";
    }


    @GetMapping("/crea")
    public String creaForm(ModelMap model) {
        model.addAttribute("producto", new Producto());
        return "productos/alta";
    }

    @PostMapping("/crea")
    public String crea(@ModelAttribute("producto") @Valid Producto producto, BindingResult result, ModelMap model) {
        String view = "redirect:listado"; //default view

        if (!result.hasErrors()) {
            if(productoDAO.buscaById(producto.getId())!=null){
                view = "productos/alta"; //ask for correct data
                model.addAttribute("error","Ya existe un producto con id "+producto.getId());
            }else{
                productoDAO.guarda(producto);
                model.clear();
            }
        } else {
            view = "productos/alta"; //ask for correct data
        }
        return view;
    }

    @GetMapping("/modifica")
    public String modificaForm(ModelMap model) {
        model.addAttribute("producto", new Producto());
        return "productos/modificar";
    }

    @PostMapping("/modifica")
    public String modifica(@ModelAttribute("producto") @Valid Producto producto, BindingResult result, ModelMap model) {
        String view = "redirect:listado"; //default view

        if (!result.hasErrors()) {
            if(productoDAO.buscaById(producto.getId())==null){
                view = "productos/modificar"; //ask for correct data
                model.addAttribute("error","No existe producto con id "+producto.getId());
            }else{
                productoDAO.modifica(producto);
                model.clear();
            }
        } else {
            view = "productos/modificar"; //ask for correct data
        }
        return view;
    }

    @GetMapping("/borra")
    public String borraForm(ModelMap model) {
        model.addAttribute("producto", new Producto());
        return "productos/borrar";
    }

    @PostMapping("/borra")
    public String borra(@ModelAttribute("producto") @Valid Producto producto, BindingResult result, ModelMap model) {
        String view = "redirect:listado"; //default view

        if(productoDAO.buscaById(producto.getId())==null){
            view = "productos/borrar"; //ask for correct data
            model.addAttribute("error","No existe producto con id "+producto.getId());
        }else{
            productoDAO.borra(producto);
            model.clear();
        }
        return view;
    }
    @GetMapping("/visualiza/{id}")
    public String visualizaId(@PathVariable Integer id, ModelMap model ) {
        model.addAttribute("producto",productoDAO.buscaById(id));
        return "productos/visualiza";
    }
}

