document.addEventListener('DOMContentLoaded', function(){

    let headerParteArriba = document.getElementsByClassName("headerParteArriba")[0];
    let headerParteAbajo = document.getElementsByClassName("headerParteAbajo")[0];
    let main = document.getElementsByTagName("main")[0];
    let footer = document.getElementsByTagName("footer")[0];

    if (headerParteArriba!=null){
        headerParteArriba.style.zoom = "50%";
    }
    if (headerParteAbajo!=null){
        headerParteAbajo.style.zoom = "63%";
    }
    if (footer!=null){
        footer.style.zoom = "63%";
    }
    if (main!=null){
        main.style.zoom = "58%";
    }

});
