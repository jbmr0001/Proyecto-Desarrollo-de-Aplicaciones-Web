$( () => {
    window.ctrl = new ComprasCtrl(); //Register global var
    ctrl.init(); //Attach view event Handlers
});
class ComprasCtrl {
    constructor () {
        this.config= {
            formularioInsertarCompra: "form[name=frInsertarCompra]",
            formularioModificarCompra: "form[name=frModificarCompra]"
        };
    }
    init () {
        $(this.config.formularioInsertarCompra)
            .on('submit', event => { //ev. handler
                let idCompra=99 //Valor auxiliar para pasar la validación ya que en este formulario no se introduce id, es autocalculado
                let email = document.querySelector('#frInsertarCompra\\:email').value
                let idProducto = document.querySelector('#frInsertarCompra\\:idProducto').value
                return this.validarDatos(idCompra,email,idProducto)
            });
        $(this.config.formularioModificarCompra)
            .on('submit', event => { //ev. handler
                let idCompra = document.querySelector('#frModificarCompra\\:id').value
                let email = document.querySelector('#frModificarCompra\\:email').value
                let idProducto = document.querySelector('#frModificarCompra\\:idProducto').value
                return this.validarDatos(idCompra,email,idProducto)
            });
    }
    validarDatos (idCompra,email,idProducto) {
        let result = true;
        //let nombre = $(this.config.ibNombre).val();

        $('#errEmail,#errIdCompra, #errIdProducto' ).empty(); //clear previous errors

        if (idCompra== 0) {
            $('#errIdCompra').text('Debe introducir un idCompra');
            result = false;
            console.log("Debe introducir un idCompra");
        };

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

        if (idProducto== 0) {
            $('#errIdProducto').text('Debe introducir un idProducto');
            result = false;
            console.log("Debe introducir un idProducto");
        };
        return result;
    }
};


