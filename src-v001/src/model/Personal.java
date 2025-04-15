package model;

public class Personal {
    private String nombre;
    private String rol;
    private int horasTrabajo;

    public Personal(String nombre, String rol, int horasTrabajo) {
        this.nombre = nombre;
        this.rol = rol;
        this.horasTrabajo = horasTrabajo;
    }

    public String getNombre() { return nombre; }
    public String getRol() { return rol; }
    public int getHorasTrabajo() { return horasTrabajo; }

    @Override
    public String toString() {
        return nombre + " - Rol: " + rol + " - Horas de trabajo: " + horasTrabajo;
    }
}
