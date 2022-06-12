let formularioPersonal = document.getElementById("formularioPersonal");
botonBorrarPersonal = document.getElementById("botonBorrarPersonal");
let botonModificarPersonal = document.getElementsByClassName("botonModificarPersonal");

// Por cada boton de modificar el personal añade un evento para cambiar
// la url del formulario y su metodo pasandole por parametro la opcion
// a la funcion
for (let i = 0; i < botonModificarPersonal.length; i++) {
    botonModificarPersonal[i].addEventListener('click', function(){
        cambiarUrlFormulario("Modificar");
    });

}


botonBorrarPersonal.addEventListener('click', function(){
    cambiarUrlFormulario("Borrar");
});

function cambiarUrlFormulario(opcion){

    switch (opcion) {

        // Si la opción es modificar
        case "Modificar":

            // ira a la url de modificarEmpleado
            formularioPersonal.action = "modificarEmpleado";

            // con el método post
            formularioPersonal.method = "post";
            break;

        // Si la opción es borrar
        case "Borrar":

            // ira a la url de confirmarBaja con el metodo get
            formularioPersonal.action = "confirmarBaja";
            formularioPersonal.method = "get";
            break;

        // Si la opción es otra (pudiese haber ocurrido un error) le devolverá a la pagina principal
        default:
            formularioPersonal.action = "/";
            formularioPersonal.method = "get";
            break;
    }

    formularioPersonal.submit();

}