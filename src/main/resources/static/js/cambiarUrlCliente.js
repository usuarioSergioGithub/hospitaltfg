let formularioCliente = document.getElementById("formularioCliente");
let botonBorrarCliente1 = document.getElementById("botonBorrarCliente");
let botonModificarCliente = document.getElementsByClassName("botonModificarCliente");

// Por cada boton de modificar el cliente añade un evento para cambiar
// la url del formulario y su metodo pasandole por parametro la opcion
// a la funcion
for (let i = 0; i < botonModificarCliente.length; i++) {
    botonModificarCliente[i].addEventListener('click', function(){
        cambiarUrlFormulario("Modificar");
    });

}


botonBorrarCliente1.addEventListener('click', function(){
    cambiarUrlFormulario("Borrar");
});

function cambiarUrlFormulario(opcion){

    switch (opcion) {

        // Si la opción es modificar
        case "Modificar":

            // ira a la url de modificarCliente
            formularioCliente.action = "modificarCliente";

            // con el método post
            formularioCliente.method = "post";
            break;

        // Si la opción es borrar
        case "Borrar":

            // ira a la url de confirmarBaja con el metodo get
            formularioCliente.action = "confirmarBaja";
            formularioCliente.method = "get";
            break;

        // Si la opción es otra (pudiese haber ocurrido un error) le devolverá a la pagina principal
        default:
            formularioCliente.action = "/";
            formularioCliente.method = "get";
            break;
    }

    formularioCliente.submit();

}