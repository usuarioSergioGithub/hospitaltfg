let elementosAOcultar = null;
document.addEventListener('DOMContentLoaded', function(){
    elementosAOcultar = document.getElementsByClassName("ocultar");

	// para ocultar elementos cuando no es admin
    for (let i = 0; i < elementosAOcultar.length; i++) {
        elementosAOcultar[i].style.display = " none ";
    }
})