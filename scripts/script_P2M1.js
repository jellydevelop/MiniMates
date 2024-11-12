//-----LISTENER SEGUN SE CARGA LA WEB-------------------------

document.addEventListener("DOMContentLoaded", function() {


//-----VARIABLES

let intentos = 0;
let numeroAleatorio=0;
const oportunidadesMax = 2;
let oportunidades = oportunidadesMax;
let correctos = {};
let tiempoInicioJuego;
  
    
//-----IDS
const iniciarJuegoButton = document.getElementById("iniciarJuego");
const elementoAudio = document.getElementById("audio");
const numeros = document.querySelectorAll("#tabla_numeros .numero");
const contanedorCanyones = document.getElementById('canyones');
   
    
 //-----FUNCIONES PARA----------------
    
//--------------> INICIAR EL JUEGO
function iniciarJuego(){

    //generamos un numero aleatorio del 1 al 3
    numeroAleatorio=Math.floor(Math.random()*3)+1;

    //habilitamos las imagenes correspondientes al nºaleatorio
    habilitarImagenes(numeroAleatorio);

    //ponemos el marcados de intentos a iniciar
    intentos=0;

    //ponemos en marcha el contador de tiempo
    tiempoInicioJuego=new Date();
}

    //***************************************** */
//--------------> GESTIONAR LA SELECCION DE NÚMERO

function seleccionarNumero(casilla){

    //si acierta
    if(parseInt(casilla.textContent) == numeroAleatorio){

        //cambiamos el estilo a verde
        casilla.style.backgroundColor ="green";

          // Mostrar un poco más tarde el mensaje de felicidades
          setTimeout(() => {
            alert(' ! Has acertado ¡');

             //finalizamos la partida => cambiar imagenes  + redireccion
             finalizarPartida();

        }, 1000);

       

    }else{

        //guardamos los intentos
        intentos++;
    
            if(intentos>=oportunidadesMax){

            // Poner la celda que contene la clase con el numero aleatorio que ha salido que es la solución en azul
            document.querySelector(`#A${numeroAleatorio}`).style.backgroundColor = "blue";
              // Mostrar un poco más tarde el mensaje de felicidades
         
              setTimeout(() => {
            alert(' La solución es: '+ numeroAleatorio);

             //finalizamos la partida => cambiar imagenes  + redireccion
             finalizarPartida();

        }, 1500);
            }else{
                // Poner la celda en naranja
                casilla.style.backgroundColor ="orange";
                alert(' Inténtalo de nuevo');            
    }
}

// Deshabilitar el número
casilla.disabled = true;

    // Si todos los números están deshabilitados (es decir, han sido seleccionados)
    if (Array.from(numeros).every(n => n.disabled)) {

        // Mostrar un mensaje de felicitación
        alert("¡Bien hecho!");

        // Finalizar la partida
        finalizarPartida();
    }
}

     //***************************************** */
//--------------> FINALIZAR LA PARTIDA

 function finalizarPartida() {

        // Cambiar las imágenes habilitadas
        cambiarImagenes();

         // Obtener el tiempo al finalizar la partida
         let tiempoFinJuego = new Date();
         let duracionJuego = formatearTiempo(tiempoFinJuego - tiempoInicioJuego); // Duración en milisegundos
 
         // Obtener la información del JSON
         let infoJSON = obtenerInfoJSON(duracionJuego);
 
         // Crear el mensaje de alerta con la información recopilada
         let alertMessage = "Información de la partida:\n";
         alertMessage += "Intentos fallidos: " + (Object.keys(infoJSON.intentosFallidos).length > 0 ? Object.keys(infoJSON.intentosFallidos).length : 0) + "\n";
         alertMessage += "Duración de la partida: " + infoJSON.duracionPartida + "\n";

         // Agregar los intentos fallidos al mensaje de alerta
    Object.keys(infoJSON.intentosFallidos).forEach(numero => {
        alertMessage += `Número ${numero}: ${infoJSON.intentosFallidos[numero]} intentos\n`;
    });

          // Mostrar el mensaje de alerta
    alert(alertMessage);

    // Redireccionar después de cierto tiempo (3 segundos en este ejemplo)
    setTimeout(() => {
        window.location.href = "/mapaMundo1Fin";
    }, 2000);
}


     //***************************************** */
//--------------> HABILITAR CAÑONES TRAS INICIAR PARTIDA

 function habilitarImagenes(cantidad) {

    let imagenesCanyones=document.querySelectorAll('.canyon');

    // Iterar sobre todas las imágenes y mostrar o ocultar según corresponda
    for (let i = 0; i < imagenesCanyones.length; i++) {

        // Si el índice de la imagen es menor que la cantidad especificada,
        // mostramos la imagen; de lo contrario, la ocultamos
        if (i < cantidad) {
            imagenesCanyones[i].removeAttribute('hidden');
        } else {
            imagenesCanyones[i].setAttribute('hidden', 'true');
        }
    }
}

  //***************************************** */
  //--------------> MODIFICAR LA IMAGEN DE LOS CAÑONES TRAS ACABAR PARTIDA

  function cambiarImagenes() {

    // seleccionamos las imágenes de los cañones que no tienen el atributo hidden
    let imagenesCanyones = document.querySelectorAll('.canyon:not([hidden])');

    // Iterar sobre cada imagen y cambiar su src
    imagenesCanyones.forEach(imagen => {

        imagen.src = "/img/canyon_disparo.png";
    });
}


//***************************************** */
  //--------------> FORMATEO DE TIEMPO EN 00:00

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
  //--------------> RECOGIDA DE INFORMACION DE LA PARTIDA
  function obtenerInfoJSON(duracionJuego) {

    let infoJSON = {
        intentosFallidos: {},
        duracionPartida: duracionJuego

    };
 
// Recorrer el objeto intentos para obtener los números de audios fallidos
    Object.keys(intentos).forEach(numero => {
        if (intentos[numero] >= oportunidadesMax) {
            infoJSON.intentosFallidos[numero] = intentos[numero];
        }
    });

    return infoJSON;
}

//-----LISTENERS

//***INICIAR */
iniciarJuegoButton.addEventListener("click", iniciarJuego);

  //***SELECCIONA NUMERO */

  numeros.forEach(numero => {
    numero.addEventListener('click', function() {

        seleccionarNumero(this);
        });
    });

           
//***REPRODUCIR AUDIO */

window.audioPlay = function() {
    elementoAudio.play();
}


// --------------LLAMADAS A LAS FUNCIONES


})
