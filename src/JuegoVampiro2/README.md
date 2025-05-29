# Diseño modular - Juego de Vampiros

<div align=center>

[![Diagrama de Clases](/images/modelosUML/Entrega2/Iteracion2/Diagrama_Entrega2.svg)](/images/modelosUML/Entrega2/Iteracion2/diagrama.puml)

</div>

# 1. **Descripción del Proyecto**

El Juego de Vampiros es un proyecto que implementa un sistema de combate por turnos donde el jugador controla a un Guerrero que debe enfrentarse a un Vampiro en una batalla estratégica. El sistema aprovechanda conceptos como herencia, polimorfismo y encapsulamiento.

La arquitectura del juego está diseñada con un enfoque modular, separando claramente las responsabilidades en tres paquetes principales:

- **auth**: Maneja la autenticación y gestión de usuarios
- **core**: Contiene la lógica central del juego
- **ui**: Gestiona la interfaz de usuario y la interacción con el jugador

### Estructura de Paquetes

**📂 auth - Gestión de Usuarios**
| Clase | Descripción |
|-------|-------------|
| [GestorUsuarios](/src/JuegoVampiro2/auth/GestorUsuarios.java) | Administra el registro y autenticación de usuarios |

**📂 core - Lógica del Juego**
| Clase | Descripción |
|-------|-------------|
| [Personaje](/src/JuegoVampiro2/core/Personaje.java) | Clase abstracta base para todos los personajes del juego |
| [Ataque](/src/JuegoVampiro2/core/Ataque.java) | Clase base para todos los tipos de ataques disponibles |
| [Arma](/src/JuegoVampiro2/core/Arma.java) | Implementa los ataques específicos del Guerrero |
| [Mordida](/src/JuegoVampiro2/core/Mordida.java) | Implementa los ataques específicos del Vampiro |
| [Pocion](/src/JuegoVampiro2/core/Pocion.java) | Gestiona el estado y efectos de las pociones curativas |
| [Guerrero](/src/JuegoVampiro2/core/Guerrero.java) | Representa al héroe controlado por el jugador |
| [Vampiro](/src/JuegoVampiro2/core/Vampiro.java) | Representa al enemigo vampiro controlado por la IA |
| [Batalla](/src/JuegoVampiro2/core/Batalla.java) | Controla la lógica del combate por turnos |
| [JuegoVampiros](/src/JuegoVampiro2/core/JuegoVampiros.java) | Clase principal que gestiona todo el flujo del juego |

**📂 ui - Interfaz de Usuario**
| Clase | Descripción |
|-------|-------------|
| [VistaConsola](/src/JuegoVampiro2/ui/VistaConsola.java) | Maneja toda la interacción con el usuario a través de la consola |
| [CredencialesUsuario](/src/JuegoVampiro2/ui/CredencialesUsuario.java) | Encapsula los datos de usuario para login/registro |

### Diagrama de Dependencias

El diseño modular sigue un patrón de dependencia de capas donde:
- La capa core no depende de ui ni auth (no conoce la interfaz ni la gestión de usuarios)
- La capa ui utiliza las entidades de core para mostrar información del juego
- La clase JuegoVampiros actúa como orquestador, coordinando las tres capas


# 2. **Cambios Realizados**

## · Cambios respecto a la entrega1

### 1. División en subcarpetas.
Para separar las responsabilidades, hemos dividido el código en auth, para la gestión de usuarios, core, para la lógica del juego, y ui, para la interfaz del usuario.

### 2.  **Extracción de Responsabilidades:**
  *   **Gestión de Usuarios:** La lógica de login y registro, junto con el almacenamiento de usuarios (antes en `JuegoVampiros`), se movió a la nueva clase `GestorUsuarios` en el paquete `auth`.
  *   **Interfaz de Usuario:** Toda la interacción con la consola (mostrar menús, mensajes, leer entradas) se extrajo de `JuegoVampiros`, `Batalla` y `Guerrero` a la nueva clase `VistaConsola` en el paquete `ui`. Se creó la clase auxiliar `CredencialesUsuario` (`ui`) para pasar datos de login/registro.

### 3.  Refactorización de Clases Existentes (y movimiento al paquete `core`).
  1. **`JuegoVampiros`:** Se transformó en la clase controladora principal. Ya no maneja directamente la interfaz ni los usuarios, sino que delega estas tareas a otras clases (`VistaConsola` y `GestorUsuarios`) . Orquesta el flujo general de la aplicación.
  2. **`Batalla`:** Se eliminó toda interacción directa con la consola y su método `main` recibiendo ahora una instancia de `VistaConsola` en su constructor para poder mostrar información y obtener las entradas indirectamente; pasando a ser su funcion unicamente centrarse en la lógica del combate
  3. **`Guerrero`:** Se eliminó la lectura directa mediante `Scanner` y la impresión en consola. La selección de acciones ahora es llebada acabo por `Batalla` mediante el uso de `VistaConsola`. Tambien se añadieron los métodos `setAccionActual` y `quiereAtacar`entre otros para gestionar el estado de la acción elegida.
  4. **`Personaje`:** Se eliminaron las impresiones directas en consola como son los mensajes del desmayo y la recuperacion recayendo estos en `Batalla`.
  5. **`Vampiro`, `Ataque`, `Arma`, `Mordida`, `Pocion`:** Se movieron al paquete `core` y se actualizaron sus declaraciones de paquete y se elimina el codigo relacionado a la UI y se añaden métodos nuevos o modifican los ya presentes para encajar con la estructura presente

  ## · Cambios post revisión del 29/05

  - Actualizacion del diagrama .puml
  