# Simulación de Ascensores en la Universidad 🏢🚪

Este proyecto es una simulación por consola de un sistema de ascensores que operan dentro de un edificio universitario de varias plantas. La simulación modela el comportamiento de personas que entran, suben a distintas plantas, permanecen un tiempo y luego deben salir por la planta baja utilizando los ascensores.

---

## 🎯 ¿Cuál es el objetivo?

- Simular el flujo diario de personas en una universidad con múltiples ascensores.
- Modelar la lógica de recogida y bajada de pasajeros, gestión de llamadas y comportamiento horario.
- Mostrar gráficamente el estado del edificio en cada minuto de la jornada mediante una representación en consola.

---

## 🕘 ¿Cómo funciona?

- La simulación avanza **minuto a minuto** desde una hora configurada de inicio.
- La universidad **abre a las 09:00** y **cierra a las 21:00**.
- Personas nuevas solo pueden entrar entre las 09:00 y las 21:00.
- Cada persona:
  - Llega por la planta 0.
  - Sube a una planta aleatoria distinta de 0.
  - Permanece allí entre 5 y 15 minutos.
  - Luego, solicita volver a la planta 0 para salir del edificio.
- Una vez cerrada la universidad:
  - No se aceptan nuevas entradas.
  - Los ascensores **siguen funcionando** hasta que no quede nadie dentro.

---

## 🛠 ¿Qué puedes modificar?

Puedes cambiar estos aspectos para adaptar la simulación:

| Parámetro                      | Ubicación             | Descripción |
|-------------------------------|------------------------|-------------|
| Hora de inicio del día        | `Mundo.java`          | Líneas `HORA_INICIO_DIA`, `MINUTO_INICIO`. |
| Hora de apertura/cierre       | `Tiempo.java`         | Constantes `HORA_APERTURA`, `HORA_CIERRE`. |
| Probabilidad de llegada       | `Universidad.java`    | Constante `PROBABILIDAD_INGRESO`. |
| Tiempo de estancia de personas| `Persona.java`        | Constantes `MIN_TIEMPO_ESTANCIA`, `MAX_TIEMPO_ESTANCIA`. |
| Número de plantas             | `Piso.java`           | Constantes `MIN_PISO` y `MAX_PISO`. |

---

## 📺 ¿Cómo se representa la universidad?

La consola muestra una vista vertical del edificio como esta:

