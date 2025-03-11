# Diseño – Reto CCCF

Principios de legibilidad, nombrado consistente, modularidad y flexibilidad.


## ¿Qué?

El sistema simula y gestiona los siguientes aspectos:

- **Llegada de Clientes:** Con una probabilidad del 60%/minuto, se decide la entrada de un nuevo cliente a la cola.
- **Atención en Cajas:**
  - Se cuenta con 4 cajas que atienden clientes; cada cliente lleva entre 5 y 15 packs de items.
  - Cada pack se procesa en 1 minuto.
  - Si hay más de 15 personas en cola, se activa una caja adicional que atiende al menos a 5 clientes (o mientras la cola supere dicho umbral).
- **Gestión y Control:**
  - El sistema muestra, minuto a minuto, la llegada de personas, el número de personas en cola y el estado de las cajas.
  - Un rol de superadministrador permite encender o apagar cajas (hasta 6).
- **Resumen Final:** Al concluir la jornada se presentan estadísticas: minutos sin cola, personas en cola al final del día, clientes atendidos y items vendidos.


## ¿Para qué?

El sistema permitirá:

- **Visualizar la simulación** del flujo de clientes y la atención en tiempo real.
- **Evaluar el rendimiento** operativo del centro comercial.
- **Probar escenarios** de carga variada y estrategias de atención, permitiendo ajustes (por ejemplo, habilitar cajas adicionales).
- **Facilitar la toma de decisiones** mediante datos consolidados al final de la jornada.



### Consideraciones de Diseño

- **Legibilidad y Nombrado:**
  Utilizar nombres descriptivos y coherentes para mejorar la comprensión del código.

- **Modularidad y Consistencia:**
  Separar de responsabilidades en clases como Cliente, Caja y Cola.

- **Composición sobre Herencia:**
  En lugar de usar herencia, obtar por objetos que colaboran en la simulación, permitiendo modificar dinámicamente la cantidad de cajas activas.

- **Flexibilidad y Extensibilidad:**
  Incorporación de cambios en la lógica de llegada de clientes sin afectar la estructura global del sistema.

---

## Propuesta de Implementación

1. **Crear Clases Específicas:**
   Implementar clases separadas para `Cliente`, `Caja`, `Cola` y `Jornada`.

2. **Separar la Lógica de Simulación:**
   La clase `Jornada` se encargará de:
   - Iniciar la jornada con una duración y gestionar cajas y cola.
   - Procesar cada minuto determinando la llegada de clientes, asignaciones a cajas y procesamiento de atención.

3. **Incorporar el Rol de SuperAdministrador:**
   Permitir mediante una interfaz o menú que el administrador pueda activar o desactivar cajas en tiempo real (hasta 6 cajas).
