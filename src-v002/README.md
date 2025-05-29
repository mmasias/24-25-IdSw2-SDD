# Restaurante - Versión 2

| Diagrama de Clases |
|--------------------|
|![Diagrama](/ModeloDeDominio/imagenes/DiagramaDeClasesDM.svg)|

El sistema `Restaurante` está organizado en paquetes siguiendo el patrón MVC y una capa de utilidades para funciones comunes.

La clase principal `Main` inicia el programa y coordina el controlador `Restaurante_`, que maneja la lógica del sistema.

---

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
   - `VistaConsola`: Interactúa exclusivamente con el usuario por consola
   - `Restaurante_`: Gestiona la lógica principal del restaurante (reservas, pedidos, gestión de mesas y personal)
   - `Utilidades`: Contiene funciones reutilizables comunes
   - `Constantes`: Centraliza valores constantes del sistema

2. **Clases del modelo especializadas**:
   - Cada entidad (mesa, plato, pedido...) está representada por su propia clase con responsabilidades claras

### Reducción del acoplamiento

1. **Separación clara de capas**:
   - El controlador no depende directamente de detalles de la vista o modelo
   - Dependencias gestionadas de forma controlada desde `Main`

2. **Uso de composición en lugar de herencia innecesaria**:
   - Relaciones entre clases mediante composición (atributos), no por herencia

### Mejoras en tamaño y modularidad

1. **Clases más pequeñas y con responsabilidades específicas**:
   - Cada clase del modelo representa su entidad sin mezclar lógica y presentación

2. **Facilidad para pruebas y mantenimiento**:
   - Clases independientes para evolución y extensión sencilla

### Otras mejoras

1. **Mayor extensibilidad**:
   - Preparado para añadir nuevas funcionalidades (gestión de menús, pagos, etc.)

2. **Mejor mantenibilidad**:
   - Código limpio, modular y comprensible, facilita trabajo en equipo y revisiones

---

## Diseño modular - Versión 2 (src-v002)

### 📦 Estructura de paquetes

| Paquete        | Propósito                                                              |
|----------------|------------------------------------------------------------------------|
| `modelo`       | Clases que representan los datos del restaurante                       |
| `vista`        | Clase que muestra información y recibe entrada del usuario             |
| `controlador`  | Clases que gestionan la lógica y el flujo principal                    |
| `util`         | Utilidades y constantes compartidas                                   |

---

### 🧱 Clases por Módulo

#### Modelo

| Clase                                | Usa / Depende de          | Descripción                                                       |
|-------------------------------------|---------------------------|------------------------------------------------------------------|
| [`Mesa`](/src-v002/modelo/Mesa.java)           | -                         | Representa una mesa con número, ocupación, estado, etc.          |
| [`Reserva`](/src-v002/modelo/Reserva.java)     | `Mesa`                    | Datos de reserva asociada a una mesa                             |
| [`Pedido`](/src-v002/modelo/Pedido.java)       | `Mesa`, `Plato`           | Pedido de platos asociados a una mesa                            |
| [`Plato`](/src-v002/modelo/Plato.java)         | -                         | Datos del plato: nombre, precio, categoría                       |
| [`Personal`](/src-v002/modelo/Personal.java)   | -                         | Representa al personal del restaurante (camareros, cocineros)    |

#### Vista

| Clase                                         | Usa / Depende de          | Descripción                                                     |
|-----------------------------------------------|---------------------------|----------------------------------------------------------------|
| [`VistaConsola`](/src-v002/vista/VistaConsola.java) | `Constantes`              | Interacción con usuario mediante consola                       |

#### Controlador

| Clase                                          | Usa / Depende de                              | Descripción                                              |
|------------------------------------------------|-----------------------------------------------|----------------------------------------------------------|
| [`Main`](/src-v002/controlador/Main.java)               | `VistaConsola`, `Restaurante_`                 | Punto de entrada y control general de la aplicación      |
| [`Restaurante_`](/src-v002/controlador/Restaurante.java) | `Mesa`, `Reserva`, `Pedido`, `Personal`, `Utilidades` | Gestión central del restaurante (lógica y coordinación) |

#### Utilidades

| Clase                                          | Usa / Depende de          | Descripción                                                |
|------------------------------------------------|---------------------------|------------------------------------------------------------|
| [`Constantes`](/src-v002/util/Constantes.java)       | -                         | Almacena textos y valores constantes del sistema          |
| [`Utilidades`](/src-v002/util/Utilidades.java)       | -                         | Métodos reutilizables (validaciones, impresión, entrada)   |

---

### 🧠 Clase Principal

| Clase                                  | Usa / Depende de                | Descripción                                           |
|---------------------------------------|--------------------------------|-------------------------------------------------------|
| [`Main`](/src-v002/controlador/Main.java) | `VistaConsola`, `Restaurante_` | Ejecuta el programa y coordina el flujo de control    |

---

## Principios de Diseño Aplicados

| Principio         | Cumplimiento   | Comentarios                                              |
|-------------------|---------------|----------------------------------------------------------|
| Alta Cohesión     | ✅ Excelente   | Cada clase cumple una única responsabilidad              |
| Bajo Acoplamiento | ✅ Excelente   | Dependencias controladas y limitadas entre módulos       |
| Tamaño Adecuado   | ✅ Excelente   | Clases concisas y enfocadas en una tarea específica       |

---

## Análisis del Acoplamiento

- Comunicación mediante atributos claros (p. ej. Pedido contiene Platos).
- Acceso controlado a métodos públicos entre vista y controlador.
- Separación clara de responsabilidades entre modelo, vista y controlador.
- Centralización de funciones comunes en `Constantes` y `Utilidades`.

---

## Comparativa entre Versiones

| Aspecto          | Versión 1 (src-v001)            | Versión 2 (src-v002)                  |
|------------------|---------------------------------|-------------------------------------|
| Arquitectura     | Monolítica sin capas claras      | Patrón MVC bien definido             |
| Cohesión         | Media                          | Alta                               |
| Acoplamiento     | Alto                           | Bajo                               |
| Mantenibilidad   | Baja                           | Alta                               |
| Extensibilidad   | Limitada                       | Alta                               |
| Reutilización    | Escasa                         | Centralizada y efectiva             |

---

## Conclusiones y Mejoras de la Versión 2

- Separación explícita de responsabilidades con arquitectura MVC.
- Organización modular en paquetes para mejor comprensión y pruebas.
- Reducción del acoplamiento, facilitando cambios futuros.
- Centralización de funciones utilitarias para evitar duplicidades.
- Mayor especialización y cohesión en las clases.
- Código más limpio, mantenible y preparado para futuras extensiones.
