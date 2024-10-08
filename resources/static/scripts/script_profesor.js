document.addEventListener('DOMContentLoaded', function() {

//----------------VERIFICACION DE ENTRADA YA REALIZADA

    // Verificar si el profesor ha entrado antes
     /*       const userRole = localStorage.getItem('userRole'); 

    if (userRole === 'profesor') {
		
            localStorage.setItem('haEntrado', 'true');
            
        } else {
			
            console.error('El usuario no tiene el rol de profesor.');
            window.location.href="/notFound";
        }
    
         */
    //----------------ID
    const botonStats=document.getElementById('stats');
    const botonDatos=document.getElementById('dataAlum');
    const botonExit = document.getElementById('exit');
    const labelMenuStats = document.getElementById("lStats");
	const labelMenuData = document.getElementById("lData");
   
    
    //----------------FUNCIONES
    
    
    function rediProfesorListaStats() {
    window.location.href = '/listaAlumnos'; 
}
//************************** */

   function rediProfesorDatos() {
    window.location.href = '/datosAlumnnoProfesor'; 
}
//************************** */

  
function salirSesion() {
    localStorage.removeItem('haEntrado'); 
    window.location.href = '/login'; 
}

    
    //----------------LISTENERS
    botonStats.addEventListener('click', rediProfesorListaStats);
    botonDatos.addEventListener('click', rediProfesorDatos);
    botonExit.addEventListener('click', salirSesion);
    labelMenuStats.addEventListener('click', rediProfesorListaStats);
	labelMenuData.addEventListener('click', rediProfesorDatos);

    });
    