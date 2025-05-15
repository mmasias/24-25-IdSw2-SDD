# PyAscensores

## Clases y Responsabilidades

---

### Mundo
**Recibe:** `Universidad`, `Tiempo`

#### Responsabilidades:
- `simular()`: Inicia y coordina el ciclo principal de la simulación, preguntando si se desea continuar, si se quiere agregar una persona manualmente y avanzando el tiempo.
- `esperar()`: Pausa la simulación y espera interacción del usuario para continuar o finalizar.
- `preguntarAgregarPersona()`: Pregunta si el usuario quiere agregar manualmente una persona o generar una aleatoria.
- `generarPersona()`: Crea una nueva instancia de `Persona` con un destino aleatorio y origen fijo en 0.
- `avanzarMinuto()`: Avanza el tiempo de la simulación en un minuto.
- `main(String[] args)`: Punto de entrada del programa. Inicializa `Mundo` y comienza la simulación con `simular()`.

---

### AscensorSimuladorAutomatico (Simulador automático con Swing)
**Recibe:** `Universidad`, `TiempoSimulado`

#### Responsabilidades:
- Controla una simulación automática del sistema, avanzando el tiempo cada segundo.
- `siguientePaso()`: Genera personas aleatorias, avanza el tiempo y ejecuta una iteración de simulación.
- `generarPersonasAleatorias()`: Añade personas con origen y destino aleatorios.
- `cambiarVelocidad(boolean)`: Ajusta la velocidad de la simulación.
- `toggleSimulacion()`: Inicia o pausa la simulación automática.
- `actualizarVista()`: Actualiza visualmente el estado actual del sistema (ascensores, personas, plantas).

---

### Tiempo

#### Responsabilidades:
- `avanzarMinuto()`: Avanza el tiempo un minuto, ajustando la hora y avanzando de hora si es necesario.
- `darLaHora()`: Devuelve una representación en formato HH:mm de la hora actual.
- `getHora()`: Retorna la hora actual como entero.
- `getMinuto()`: Retorna los minutos actuales como entero.
- `getFecha()`: Retorna la fecha actual del sistema (`LocalDate`).
- `esFinDeSemana()`: Indica si la fecha actual corresponde a sábado o domingo.
- `esFestivo()`: Informa si la fecha actual es un día festivo específico predefinido (1 de enero, 25 de diciembre, 1 de noviembre).

---

### TiempoSimulado (Extensión de Tiempo)

#### Responsabilidades:
- Hereda todas las funciones de `Tiempo`, forzando que nunca sea fin de semana ni festivo.
- `reiniciar(hora, minuto)`: Permite restablecer la hora simulada, útil para reiniciar la simulación diaria.

---

### Universidad
**Recibe:** `Tiempo`, `Planta`, `Ascensor`, `Persona`

#### Responsabilidades:
- `Universidad(Tiempo)`: Constructor que inicializa las plantas, ascensores y controlador.
- `estaAbierta()`: Verifica si la universidad está en horario de funcionamiento (hora, día, festivos).
- `acogerPersona(Persona)`: Añade una persona manualmente.
- `acogerPersona(int origen, int destino)`: Agrega persona con origen/destino específicos, usada por GUI y simulador automático.
- `evolucionDeLaUniversidad()`: Mueve los ascensores conforme a las solicitudes activas.
- `imprimirEstado()`: Imprime estado completo de plantas y ascensores.
- `simular()`: Ejecuta una ronda completa de simulación (mover ascensores + imprimir estado).
- `obtenerCantidadEsperando(int planta)`: Cantidad de personas esperando en una planta.
- `obtenerCantidadEnPlanta(int planta)`: Cantidad de personas presentes en una planta.

---

### Persona
**Recibe:** `Ascensor` (a través de `ControlAscensor`)

#### Responsabilidades:
- `llamarAlAscensor(ControlAscensor)`: Solicita ascensor desde su planta origen.
- `getPlantaOrigen()`: Devuelve planta de inicio.
- `getPlantaDestino()`: Devuelve planta objetivo.
- `estaAtendido()`: Indica si ya fue recogido.
- `marcarAtendido()`: Marca a la persona como recogida.

