# PyAscensores

## Clases y Responsabilidades

---

### Mundo  
Recibe: `Universidad`, `Tiempo`

#### Responsabilidades:
- `simular()`: Inicia y coordina la simulación general.  
- `esperar()`: Pide un intro para continuar.  
- `generarPersona()`: Crea nuevas personas en la simulación.  
- `avanzarMinuto()`: Avanza el reloj y actualiza el sistema.  
- `guardarEstadísticas()`: Registra datos para análisis posterior.  
- `manejarEventosAleatorios()`: Introduce incidentes como averías, cortes, etc.

---

### Tiempo

#### Responsabilidades:
- `darFormato()`: Devuelve una representación legible de la hora.  
- `darLaHora()`: Proporciona la hora actual.  
- `esFestivo()` / `esFinDeSemana()`: Informa sobre días especiales o no laborables.

---

### Universidad  
Recibe: `Tiempo`, `Planta`, `Ascensor`, `Persona`

#### Responsabilidades:
- `estaAbierta()`: Verifica si la universidad está en horario de funcionamiento.  
- `acogerPersona()`: Permite el ingreso y movimiento de personas.  
- `evolucionDeLaEvolucion()`: Gestiona el estado del edificio y sus componentes.  
- `imprimirEstado()`: Imprime el estado actual para monitoreo o depuración.

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

### Planta  
Recibe: `Persona`

#### Responsabilidades:
- `personasEnPlanta()`: Lista personas presentes actualmente.  
- `personasEsperando()`: Personas que esperan el ascensor.  
- `registrarTiempoEspera()`: Mide cuánto espera cada persona.  
- `tieneAccesoRestringido()`: Controla el acceso limitado.  
- `estaDisponible()`: Verifica si la planta está en uso o en mantenimiento.

---

### Ascensor  
Recibe: `Persona`

#### Responsabilidades:
- `asignarPlantas(List<Planta>)`: Asigna las plantas disponibles al ascensor.  
- `atenderLlamada(int plantaOrigen, Persona persona)`: Registra una llamada pendiente.  
- `mover()`: Realiza el movimiento del ascensor dependiendo de si transporta personas o responde a llamadas.  
- `atenderLlamadaPendiente()`: Atiende la siguiente llamada en cola, si la hay.  
- `moverHaciaDestinoPersona()`: Lleva a las personas a su planta destino.  
- `buscarPlanta(int numero)`: Localiza una planta por su número.  
- `imprimirEstado()`: Muestra en consola la planta actual y número de personas.  
- `getId()`: Devuelve el identificador del ascensor.  
- `getPlantaActualAsInt()`: Devuelve la planta actual como número entero.

---

### ControlAscensor  
Recibe: `Ascensor`, `Persona`

#### Responsabilidades:
- `procesarLlamada(Persona persona, int origen, int destino)`: Selecciona el ascensor más cercano y le asigna la llamada.  
- `seleccionarAscensor(int planta)`: Determina el ascensor más cercano a una planta determinada.  
- `moverAscensores()`: Solicita a todos los ascensores que avancen según su estado.  
- `imprimirEstadoAscensores()`: Muestra el estado de todos los ascensores en consola.

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
- Pico súbito de demanda (Clases que terminan simultáneamente en varias plantas)
