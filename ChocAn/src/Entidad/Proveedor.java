package Entidad;
public class Proveedor {
    
    private String nombre;
    private String email;
    private int numero;// 9 digitos
    private String direccion;
    private String ciudad;
    private int zip;
    
    public Proveedor(int numero, String nombre, String email, String direccion, String ciudad, int zip) {
        this.nombre = nombre;
        this.email = email;
        this.numero = numero;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.zip = zip;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}
