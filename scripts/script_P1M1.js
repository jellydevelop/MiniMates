document.addEventListener("DOMContentLoaded", function() {
    // Variables
    const inicio = 1;
    const fin = 10;
    let selectedAudio = null;
    let intentos = {};
    let correctos={};
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
        [1, new Audio('/audio/audioNumeros1.mp3')],
        [2, new Audio('/audio/audioNumeros2.mp3')],
        [3, new Audio('/audio/audioNumeros3.mp3')],
        [4, new Audio('/audio/audioNumeros4.mp3')],
        [5, new Audio('/audio/audioNumeros5.mp3')],
        [6, new Audio('/audio/audioNumeros6.mp3')],
        [7, new Audio('/audio/audioNumeros7.mp3')],
        [8, new Audio('/audio/audioNumeros8.mp3')],
        [9, new Audio('/audio/audioNumeros9.mp3')],
        [10, new Audio('/audio/audioNumeros10.mp3')],
        ['acierto', new Audio('/audios/collect-points-190037.mp3')],
        ['meh', new Audio('/audios/failure.mp3')]
    ]);

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

    // Funciones para reproducir audios
    function audioPlay(audio) {
        audio.play().catch(error => {
            console.log('Error al reproducir el audio:', error);
        });
    }

    function reproducirAudio(numero) {
        let audio = audios.get(numero);
        if (audio) {
            audioPlay(audio);
        }
    }

    function seleccionarNumero(numeroElement) {
        if (selectedAudio) {
            const numeroSeleccionado = parseInt(numeroElement.textContent);
            const audioCorrecto = parseInt(selectedAudio);

            if (numeroSeleccionado === audioCorrecto) {

                numeroElement.style.backgroundColor = "green";
                numeroElement.disabled = true;

                cambiarImagenBoton("iniciarJuego", "/img/check.png");
                iniciarJuegoButton.style.backgroundColor = "green";

                setTimeout(() => {
                    iniciarJuegoButton.style.backgroundColor = "";

                }, 1000);


                audioPlay(audios.get('acierto'));

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

                if (!intentos[numeroSeleccionado]) {

                    intentos[numeroSeleccionado] = 1;

                }else{

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
                    iniciarJuegoButton.style.backgroundColor = "#8B0000	";
                }, 1000);
                iniciarJuegoButton.style.backgroundColor = " ";

                alert('¡Incorrecto! Intenta de nuevo.');
                }
            }
        }
    }

    // Funciones para iniciar el juego
    function iniciarJuego() {

        if (!juegoIniciado) {
            juegoIniciado = true;
            oportunidades = oportunidadesMax;
            actualizarEstadoCeldas();
            intentos = {};
            tiempoInicioJuego = new Date();
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

    function actualizarEstadoCeldas() {
        numeros.forEach(celda => {
            celda.style.backgroundColor = '';
            celda.disabled = false;
            celda.classList.remove('seleccionada');
        });
    }

    function cambiarImagenBoton(id, nuevaImagen) {
        let img = document.getElementById(id);
        if (img) {
            let imgDentroBoton = img.querySelector('img'); // Busca la imagen dentro del botón
            if (imgDentroBoton) {
                // Guarda la imagen actual para poder restaurarla después
                let imagenActual = imgDentroBoton.src;
    
                // Cambia la imagen por la nueva imagen
                imgDentroBoton.src = nuevaImagen;
                console.log(`Imagen cambiada a ${nuevaImagen}`);
    
                // Después de 1 segundo, vuelve a cambiar a la imagen original
                setTimeout(() => {
                    imgDentroBoton.src = imagenActual;
                    console.log(`Imagen restaurada a ${imagenActual}`);
                }, 1000);
            } else {
                console.log(`No se encontró la imagen dentro del botón con ID ${id}`);
            }
        } else {
            console.log(`Elemento con ID ${id} no encontrado.`);
        }
    }

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
    
    

    function formatearTiempo(tiempoEnMilisegundos) {
        let segundos = Math.floor(tiempoEnMilisegundos / 1000);
        let minutos = Math.floor(segundos / 60);
        segundos = segundos % 60;
        let minutosFormateados = minutos < 10 ? "0" + minutos : minutos;
        let segundosFormateados = segundos < 10 ? "0" + segundos : segundos;
        return minutosFormateados + ":" + segundosFormateados;
    }

    function obtenerInfoJSON(duracionJuego) {
        let infoJSON = {
            intentosFallidos: {},
            audiosCorrectos: {},
            duracionPartida: duracionJuego
        };

        for (let i = inicio; i <= fin; i++) {
            infoJSON.intentosFallidos[i] = intentos[i] || 0;
        }

       Object.keys(correctos).forEach(numero => {
            infoJSON.audiosCorrectos[numero] = correctos[numero];
        });

        return infoJSON;
    }

    function finalizarPartida() {

        let tiempoFinJuego = new Date();
        let duracionJuego = formatearTiempo(tiempoFinJuego - tiempoInicioJuego);
        let infoJSON = obtenerInfoJSON(duracionJuego);

        let intentosCorrectos = Object.keys(infoJSON.audiosCorrectos).length;
        let intentosFallidos = Object.values(infoJSON.intentosFallidos).reduce((a, b) => a + b, 0);

        let alertMessage = "Información de la partida:\n";
        alertMessage += `Intentos correctos: ${intentosCorrectos}\n`;
        alertMessage += `Intentos fallidos: ${intentosFallidos}\n`;
        alertMessage += `Duración de la partida: ${infoJSON.duracionPartida}`;

        alert(alertMessage);

        cambiarImagenCofre('cofre_cerrado', 'img/chest-145752_1280.png');

        setTimeout(() => {
            window.location.href = "/mapaMundo1P2";
        }, 1500);
    }

    // Asignar números al cargar la página
    asignarNumeros();
});

