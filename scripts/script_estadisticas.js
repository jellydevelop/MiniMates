document.addEventListener('DOMContentLoaded', function() {

	//----------------VERIFICACION DE PARTIDA YA JUGADA

	// Verificar si el alumno ha jugado antes
	if (localStorage.getItem('haJugado')) {

		// Si ha jugado, ocultar la opción de jugar
		const jugarOption = document.getElementById('dataPartidas');

		if (jugarOption) {
			jugarOption.hidden = true;
		}
	}

	//----------------VERIFICACION DE ROL

	const rolUsuario = localStorage.getItem('rolUsuario');

	//----------------ID
	const botonExit = document.getElementById('exit');
	const botonAtras = document.getElementById('rediAtras');
	const botonCierreGraf = document.getElementById('cierreGraf');
	const botonCierrTabla = document.getElementById('cierreTabla');
	const selectMundos = document.getElementById('selectMundos');
	const selectRetos = document.getElementById('selectRetos');



	//----------------FUNCIONES


	//************************** */

	function rediMenuAlumno() {
		if (rolUsuario == 'esProfesor') {
			window.location.href = '/listaAlumnos';

		} else {
			window.location.href = '/alumno';

		}

	}

	//************************** */

	function salirSesion() {
		alert('Cerrando sesión...');
		localStorage.removeItem('haJugado');
		localStorage.removeItem('emailUsuario');
		localStorage.removeItem('rolUsuario');
		localStorage.removeItem('alumnoSeleccionado');
		localStorage.removeItem('letraClase');


		window.location.href = '/login';
	}
	//************************** */

	function escondeTabla() {
		const tabla = document.getElementById('tablaInfo');
		tabla.hidden = true;
		botonCierrTabla.hidden = true;

	}

	//************************** */

	function escondeGrafica() {
		const grafica = document.getElementById('graficasInfo');
		grafica.hidden = true;
		botonCierreGraf.hidden = true;

	}

	//************************** */

	function despliegaTabla() {
		if (selectMundos.value !== "") {
			const tabla = document.getElementById('tablaInfo');
			tabla.hidden = false;
			botonCierrTabla.hidden = false;

		}


	}

	//************************** */

	function despliegaGrafica() {
		if (selectRetos.value !== "") {
			const grafica = document.getElementById('graficasInfo');
			grafica.hidden = false;
			botonCierreGraf.hidden = false;

		}


	}

	//----------------FUNCIÓN INICIALES ALUMNO
// Función principal para obtener las iniciales según el rol del usuario
function nicialesusuario() {
    const emailUsuario = localStorage.getItem('emailUsuario');
    const rolUsuario = localStorage.getItem('rolUsuario');

    if (!emailUsuario) {
        console.error('El email del usuario no está en el localStorage');
        return;
    }

    if (rolUsuario === 'esAlumno') {
        // Llamada para obtener las iniciales del alumno
        obtenerInicialesAlumno(emailUsuario);
    } else if (rolUsuario === 'esProfesor') {
        // Llamada para obtener las iniciales del alumno seleccionado por el profesor
        obtenerInicialesProfesor();
    } else {
        console.error('Rol de usuario no reconocido:', rolUsuario);
    }
}

// Función para obtener las iniciales del alumno cuando es alumno
function obtenerInicialesAlumno(emailUsuario) {
    fetch(`/alumno/obtenerIniciales/${emailUsuario}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener las iniciales');
            }
            return response.text();
        })
        .then(data => {
            if (data) {
                const divNombre = document.getElementById('divNombre');
                divNombre.innerHTML = `<h2>${data}</h2>`;
            } else {
                console.error('No se recibieron iniciales para el alumno');
            }
        })
        .catch(error => {
            console.error('Error al obtener las iniciales:', error);
        });
}

// Función para obtener las iniciales del alumno seleccionado por el profesor
function obtenerInicialesProfesor() {
    const alumnoSeleccionado = JSON.parse(localStorage.getItem('alumnoSeleccionado'));

    if (alumnoSeleccionado && alumnoSeleccionado.nombre && alumnoSeleccionado.primApellido && alumnoSeleccionado.secApellido) {
        const { nombre, primApellido, secApellido } = alumnoSeleccionado;
        const iniciales = `${nombre[0].toUpperCase()}${primApellido[0].toUpperCase()}${secApellido[0].toUpperCase()}`;

        // Mostrar las iniciales del alumno seleccionado
        const divNombre = document.getElementById('divNombre');
        divNombre.innerHTML = `<h2>${iniciales}</h2>`;
    } else {
        console.error('No se encontró información del alumno seleccionado');
    }
}

// Invocamos la función para obtener las iniciales al cargar la página
nicialesusuario();

	
//----------------LISTPETICIÓN DE DATOS



//----------------GRAFICA

  let selector = document.getElementById('selectRetos');
    const graficaP1 = document.getElementById('graficasInfo');

    // Configuración básica para mostrar los días de la semana
    const diasSemana = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'];
    const aciertosPorDia = [5, 2, 1, 10, 0, 5, 0]; // 7 días, inicializados a 0 minutos

    // Crear el gráfico lineal con Chart.js (sin datos, solo la estructura con 7 días)
    const ctx = document.getElementById('graph').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'line', // Gráfico lineal
        data: {
            labels: diasSemana, // Etiquetas para los días de la semana
            datasets: [{
                label: 'Aciertos',
                data: aciertosPorDia,
                      backgroundColor: "green",
 // Datos de tiempo por día (todos 0 por ahora)
                fill: false, // No llenar debajo de la línea
                borderColor: 'rgba(54, 162, 235, 1)', // Color de la línea
                tension: 0.1 // Suavizar la línea
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true, // El eje Y empieza en 0
                    max: 10, // Máximo de 15 minutos
                    ticks: {
                        stepSize: 1 // Intervalo de las líneas en el eje Y
                    }
                },
                x: {
                    ticks: {
                        autoSkip: true, // Saltar etiquetas si es necesario
                    }
                }
            }
        }
    });

   


	//----------------LISTENERS

	botonExit.addEventListener('click', salirSesion);
	botonAtras.addEventListener('click', rediMenuAlumno);
	botonCierreGraf.addEventListener('click', escondeGrafica);
	botonCierrTabla.addEventListener('click', escondeTabla);
	selectMundos.addEventListener('change', despliegaTabla);
	selectRetos.addEventListener('change', despliegaGrafica);
	 // Mostrar la gráfica cuando los datos estén listos
 // Escuchar el cambio en el select de retos
    selector.addEventListener('change', function () {
        if (selector.value === 'conteo') {
            // Mostrar la gráfica si se selecciona "CONTEO"
            const canvas = document.getElementById('graph');
canvas.width = canvas.parentElement.clientWidth;  // Ancho dinámico basado en el contenedor
        canvas.height = canvas.parentElement.clientHeight
            graficaP1.hidden = false;
        } else {
            // Ocultar la gráfica si no se selecciona "CONTEO"
            grafica.hidden = true;
        }
    });

});