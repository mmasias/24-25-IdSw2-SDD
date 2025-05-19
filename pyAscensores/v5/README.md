
## Descripción General

El simulador modela un edificio de universidad con 7 plantas (desde -3 hasta 3, donde 0 es la planta baja) y varios ascensores que transportan personas entre ellas. El sistema sigue un patrón de arquitectura Modelo-Vista-Controlador (MVC) para separar la lógica de negocio de la interfaz de usuario.

## Estructura del Proyecto

El proyecto está organizado en tres paquetes principales:

### Paquete `modelo`

Contiene las clases e interfaces que representan los elementos fundamentales de la simulación:

- `Universidad`: Implementa `IEdificio` y gestiona las plantas, ascensores y personas.
- `Ascensor`: Implementa `ITransporte` y representa un ascensor con capacidad limitada.
- `Persona`: Implementa `IPersona` y define una persona con un origen y destino.
- `Planta`: Implementa `IPlanta` y representa una planta del edificio.
- `TiempoSimulado`: Implementa `ITiempo` y maneja el paso del tiempo en la simulación.

Interfaces:
- `IEdificio`: Define comportamientos para el edificio.
- `ITransporte`: Define comportamientos para los medios de transporte.
- `IPersona`: Define propiedades básicas de una persona.
- `IPlanta`: Define comportamientos para una planta.
- `ITiempo`: Define comportamientos para el manejo del tiempo.

### Paquete `vista`

Contiene las clases que muestran la información de la simulación:

- `AscensorVista`: Muestra información específica de un ascensor.
- `EdificioVista`: Muestra el estado general del edificio.
- `PersonaVista`: Muestra información de una persona.
- `PlantaVista`: Muestra información de una planta.
- `TiempoVista`: Muestra información sobre el tiempo.
- `TransporteVista`: Muestra información genérica de un transporte.
- `UniversidadVista`: Muestra una representación visual de la universidad.
- `SimuladorAscensores`: Coordina la visualización de la simulación completa.
- `VistaFactory`: Fábrica para crear vistas según el tipo de objeto.

Interfaces:
- `IVista`: Define el comportamiento básico de todas las vistas.

### Paquete `controlador`

El proyecto también utiliza un controlador para coordinar el modelo y la vista:

- `ControladorSimulacion`: Gestiona el flujo de la simulación (referenciado pero no incluido en los archivos).

## Características Principales

- **Simulación en tiempo real**: El sistema puede funcionar en modo automático o manual.
- **Visualización detallada**: Muestra el estado de las plantas, ascensores y personas.
- **Comportamiento realista**: Los ascensores siguen patrones lógicos, como cambiar de dirección cuando están llenos o vacíos.
- **Gestión temporal**: Simula el paso del tiempo con horarios comerciales.
- **Generación aleatoria de personas**: Las personas aparecen aleatoriamente en el edificio.

## Algoritmos Clave

### Movimiento de Ascensores

Los ascensores siguen estas reglas básicas:
- Cambian de dirección al llegar a los extremos del edificio.
- Tienden a ir a la planta baja (planta 0) cuando están vacíos.
- Adaptan su dirección a los destinos de los pasajeros.

### Gestión de Personas

- Las personas tienen un origen y un destino.
- Al final del día, las personas tienden a moverse hacia la planta baja para salir.
- Durante el día, existe una probabilidad de movimiento interno entre plantas.


## Visualización

El simulador muestra:
- Plantas y su numeración (-3 a 3, siendo 0 la planta baja)
- Personas esperando en cada planta
- Posición y estado de los ascensores
- Ocupación de los ascensores
- Hora actual y estado del edificio (abierto/cerrado)

Ejemplo de visualización:

```
          Personas                                    Personas
        esperando                                    en la planta
Planta  3    _____   | |     | |     | |         _____
Planta  2    _____   | |     | |     | |         _____
Planta  1    ___3_   | |     | |     | |         ___5_
Planta  B    _____   [=0=]   | |     [v4v]       __12__
Planta -1    ___2_   | |     | |     | |         ___4_
Planta -2    _____   | |     [^1^]   | |         _____
Planta -3    _____   | |     | |     | |         _____
                   /--------- Ascensores ------/
Tiempo: Día 1 - 15:30
```

## Resumen de la Arquitectura

El proyecto sigue una arquitectura MVC (Modelo-Vista-Controlador):

- **Modelo**: Clases en el paquete `modelo` que representan el estado del sistema.
- **Vista**: Clases en el paquete `vista` que muestran el estado al usuario.
- **Controlador**: Coordina el modelo y la vista, procesando las entradas del usuario.

