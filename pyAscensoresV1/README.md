# PyAscensores

## Clases y Responsabilidades

---

### Mundo
**Recibe:** `Universidad`, `Tiempo`

---

#### Responsabilidades:

- `simular()`: Inicia y coordina el ciclo principal de la simulación, preguntando si se desea continuar, si se quiere agregar una persona manualmente y avanzando el tiempo.
- `esperar()`: Pausa la simulación y espera interacción del usuario para continuar o finalizar.
- `preguntarAgregarPersona()`: Pregunta si el usuario quiere agregar manualmente una persona o generar una aleatoria.
- `generarPersona()`: Crea una nueva instancia de `Persona` con un destino aleatorio y origen fijo en 0.
- `avanzarMinuto()`: Avanza el tiempo de la simulación en un minuto.
- `main(String[] args)`: Punto de entrada del programa. Inicializa `Mundo` y comienza la simulación con `simular()`.

---

### AscensorSimuladorGUI (Interfaz Gráfica Swing)
**Recibe:** `Universidad`

---

#### Responsabilidades:

- Visualiza el estado de cada planta, ascensores y personas.
- Permite avanzar manualmente el tiempo minuto a minuto.
- Permite agregar personas automáticamente (aleatorio) o manualmente (origen y destino seleccionados).
- `actualizarVista()`: Refresca todos los componentes visuales de la interfaz con el estado más reciente.

---

### Tiempo

---

#### Responsabilidades:

- `avanzarMinuto()`: Avanza el tiempo un minuto, ajustando la hora si es necesario.
- `darLaHora()`: Devuelve una representación en formato HH:mm de la hora actual.
- `getHora()`: Retorna la hora actual como entero.
- `getMinuto()`: Retorna los minutos actuales como entero.
- `getFecha()`: Retorna la fecha actual del sistema (`LocalDate`).
- `esFinDeSemana()`: Indica si la fecha actual corresponde a sábado o domingo.
- `esFestivo()`: Informa si la fecha actual es un día festivo específico predefinido.

---
### Universidad  
**Recibe:** `Tiempo`, `Planta`, `Ascensor`, `Persona`

---

#### Responsabilidades:

- `Universidad(Tiempo)`: Constructor que inicializa las plantas, ascensores, y el sistema de control, vinculando todo con el tiempo actual.
- `estaAbierta()`: Verifica si la universidad está en horario de funcionamiento, considerando hora, fines de semana y festivos.
- `acogerPersona(Persona)`: Registra una nueva persona si la universidad está abierta, la asigna a su planta de origen y le permite llamar al ascensor.
- `acogerPersona(int origen, int destino)`: Sobrecarga para agregar una persona manual desde cualquier planta.
- `evolucionDeLaUniversidad()`: Gestiona el movimiento de los ascensores a través del controlador central.
- `imprimirEstado()`: Imprime el estado actual del edificio, mostrando personas esperando y en cada planta, así como la posición de los ascensores.
- `simular()`: Ejecuta una iteración completa de simulación, verificando si está abierta y luego llamando a `evolucionDeLaUniversidad()` y `imprimirEstado()`.

---

### Persona
**Recibe:** `Ascensor` (a través de `ControlAscensor`)

---

#### Responsabilidades:

- `llamarAlAscensor(ControlAscensor)`: Solicita un ascensor usando el controlador central, indicando planta origen y destino.
- `getPlantaOrigen()`: Devuelve la planta desde donde la persona inicia su viaje.
- `getPlantaDestino()`: Devuelve la planta a la que desea ir.
- `estaAtendido()`: Informa si la persona ya fue recogida por un ascensor.
- `marcarAtendido()`: Marca que la persona ya fue recogida por un ascensor.

---

### Ascensor
**Recibe:** `Persona`, `Llamada`

---

#### Responsabilidades:

- `asignarPlantas(List<Planta>)`: Conecta el ascensor con las plantas del edificio.
- `atenderLlamada(Llamada)`: Añade una llamada a la cola del ascensor.
- `mover()`: Lógica central para bajar personas, recoger nuevas y moverse según destino o llamadas.
- `bajarPersonasEnPlantaActual()`: Permite que las personas bajen si están en su planta destino.
- `recogerPersonasEnPlantaActual()`: Recoge personas que esperan en la planta actual, si hay espacio disponible.
- `moverHaciaDestinoPersona()`: Se mueve hacia la planta destino de la primera persona en la lista.
- `atenderLlamadaPendiente()`: Procesa la próxima llamada en la cola si no hay personas dentro.
- `buscarPlanta(int)`: Busca una planta en la lista por su número.
- `getId()`: Devuelve el identificador del ascensor.
- `getPlantaActualAsInt()`: Devuelve el número de la planta actual.
- `personasEnElAscensor()`: Retorna la cantidad de personas dentro del ascensor.
- `imprimirEstado()`: Imprime por consola el estado actual del ascensor.

---

### ControlAscensor
**Recibe:** `List<Ascensor>`

---

#### Responsabilidades:

- `procesarLlamada(Persona, int origen, int destino)`: Genera una nueva llamada a partir de una persona y sus plantas, y la asigna al ascensor más cercano.
- `seleccionarAscensor(int planta)`: Busca el ascensor más cercano a la planta de origen y lo retorna. (Uso interno).
- `moverAscensores()`: Ejecuta la función de mover para todos los ascensores registrados en el sistema.
- `imprimirEstadoAscensores()`: Muestra por consola el estado actual de todos los ascensores del sistema.

---

### Llamada
**Recibe:** `int plantaOrigen`, `int plantaDestino`, `Persona persona`

---

#### Responsabilidades:

- `getPlantaOrigen()`: Devuelve la planta desde donde se solicitó el ascensor.
- `getPlantaDestino()`: Devuelve la planta destino deseada por la persona.
- `getPersona()`: Retorna la persona que hizo la solicitud.
- `setPlantaOrigen(int)`: Permite modificar la planta de origen.
- `setPlantaDestino(int)`: Permite modificar la planta destino.
- `setPersona(Persona)`: Permite cambiar la persona asociada a la llamada.

---

### Planta
**Recibe:** `Persona`

---

#### Responsabilidades:

- `getNumero()`: Devuelve el número que identifica a la planta.
- `personaLlega(Persona)`: Registra a una persona que llegó a la planta y la agrega a la lista de espera.
- `personaEsperaAscensor(Persona)`: Agrega a una persona a la lista de espera del ascensor.
- `personaSubeAlAscensor(Persona)`: Elimina a una persona de la lista de espera y de la planta cuando sube al ascensor.
- `personaLlegaADestino(Persona)`: Registra que una persona ha llegado a su destino agregándola a la planta.
- `getCantidadEsperando()`: Retorna la cantidad de personas que están esperando el ascensor en esa planta.
- `getCantidadEnPlanta()`: Retorna la cantidad de personas que están en la planta (no necesariamente esperando).
- `getPersonasEsperando()`: Devuelve la lista de personas que están esperando ascensor en esa planta.

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
