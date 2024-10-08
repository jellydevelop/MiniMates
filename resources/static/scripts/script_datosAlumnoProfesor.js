document.addEventListener('DOMContentLoaded', function() {

//----- RECOGIDA IDS
const miInput = document.querySelector('input');

        ////--- FORMULARIOS
        let radioForm = document.getElementById("formEleccion");
            let altaForm = document.getElementById("formAlta");
            let bajaForm = document.getElementById("formBaja");
            let updateForm = document.getElementById("formModificacion");

        ////--- BOTONES
        let radioBtn = document.getElementById("botonEnviarEleccion");
            let radioAlta = document.getElementById("radioAlta");
            let radioBaja = document.getElementById("radioBaja");
            let radioMod = document.getElementById("radioMod");
			const botonAtras = document.getElementById('rediAtras');
			const botonExit = document.getElementById('exit');



        let altaBtn = document.getElementById("botonEnviarAlta");
        let bajaBtn = document.getElementById("botonEnviarBaja");
        let updateBtn = document.getElementById("radioMod");

        ////--- LOGS

        let nameLog = document.getElementById("nameAlum");
        let mailLog = document.getElementById("mailAlum");
        let idLOG=document.getElementById("idAlum");




//----- FCNES VALIDACIONES

//Estas 2 funciones gestionan el contenido de las casillas
    function validacionTrue() {
                // Quita el mensaje según escribes
                miInput.setCustomValidity('');
                // Comprueba si debe validarlo
                miInput.checkValidity();
    }
        
    function validacionFalse (){
        miInput.setCustomValidity('Si no es molestia,¿me dices tu nombre?');
    }

//----- FCNES LISTENER

function gestionarRadioBtns(event){

    event.preventDefault();

        if(radioAlta.checked){
            altaForm.hidden = false;
        } else {
            altaForm.hidden = true;
        }

        if(radioBaja.checked){
            bajaForm.hidden = false;
        } else {
            bajaForm.hidden = true;
        }

        if(radioMod.checked){
            updateForm.hidden = false;
        } else {
            updateForm.hidden = true;
        }
}

//**************************************************** */

function limpiaEspacios()  {
    miInput.value = miInput.value.trim();
}
//**************************************************** */

function obtenerTresPrimerasLetras(nombre) {
    return nombre.substring(0, 3);
}

//**************************************************** */
    
   function rediMenuProfesor(evento){
    window.location.href = '/profesor'; 

}

//************************** */

function salirSesion() {
    localStorage.removeItem('haJugado'); 
    window.location.href = '/login'; 
}
    


////********************************************* DATOS ALUMNO . HTML ******************************************** */
   ////*********************** ALTA ******************************************** */

     // Obtenemos el formulario
const formAlta = document.getElementById('formAlta');

// Agregamos un evento de escucha para el envío del formulario
formAlta.addEventListener('submit', function(event) {
    event.preventDefault(); // Evitamos que se envíe el formulario de forma predeterminada
    
    // Capturamos los valores de los campos de entrada
    const nombreAlumno = document.getElementById('nameAlum').value;
    const mailAlumno = document.getElementById('mailAlum').value;

    // Construimos el objeto con los datos del alumno a enviar al servidor
    const datosAlumno = {
        nombre: nombreAlumno,
        mail: mailAlumno,
        rol: 'alumno',
        pass: nombreAlumno
    };

    // Realizamos la solicitud fetch al servidor para añadir el nuevo alumno
    fetch('/aniadiralumno', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(datosAlumno)
})
.then(response => {
    if (!response.ok) {
        throw new Error('Error en la respuesta del servidor');
    }
    return response.json();
})
.then(data => {
    console.log(data);
    alert('Alumno añadido correctamente');
})
.catch(error => {
    console.error('Error:', error);
});
});


   ////*********************** BAJA POR ID ******************************************** */
   
  /* bajaForm.addEventListener("submit", function(event) {
   		 event.preventDefault(); // Evita que se envíe el formulario de forma predeterminada

    // Captura el ID del formulario
    let idAlum = document.getElementById("idAlum").value;


    // Realiza la solicitud AJAX al servidor para eliminar al alumno por su ID
     fetch('/borrar_usuario/' + idAlum, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al dar de baja al alumno');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        alert('Alumno dado de baja correctamente');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Hubo un error al dar de baja al alumno');
    });
});
/*

////*********************** BAJA POR MAIL ******************************************** */
    document.addEventListener("DOMContentLoaded", function() {
		    let bajaForm = document.getElementById('formBaja');
		    let mailLog = document.getElementById('mailLog');

    if(bajaForm && mailLog) {
        bajaForm.addEventListener("submit", function(event) {
            event.preventDefault(); // Evita que se envíe el formulario de forma predeterminada

            // Captura el valor del campo de entrada
            let mailAlumBaja = mailLog.value;
            console.log(mailAlumBaja);

            // Realiza la solicitud AJAX al servidor para eliminar al alumno por su ID
            fetch('/borrar_usuario_mail/' + mailAlumBaja, 
            {
                method: 'DELETE'
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al dar de baja al alumno');
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
                alert('Alumno dado de baja correctamente');
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Hubo un error al dar de baja al alumno');
            });
        });
    }
});

 
   ////*********************** UPDATE ******************************************** */
// Agregamos un evento de escucha para el envío del formulario
formModificacion.addEventListener('submit', function(event) {
    event.preventDefault(); // Evitamos que se envíe el formulario de forma predeterminada
    
    // Capturamos los valores de los campos de entrada
    const mailUsuarioUp = document.getElementById('mailUserUp').value;
    const nombreAlumnoUp = document.getElementById('nameAlumUp').value;
    const nuevoMailAlumno = document.getElementById('emailAlumUp').value;

    // Construimos el objeto con los datos del alumno a enviar al servidor
    let datosAlumnoMod = {};

    // Verificamos qué campos se deben modificar
    if (document.getElementById('modificarNombre').checked) {
        datosAlumnoMod.nombre = nombreAlumnoUp;
    }
    if (document.getElementById('modificarEmail').checked) {
        datosAlumnoMod.mail = nuevoMailAlumno;
    }

    // Realizamos la solicitud fetch al servidor para actualizar los datos del alumno
    fetch(`/modificamos_datos_usuario/${mailUsuarioUp}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosAlumnoMod)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al modificar los datos del alumno');
        }
        return response.json();
    })
    .then(data => {
        console.log(data); // Podemos mostrar la respuesta del servidor en la consola
        
        alert('Datos del alumno modificados correctamente');
        // Aquí puedes agregar más lógica según sea necesario, como actualizar la interfaz de usuario, etc.
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Hubo un error al modificar los datos del alumno');
    });
});

//----- LISTENER

///Muestra los formularios segun la eleccion del profesor al pulsar el boton 
     radioBtn.addEventListener("click",gestionarRadioBtns);

// Quita la validación mientras escribes
    miInput.addEventListener('input', validacionTrue);

// Muestra el mensaje de validación
    miInput.addEventListener('invalid',validacionFalse);

// Quita los espacios al principio y al final
    miInput.addEventListener('blur', limpiaEspacios);
   
//Redirige a la página anterior--> menu
botonAtras.addEventListener('click', rediMenuProfesor);

//cierra la sesión--> login
botonExit.addEventListener('click', salirSesion);

});
////FIN FUNCION PETICION
