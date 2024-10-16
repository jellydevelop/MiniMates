// ---- RECOGIDA IDS
let btnSubmit = document.getElementById("botonComprobar");

// ---- LISTENER
btnSubmit.addEventListener("click", mandamosInfoUser);

// ----- FUNCIONES
async function mandamosInfoUser(event) {
    event.preventDefault();

    // RECOGIDA CONTENIDO FORMULARIO
    let mailUser = document.getElementById("mailUser").value;
    let passUser = document.getElementById("passUser").value;

    console.log('Datos formulario:');
    console.log(mailUser);
    console.log(passUser);

    // PREPARACIÓN DEL JSON CON DATOS PARA ENVIAR
    let data = {
        mailUsuario: mailUser,
        passUsuario: passUser
    };
    console.log('Datos JSON:', data);

    // OPCIONES PARA PETICIÓN
    let options = {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    };

    // PETICIÓN HTTP
    try {
        const response = await fetch('/verificacion', options);

        if (!response.ok) {
            throw new Error('Error en la solicitud. Código de estado: ' + response.status);
        }

        const result = await response.json();
        console.log('Respuesta del servidor:', result);
        
       

        // Suponiendo que la respuesta indica un inicio de sesión exitoso
        window.location.href = result.redirectUrl;
        
    } catch (error) {
        console.error('Error:', error.message);
        window.location.href = '/rediLogin'; // Redirigir en caso de un error
    }
}////FIN LOGIN.JS
    