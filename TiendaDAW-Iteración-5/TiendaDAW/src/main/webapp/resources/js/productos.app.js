/** Función de utilidad para selección de elementos en DOM
 * @param selector, selector CSS del elemento
 **/
let el = selector => document.querySelector(selector);

class ProductosCtrl {
    constructor() {
        this.srvUrl = "api/productos"; //REST service url

//view-model
        this.productos = [];
        this.producto=null
    }

    init () {
        el('#fAlta').addEventListener('submit', event => {
            this.alta(event);
        });
        el('#fMod').addEventListener('submit', event => {
            this.modificar(event);
        });

        el('#fBorra').addEventListener('submit', event => {
            this.borrar(event);
        });
        this.cargaProductos();
    }


    cargaProductos() {
        return fetch( this.srvUrl )
            .then(response => response.json()) //Promise resolve handler
            .then( productos => { //Promise resolve handler
                this.productos=productos;
                this.visualizaProductos();
                return true;
            })
            .catch(() => { //Network error
                el('#errores').innerHTML="Error en conexión";
                console.error("Error en conexión");
                return false;
            });
    }
    visualizaProductos() {
        let ul = el('#lista');
        ul.innerHTML = '';
        this.productos.forEach(producto => {
            let li = document.createElement('li');
            li.innerHTML = `<b>${producto.id}</b> <b>${producto.nombre}</b> <b>${producto.descripcion}</b> <b>${producto.precio}</b>
            <b>${producto.ruta}</b> <b>${producto.stock}</b>`;
            ul.appendChild(li);
        });
    }
    async alta(event) { //async/await onsubmit handler
        event.preventDefault();
        let id = 0
        let nombre = el('[name="nombreInsertar"]').value;
        let descripcion = el('[name="descripcionInsertar"]').value;
        let precio = el('[name="precioInsertar"]').value;
        let ruta = el('[name="rutaInsertar"]').value;
        let stock = el('[name="stockInsertar"]').value;

        console.log('creación de producto %s: %s', id,nombre,descripcion,precio,ruta,stock);
        let producto = {
            id: id,
            nombre: nombre,
            descripcion:descripcion,
            precio:precio,
            ruta:ruta,
            stock:stock
        };
        if ( await this.enviaProducto(producto,"POST") ) {
            el('#fAlta').reset(); //clear form
            this.cargaProductos()
        }
    }

    async modificar(event) { //async/await onsubmit handler
        event.preventDefault();
        let id = el('[name="idModificar"]').value;
        let nombre = el('[name="nombreModificar"]').value;
        let descripcion = el('[name="descripcionModificar"]').value;
        let precio = el('[name="precioModificar"]').value;
        let ruta = el('[name="rutaModificar"]').value;
        let stock = el('[name="stockModificar"]').value;
        console.log("hola")
        console.log('modificacion de producto %s: %s', id,nombre,descripcion,precio,ruta,stock);
        let producto = {
            id: id,
            nombre: nombre,
            descripcion:descripcion,
            precio:precio,
            ruta:ruta,
            stock:stock
        };

        if ( await this.enviaProducto(producto,"PUT") ) {
            el('#fMod').reset(); //clear form
            this.cargaProductos();
        }
    }

    async borrar(event) { //async/await onsubmit handler
        event.preventDefault();
        let id = el('[name="idBorrar"]').value;
        console.log('borrado de producto %s: %s', id);
        let producto = {
            id: id
        };
        if ( await this.enviaProductoBorrado(producto,"DELETE") ) {
            el('#fBorra').reset(); //clear form
            this.cargaProductos();
        }
    }

    async enviaProducto(producto,operacion) { //async/await function
        let enviado = false;
        let errores="";
        let url=this.srvUrl
        console.log(producto.id)
        console.log(operacion=="PUT")
        if (operacion == "PUT"){
            url=this.srvUrl+"/"+producto.id;

        }
        console.log(url);
        try {
            let response = await fetch (url, {
                method: operacion,
                body: JSON.stringify(producto),
                headers: {
                    'Content-type': 'application/json',
                    'accept': 'application/json'
                }
            })
//get json response: libro or bean validation errors
            let data = await response.json();
            if (response.ok) { //producto accepted on server? (status 200)
                enviado = true;
                console.log(`Confirmada alta del producto: ${data.id}`);
            } else { // bean-validation errors!
                errores = data[0].message;
                console.warn(data);
            }
        } catch (ex) { //Network error
            errores = "Error en conexión";
            console.error(errores);
        }
        el('#errores').innerHTML = errores; //show or clear errors
        return enviado;
    }

    async enviaProductoBorrado(producto,operacion) { //async/await function
        let enviado = false;
        let errores="";
        let url=this.srvUrl
        console.log(producto.id)
        url=this.srvUrl+"/"+producto.id;

        console.log(url);
        try {
            let response = await fetch (url, {
                method: operacion
            })
//get json response: libro or bean validation errors
            await response;
            if (response.ok) { //producto accepted on server? (status 200)
                enviado = true;
                console.log(`Confirmada borrada de producto:`);
            }
        } catch (ex) { //Network error
            errores = "Error en conexión";
            console.error(errores);
        }
        el('#errores').innerHTML = errores; //show or clear errors
        return enviado;
    }

}

window.addEventListener('load', () => {
//Create and initialize controller
    window.ctrl = new ProductosCtrl();
    console.log('Inicializando controlador de productos');
    ctrl.init();
});