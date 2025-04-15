## 1. División en subcarpetas.
Para separar las responsabilidades, hemos dividido el código en auth, para la gestión de usuarios, core, para la lógica del juego, y ui, para la interfaz del usuario.

2.  **Extracción de Responsabilidades:**
  *   **Gestión de Usuarios:** La lógica de login y registro, junto con el almacenamiento de usuarios (antes en `JuegoVampiros`), se movió a la nueva clase `GestorUsuarios` en el paquete `auth`.
  *   **Interfaz de Usuario:** Toda la interacción con la consola (mostrar menús, mensajes, leer entradas) se extrajo de `JuegoVampiros`, `Batalla` y `Guerrero` a la nueva clase `VistaConsola` en el paquete `ui`. Se creó la clase auxiliar `CredencialesUsuario` (`ui`) para pasar datos de login/registro.

## 3.  Refactorización de Clases Existentes (y movimiento al paquete `core`).
  1. **`JuegoVampiros`:** Se transformó en la clase controladora principal. Ya no maneja directamente la interfaz ni los usuarios, sino que delega estas tareas a otras clases (`VistaConsola` y `GestorUsuarios`) . Orquesta el flujo general de la aplicación.
  2. **`Batalla`:** Se eliminó toda interacción directa con la consola y su método `main` recibiendo ahora una instancia de `VistaConsola` en su constructor para poder mostrar información y obtener las entradas indirectamente; pasando a ser su funcion unicamente centrarse en la lógica del combate
  3. **`Guerrero`:** Se eliminó la lectura directa mediante `Scanner` y la impresión en consola. La selección de acciones ahora es llebada acabo por `Batalla` mediante el uso de `VistaConsola`. Tambien se añadieron los métodos `setAccionActual` y `quiereAtacar`entre otros para gestionar el estado de la acción elegida.
  4. **`Personaje`:** Se eliminaron las impresiones directas en consola como son los mensajes del desmayo y la recuperacion recayendo estos en `Batalla`.
  5. **`Vampiro`, `Ataque`, `Arma`, `Mordida`, `Pocion`:** Se movieron al paquete `core` y se actualizaron sus declaraciones de paquete y se elimina el codigo relacionado a la UI y se añaden métodos nuevos o modifican los ya presentes para encajar con la estructura presente
