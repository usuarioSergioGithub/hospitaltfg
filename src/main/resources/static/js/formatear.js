document.addEventListener('DOMContentLoaded', function(){
    (function formatear(){
        let elementosAFormatear = document.getElementsByClassName("formatear");

        let elemento = null;
        for (let i = 0; i < elementosAFormatear.length; i++) {
            elemento = elementosAFormatear[i];

            switch (elemento.innerHTML) {
                case "null":
                    elemento.innerHTML="-";
                    break;
            
                case "false":
                    elemento.innerHTML="No";
                    break;
    
                case "true":
                    elemento.innerHTML="Sí";
                    break;
/*
                case elemento.innerHTML:
                    elemento.innerHTML="Sí";
                    break;*/
                default:
                    break;
            }
            
        }
    })()
});