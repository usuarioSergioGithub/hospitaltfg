document.addEventListener('DOMContentLoaded', function () {

    let pagada = document.getElementsByClassName("pagada");
    let sinPagar = document.getElementsByClassName("sinPagar");

    let fila =null;
    for (let j = 0; j < pagada.length; j++) {
        //fila = pagada[j].composedPath()[2]
        pagada[j].style.backgroundColor = "#d1e7dd";

    }

    for (let j = 0; j < sinPagar.length; j++) {
        //fila = sinPagar[j].composedPath()[2]
        sinPagar[j].style.backgroundColor = "#f8d7da";

    }
});

