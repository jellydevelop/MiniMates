document.addEventListener('DOMContentLoaded', function() {


//----------------VERIFICACION DE PARTIDA YA JUGADA

    // Verificar si el alumno ha jugado antes
    if (localStorage.getItem('haJugado')) {

        // Si ha jugado, ocultar la opción de jugar
        const jugarOption = document.getElementById('dataPartidas');
        
        if (jugarOption) {
            jugarOption.hidden=true;
        }
    }
    

//----------------ID
const botonExit = document.getElementById('exit');
const botonAtras = document.getElementById('rediAtras');
const botonCierreGraf = document.getElementById('cierreGraf');
const botonCierrTabla = document.getElementById('cierreTabla');
 const selectMundos = document.getElementById('selectMundos');
 const selectRetos = document.getElementById('selectRetos');





//----------------FUNCIONES


//************************** */

function rediMenuAlumno(evento){
    window.location.href = '/alumno'; 

}

//************************** */

function salirSesion() {
    localStorage.removeItem('haJugado'); 
    window.location.href = '/login'; 
}

//************************** */

function escondeTabla() {
    const tabla=document.getElementById('tablaInfo');
    tabla.hidden=true;
    botonCierrTabla.hidden=true;
    
}

//************************** */

function escondeGrafica() {
    const grafica=document.getElementById('graficasInfo');
    grafica.hidden=true;
    botonCierreGraf.hidden=true;
    
}

//************************** */

function despliegaTabla() {
	if(selectMundos.value!==""){
		const tabla=document.getElementById('tablaInfo');
	    tabla.hidden=false;
	    botonCierrTabla.hidden=false;
			
	}
    
    
}

//************************** */

function despliegaGrafica() {
	if(selectRetos.value!==""){
    const grafica=document.getElementById('graficasInfo');
	    grafica.hidden=false;
	    botonCierreGraf.hidden=false;
			
	}
    
    
}
//----------------LISTENERS

botonExit.addEventListener('click', salirSesion);
botonAtras.addEventListener('click', rediMenuAlumno);
botonCierreGraf.addEventListener('click', escondeGrafica);
botonCierrTabla.addEventListener('click', escondeTabla);
selectMundos.addEventListener('change', despliegaTabla);
selectRetos.addEventListener('change', despliegaGrafica);






});

