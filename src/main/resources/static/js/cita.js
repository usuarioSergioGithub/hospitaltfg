var especialidades = null;
var personal = null;
var prestaciones = null;
var paraMi = null;
var buscar = null
var sinSeleccionar = null
document.addEventListener('DOMContentLoaded', function(){

    let valoresPorDefecto = document.getElementsByClassName("selected");

    // por cada clase selected establece que ese option del select este marcado
    for (let i = 0; i < valoresPorDefecto.length; i++) {
        valoresPorDefecto[i].selected = 'selected'

    }

    // si llega a null significa que es usuario estandar ya que esta oculto
    paraMi = document.getElementById('paraMi');

    // Para el select de las especialidades recargará los datos
    especialidades = document.getElementById('especialidades');
    especialidades.addEventListener('change', comprobarDatos);

    buscar = document.getElementById("buscar");
    sinSeleccionar = document.getElementById("sinSeleccionar");

    if (sinSeleccionar.selected){
        buscar.disabled = true;
    }else{
        buscar.disabled = false;
    }

    personal = document.getElementById('personal');

    prestaciones = document.getElementById('prestaciones');

});

function comprobarDatos(){

    if (sinSeleccionar.selected){
            buscar.disabled = true;
        }else{
            buscar.disabled = false;
        }

    if (paraMi==null){
         window.location.href = "http://localhost:8080/recargardatos?" +
                                                   "especialidad=" + especialidades.options[especialidades.selectedIndex].value +
                                                   "&doctor=" + personal.options[personal.selectedIndex].value +
                                                   "&prestacion=" + prestaciones.options[prestaciones.selectedIndex].value;
    }else{
        window.location.href = "http://localhost:8080/recargardatos?" +
                           "especialidad=" + especialidades.options[especialidades.selectedIndex].value +
                           "&doctor=" + personal.options[personal.selectedIndex].value +
                           "&prestacion=" + prestaciones.options[prestaciones.selectedIndex].value +
                           "&paraMi=" + paraMi.checked;

    }

}