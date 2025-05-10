# PyAscensores


## Principios de POO aplicados

- **Abstracción**: Clases como `Ascensor`, `Persona`, `Planta`, `Tiempo` modelan conceptos del mundo real.
- **Encapsulación**: Uso de atributos privados y métodos controlados.
- **Herencia**: `TiempoSimulado` extiende de `Tiempo` para especialización.
- **Polimorfismo**: Interfaces como `ITiempo`, `EstadoAscensor`, `Movible`.

## Principios SOLID aplicados

| Principio | Descripción | Cumplimiento |
|----------|-------------|--------------|
| **S** - Single Responsibility | Cada clase tiene una sola responsabilidad clara.
| **O** - Open/Closed | Nuevos movimientos del ascensor se pueden agregar sin modificar `Ascensor`.
| **L** - Liskov Substitution | `TiempoSimulado` puede sustituir a `Tiempo`. 
| **I** - Interface Segregation | Interfaces separadas para `Movible`, `Simulable`, `ITiempo`.
| **D** - Dependency Inversion | `Universidad` depende de interfaces (`ITiempo`), no de clases concretas. 

### Total cumplimiento estimado: **94% SOLID**

## Arquitectura

- `Ascensor` usa estrategia (`EstadoAscensor`) para moverse.
- `Universidad` orquesta toda la simulación y recibe dependencias por constructor.
- Interfaces ligeras permiten testeo y extensión sin acoplar código.
