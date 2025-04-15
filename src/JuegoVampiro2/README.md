# Diseño modular - Juego de Vampiros

<div align=center>

[![Diagrama de Clases](/images/modelosUML/Entrega1/Diagrama_Entrega1.svg)](/images/modelosUML/Entrega1/diagrama.puml)

</div>

## 1. **Descripción del Proyecto**

El Juego de Vampiros es un proyecto que implementa un sistema de combate por turnos donde el jugador controla a un Guerrero que debe enfrentarse a un Vampiro en una batalla estratégica. El sistema está desarrollado con un enfoque orientado a objetos en Java, aprovechando conceptos como herencia, polimorfismo y encapsulamiento.
La arquitectura del juego está diseñada para ser modular y extensible, permitiendo añadir fácilmente nuevos tipos de personajes, armas y habilidades en futuras versiones.

### Clases

**🧬 Clases Base**
| Clase | Descripción |
|-------|-------------|
| [Personaje](/src/JuegoVampiro/Personaje.java) | Clase abstracta base para todos los personajes del juego |
| [Ataque](/src/JuegoVampiro/Ataque.java) | Clase base para todos los tipos de ataques disponibles |

**🧱 Nivel Básico**
| Clase | Depende de / Usa | Descripción |
|-------|------------------|-------------|
| [Arma](/src/JuegoVampiro/Arma.java) | `Ataque` | Implementa los ataques específicos del Guerrero |
| [Mordida](/src/JuegoVampiro/Mordida.java) | `Ataque` | Implementa los ataques específicos del Vampiro |
| [Pocion](/src/JuegoVampiro/Pocion.java) | - | Gestiona el estado y efectos de las pociones curativas |

**🧩 Nivel Medio**
| Clase | Depende de / Usa | Descripción |
|-------|------------------|-------------|
| [Guerrero](/src/JuegoVampiro/Guerrero.java) | `Personaje`, `Arma`, `Pocion` | Representa al héroe controlado por el jugador |
| [Vampiro](/src/JuegoVampiro/Vampiro.java) | `Personaje`, `Mordida` | Representa al enemigo vampiro controlado por la IA |

**🧠 Nivel Alto**
| Clase | Depende de / Usa | Descripción |
|-------|------------------|-------------|
| [Batalla](/src/JuegoVampiro/Batalla.java) | `Guerrero`, `Vampiro` | Controla la lógica del combate por turnos |
| [JuegoVampiros](/src/JuegoVampiro/JuegoVampiros.java) | `Batalla`, `Guerrero`, `Vampiro` | Clase principal que gestiona todo el flujo del juego |

## 2. **Cambios Realizados**

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
