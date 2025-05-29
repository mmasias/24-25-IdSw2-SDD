# Restaurante - Versión 2

| Diagrama de Clases |
|--------------------|
|![Diagrama](/ModeloDeDominio/imagenes/DiagramaDeClasesDM.svg)|

- El sistema `Restaurante` está organizado en paquetes siguiendo el patrón MVC y una capa de utilidades para funciones comunes.

- La clase principal `Main` inicia el programa y coordina el controlador `Restaurante_`, el cual maneja la lógica del sistema.

## Diferencias con la versión anterior (src-v001)

### Cambios en la arquitectura

1. **Implementación del patrón MVC (Modelo-Vista-Controlador)**:
   - **Modelo**: Clases que representan entidades del dominio (`Mesa`, `Reserva`, `Pedido`, `Plato`, `Personal`)
   - **Vista**: Clase encargada de mostrar información al usuario (`VistaConsola`)
   - **Controlador**: Clases que gestionan la lógica y flujo principal del programa (`Main`, `Restaurante_`)

2. **Estructura de paquetes**:
   - Código reorganizado en paquetes separados según su responsabilidad (`modelo`, `vista`, `controlador`, `util`)
   - Mejora la mantenibilidad y claridad del código

### Mejoras en cohesión

1. **Separación de responsabilidades**:
   - `VistaConsola`: Encargada exclusivamente de interactuar con el usuario por consola
   - `Restaurante_`: Gestiona la lógica principal del restaurante, como reservas, pedidos y gestión de mesas/personal
   - `Utilidades`: Reúne funciones reutilizables
   - `Constantes`: Centraliza los valores constantes del sistema

2. **Clases del modelo especializadas**:
   - Cada entidad (mesa, plato, pedido...) está representada por su propia clase con una única responsabilidad clara

### Reducción del acoplamiento

1. **Separación clara de capas**:
   - El controlador no depende directamente de los detalles de la vista o del modelo
   - Las dependencias están gestionadas de forma controlada desde `Main`

2. **Uso de composición en lugar de herencia innecesaria**:
   - Las relaciones entre clases se establecen mediante composición (atributos) y no por herencia

### Mejoras en el tamaño de los módulos

1. **Clases más pequeñas y con responsabilidades específicas**:
   - Cada clase del modelo se centra únicamente en representar su entidad
   - La lógica no se mezcla con la presentación

2. **Mayor facilidad de pruebas y mantenimiento**:
   - Las clases pueden evolucionar de forma independiente
   - El sistema es más extensible y fácil de modificar

### Otras mejoras

1. **Mayor extensibilidad**:
   - Preparado para añadir nuevas funciones como gestión de menús, pagos, etc.
   - La estructura facilita futuras ampliaciones sin romper lo existente

2. **Mejor mantenibilidad**:
   - Código más limpio, modular y comprensible
   - Separación de lógica, presentación y utilidades facilita el trabajo en equipo y las revisiones
