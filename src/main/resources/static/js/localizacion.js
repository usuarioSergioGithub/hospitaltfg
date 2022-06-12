let map;

function crearMapa() {

    // Establecer las coordenadas ( se tienen que llamar lat y lng ya que es como las tomara la api de google
    let coordenada = {
        lat: 40.41825167076113,
        lng: -3.6681336317301665
    };

    // Crear mapa
    map = new google.maps.Map(document.getElementById("map"), {
        center: coordenada,
        zoom: 18,
    });

    // Crear icono
    var icon = {
            url: "../img/bandera.png", // url
            scaledSize: new google.maps.Size(130, 130), // tamaño
        };

    // Mostar la ubicacion
    var ubicacionExacta = new google.maps.Marker({
        position: coordenada,// posicion del mapa que mostrará
        map: map,// el mapa donde lo mostrará
        icon: icon
    })





}