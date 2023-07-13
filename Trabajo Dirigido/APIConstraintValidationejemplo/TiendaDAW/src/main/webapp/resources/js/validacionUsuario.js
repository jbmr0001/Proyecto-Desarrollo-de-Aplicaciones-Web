const email = document.getElementById("mail");
const contr = document.getElementById("contr");
const tarjeta = document.getElementById("tarjeta");
email.addEventListener("input", (event) => {
    if (email.validity.valueMissing){
        email.setCustomValidity("Debe introducir un email");
    } else {
        if (email.validity.typeMismatch) {
            email.setCustomValidity("Formato de email inválido");
        } else {
            email.setCustomValidity("");
        }
    }
});
contr.addEventListener("input", (event) => {
    if (contr.validity.valueMissing) {
        contr.setCustomValidity("Debe introducir una contraseña");
    } else {
        contr.setCustomValidity("");
    }
});
tarjeta.addEventListener("input", (event) => {
    if (!(/^[0-9]{12,14}$/.test(tarjeta.value))) {
        tarjeta.setCustomValidity("Debe ser un número de entre 12 y 14 dígitos");
    } else {
        tarjeta.setCustomValidity("");
    }
});
