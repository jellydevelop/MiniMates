//----- RECOGIDA IDS
const miInput = document.querySelector('input');

        ////--- FORMULARIO
        let radioForm = document.getElementById("formRedireccion");
            let radioStats = document.getElementById("stats");
            let radioDataAlumno = document.getElementById("dataAlum");

        ////--- BOTONES
        let radioBtn = document.getElementById("botonRedireccionar");
           
//----- LISTENER

///Muestra los formularios segun la eleccion del profesor al pulsar el boton 
     radioBtn.addEventListener("click",redireccionamos);

// Quita la validación mientras escribes
    miInput.addEventListener('input', validacionTrue);

// Muestra el mensaje de validación
    miInput.addEventListener('invalid',validacionFalse);

// Quita los espacios al principio y al final
    miInput.addEventListener('blur', limpiaEspacios);
    

//----- FCNES LISTENER


/// Funcion que envia al usuario a la página que ha elegido gracias al elemento RADIO
function redireccionamos(event){

    event.preventDefault();

        if(radioStats.checked){
            window.location.href = "lista_alumnos_estadisticas.html";        }

        if(radioDataAlumno.checked){
            window.location.href = "datos_alumno_profesor.html";
        }

    
}




////FIN FUNCION PETICION