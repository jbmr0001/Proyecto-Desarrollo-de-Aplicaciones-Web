/** Función de utilidad para selección de elementos en DOM
 * @param selector, selector CSS del elemento
 **/
let el = selector => document.querySelector(selector);

class ComprasCtrl {
    constructor() {
        this.srvUrl = "api/compras"; //REST service url

//view-model
        this.compras = [];
        this.compra=null
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
        this.cargaCompras();
    }


    cargaCompras() {
        return fetch( this.srvUrl )
            .then(response => response.json()) //Promise resolve handler
            .then( compras => { //Promise resolve handler
                this.compras=compras;
                this.visualizaCompras();
                return true;
            })
            .catch(() => { //Network error
                el('#errores').innerHTML="Error en conexión";
                console.error("Error en conexión");
                return false;
            });
    }
    visualizaCompras() {
        let ul = el('#lista');
        ul.innerHTML = '';
        this.compras.forEach(compra => {
            let li = document.createElement('li');
            li.innerHTML = `<b>${compra.id}</b> <b>${compra.email}</b> <b>${compra.idProducto}</b>
            `;
            ul.appendChild(li);
        });
    }
    async alta(event) { //async/await onsubmit handler
        event.preventDefault();
        let id = 0
        let email = el('[name="emailInsertar"]').value;
        let idProducto = el('[name="idProductoInsertar"]').value;

        console.log('alta de compra %s: %s', id,email,idProducto);
        let compra = {
            id: id,
            email: email,
            idProducto:idProducto
        };
        if ( await this.enviaCompra(compra,"POST") ) {
            el('#fAlta').reset(); //clear form
            this.cargaCompras();
        }
    }

    async modificar(event) { //async/await onsubmit handler
        event.preventDefault();
        let id = el('[name="idModificar"]').value;
        let email = el('[name="emailModificar"]').value;
        let idProducto = el('[name="idProductoModificar"]').value;
        console.log('alta de compra %s: %s', id,email,idProducto);
        let compra = {
            id: id,
            email: email,
            idProducto:idProducto
        };

        if ( await this.enviaCompra(compra,"PUT") ) {
            el('#fMod').reset(); //clear form
            this.cargaCompras();
        }
    }

    async borrar(event) { //async/await onsubmit handler
        event.preventDefault();
        let id = el('[name="idBorrar"]').value;
        console.log('borrado de compra %s: %s', id);
        let compra = {
            id:id
        };
        if ( await this.enviaCompraBorrada(compra,"DELETE") ) {
            el('#fBorra').reset(); //clear form
            this.cargaCompras();
        }
    }

    async enviaCompra(compra,operacion) { //async/await function
        let enviado = false;
        let errores="";
        let url=this.srvUrl
        console.log(compra.id)
        console.log(operacion=="PUT")
        if (operacion == "PUT"){
            url=this.srvUrl+"/"+compra.id;

        }
        console.log(url);
        try {
            let response = await fetch (url, {
                method: operacion,
                body: JSON.stringify(compra),
                headers: {
                    'Content-type': 'application/json',
                    'accept': 'application/json'
                }
            })
//get json response: libro or bean validation errors
            let data = await response.json();
            if (response.ok) {
                enviado = true;
                console.log(`Confirmada alta de compra: ${data.id}`);
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

    async enviaCompraBorrada(compra,operacion) { //async/await function
        let enviado = false;
        let errores="";
        let url=this.srvUrl
        console.log(compra.id)
        url=this.srvUrl+"/"+compra.id;

        console.log(url);
        try {
            let response = await fetch (url, {
                method: operacion
            })
//get json response: libro or bean validation errors
            await response;
            if (response.ok) {
                enviado = true;
                console.log(`Confirmada borrada de compra:`);
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
    window.ctrl = new ComprasCtrl();
    console.log('Inicializando controlador de compras');
    ctrl.init();
});