---

### Ascensor
**Recibe:** `Persona`, `Llamada`

#### Responsabilidades:
- `asignarPlantas(List<Planta>)`: Conecta el ascensor con las plantas físicas.
- `atenderLlamada(Llamada)`: Recibe nuevas solicitudes.
- `mover()`: Realiza operaciones de movimiento, bajada, subida y planificación.
- `bajarPersonasEnPlantaActual()`: Baja pasajeros que llegaron a destino.
- `recogerPersonasEnPlantaActual()`: Sube pasajeros si hay lugar.
- `moverHaciaDestinoPersona()`: Se desplaza hacia el destino de su primer pasajero.
- `atenderLlamadaPendiente()`: Atiende la próxima llamada si no lleva a nadie.
- `buscarPlanta(int)`: Retorna planta desde su número.
- `getId()`: Identificador único (ej: A1, A2).
- `getPlantaActualAsInt()`: Número de planta actual.
- `personasEnElAscensor()`: Cuenta personas a bordo.
- `imprimirEstado()`: Imprime el estado del ascensor.

---

### ControlAscensor
**Recibe:** `List<Ascensor>`

#### Responsabilidades:
- `procesarLlamada(Persona, int origen, int destino)`: Enruta persona al ascensor más conveniente.
- `seleccionarAscensor(int planta)`: Retorna ascensor más cercano a la planta.
- `moverAscensores()`: Llama a `mover()` en cada ascensor.

---

### Llamada
**Recibe:** `int plantaOrigen`, `int plantaDestino`, `Persona persona`

#### Responsabilidades:
- `getPlantaOrigen()`: Devuelve planta desde donde se pidió el ascensor.
- `getPlantaDestino()`: Planta destino.
- `getPersona()`: Persona que generó la llamada.

---

### Planta
**Recibe:** `Persona`

#### Responsabilidades:
- `getNumero()`: Número identificador.
- `personaEsperaAscensor(Persona)`: Agrega persona a lista de espera.
- `personaSubeAlAscensor(Persona)`: Remueve de espera y registra subida.
- `personaLlegaADestino(Persona)`: Añade a lista de presentes.
- `getCantidadEsperando()`: Cantidad esperando.
- `getCantidadEnPlanta()`: Cantidad total presente.
- `getPersonasEsperando()`: Lista de espera actual.

---

### Simulable
**Tipo:** `Interface`

#### Responsabilidades:
- `simular()`: Define el comportamiento de simulación que debe implementar cualquier clase que desee ser simulada (como `Mundo`, `Universidad`, etc.).

---

### Movible
**Tipo:** `Interface`

#### Responsabilidades:
- `mover()`: Define la acción de movimiento que deben implementar las clases que representen elementos móviles (como `Ascensor`).

---

### ITiempo
**Tipo:** `Interface`

#### Responsabilidades:
- `avanzarMinuto()`: Avanza un minuto en el tiempo simulado.
- `darLaHora()`: Devuelve la hora actual como cadena en formato `HH:mm`.
- `getHora()`: Devuelve la hora como entero.
- `esFinDeSemana()`: Retorna si es fin de semana.
- `esFestivo()`: Retorna si es un día festivo.

---

### EstadoAscensor
**Tipo:** `Interface`

#### Responsabilidades:
- `ejecutar(Ascensor)`: Define el comportamiento del ascensor según su estado actual (ej. moverse hacia una llamada o destino).

---

### MovimientoHaciaLlamada
**Implementa:** `EstadoAscensor`

#### Responsabilidades:
- `ejecutar(Ascensor)`: Mueve el ascensor hacia la llamada más cercana.

---

### MovimientoHaciaDestino
**Implementa:** `EstadoAscensor`

#### Responsabilidades:
- `ejecutar(Ascensor)`: Mueve el ascensor hacia el destino de su primer pasajero.

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