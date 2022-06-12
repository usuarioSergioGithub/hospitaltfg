let botonBorrarPersonal = null;

// Cuando cargue todo el html
document.addEventListener('DOMContentLoaded', function () {

    // añade un evento para que cuando
    // el usuario pulse el boton de borrar personal llame a la funcion de recoger datos
    botonBorrarPersonal = document.getElementById("botonBorrarPersonal");

    botonBorrarPersonal.addEventListener('click', function (ev) {

        recogerDatos(ev);
    });

    
});

function recogerDatos(ev){

    // Toma todos los dniNColegiado
    let identificadores = document.getElementsByClassName("dniNColegiado");

    // Toma los empleados seleccionados
    let seleccionados = document.getElementsByClassName("selCheck");

    let parametrosUrl = "";

    // Por cada empleado seleccionado
    for (let i = 0; i < seleccionados.length; i++) {

        // Si esta checked recogerá su valor por defecto
        if (seleccionados[i].checked){
            if(parametrosUrl==""){// es el primero que se va a añadir
                parametrosUrl+=`?empleadosBorrado=${seleccionados[i].defaultValue}`;
            }else{
                parametrosUrl+=`&empleadosBorrado=${seleccionados[i].defaultValue}`;
            }
            

        }

    }

    window.location.href = "confirmarBaja" + parametrosUrl;
}