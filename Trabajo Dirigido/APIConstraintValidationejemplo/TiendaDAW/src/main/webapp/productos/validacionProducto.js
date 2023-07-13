const nombre = document.getElementById("nombre");
const stock = document.getElementById("stock");
const precio = document.getElementById("precio");
nombre.addEventListener("input", (event) => {
    if (nombre.validity.valueMissing) {
        nombre.setCustomValidity("Debe introducir un Nombre");
    } else {
        if (nombre.validity.tooShort) {
            nombre.setCustomValidity("Logitud minima de 10 carácteres");
        } else {
            nombre.setCustomValidity("");
        }
    }
});
precio.addEventListener("input", (event) => {
    if (precio.validity.valueMissing) {
        precio.setCustomValidity("Debe introducir un Precio");
    } else {
        if (precio.validity.rangeUnderflow) {
            precio.setCustomValidity("Precio mínimo de 1 Euro");
        } else {
            precio.setCustomValidity("");
        }
    }
});
stock.addEventListener("input", (event) => {
    if (stock.validity.valueMissing) {
        stock.setCustomValidity("Debe introducir un Stock");
    } else {
        if (stock.validity.rangeUnderflow) {
            stock.setCustomValidity("Debe insertar al menos 1 producto");
        } else {
            stock.setCustomValidity("");
        }
    }
});
