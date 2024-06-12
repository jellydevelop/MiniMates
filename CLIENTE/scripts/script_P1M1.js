//-----LISTENER SEGUN SE CARGA LA WEB-------------------------

document.addEventListener("DOMContentLoaded", function() {


//-----VARIABLES
const inicio=1;
const fin=10;
let selectedAudio = null;
let intentos = {};
let numeroAleatorio;
const oportunidadesMax = 3;
let oportunidades = oportunidadesMax;
let audioSeleccionado = null;
let correctos = {};
let tiempoInicioJuego;



//-----IDS
const iniciarJuegoButton = document.getElementById("iniciarJuego");
const elementoAudio = document.getElementById("audio");
const numeros = document.querySelectorAll("#tabla_numeros .numero");
const audioButtons = document.querySelectorAll("#lista_audios .numeroL button");
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
    [10, new Audio('/audio/audioNumeros10.mp3')]
]);

// Asigna el número del audio como valor del botón
////se le suma 1 porque el index empieza en 0
audioButtons.forEach((button, index) => {
    button.value = index + 1;
});

//-----FUNCIONES PARA RELLENAR TABLA DE NUMEROS ALEATORIOS-------------------------

function numerosRandom(inicio,fin){

    return Math.floor(Math.random() * (fin - inicio + 1)) + inicio;

}
//***************************************** */
function barajarNumeros(array) {

    // creamos un array
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
}

//***************************************** */
function asignarNumeros() {

    //creamos un array vacio para contener los valores aleatorios
    let numerosUsables = [];

    // con este bucle rellenamos lo que seran los futuros valores de los botones
    /// con los numeros del 1 al 10
    for (let i = inicio; i <= fin; i++) {

        // rellenamos el array "numeros"
        numerosUsables.push(i);
    }

    // Barajamos los números
    numerosUsables = barajarNumeros(numerosUsables);
    
    // atrapamos el elemento "boton" de los numeros
    let botones = document.querySelectorAll('.numero');
    
    // recorremos todos los botones de la tabla
    botones.forEach((boton, index) => {

        //hacemos que no se repita el numero que asignaremos
        let numeroAsignado = numerosUsables[index];
        
        // los asignamos al valor y el contenido de texto de los botones
        boton.textContent = numeroAsignado;
        boton.value = numeroAsignado;
    });
}

// ------> Asigna los números aleatorios al cargar la página
document.addEventListener("DOMContentLoaded", function() {
    asignarNumeros();
});
//*************************************************************************** */

//-----FUNCIONES PARA  REPRODUCIR AUDIOS -------------------------

function audioPlay(evento){

    elementoAudio.play().catch(error => {
        console.log('Error al reproducir el audio:', error);
    });
}
// -*****************************

function reproducirAudio(numero) {
    //cogemos la clave del audio segun el mapa
    let audio = audios.get(numero);
    if (audio) {
        audio.play();
    }
}

// -*****************************
function seleccionarNumero(numeroElement) {
    if (selectedAudio) {
        const numeroSeleccionado = parseInt(numeroElement.textContent);
        const audioCorrecto = parseInt(selectedAudio.value);

        if (numeroSeleccionado === audioCorrecto) {
            numeroElement.style.backgroundColor = "green";
            selectedAudio.parentElement.style.backgroundColor = "green";
            selectedAudio.disabled = true;
            numeroElement.disabled = true;
            correctos[numeroSeleccionado] = true;
            alert('¡Correcto! ¡Has acertado!');

        } else {
    

            if (!intentos[numeroSeleccionado]) {
                intentos[numeroSeleccionado] = 0;
            }
            ///almacenamos numero intentos
            intentos[numeroSeleccionado]++;

            if (intentos[numeroSeleccionado] >= 3) {
                selectedAudio.parentElement.style.backgroundColor = "blue";
                
                
                // Marcar la casilla correcta en azul
                const celdaSolucion= Array.from(document.querySelectorAll("#tabla_numeros .numero")).find(celda => parseInt(celda.textContent) === audioCorrecto);
                if (celdaSolucion) {

                    celdaSolucion.style.backgroundColor = "blue";
                }

                alert(`Has fallado 3 veces. La respuesta correcta es: ${audioCorrecto}`);
            
            } else {
                numeroElement.style.backgroundColor = "orange";
                selectedAudio.parentElement.style.backgroundColor = "orange";
                alert('¡Incorrecto! Intenta de nuevo.');
            }
        }
    }
}


// -*****************************

    audioButtons.forEach(button => {
        button.disabled = false;
        button.parentElement.style.backgroundColor = "";
        button.addEventListener("click", function() {
            seleccionarAudio(this.value);
        });
    });


//-----FUNCIONES PARA  INICIAR JUEGO -------------------------

