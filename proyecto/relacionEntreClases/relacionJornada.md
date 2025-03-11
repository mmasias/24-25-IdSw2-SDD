# Relaciones entre Clases

En el sistema de simulación de un supermercado, las clases están relacionadas de la siguiente manera:

---

## 1. Relación entre `Simulacion` y `Cola`, `GestorCajas`, `Estadisticas`

- **Tipo de relación**: Composición.
- **Descripción**: La clase `GestorCajas` gestiona un conjunto de Caja, pero las `cajas` pueden existir independientemente del gestor.
- **Implementación en código**:

    ```java
    public class Simulacion {
        private Cola cola;
        private GestorCajas cajas;
        private Estadisticas estadis;

        public Simulacion() {
            this.cola = new Cola();
            this.cajas = new GestorCajas(4);
            this.estadis = new Estadisticas();
        }
    }

---

## 2. Relacion entre `GestorCajas` y `Caja`

- **Tipo de relación**: Agregacion.
- **Descripción**: La clase `Simulacion` es responsable de crear y gestionar las instancias de `Cola`, `GestorCajas` y `Estadisticas`. Estas instancias no tienen sentido fuera del contexto de la simulación.
- **Implementación en código**:

    ```java
    public class GestorCajas {
        private Caja[] cajas;

        public GestorCajas(int cantidad) {
            cajas = new Caja[cantidad];
            for (int i = 0; i < cantidad; i++) {
                cajas[i] = new Caja();
            }
        }
    }


## 3. Relación entre `Cola` y `Cliente`

- **Tipo de relación**: Composición.
- **Descripción**: La clase Cola almacena objetos de tipo Cliente. Los clientes no tienen sentido fuera de la cola en este contexto.
- **Implementación en código**:

    ```java
    public class Cola {
        private Queue<Cliente> clientes = new LinkedList<>();

        public void agregar(Cliente cliente) {
            clientes.add(cliente);
        }
    }


## 4. Relación entre `Caja` y `Cliente`

- **Tipo de relación**: Asociación.
- **Descripción**: La clase Caja atiende a un Cliente de manera temporal. El cliente no es propiedad de la caja, solo está asociado a ella durante el proceso de atención.
- **Implementación en código**:

    ```java
    public class Caja {
        private Cliente clienteActual;

        public void asignarCliente(Cliente cliente) {
        this.clienteActual = cliente;
        }
    }


## 5. Relación entre `GestorCajas` y `Estadisticas`

- **Tipo de relación**: Dependencia.
- **Descripción**: La clase GestorCajas depende de Estadisticas para registrar información sobre los clientes atendidos y los Productos procesados.
- **Implementación en código**:

    ```java

    public class GestorCajas {
        public void procesar(Cola cola, Estadisticas estadis) {
            for (Caja caja : cajas) {
                if (caja.estaLibre() && !cola.estaVacia()) {
                    Cliente cliente = cola.siguiente();
                    caja.asignarCliente(cliente);
                    estadis.clienteAtendido(cliente.getProductos());
                }
                caja.procesarMinuto();
            }
        }
    }


## 6. Relación entre `Cliente` y `Producto`

- **Tipo de relación**: Composición.
- **Descripción**: La clase Cliente está compuesta por una lista de Producto. Los Productos no tienen sentido fuera del contexto de un cliente.
- **Implementación en código**:

    ```java
    public class Cliente {
        private List<Producto> ProductosComprados;

        public Cliente() {
            this.ProductosComprados = new ArrayList<>();
            for (int i = 0; i < Productos; i++) {
                ProductosComprados.add(new Producto("Producto" + i, Math.random() * 10));
            }
        }
    }