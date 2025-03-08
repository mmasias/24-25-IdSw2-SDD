package pyAscensores;

import java.util.ArrayList;
import java.util.List;

public class Universidad {
    private List<Planta> plantas;
    private List<Ascensor> ascensores;
    private List<Persona> personas;

    public Universidad(){
        plantas = new ArrayList<Planta>();
        ascensores = new ArrayList<Ascensor>();
        personas = new ArrayList<Persona>();

        for(int i = -3; i <= 3; i++){
            plantas.add(new Planta(i));
        }
        

        ascensores.add(new Ascensor(6));
        ascensores.add(new Ascensor(6));
    }

    public void acogerPersona(Persona persona){
        personas.add(persona);
        this.llamarAscensor(persona);
    }

    public void llamarAscensor(Persona p) {
        Ascensor mejor = null;
        int distanciaMinima = Integer.MAX_VALUE;
        
        for (Ascensor ascensor : ascensores) {
            int distancia = Math.abs(ascensor.getPlantaActual() - p.getPlantaActual());
            if (distancia < distanciaMinima && ascensor.puedeRecogerPersona(p)) {
                distanciaMinima = distancia;
                mejor = ascensor;
            }
        }
        
        if (mejor != null) {
            mejor.recogerPersona(p);
        }
    }

    public void imprimirEstado() {
        System.out.println("Personas                                    Personas");
        System.out.println("          esperando                                    en la planta\n");
        
        for (int i = 3; i >= -3; i--) {
            Planta planta = plantas.get(i + 3);
            String esperando = planta.getPersonasEsperando();
            String ascensor1 = ascensores.get(0).representacion(i);
            String ascensor2 = ascensores.get(1).representacion(i);
            String enPlanta = planta.getPersonasEnPlanta();
            
            System.out.printf("Planta %2d    %-5s      | |    %-5s    | |    %-5s    | |    %-5s\n", i, esperando, ascensor1, ascensor2, enPlanta);
        }
        System.out.println("                       /--------- Ascensores ------/");
    }
}
