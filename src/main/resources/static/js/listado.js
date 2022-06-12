// Cuando cargue la web
document.addEventListener('DOMContentLoaded', function(){

    let cadaFila=null;
    cadaFila=document.getElementsByClassName("fila");

    // Por cada asignatura añade el evento de pintar la fila a modo advertencia de que va a eliminar esa asignatura
    for (let j = 0; j < cadaFila.length; j++) {
        cadaFila[j].addEventListener('change', function(ev){
            
            pintarFila(ev.composedPath());// devuelve la estructura completa desde ese checkbox
        });
        
    }
});

function pintarFila(ev){
    let checkbox=ev[0];// checkbox seleccionado
    let fila=ev[2];// fila seleccionada
    if(checkbox.checked){
        fila.style.backgroundColor="#f1e06838";
    }else{
        fila.style.backgroundColor="";
    }
    
}