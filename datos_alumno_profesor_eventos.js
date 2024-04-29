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



////FIN FUNCION PETICION
