let dniPaciente=null;
var paraMi = null;
document.addEventListener('DOMContentLoaded', function(){

    dniPaciente=document.getElementById("dniPaciente");
    paraMi = document.getElementById('paraMi');

    if (paraMi!=null){

        if(paraMi.checked){
            dniPaciente.style.display = "none";
        }else{
            dniPaciente.style.display = "inline";
        }
        paraMi.addEventListener('change', actDesOpcion);

    }

});

function actDesOpcion(){

    if(paraMi.checked){
        dniPaciente.style.display = "none";
        dniPaciente.value=null;
    }

    else{
        dniPaciente.style.display = "inline";
    }

}