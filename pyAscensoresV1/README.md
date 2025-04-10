# PyAscensores

## Clases y Responsabilidades

---

### Mundo
Recibe: `Universidad`, `Tiempo`

#### Responsabilidades:
- `simular()`: Inicia y coordina la simulación general.
- `esperar()`: Controla el paso del tiempo dentro de la simulación.
- `generarPersona()`: Crea nuevas personas en la simulación.
- `avanzarMinuto()`: Avanza el reloj y actualiza el sistema.
- `guardarEstadísticas()`: Registra datos para análisis posterior.
- `iniciarSimulación()` / `detenerSimulación()`: Controla el ciclo de vida de la simulación.
- `manejarEventosAleatorios()`: Introduce incidentes como averías, cortes, etc.

---

### Tiempo

#### Responsabilidades:
- `darFormato()`: Devuelve una representación legible de la hora.
- `darLaHora()`: Proporciona la hora actual.
- `esHoraPunta()`: Identifica si es un periodo de alta afluencia.
- `esFestivo()` / `esFinDeSemana()`: Informa sobre días especiales o no laborables.

---

### Universidad
Recibe: `Tiempo`, `Planta`, `Ascensor`, `Persona`

#### Responsabilidades:
- `estaAbierta()`: Verifica si la universidad está en horario de funcionamiento.
- `acogerPersona()`: Permite el ingreso y movimiento de personas.
- `actualizarEstado()`: Gestiona el estado del edificio y sus componentes.
- `imprimirEstado()`: Imprime el estado actual para monitoreo o depuración.
- `gestionarEmergencias()`: Coordina evacuaciones o respuestas ante incidentes.
- `programarMantenimiento()`: Planifica mantenimientos de ascensores y servicios.
- `controlarAccesos()`: Aplica restricciones a zonas específicas.

---

### Persona
Recibe: `Ascensor`

#### Responsabilidades:
- `llamarAlAscensor()`: Solicita un ascensor.
- `subirAlAscensor()`: Entra en un ascensor disponible.
- `seleccionarPlantaDestino()`: Indica a qué planta quiere ir.
- `bajarDelAscensor()`: Sale al llegar a su destino.
- `decidirComoMoverse()`: Escoge entre escaleras o ascensor.
- `establecerPrioridad()`: Define su nivel de prioridad (profesor, PMR, etc.).
- `cancelarViaje()`: Cambia de decisión respecto a su destino.
- `reportarProblema()`: Informa sobre errores o incidencias.
- `esperarTiempoMáximo()`: Controla la paciencia del usuario.

---

### Ascensor
Recibe: `Persona`, `Llamada`

#### Responsabilidades:
- `estaLleno()`: Comprueba si ha alcanzado su capacidad.
- `atenderLlamadas()`: Atiende solicitudes pendientes.
- `plantaActual()`: Indica dónde se encuentra.
- `moverse()`: Cambia de planta en función de la lógica.
- `entrarEnModoEmergencia()`: Activa protocolos especiales por error o evento.
- `requiereMantenimiento()`: Señala si necesita intervención técnica.
- `optimizarRuta()`: Mejora la eficiencia del recorrido.
- `calcularPesoActual()`: Evalúa la carga total en el interior.
- `abrirPuertas()` / `cerrarPuertas()`: Controla acceso con temporización y seguridad.
- `asignarPlantas()`: Conecta el ascensor con las plantas del edificio.
- `atenderLlamada(Llamada)`: Añade una llamada a la cola del ascensor.
- `mover()`: Lógica para desplazar el ascensor, atender llamadas y dejar personas.

---

### ControlAscensor
Recibe: `List<Ascensor>`

#### Responsabilidades:
- `procesarLlamada()`: Genera una nueva llamada desde una persona y la asigna al ascensor más cercano.
- `seleccionarAscensor()`: Elige el ascensor óptimo para una llamada.
- `moverAscensores()`: Ejecuta la acción de mover todos los ascensores.
- `imprimirEstadoAscensores()`: Muestra en consola la situación actual de cada ascensor.

---

### Llamada
Recibe: `int plantaOrigen`, `Persona persona`

#### Responsabilidades:
- Representa una solicitud de ascensor realizada por una persona.
- Proporciona acceso a los datos de origen y de la persona asociada.

---

### Planta
Recibe: `Persona`

#### Responsabilidades:
- `personasEnPlanta()`: Lista personas presentes actualmente.
- `personasEsperando()`: Personas que esperan el ascensor.
- `registrarTiempoEspera()`: Mide cuánto espera cada persona.
- `tieneAccesoRestringido()`: Controla el acceso limitado.
- `estaDisponible()`: Verifica si la planta está en uso o en mantenimiento.

---

## Casos a tener en cuenta

### Operativos:
- Ascensor detenido inesperadamente
- Corte de energía
- Puertas bloqueadas

### Usuario:
- Grupos grandes intentando entrar juntos
- Personas con movilidad reducida
- Comportamientos en hora punta y/o horas valle

### Universidad:
- Eventos especiales con alta afluencia
- Diferencias entre periodo de exámenes y clases normales
