// -------------------VARIABLES

// -------------------IDS

const btn_aInicio = document.getElementById("btn_inicio");
const btn_aReto1 = document.getElementById("btn_reto1");
const mati = document.getElementById('mati');
const audio = document.getElementById('audio_reto1');

// -------------------LISTENERS

window.onload = function() {
	setTimeout(irAReto1, 1000); // Ejecuta la función después de 2 segundos
};
// -------------------FUNCIONES

function audioPlay() {

	audio.play();

}

// -*****************************
function irAReto1() {

	const matiImg  = document.querySelector("#mati img");

	//cambio de estilos de los elementos  ==>> color de INICIO  + activar animacion
	btn_aInicio.style.background = "lightgreen";
	matiImg .style.animation = "inicioReto1 2s forwards";

	// Reproducir el audio antes de redirigir
	audioPlay();

	//dejamos hueco de tiempo para que seacabe la
	//animación y luego  redireccione a la pantalla 1
	
	matiImg.addEventListener("animationend", function() {

		window.location.href = "/reto1M1";  // Redirige a la pantalla del reto 1
	});

}

