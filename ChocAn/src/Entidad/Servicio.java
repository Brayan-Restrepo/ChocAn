package Entidad;

public class Servicio {
    
    private String nombre;
    private int numero;//6 Digitos
    private double tarifa;//Max 999,99

    public Servicio( int numero, String nombre, double tarifa) {
        this.nombre = nombre;
        this.numero = numero;
        this.tarifa = tarifa;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public double getTarifa() {
        return tarifa;
    }
    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }
   
    
}
