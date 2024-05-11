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

        let altaBtn = document.getElementById("botonEnviarAlta");
        let bajaBtn = document.getElementById("botonEnviarBaja");
        let updateBtn = document.getElementById("radioMod");

        ////--- LOGS

        let nameLog = document.getElementById("nameAlum");
        let mailLog = document.getElementById("mailAlum");
        let idLOG=document.getElementById("idAlum");



//----- LISTENER

///Muestra los formularios segun la eleccion del profesor al pulsar el boton 
     radioBtn.addEventListener("click",gestionarRadioBtns);

// Quita la validación mientras escribes
    miInput.addEventListener('input', validacionTrue);

// Muestra el mensaje de validación
    miInput.addEventListener('invalid',validacionFalse);

// Quita los espacios al principio y al final
    miInput.addEventListener('blur', limpiaEspacios);
    


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

function limpiaEspacios()  {
    miInput.value = miInput.value.trim();
}

function obtenerTresPrimerasLetras(nombre) {
    return nombre.substring(0, 3);
}


////********************************************* DATOS ALUMNO . HTML ******************************************** */
   ////*********************** ALTA ******************************************** */

   
        altaForm.addEventListener("submit", function(event) {
    event.preventDefault(); // Evita que se envíe el formulario de forma predeterminada
    
    // Captura los datos del formulario
    let mailAlum = document.getElementById("mailAlum").value;
    let nameAlum = document.getElementById("nameAlum").value;
    
    console.log(mailAlum);
        console.log(nameAlum);

    
    // Creamos un objeto con todos los atributos requeridos por el backend
		let datosAlumno = {
			nombreUsuario: nameAlum,
		    mailUsuario: mailAlum,
		    rolUsuario: "alumno",
		    passUsuario: obtenerTresPrimerasLetras(nameAlum) + "alumno"
		};
    
console.log(datosAlumno)  ;
  // Realiza la solicitud AJAX al servidor
    fetch('/aniadiralumno', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosAlumno)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al dar de alta al alumno');
        }
        
        console.log(response);
        return response.json();
    })
    .then(data => {
		console.log(data);
        // Maneja la respuesta del servidor, por ejemplo, mostrando un mensaje de éxito
        alert('Alumno dado de alta correctamente');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Hubo un error al dar de alta al alumno');
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
   
 c
   ////*********************** UPDATE ******************************************** */



////FIN FUNCION PETICION

