// Cuando cargue la web
document.addEventListener('DOMContentLoaded', function(){

    let cadaFila=null;
    modificar=document.getElementsByClassName("modificar");

    // Por cada asignatura añade el evento de pintar la fila a modo advertencia de que va a eliminar esa asignatura
    for(let i=0; i<modificar.length; i++){
        modificar[i].addEventListener('click', function(ev){

            habilitarModificacion(ev.composedPath());// devuelve la estructura completa desde ese checkbox
        });
    }

});


function habilitarModificacion(ev){
    let fila=ev[2];// fila seleccionada

    let personas=fila.getElementsByClassName("persona");
    let campo = fila.getElementsByClassName("campo");
    for (let i=0; i<personas.length; i++){

        personas[i].style.display = "none";
        campo[i].type = "text";
        campo[i].value=personas[i].innerHTML;
    }

    let personaNoModificar= document.getElementsByClassName("personaNoModificar");
    for (let i=0; i<personaNoModificar.length; i++){

            personaNoModificar[i].readOnly = true;

        }


}