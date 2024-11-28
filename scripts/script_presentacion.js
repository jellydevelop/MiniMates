//document.addEventListener("DOMContentLoaded", () => {
	// -------------------VARIABLES
	const btn_redi=document.getElementById("btn_continuar");
const elementoAudio = document.getElementById("audio");
	//const emailUsuario = localStorage.getItem('emailUsuario');
	
	// Tabla de correspondencia entre ID de mundos y archivos HTML
	/*const rutasMundos = {
		1: "introMundo1_pagi4.html", // Mundo 1 - piratas
		2: "introM2.html",// Mundo 2- espacio
		3: "introM3.html",// Mundo 3- bosque
		4:"introM4.html"// Mundo 4- mar
	};

	// Verificar si el correo del usuario existe
	if (!emailUsuario) {
		alert("Error: no se pudo identificar al usuario.");
		return;
	}
*/
	// -------------------FUNCIONES
/*
	function iniciarPartida() {
		const emailUsuario = localStorage.getItem('emailUsuario');
console.log(emailUsuario); 

		if (!emailUsuario) {
			alert("Error: no se pudo identificar al usuario.");
			return;
		}

		const parametros = new URLSearchParams();
		parametros.append('emailUsuario', emailUsuario);
		console.log("Parámetros enviados:", parametros.toString()); // Verifica los parámetros enviados

fetch('/partidas/iniciarPartida', {
			method: 'POST',
			headers: {
        'Content-Type': 'application/json',
    },
        body: JSON.stringify({ emailUsuario: emailUsuario })  // Enviamos los datos como JSON

		})
			.then(response => {
				console.log(response);

				if (!response.ok) {
					throw new Error('Error al iniciar la partida');
				}
				console.log(response);

				return response.json();
			})
			.then(idMundo => {
				console.log('ID del mundo recibido: ', idMundo);
				localStorage.setItem('mundoActual', idMundo);
				redirigirAMundo(idMundo);
			})
			.catch(error => {
				console.error('Error al iniciar la partida:', error);
				alert("No se pudo iniciar la partida. Intente más tarde.");
			});
			// Redirigir directamente a introM1
		window.location.href = "/introMundo1_pagi4.html";
	}*/

	// Función que redirige según el ID del mundo
	/*function redirigirAMundo(idMundo) {
		const ruta = rutasMundos[idMundo];
		if (ruta) {
			window.location.href = ruta;
		} else {
			console.warn("No se encontró una ruta para el mundo recibido.");
		}
	}
*/

// -------------------FUNCIONES

function audioPlay(evento){

    if(evento){

        elementoAudio.play();
    }
}

// -*****************************
function rediIntro(evento){

    if(evento){

        window.location.href = "/introMundo1";
    }
    
}

   // -------------------LISTENERS

document.getElementById("audio_mati").addEventListener("click", audioPlay);

audio.addEventListener("click",audioPlay);

btn_redi.addEventListener("click",rediIntro);