function iniciarJuego() {
    // Reinicia las variables necesarias
    oportunidades = oportunidadesMax;
    actualizarEstadoCeldas();
    asignarNumeros(); // Asigna los números al iniciar el juego
    numeroAleatorio = numerosRandom(inicio, fin);
    intentos = {}; // Reinicia los intentos totales
    correctos = {}; // Reinicia los correctos
    intentosFallidos = {}; // Reinicia los intentos fallidos
    audioSeleccionado = null;
    tiempoInicioJuego = new Date();
}

//-----LISTENERS

//***INICIAR */
iniciarJuegoButton.addEventListener("click", iniciarJuego);

  //***SELECCIONA NUMERO */

  numeros.forEach(numero => {
    numero.addEventListener('click', function() {

        seleccionarNumero(this);

        if (Array.from(numeros).every(n => n.disabled)) {
            
            //mensaje al finalizar toda la tabla
            alert("¡Bien hecho!");
            finalizarPartida();
        }
    });
});
        
//***REPRODUCIR AUDIO */

window.audioPlay = function() {
    elementoAudio.play();
}

//***ELEGIR AUDIO*/

window.seleccionarAudio = function(audioNumber) {
    selectedAudio = audioButtons[audioNumber - 1];
}


// -*****************************
//Función para volver a dejar las celdas como estaban

function actualizarEstadoCeldas() {
    numeros.forEach(celda => {
        celda.style.backgroundColor = ''; 
        celda.disabled = false;
        celda.classList.remove('seleccionada');
    });
}

// -*****************************
//Función para cambiar la imagen del cofre

function cambiarImagen(id, nuevaImagen) {
    let img = document.getElementById(id);
    if (img) {
        img.src = nuevaImagen;
    }else{
        console.log(`Elemento con ID ${id} no encontrado.`);
    }
}

// -*****************************
// Función para formatear el tiempo en minutos y segundos
function formatearTiempo(tiempoEnMilisegundos) {
    // Convertir milisegundos a segundos
    let segundos = Math.floor(tiempoEnMilisegundos / 1000);
    
    // Calcular los minutos y los segundos restantes
    let minutos = Math.floor(segundos / 60);
    segundos = segundos % 60;
    
    // Formatear los minutos y segundos con ceros a la izquierda si son menores que 10
    let minutosFormateados = minutos < 10 ? "0" + minutos : minutos;
    let segundosFormateados = segundos < 10 ? "0" + segundos : segundos;
    
    // Devolver el tiempo formateado en formato "mm:ss"
    return minutosFormateados + ":" + segundosFormateados;
}

// -*****************************
//Funcion para recoger info de la partida para server
function obtenerInfoJSON(duracionJuego) {
    let infoJSON = {
        intentosFallidos: {},
        audiosCorrectos: {},
        duracionPartida: duracionJuego

    };

 // Asegurarse de registrar todos los números, incluso si no hay intentos fallidos
        for (let i = inicio; i <= fin; i++) {
            infoJSON.intentosFallidos[i] = intentos[i] || 0;
}

    // Agregar los números de audios correctos
    Object.keys(correctos).forEach(numero => {
        infoJSON.audiosCorrectos[numero] = correctos[numero];
    });

    return infoJSON;
}

// -*****************************
//Funcion al acabar la partida

function finalizarPartida() {

     // Obtener el tiempo al finalizar la partida
     let tiempoFinJuego = new Date();
     let duracionJuego = formatearTiempo(tiempoFinJuego - tiempoInicioJuego); // Duración en milisegundos


    // Obtener la información del JSON
    let infoJSON = obtenerInfoJSON(duracionJuego);

    // Crear el mensaje de alerta con la información recopilada
    let alertMessage = "Información de la partida:\n";
    alertMessage += "Intentos fallidos:\n";

     // Agregar los intentos fallidos al mensaje de alerta
     Object.keys(infoJSON.intentosFallidos).forEach(numero => {
        alertMessage += `Número ${numero}: ${infoJSON.intentosFallidos[numero]} intentos\n`;
    });

    alertMessage += "\nAudios correctos:\n";
    // Agregar los audios correctos al mensaje de alerta
    Object.keys(infoJSON.audiosCorrectos).forEach(numero => {
        alertMessage += `Número ${numero}: Correcto\n`;
    });

  // Agregar la duración de la partida al mensaje de alerta
  alertMessage += `\nDuración de la partida: ${infoJSON.duracionPartida}`;

// Mostrar el mensaje de alerta
alert(alertMessage);


    // Cambiar la imagen del cofre
    cambiarImagen('cofre_cerrado', 'img/chest-145752_1280.png');

    // Redireccionar después de cierto tiempo (3 segundos en este ejemplo)
    setTimeout(() => {
        window.location.href = "mapaMundo_pag7.html";
    }, 2000);
}
 // -*****************************

// --------------------Llamadas a la funcion que coloca los numeros
asignarNumeros();

// --------------------Llamadas a las funciones de reproducir audios
audioPlay(); 
reproducirAudio(numero);



});
