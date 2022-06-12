(function () {
	window.onpageshow = function(event) {
		if (event.persisted) {
			window.location.reload();
		}
	};
})();
/*




(function recogerDato() {

	let usuario = document.getElementsByClassName("usuario")[0];

	let usuaroSesion = localStorage.getItem('usuarioWeb');

	if (usuaroSesion=="null" || usuaroSesion==null || usuaroSesion=="anonymousUser"){
		let iniciarSesion=document.getElementById("cerrarSesion");

		iniciarSesion.style.visibility = "hidden";

		usuario.innerHTML="Identificarse";

	}else{
		let iniciarSesion=document.getElementById("iniciarSesion");

		iniciarSesion.style.visibility = "hidden";

        // splice -> separar texto entre @
        // slice --> obtener desde una posicion en concreto
		usuaroSesion = usuaroSesion.split("@")[0][0].toLocaleUpperCase();
        let resto = usuaroSesion.slice(1)
		usuario.innerHTML = "Hola, " + usuaroSesion + resto;
	}

})();*/
