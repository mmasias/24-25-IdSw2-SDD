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

        for(int i = 1; i < 4; i++){
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
}
