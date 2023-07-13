$( () => {
    window.ctrl = new ProductosCtrl(); //Register global var
    ctrl.init(); //Attach view event Handlers
});
class ProductosCtrl {
    constructor () {
        this.config= {
            formularioInsertarProducto: "form[name=frInsertarProducto]",
            formularioModificarProducto: "form[name=frModificarProducto]"
        };
    }
    init () {
        $(this.config.formularioInsertarProducto)
            .on('submit', event => { //ev. handler
                let idProducto=99 //Valor auxiliar para pasar la validación ya que en este formulario no se introduce id, es autocalculado
                let nombre = document.querySelector('#frInsertarProducto\\:nombre').value
                let precio = document.querySelector('#frInsertarProducto\\:precio').value
                let stock = document.querySelector('#frInsertarProducto\\:stock').value
                return this.validarDatos(idProducto,nombre,precio,stock)
            });
        $(this.config.formularioModificarProducto)
            .on('submit', event => { //ev. handler
                let idProducto = document.querySelector('#frModificarProducto\\:id').value
                let nombre = document.querySelector('#frModificarProducto\\:nombre').value
                let precio = document.querySelector('#frModificarProducto\\:precio').value
                let stock = document.querySelector('#frModificarProducto\\:stock').value
                return this.validarDatos(idProducto,nombre,precio,stock)
            });
    }
    validarDatos (idProducto,nombre,precio,stock) {
        let result = true;
        $('#errIdProducto,#errNombre, #errPrecio, #errStock' ).empty(); //clear previous errors

        if (idProducto== 0) {
            $('#errIdProducto').text('Debe introducir un idProducto');
            result = false;
            console.log("Debe introducir un idProducto");
        };

        if (nombre==""){
            $('#errNombre').text('Debe introducir un nombre ');
            result = false;
            console.log("Debe introducir un nombre");
        }else{
            if (nombre.size>300) {
                $('#errNombre').text('Debe introducir un nombre con menos de 300 caracteres');
                result = false;
                console.log("Debe introducir un nombre con menos de 300 caracteres");
            };
        }

        if (precio<1) {
            $('#errPrecio').text('El precio mínimo es 1');
            result = false;
            console.log("El precio mínimo es 1");
        };

        if (stock< 1) {
            $('#errStock').text('El stock mínimo es 1');
            result = false;
            console.log("El stock mínimo es 1");
        };

        return result;
    }
};