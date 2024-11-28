document.addEventListener("DOMContentLoaded", function() {
	// Variables
	const inicio = 1;
	const fin = 10;
	let selectedAudio = null;
	let intentos = {};
	let correctos = {};
	const oportunidadesMax = 3;
	let audioIndex = 1;
	let juegoIniciado = false;

	// IDs
	const iniciarJuegoButton = document.getElementById("iniciarJuego");
	const elementoAudio = document.getElementById("audio");
	const numeros = document.querySelectorAll("#tabla_numeros .numero");
	const cofreCerrado = document.getElementById('cofre_cerrado');

	// Mapa de audios
	let audios = new Map([
		[1, new Audio('/audios/collect-points-190037.mp3')],
		[2, new Audio('/audios/collect-points-190037.mp3')],
		[3, new Audio('/audios/collect-points-190037.mp3')],
		[4, new Audio('/audios/collect-points-190037.mp3')],
		[5, new Audio('/audios/collect-points-190037.mp3')],
		[6, new Audio('/audios/collect-points-190037.mp3')],
		[7, new Audio('/audios/collect-points-190037.mp3')],
		[8, new Audio('/audios/collect-points-190037.mp3')],
		[9, new Audio('/audios/collect-points-190037.mp3')],
		[10, new Audio('/audios/collect-points-190037.mp3')],
		['acierto', new Audio('/audios/collect-points-190037.mp3')],
		['meh', new Audio('/audios/failure.mp3')]
	]);
	//-----------------------FUNCIONES

	// Función para reproducir el audio
	function audioPlay() {
		// Obtener el elemento audio
		const audioElement = document.getElementById('audio');

		// Reproducir el audio
		if (audioElement) {
			audioElement.play().catch(error => {
				console.log('Error al reproducir el audio:', error);
			});
		} else {
			console.error("El elemento de audio no se encontró.");
		}
	}
	//***************************** */

	// Función para reducir el tamaño del botón y cambiar su estilo al fallar
	function reducirBoton() {
		iniciarJuegoButton.style.transform = "scale(0.5)";
		iniciarJuegoButton.style.backgroundColor = "#C85250";
	}
	//***************************** */

	// Función para restaurar el tamaño del botón y eliminar el listener
	function restaurarBoton() {
		iniciarJuegoButton.style.transform = "scale(1)";
		iniciarJuegoButton.style.backgroundColor = "";
		iniciarJuegoButton.removeEventListener("click", reducirBoton);
	}
	//***************************** */
	// Funciones para rellenar la tabla de números aleatorios
	function numerosRandom(inicio, fin) {
		return Math.floor(Math.random() * (fin - inicio + 1)) + inicio;
	}

	function barajarNumeros(array) {
		for (let i = array.length - 1; i > 0; i--) {
			const j = Math.floor(Math.random() * (i + 1));
			[array[i], array[j]] = [array[j], array[i]];
		}
		return array;
	}
	//************************************ */
	function asignarNumeros() {
		let numerosUsables = [];
		for (let i = inicio; i <= fin; i++) {
			numerosUsables.push(i);
		}
		numerosUsables = barajarNumeros(numerosUsables);

		let botones = document.querySelectorAll('.numero');
		botones.forEach((boton, index) => {
			let numeroAsignado = numerosUsables[index];
			boton.textContent = numeroAsignado;
			boton.value = numeroAsignado;
		});
	}
	/******************************* */

	/****************************** */
	// Función para reproducir los audios del juego
	function reproducirAudio(numero) {
		let audio = audios.get(numero);
		if (audio) {
			audio.play().catch(error => {
				console.log('Error al reproducir el audio:', error);
			});
		}
	}
	//************************* */
	function seleccionarNumero(numeroElement) {
		if (selectedAudio) {
			const numeroSeleccionado = parseInt(numeroElement.textContent);
			const audioCorrecto = parseInt(selectedAudio);

			if (numeroSeleccionado === audioCorrecto) {

				// Cambiar a estilo de acierto

				numeroElement.style.backgroundColor = "green";
				numeroElement.style.color = "white";
				numeroElement.style.fontWeight = "bold";
				numeroElement.disabled = true;


				cambiarImagenBoton("iniciarJuego", "/img/check.png", "green");
				//hueco de tiempo
				setTimeout(() => {
					iniciarJuegoButton.style.backgroundColor = "";

				}, 1000);
				//el boton vuelve a su imagen original
				restaurarBoton();

				//sonido de acierto
				audioPlay(audios.get('acierto'));

				//hueco de tiempo

				setTimeout(() => {
					cambiarImagenBoton("iniciarJuego", "/img/letter-x_16083482.png");
					if (audioIndex <= fin) {
						reproducirAudio(audioIndex);
						selectedAudio = audioIndex;
						audioIndex++;
					} else {
						alert("¡Bien hecho!");
						setTimeout(() => {

							finalizarPartida();

						}, 1000);
					}
				}, 2000);

				correctos[numeroSeleccionado] = true;

			} else {
				// Lógica cuando falla el usuario

				iniciarJuegoButton.addEventListener("click", reducirBoton);

				//audio fallo
				audioPlay(audios.get('meh'));


				if (!intentos[numeroSeleccionado]) {

					intentos[numeroSeleccionado] = 1;

				} else {

					intentos[numeroSeleccionado]++;

				}

				if (intentos[numeroSeleccionado] >= 3) {

					const celdaSolucion = Array.from(numeros).find(celda => parseInt(celda.textContent) === audioCorrecto);

					if (celdaSolucion) {
						celdaSolucion.style.backgroundColor = "blue";
					}

					audioPlay(audios.get('meh'));
					alert(`Has fallado 3 veces. La respuesta correcta es: ${audioCorrecto}`);

				} else {
					numeroElement.style.backgroundColor = "orange";
					audioPlay(audios.get('meh'));
					// Cambiar temporalmente el color de fondo del botón a rojo por 1 segundo
					setTimeout(() => {
						cambiarImagenBoton("iniciarJuego", "/img/letter-x_16083482.png", "#8B0000");

					}, 1000);
					iniciarJuegoButton.style.backgroundColor = " ";

					alert('¡Incorrecto! Intenta de nuevo.');
				}
			}
		}
	}
	//************************* */
let tiempoInicioPantalla;
	// Funciones para iniciar el juego
	function iniciarJuego() {

		if (!juegoIniciado) {
			juegoIniciado = true;
			oportunidades = oportunidadesMax;
			actualizarEstadoCeldas();
			intentos = {};
	 tiempoInicioPantalla = new Date();
			audioIndex = 1;
			reproducirAudio(audioIndex);
			selectedAudio = audioIndex;
			audioIndex++;
			iniciarJuegoButton.disabled = true;
		}
	}

	iniciarJuegoButton.addEventListener("click", iniciarJuego);

	numeros.forEach(numero => {
		numero.addEventListener('click', function() {
			seleccionarNumero(this);
		});
	});
	//************************* */

	function actualizarEstadoCeldas() {
		numeros.forEach(celda => {
			celda.style.backgroundColor = '';
			celda.disabled = false;
			celda.classList.remove('seleccionada');
		});
	}
	//************************* */

	function cambiarImagenBoton(id, nuevaImagen, nuevoColorFondo = null) {
		let img = document.getElementById(id);
		if (img) {
			let imgDentroBoton = img.querySelector('img'); // Busca la imagen dentro del botón

			// Cambiar la imagen dentro del botón
			if (imgDentroBoton) {
				let imagenActual = imgDentroBoton.src; // Guarda la imagen actual
				imgDentroBoton.src = nuevaImagen; // Cambia la imagen
				console.log(`Imagen cambiada a ${nuevaImagen}`);

				// Después de 1 segundo, restaura la imagen original
				setTimeout(() => {
					imgDentroBoton.src = imagenActual;
					console.log(`Imagen restaurada a ${imagenActual}`);
				}, 1000);
			} else {
				console.log(`No se encontró la imagen dentro del botón con ID ${id}`);
			}

			// Cambiar el color de fondo del botón (si se proporciona)
			if (nuevoColorFondo) {
				let colorOriginal = img.style.backgroundColor; // Guarda el color original
				img.style.backgroundColor = nuevoColorFondo; // Cambia el color de fondo
				console.log(`Color de fondo cambiado a ${nuevoColorFondo}`);

				// Después de 1 segundo, restaura el color original
				setTimeout(() => {
					img.style.backgroundColor = colorOriginal;
					console.log(`Color de fondo restaurado a ${colorOriginal}`);
				}, 1000);
			}
		} else {
			console.log(`Elemento con ID ${id} no encontrado.`);
		}
	}

	//************************* */

	function cambiarImagenCofre(id, nuevaImagen) {
		let img = document.getElementById(id);
		if (img) {
			// Guarda la imagen actual para poder restaurarla después
			let imagenActual = img.src;

			// Cambia la imagen por la nueva imagen
			img.src = nuevaImagen;
			console.log(`Imagen cambiada a ${nuevaImagen}`);

			// Después de 1 segundo, vuelve a cambiar a la imagen original
			setTimeout(() => {
				img.src = imagenActual;
				console.log(`Imagen restaurada a ${imagenActual}`);
			}, 1000);
		} else {
			console.log(`Elemento con ID ${id} no encontrado.`);
		}
	}


	//************************* */

	function formatearTiempo(tiempoEnMilisegundos) {
		let segundos = Math.floor(tiempoEnMilisegundos / 1000);
		let minutos = Math.floor(segundos / 60);
		segundos = segundos % 60;
		let minutosFormateados = minutos < 10 ? "0" + minutos : minutos;
		let segundosFormateados = segundos < 10 ? "0" + segundos : segundos;
		return minutosFormateados + ":" + segundosFormateados;
	}
	//************************* */
	/*
		function obtenerInfoJSON(duracionJuego) {
			let infoJSON = {
				tipoReto: "RECONOCIMIENTO NUMEROS",
						tiempoInicioPantalla: tiempoInicioJuego.toISOString(),
			tiempoFinPantalla: tiempoFinJuego.toISOString(),
	
			aciertosPantalla: Object.keys(correctos).length,
			fallosPantalla: Object.values(intentos).reduce((a, b) => a + b, 0)
			};
	
			for (let i = inicio; i <= fin; i++) {
				infoJSON.intentosFallidos[i] = intentos[i] || 0;
			}
	
			Object.keys(correctos).forEach(numero => {
				infoJSON.audiosCorrectos[numero] = correctos[numero];
			});
	
			return infoJSON;
		}*/
	//************************* */

	function finalizarPartida() {
		let tiempoFinJuego = new Date();
    let duracionPantalla = tiempoFinJuego - tiempoInicioPantalla;  // Duración en milisegundos
console.log(duracionPantalla);
   // Convertir la duración a un formato que se pueda almacenar en la base de datos (Instant)
    console.log("Duración de la partida en milisegundos:", duracionPantalla);  // Verifica el valor en milisegundos
    
		// Construir el JSON con la información del juego
		   let infoJSON = {
        tipoReto: "RECONOCIMIENTO NUMEROS",
        mundo_id:1,
        tiempoInicioPantalla: tiempoInicioPantalla.toISOString(),  // Enviar como ISO String
        tiempoFinPantalla: tiempoFinJuego.toISOString(),  // Enviar como ISO String
        aciertosPantalla: Object.keys(correctos).length,
        fallosPantalla: Object.values(intentos).reduce((a, b) => a + b, 0),
        duracionPantalla: duracionPantalla    // Incluir la duración calculada en el JSON
    };

		console.log("Enviando datos del juego:", infoJSON);

		// Enviar los datos al backend
		fetch("/salvarPantalla", {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(infoJSON)
		})
			.then((response) => {
				if (!response.ok) {
					throw new Error("Error al guardar los datos del juego");
				}
				return response.text();
			})
			.then((data) => {
				console.log("Respuesta del servidor:", data);

        alert(`Información de la partida guardada:\nAciertos: ${infoJSON.aciertosPantalla}\nFallos: ${infoJSON.fallosPantalla}\nDuración: ${duracionPantalla}`);
			})
			.catch((error) => {
				console.error("Error al guardar los datos del juego:", error);
				alert("Hubo un error al guardar los datos de la partida.");
			});

		alert('Datos de la partida guardados');
		cambiarImagenCofre('cofre_cerrado', 'img/chest-145752_1280.png');

		/*	setTimeout(() => {
				window.location.href = "/mapaMundo1P2";
			}, 1500);*/
	}

	// Asignar números al cargar la página
	asignarNumeros();
});

