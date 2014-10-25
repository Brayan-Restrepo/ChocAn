package Entidad;

import java.util.Calendar;

public class Factura {
    
    private String fechaActual;
    private String fechaPrestacionServicio;
    private int numero;     //numero: numero de la factura
    private int numeroProveedor;    //9 digitos
    private int numeroMiembro;      //9 Digitos
    private int numeroServicio;     //6 Digitos
    private String comentario;      //Max 100 Caracteres
    
    
    public Factura(int numero, int numeroProveedor, int numeroMiembro, int numeroServicio, String comentario, String fechaActual, String fechaPrestacionServicio) {
        this.fechaActual = fechaActual;
        this.fechaPrestacionServicio = fechaPrestacionServicio;
        this.numero = numero;
        this.numeroProveedor = numeroProveedor;
        this.numeroMiembro = numeroMiembro;
        this.numeroServicio = numeroServicio;
        this.comentario = comentario;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getFechaPrestacionServicio() {
        return fechaPrestacionServicio;
    }

    public void setFechaPrestacionServicio(String fechaPrestacionServicio) {
        this.fechaPrestacionServicio = fechaPrestacionServicio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumeroProveedor() {
        return numeroProveedor;
    }

    public void setNumeroProveedor(int numeroProveedor) {
        this.numeroProveedor = numeroProveedor;
    }

    public int getNumeroMiembro() {
        return numeroMiembro;
    }

    public void setNumeroMiembro(int numeroMiembro) {
        this.numeroMiembro = numeroMiembro;
    }

    public int getCodigoServicio() {
        return numeroServicio;
    }

    public void setCodigoServicio(int numeroServicio) {
        this.numeroServicio = numeroServicio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
