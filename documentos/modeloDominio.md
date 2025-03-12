# Diagrama de Clases

Este repositorio contiene el diagrama de clases para la simulación de un sistema de gestión de cajas y colas.

## Diagramas

![](/images/modelosUML/diagramaClases.svg)

![](/images/modelosUML/diagramaOnjetos.svg)

![](/images/modelosUML/diagramaEstadosJornada.svg)

![](/images/modelosUML/diagramaEstadosCaja.svg)

![](/images/modelosUML/diagramaEstadosCliente.svg)

## Descripción de Clases

- **Simulacion**: Controla el flujo del sistema.
- **Cola**: Almacena a los clientes en espera.
- **Caja**: Atiende a los clientes.
- **Estadisticas**: Registra información clave, como minutos sin cola, personas en cola al cierre, personas atendidas y artículos vendidos.
- **GestorCajas**: Gestiona el estado de las cajas.
- **Cliente**: Representa a los clientes en la simulación.
- **Item**: Representa los artículos que compra un cliente.
