let cadaFila=null;
// Cuando cargue la web
document.addEventListener('DOMContentLoaded', function(){

    controlBorrado();
        cadaFila=document.getElementsByClassName("fila");

        for (let j = 0; j < cadaFila.length; j++) {
            cadaFila[j].addEventListener('change', function(ev){

                controlBorrado();
            });

        }
});

function controlBorrado(){
    let empSelec=0;
    let numEmp=0;
    cadaFila=document.getElementsByClassName("fila");
    let botonBorrarCliente = document.getElementById("botonBorrarCliente");


    if(cadaFila.length>0){

        for (let i = 0; i < cadaFila.length; i++) {
            if(cadaFila[i].checked){
                empSelec++;
            }
            numEmp++;

        }

        if(empSelec==0){
            botonBorrarCliente.disabled = true;

        }else{
            botonBorrarCliente.disabled = false;

        }
    }

}