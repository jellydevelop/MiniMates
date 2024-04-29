
//----- RECOGIDA IDS

let registro = document.getElementById("a_registro");
//----- LISTENER
 registro.addEventListener("click",mandarALogin) ;


//----- FCNES

function mandarALogin () {

    window.location.href = "/login";

    
}
