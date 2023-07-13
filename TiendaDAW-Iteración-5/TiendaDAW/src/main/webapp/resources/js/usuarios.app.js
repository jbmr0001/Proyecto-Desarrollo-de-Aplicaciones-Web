/** Función de utilidad para selección de elementos en DOM
 * @param selector, selector CSS del elemento
 **/
let el = selector => document.querySelector(selector);

class UsuariosCtrl {
    constructor() {
        this.srvUrl = "api/usuarios"; //REST service url

//view-model
        this.usuarios = [];
        this.usuario=null
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
        this.cargaUsuarios();
    }


    cargaUsuarios() {
        return fetch( this.srvUrl )
            .then(response => response.json()) //Promise resolve handler
            .then( usuarios => { //Promise resolve handler
                this.usuarios=usuarios;
                this.visualizaUsuarios();
                return true;
            })
            .catch(() => { //Network error
                el('#errores').innerHTML="Error en conexión";
                console.error("Error en conexión");
                return false;
            });
    }
    visualizaUsuarios() {
        let ul = el('#lista');
        ul.innerHTML = '';
        this.usuarios.forEach(usuario => {
            let li = document.createElement('li');
            li.innerHTML = `<b>${usuario.email}</b> <b>${usuario.contraseña}</b> <b>${usuario.nombre}</b>
            <b>${usuario.direccion}</b> <b>${usuario.tarjeta}</b>   <b>${usuario.admin}</b>`;
            ul.appendChild(li);
        });
    }
    async alta(event) { //async/await onsubmit handler
        event.preventDefault();
        let email = el('[name="emailInsertar"]').value;
        let contraseña = el('[name="contraseñaInsertar"]').value;
        let nombre = el('[name="nombreInsertar"]').value;
        let direccion = el('[name="direccionInsertar"]').value;
        let tarjeta = el('[name="tarjetaInsertar"]').value;
        let admin = el('[name="adminInsertar"]').value;

        console.log('alta de usuario %s: %s', email, contraseña,nombre,direccion,tarjeta,admin);
        let usuario = {
            email: email,
            contraseña: contraseña,
            nombre:nombre,
            direccion:direccion,
            tarjeta:tarjeta,
            admin:admin
        };
        if ( await this.enviaUsuario(usuario,"POST") ) {
            el('#fAlta').reset(); //clear form
            this.cargaUsuarios();
        }
    }

    async modificar(event) { //async/await onsubmit handler
        event.preventDefault();
        let email = el('[name="emailModificar"]').value;
        let contraseña = el('[name="contraseñaModificar"]').value;
        let nombre = el('[name="nombreModificar"]').value;
        let direccion = el('[name="direccionModificar"]').value;
        let tarjeta = el('[name="tarjetaModificar"]').value;
        let admin = el('[name="adminModificar"]').value;
        console.log('modificacion de usuario %s: %s', email, contraseña,nombre,direccion,tarjeta,admin);
        let usuario = {
            email: email,
            contraseña: contraseña,
            nombre:nombre,
            direccion:direccion,
            tarjeta:tarjeta,
            admin:admin
        };

        if ( await this.enviaUsuario(usuario,"PUT") ) {
            el('#fMod').reset(); //clear form
            this.cargaUsuarios();
        }
    }

    async borrar(event) { //async/await onsubmit handler
        event.preventDefault();
        let email = el('[name="emailBorrar"]').value;
        console.log('borrado de usuario %s: %s', email);
        let usuario = {
            email: email
        };
        //this.validarDatos(usuario);
        if ( await this.enviaUsuarioBorrado(usuario,"DELETE") ) {
            el('#fBorra').reset(); //clear form
            this.cargaUsuarios();
        }
    }

    async enviaUsuario(usuario,operacion) { //async/await function
        let enviado = false;
        let errores="";
        let url=this.srvUrl
        console.log(usuario.email)
        console.log(operacion=="PUT")
        if (operacion == "PUT"){
            url=this.srvUrl+"/"+usuario.email;

        }
        console.log(url);
        try {
            let response = await fetch (url, {
                method: operacion,
                body: JSON.stringify(usuario),
                headers: {
                    'Content-type': 'application/json',
                    'accept': 'application/json'
                }
            })
//get json response: libro or bean validation errors
            let data = await response.json();
            if (response.ok) { //usuario accepted on server? (status 200)
                enviado = true;
                console.log(`Confirmada alta de usuario: ${data.email}`);
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

    async enviaUsuarioBorrado(usuario,operacion) { //async/await function
        let enviado = false;
        let errores="";
        let url=this.srvUrl
        console.log(usuario.email)
        url=this.srvUrl+"/"+usuario.email;

        console.log(url);
        try {
            let response = await fetch (url, {
                method: operacion
            })
//get json response: libro or bean validation errors
            await response;
            if (response.ok) { //usuario accepted on server? (status 200)
                enviado = true;
                console.log(`Confirmada borrada de usuario:`);
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
    window.ctrl = new UsuariosCtrl();
    console.log('Inicializando controlador de usuarios');
    ctrl.init();
});