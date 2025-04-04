# Responsabilidades
## Mundo
Recibe `Universidad` y `Tiempo`
+ simular
+ esperar
+ generarPersona
+ avanzarMinuto
## Tiempo
+ darFormato (antiguo toString)
+ darLaHora
## Universidad
Recibe `Tiempo`, `Planta`, `Ascensor` y `Persona`
+ estaAbierta
+ acogerPersona
+ actualizarEstado
+ imprimirEstado
## Persona
Recibe `Ascensor`?
+ llamarAlAscensor
+ subirAlAscensor
+ seleccionarPlantaDestino
+ bajarDelAscensor
+ decidirComoMoverse (moverse en ascensor o por las escaleras)
## Ascensor
Recibe `Persona`?
+ estaLleno
+ atenderLlamadas
+ plantaActual
+ moverse
## Planta 
Recibe `Persona`?
+ personasEnPlanta
+ personasEsperando



