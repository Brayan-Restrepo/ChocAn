package Entidad;

public class Miembro {
    
    private String nombre, email;
    private int numero;//Nueve digito
    private String direccion;
    private String ciudad;
    private int zip;
    private boolean estado; //true:Activo; false:suspendido
   

    public Miembro(int numero, String nombre, String email, String direccion, String ciudad, int zip, String estado) {
        this.nombre = nombre;
        this.email = email;
        this.numero = numero;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.zip = zip;
        this.setEstado(estado);
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
    public String getEstado()   {
        if(this.estado==true)   {
            return "Activado";
        }else{
            return "Suspendido";
        }
    }

    public void setEstado(String estado) {
        if(estado.equalsIgnoreCase("Activado")){
            this.estado = true;
        }else{
            this.estado=false;
        }  
    }
    
}
