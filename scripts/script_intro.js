document.addEventListener("DOMContentLoaded", () => {
// -------------------VARIABLES
const idMundoActual = localStorage.getItem('mundoActual');
const emailUsuario = localStorage.getItem('emailUsuario');
const apiRediUrl = '/api/mundos';


    //********** */
    
     // Verificar si emailUsuario existe
    if (!idMundoActual) {
        console.error("No se encontró el mundo  actual en localStorage");
    } else {
        console.log("Mundo ID:", idMundoActual);
    }
    
// -------------------IDS

const btn_redi = document.getElementById("btn_iniciarJuego");
const elementoAudio = document.getElementById("audio");
const btn_audio = document.getElementById("audio_mati");


// -------------------FUNCIONES

function rediMapa(evento) {

	if (evento) {

		window.location.href = "/mapaMundo1P1";
	}
}

// -*****************************
function audioPlay(evento) {

	elementoAudio.play().catch(error => {
		console.log('Error al reproducir el audio:', error);
	});
}
// -*****************************
function inicioAudio() {

	setTimeout(() => {
		elementoAudio.play().catch(error => {
			console.log('Autoplay was prevented:', error);
		});
	}, 1500); // 1.5 segundos después de cargar la página
}
// -------------------PETICION ACTIVAR MUNDO

function activarMundo(idMundo) {
	return fetch(`${apiRediUrl}/activar/${idMundo}`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('Error al activar el mundo');
			}
			return response.json();
		})
		.then(data => {
			console.log('Mundo activado:', data);
			localStorage.setItem('mundoActual', data.idMundo);
			return data;
		})
		.catch(error => {
			alert('Error: ' + error.message);
			console.error('Error al activar el mundo:', error);
		});
}
// -------------------PETICION ACTIVAR MUNDO
function obtenerEstadoMundo(idMundo) {
	return fetch(`${apiRediUrl}/${idMundo}`, {
		method: 'GET',
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('Error al consultar el estado del mundo');
			}
			return response.json();
		})
		.then(data => data)
		.catch(error => {
			alert('Error: ' + error.message);
			console.error('Error al consultar el estado del mundo:', error);
		});
}

// -------------------PETICION INICIAR MUNDO

async function inicializarMundo(idMundo) {
	
	const estadoMundo = await obtenerEstadoMundo(idMundo);
	
	//validamos
	if (!estadoMundo.activado) {
		console.log('El mundo está desactivado, activándolo...');
		return activarMundo(idMundo);
		
	} else {
		console.log('El mundo ya está activado.');
		localStorage.setItem('mundoActual', estadoMundo.idMundo);
		return estadoMundo;
	}
}

// -------------------LISTENERS

btn_redi.addEventListener("click", rediMapa);
audio.addEventListener("click", audioPlay);

//  listener para el evento que carga el audio al cargar la web => auto lectura
document.addEventListener("DOMContentLoaded", inicioAudio);
window.onload = alert("****** MUNDO 1 ******");

// -------------------LLAMADA A FUNCIONES
// Si no hay un mundo actual, iniciamos con el mundo 1
const mundoId = idMundoActual || 1;

inicializarMundo(mundoId)
    .then(mundo => {
        console.log('Mundo listo para jugar:', mundo);
    })
    .catch(error => {
        console.error('Error al inicializar el mundo:', error);
    });
    });
    