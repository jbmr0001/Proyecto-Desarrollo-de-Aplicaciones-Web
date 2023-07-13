$( () => {
    window.ctrl = new UsuariosCtrl(); //Register global var
    ctrl.init(); //Attach view event Handlers
});
class UsuariosCtrl {
    constructor () {
        this.config= {
            formularioRegistro: "form[name=frRegistro]",
            formularioLogin: "form[name=frLogin]",
            formularioInsertarUsuario: "form[name=frInsertarUsuario]",
            formularioModificarUsuario: "form[name=frModificarUsuario]",
            ibEmail: "[name=email]",
            ibContraseña: "[name=contr]"

        };
    }
    init () {
        $(this.config.formularioRegistro)
            .on('submit', event => { //ev. handler
                let email = document.querySelector('#frRegistro\\:email').value
                let contraseña = document.querySelector('#frRegistro\\:contr').value
                return this.validarDatos(email,contraseña)
            });

        $(this.config.formularioLogin)
            .on('submit', event => { //ev. handler
                console.log("hola")
                let email = $(this.config.ibEmail).val();
                let contraseña = $(this.config.ibContraseña).val();
                return this.validarDatos(email,contraseña)
            });

        $(this.config.formularioInsertarUsuario)
            .on('submit', event => { //ev. handler
                let email = document.querySelector('#frInsertarUsuario\\:email').value
                let contraseña = document.querySelector('#frInsertarUsuario\\:contr').value
                return this.validarDatos(email,contraseña)
            });
        $(this.config.formularioModificarUsuario)
            .on('submit', event => { //ev. handler
                let email = document.querySelector('#frModificarUsuario\\:email').value
                let contraseña = document.querySelector('#frModificarUsuario\\:contr').value
                return this.validarDatos(email,contraseña)
            });
    }
    validarDatos (email,contraseña) {
        let result = true;
        //let nombre = $(this.config.ibNombre).val();

        $('#errEmail, #errContraseña' ).empty(); //clear previous errors

        if (email.length == 0) {
            $('#errEmail').text('Debe introducir un email');
            result = false;
            console.log("Debe introducir un email");
        }else{
            if (email.length < 4 || email.length > 100){
                $('#errEmail').text('El email debe tener una logitud entre 4 y 100 caracteres');
                result = false;
                console.log("El email debe tener una logitud entre 4 y 100 caracteres");
            }
            if(!(/^.+@.+$/.test(email))){
                $('#errEmail').text('Formato de email inválido. Formato válido (...@...)');
                result = false;
                console.log("Formato de email inválido. Formato válido (...@...)");
            }
        };

        if (contraseña.length == 0) {
            $('#errContraseña').text('Debe introducir una contraseña');
            result = false;
            console.log("Debe introducir una contraseña");
        };
        return result;
    }
};


