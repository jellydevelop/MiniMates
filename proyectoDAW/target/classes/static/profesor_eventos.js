//----- RECOGIDA IDS

        ////--- FORMULARIO
        let radioForm=document.getElementById("radioForm");
            let radioStats = document.getElementById("stats");
            let radioDataAlumno = document.getElementById("dataAlum");

        ////--- BOTONES
        let radioBtn = document.getElementById("botonRedireccionar");
           
//----- LISTENER

///Redirige según la elección y tras pulsar  el boton 
    radioForm.addEventListener("submit",redireccionamos);

//----- FCNES LISTENER

/// Funcion que envia al usuario a la página que ha elegido gracias al elemento RADIO
function redireccionamos(event){
    let opcionEstadisticas = document.getElementById('estadisticas').checked;
    let opcionDatosAlumno = document.getElementById('datosAlumno').checked;

    event.preventDefault();

    if (!opcionEstadisticas && !opcionDatosAlumno) {
        alert('Por favor, selecciona una opción antes de enviar.');
    } else {
        // Si se ha seleccionado al menos una opción, permitir el envío del formulario
        event.currentTarget.submit();
    }
}

// Agrega un evento 'click' al botón de enviar para verificar si se han seleccionado opciones antes de enviar el formulario.
radioBtn.addEventListener("click", function(event) {
    let opcionEstadisticas = document.getElementById('estadisticas').checked;
    let opcionDatosAlumno = document.getElementById('datosAlumno').checked;

    if (!opcionEstadisticas && !opcionDatosAlumno) {
        alert('Por favor, selecciona una opción antes de enviar.');
        event.preventDefault(); // Evita que el formulario se envíe si no se han seleccionado opciones.
    }
});