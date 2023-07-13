const id = document.getElementById("id");
const idProducto = document.getElementById("idProducto");
const email = document.getElementById("email");
id.addEventListener("input", (event) => {
    if (id.validity.valueMissing) {
        id.setCustomValidity("Debe introducir un Id");
    } else {
        if (id.validity.rangeUnderflow) {
            id.setCustomValidity("Debe ser superior a 1");
        } else {
            id.setCustomValidity("");
        }
    }
});

idProducto.addEventListener("input", (event) => {
    if (idProducto.validity.valueMissing) {
        idProducto.setCustomValidity("Debe introducir un IdProducto");
    } else {
        if (idProducto.validity.rangeUnderflow) {
            idProducto.setCustomValidity("Debe ser superior a 1");
        } else {
            idProducto.setCustomValidity("");
        }
    }
});
email.addEventListener("input", (event) => {
    if (email.validity.valueMissing) {
        email.setCustomValidity("Debe introducir un email");
    } else {
        if (email.validity.typeMismatch) {
            email.setCustomValidity("Formato de email inválido")
        } else {
            email.setCustomValidity("");
        }
    }
});