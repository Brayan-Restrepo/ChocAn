/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entidad.ChocAn;
import Entidad.Factura;
import Entidad.Miembro;
import Entidad.Proveedor;
import Entidad.Servicio;
import Limite.InterfazPrincipalProveedor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brayan
 */
public class ControlChocan {

    public void leerArchivo(String ruta) {

        String[][] archivo;
        String[] aux;

        File f;
        FileReader fr;
        BufferedReader br;

        try {
            File fruta = new File("");

            f = new File(fruta.getCanonicalPath() + "\\src\\Archivos\\" + ruta + ".txt");

            fr = new FileReader(f);
            br = new BufferedReader(fr);

            String tem;
            if (ruta.equalsIgnoreCase("Miembro") || ruta.equalsIgnoreCase("Proveedor") || ruta.equalsIgnoreCase("servicio")) {
                while ((tem = br.readLine()) != null) {
                    char[] linea = tem.toCharArray();
                    String numero = "", nombre = "", email = "", direccion = "", ciudad = "", zip = "";
                    String estado="";
                    int k = 0;
                    for (int j = 0; j < tem.length(); j++) {
                        if (k == 0 && linea[j] != ',') {
                            numero += linea[j];
                        } else if (k == 1 && linea[j] != ',') {
                            nombre += linea[j];
                        } else if (k == 2 && linea[j] != ',') {
                            email += linea[j];
                        } else if (k == 3 && linea[j] != ',') {
                            direccion += linea[j];
                        } else if (k == 4 && linea[j] != ',') {
                            ciudad += linea[j];
                        } else if (k == 5 && linea[j] != ',') {
                            zip += linea[j];
                        } else if(k == 6 && linea[j] != ',') {
                            estado+=linea[j];
                        }else{
                            k++;
                        }
                        
                    }

                    if (ruta.equalsIgnoreCase("Miembro")) {
                        ChocAn.listaMiembro.add(new Miembro(Integer.valueOf(numero), nombre, email, direccion, ciudad, Integer.valueOf(zip), estado));
                    } else if (ruta.equalsIgnoreCase("Proveedor")) {
                        ChocAn.listaProveedor.add(new Proveedor(Integer.valueOf(numero), nombre, email, direccion, ciudad, Integer.valueOf(zip)));
                    } else if (ruta.equalsIgnoreCase("servicio")) {
                        ChocAn.listaServicio.add(new Servicio(Integer.valueOf(numero), nombre, Double.valueOf(email)));// Email = Tarifa
                    }
                }
            }
            if (ruta.equalsIgnoreCase("Factura")) {
                while ((tem = br.readLine()) != null) {
                    char[] linea = tem.toCharArray();
                    String numeroFactura = "", numeroMiembro = "", numeroProveedor = "", numeroServicio = "", comentario = "", fechaSistema = "", fecha = "";
                    int k = 0;
                    for (int j = 0; j < tem.length(); j++) {
                        if (k == 0 && linea[j] != ',') {
                            numeroFactura += linea[j];
                        } else if (k == 1 && linea[j] != ',') {
                            numeroMiembro += linea[j];
                        } else if (k == 2 && linea[j] != ',') {
                            numeroProveedor += linea[j];
                        } else if (k == 3 && linea[j] != ',') {
                            numeroServicio += linea[j];
                        } else if (k == 4 && linea[j] != ',') {
                            comentario += linea[j];
                        } else if (k == 5 && linea[j] != ',') {
                            fechaSistema += linea[j];
                        } else if (k == 6 && linea[j] != ',') {
                            fecha += linea[j];
                        } else {
                            k++;
                        }
                    }
                    ChocAn.listaFactura.add(new Factura(Integer.valueOf(numeroFactura), Integer.valueOf(numeroProveedor), Integer.valueOf(numeroMiembro), Integer.valueOf(numeroServicio), comentario, fechaSistema, fecha));
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     Cuenta cuantas lineas tiene un archivo
     */
    public int contarLinasArchivo(BufferedReader br) {
        //Cuenta cuantas lineas tiene el archivo
        int i = 0;

        try {
            while (br.readLine() != null) {
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Retorna el numero de lineas de un archivo
        return i;
    }

    public int contarLinasArchivo(String ruta) {

        //Cuenta cuantas lineas tiene el archivo
        int i = 0;
        try {
            File f = new File(ruta);
            FileReader fr;
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while (br.readLine() != null) {
                i++;
            }

            //Retorna el numero de lineas de un archivo
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    /*
     Metodo encargado de buscar un Codigo de la lista y luego eliminarlo
     */

    public void eliminarCodigo(int codigo) {

        if (codigo >= 170000000) {
            for (int i = 0; i < ChocAn.listaMiembro.size(); i++) {
                int j = ChocAn.listaMiembro.get(i).getNumero();
                if (j == codigo) {
                    ChocAn.listaMiembro.remove(ChocAn.listaMiembro.get(i));
                    break;
                }
            }
        }
        if (codigo >= 160000000) {
            for (int i = 0; i < ChocAn.listaProveedor.size(); i++) {
                int j = ChocAn.listaProveedor.get(i).getNumero();
                if (j == codigo) {
                    ChocAn.listaProveedor.remove(ChocAn.listaProveedor.get(i));
                    break;
                }
            }
        }
    }

    public String[] buscar(int numero) {
        String[] buscar = new String[7];
        if (numero >= 170000000) {
            for (int i = 0; i < ChocAn.listaMiembro.size(); i++) {
                if (ChocAn.listaMiembro.get(i).getNumero() == numero) {
                    buscar[0] = String.valueOf(ChocAn.listaMiembro.get(i).getNumero());
                    buscar[1] = String.valueOf(ChocAn.listaMiembro.get(i).getNombre());
                    buscar[2] = String.valueOf(ChocAn.listaMiembro.get(i).getEmail());
                    buscar[3] = String.valueOf(ChocAn.listaMiembro.get(i).getEstado());
                    buscar[4] = String.valueOf(ChocAn.listaMiembro.get(i).getDireccion());
                    buscar[5] = String.valueOf(ChocAn.listaMiembro.get(i).getCiudad());
                    buscar[6] = String.valueOf(ChocAn.listaMiembro.get(i).getZip());
                }
            }
        }
        if (numero >= 160000000 && numero < 170000000) {
            for (int i = 0; i < ChocAn.listaProveedor.size(); i++) {
                if (ChocAn.listaProveedor.get(i).getNumero() == numero) {
                    buscar[0] = String.valueOf(ChocAn.listaProveedor.get(i).getNumero());
                    buscar[1] = String.valueOf(ChocAn.listaProveedor.get(i).getNombre());
                    buscar[2] = String.valueOf(ChocAn.listaProveedor.get(i).getEmail());
                    buscar[3] = "";
                    buscar[4] = String.valueOf(ChocAn.listaProveedor.get(i).getDireccion());
                    buscar[5] = String.valueOf(ChocAn.listaProveedor.get(i).getCiudad());
                    buscar[6] = String.valueOf(ChocAn.listaProveedor.get(i).getZip());
                }
            }
        }
        return buscar;
    }

    /*
     Metodo encargaado en determinar cual es el codigo sigiente
     */
    public int codigo(String persona) {
        int codigo = 0;

        if (persona.equalsIgnoreCase("Miembro")) {
            for (int i = 0; i < ChocAn.listaMiembro.size(); i++) {
                if (codigo < ChocAn.listaMiembro.get(i).getNumero()) {
                    codigo = ChocAn.listaMiembro.get(i).getNumero();
                }
            }
        } else if (persona.equalsIgnoreCase("Proveedor")) {
            for (int i = 0; i < ChocAn.listaProveedor.size(); i++) {
                if (codigo < ChocAn.listaProveedor.get(i).getNumero()) {
                    codigo = ChocAn.listaProveedor.get(i).getNumero();
                }
            }
        }

        return codigo + 1;
    }

    public void llenarLista(DefaultTableModel modelo) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (Integer.valueOf(modelo.getValueAt(i, 0).toString()) >= 170000000) {//Miembro
                for (int j = 0; j < ChocAn.listaMiembro.size(); j++) {
                    if (Integer.valueOf(modelo.getValueAt(i, 0).toString()) == ChocAn.listaMiembro.get(j).getNumero()) {

                        ChocAn.listaMiembro.get(j).setNombre(modelo.getValueAt(i, 1).toString() + "");
                        ChocAn.listaMiembro.get(j).setEmail(modelo.getValueAt(i, 2).toString() + "");
                        ChocAn.listaMiembro.get(j).setDireccion(modelo.getValueAt(i, 4).toString() + "");
                        ChocAn.listaMiembro.get(j).setCiudad(modelo.getValueAt(i, 5).toString() + "");
                        ChocAn.listaMiembro.get(j).setZip(Integer.valueOf(modelo.getValueAt(i, 6).toString()));

                    }
                }
            } else if (Integer.valueOf(modelo.getValueAt(i, 0).toString()) < 170000000) {//Proveedor
                for (int j = 0; j < ChocAn.listaProveedor.size(); j++) {
                    if (Integer.valueOf(modelo.getValueAt(i, 0).toString()) == ChocAn.listaProveedor.get(j).getNumero()) {

                        ChocAn.listaProveedor.get(j).setNombre(modelo.getValueAt(i, 1).toString() + "");
                        ChocAn.listaProveedor.get(j).setEmail(modelo.getValueAt(i, 2).toString() + "");
                        ChocAn.listaProveedor.get(j).setDireccion(modelo.getValueAt(i, 4).toString() + "");
                        ChocAn.listaProveedor.get(j).setCiudad(modelo.getValueAt(i, 5).toString() + "");
                        ChocAn.listaProveedor.get(j).setZip(Integer.valueOf(modelo.getValueAt(i, 6).toString()));

                    }
                }
            }
        }
    }

    /*
     Metodo que desocupa la lista para luego ser llenada conlos datos de la tabla
     */
    public void vaciarListas() {
        for (int i = 0; i < ChocAn.listaMiembro.size(); i++) {
            ChocAn.listaMiembro.remove(0);
        }
        for (int i = 0; i < ChocAn.listaProveedor.size(); i++) {
            ChocAn.listaMiembro.remove(0);
        }
    }

    /*
     Metodo especializado en guadar los datos de Miembro y porveedor en un .txt
     */
    public void guardarArchivo(String ruta) {

        File archivo;

//Escritura
        try {
            File fruta = new File("");
            archivo = new File(fruta.getCanonicalPath() + "\\src\\Archivos\\" + ruta + ".txt");
            FileWriter w = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            if (ruta.equalsIgnoreCase("Miembro")) {
                for (int i = 0; i < ChocAn.listaMiembro.size(); i++) {
                    wr.write(ChocAn.listaMiembro.get(i).getNumero() + "," + ChocAn.listaMiembro.get(i).getNombre()
                            + "," + ChocAn.listaMiembro.get(i).getEmail() + "," + ChocAn.listaMiembro.get(i).getDireccion()
                            + "," + ChocAn.listaMiembro.get(i).getCiudad() + "," + ChocAn.listaMiembro.get(i).getZip()
                            + "," + ChocAn.listaMiembro.get(i).getEstado() + "\r\n");//escribimos en el archivo 

                }
            }
            if (ruta.equalsIgnoreCase("Proveedor")) {
                for (int i = 0; i < ChocAn.listaProveedor.size(); i++) {
                    wr.write(ChocAn.listaProveedor.get(i).getNumero() + "," + ChocAn.listaProveedor.get(i).getNombre()
                            + "," + ChocAn.listaProveedor.get(i).getEmail() + "," + ChocAn.listaProveedor.get(i).getDireccion()
                            + "," + ChocAn.listaProveedor.get(i).getCiudad() + "," + ChocAn.listaProveedor.get(i).getZip() + "\r\n");//escribimos en el archivo 

                }
            }
            if (ruta.equalsIgnoreCase("Factura")) {
                for (int i = 0; i < ChocAn.listaFactura.size(); i++) {
                    wr.write(ChocAn.listaFactura.get(i).getNumero() + "," + ChocAn.listaFactura.get(i).getNumeroMiembro() + ","
                            + ChocAn.listaFactura.get(i).getNumeroProveedor() + "," + ChocAn.listaFactura.get(i).getCodigoServicio() + ","
                            + ChocAn.listaFactura.get(i).getComentario() + "," + ChocAn.listaFactura.get(i).getFechaActual() + ","
                            + ChocAn.listaFactura.get(i).getFechaPrestacionServicio() + "\r\n");
                }
            }
            //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
            //de no hacerlo no se escribirá nada en el archivo
            wr.close();
            bw.close();

        } catch (IOException e) {
        };
    }

    //----------------------------------------------------
    //                      PROVEEDOR
    //----------------------------------------------------
    public boolean confirmarNumeroProveedor(int numero) {
        for (int i = 0; i < ChocAn.listaProveedor.size(); i++) {
            if (numero == ChocAn.listaProveedor.get(i).getNumero()) {
                return true;
            }
        }
        return false;
    }

    public boolean cofirmarNumeroMiembro(int numero) {
        for (int i = 0; i < ChocAn.listaMiembro.size(); i++) {
            if (numero == ChocAn.listaMiembro.get(i).getNumero()) {
                return true;
            }
        }
        return false;
    }

    public boolean cofirmarNumeroServicio(int numero) {
        for (int i = 0; i < ChocAn.listaServicio.size(); i++) {
            if (numero == ChocAn.listaServicio.get(i).getNumero()) {
                return true;
            }
        }
        return false;
    }

    public int buscarMiembro(int numero) {
        for (int i = 0; i < ChocAn.listaMiembro.size(); i++) {
            if (numero == ChocAn.listaMiembro.get(i).getNumero()) {
                return i;
            }
        }
        return 0;
    }

    public int buscarServicio(int numero) {
        for (int i = 0; i < ChocAn.listaServicio.size(); i++) {
            if (numero == ChocAn.listaServicio.get(i).getNumero()) {
                return i;
            }
        }
        return 0;
    }

    public int buscarProveedor(int numero) {
        for (int i = 0; i < ChocAn.listaProveedor.size(); i++) {
            if (numero == ChocAn.listaProveedor.get(i).getNumero()) {
                return i;
            }
        }
        return 0;
    }

    public int numeroFactura() {
        int numero = 0;
        for (int i = 0; i < ChocAn.listaFactura.size(); i++) {
            if (numero <= ChocAn.listaFactura.get(i).getNumero()) {
                numero = ChocAn.listaFactura.get(i).getNumero() + 1;
            }
        }

        return numero;
    }

    public void directorioServicios(String ruta) {
        File archivo;

//Escritura
        try {
            File fruta = new File("");
            archivo = new File(fruta.getCanonicalPath() + "\\src\\Archivos\\" + ruta + ".txt");

            FileWriter w = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write("\r\n\t\t\t\t\t..:::DIRECTORIO DE SERVICIOS:::..\r\n\r\n");
            wr.write("\t\t\tNumero\t|\tNombre\t\t\t\t\t\t|\tTarifa\r\n");

            wr.write("\t\t\t" + ChocAn.listaServicio.get(1).getNumero() + "\t|\t"
                    + ChocAn.listaServicio.get(1).getNombre() + "\t\t\t|\t"
                    + ChocAn.listaServicio.get(1).getTarifa()
                    + "\r\n");
            wr.write("\t\t\t" + ChocAn.listaServicio.get(2).getNumero() + "\t|\t"
                    + ChocAn.listaServicio.get(2).getNombre() + "\t\t|\t"
                    + ChocAn.listaServicio.get(2).getTarifa()
                    + "\r\n");
            wr.write("\t\t\t" + ChocAn.listaServicio.get(0).getNumero() + "\t|\t"
                    + ChocAn.listaServicio.get(0).getNombre() + "\t|\t"
                    + ChocAn.listaServicio.get(0).getTarifa()
                    + "\r\n");

            wr.close();
            bw.close();

        } catch (IOException e) {
        };
    }

    //-------------------------------------------------
    //                      REPORTES
    //-------------------------------------------------
    public String fechaSistema() {//MM-DD-AAAA
        String fecha = "";

        Calendar c = new GregorianCalendar();

        int mes = c.get(Calendar.MONTH) + 1;
        int dia = c.get(Calendar.DATE);
        int ano = c.get(Calendar.YEAR);

        String Mes = String.valueOf(mes);
        String Dia = String.valueOf(dia);
        String Ano = String.valueOf(ano);

        if (Mes.length() < 2) {
            Mes = "0" + Mes;
        }
        if (Dia.length() < 2) {
            Dia = "0" + Dia;
        }
        fecha = Mes + "-" + Dia + "-" + Ano;
        return fecha;
    }

    public void miembroFactura() {//Factura
        try {
            String ruta = "Factura";
            File f;
            FileReader fr;
            BufferedReader br;
            File fruta = new File("");
            f = new File(fruta.getCanonicalPath() + "\\src\\Archivos\\" + ruta + ".txt");
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            String tem;
            while ((tem = br.readLine()) != null) {
                char[] linea = tem.toCharArray();
                String numeroMiembro = "";
                int k = 0;
                for (int j = 0; j < tem.length(); j++) {

                    if (k == 0 && linea[j] != ',') {

                    } else if (k == 1 && linea[j] != ',') {
                        numeroMiembro += linea[j];
                    } else if (k == 2) {
                        this.escrivirReporteMiembro(numeroMiembro);
                        break;
                    } else {
                        k++;
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escrivirReporteMiembro(String numeroMiembro) {

        int numero = Integer.valueOf(numeroMiembro);
        int i = this.buscarMiembro(numero);
        String nombre = ChocAn.listaMiembro.get(i).getNombre();
        String direccion = ChocAn.listaMiembro.get(i).getDireccion();
        String ciudad = ChocAn.listaMiembro.get(i).getCiudad();
        int zip = ChocAn.listaMiembro.get(i).getZip();
        String ruta = nombre + this.fechaSistema();

        File archivo;
        try {
            File fruta = new File("");
            archivo = new File(fruta.getCanonicalPath() + "\\src\\Archivos\\Reporte Miembro\\" + ruta + ".txt");

            FileWriter w = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);

            this.llenarReporteMiembro(wr, nombre, numero, direccion, zip);
            this.terminarreporteMiembro(numero, wr);
            wr.close();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarreporteMiembro(int numero, PrintWriter wr) {//Factura
        try {
            String ruta = "Factura";
            File f;
            FileReader fr;
            BufferedReader br;
            File fruta = new File("");
            f = new File(fruta.getCanonicalPath() + "\\src\\Archivos\\" + ruta + ".txt");
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            String tem;
            while ((tem = br.readLine()) != null) {
                char[] linea = tem.toCharArray();
                String numeroMiembro = "";
                String numeroProveedor = "";
                String numeroServicio = "";
                String fecha = "";
                int k = 0;
                for (int j = 0; j < tem.length(); j++) {

                    if (k == 0 && linea[j] != ',') {

                    } else if (k == 1 && linea[j] != ',') {//Miembro
                        numeroMiembro += linea[j];
                    } else if (k == 2 && linea[j] != ',' && numero == Integer.valueOf(numeroMiembro)) {//Proveedor
                        numeroProveedor += linea[j];
                    } else if (k == 3 && linea[j] != ',' && numero == Integer.valueOf(numeroMiembro)) {//Servicios
                        numeroServicio += linea[j];
                    } else if (k == 4 && linea[j] != ',' && numero == Integer.valueOf(numeroMiembro)) {//Comentario

                    } else if (k == 5 && linea[j] != ',' && numero == Integer.valueOf(numeroMiembro)) {//Fecha Sistema

                    } else if (k == 6 && linea[j] != ',' && numero == Integer.valueOf(numeroMiembro)) {//Fecha de servivio prestado
                        fecha += linea[j];
                    } else {
                        k++;
                    }
                }
                if (!fecha.equalsIgnoreCase("")) {
                    int proveedor = this.buscarProveedor(Integer.valueOf(numeroProveedor));
                    int servicio = this.buscarServicio(Integer.valueOf(numeroServicio));
                    wr.write("\r\nFecha: " + fecha + "\r\nProveedor " + ChocAn.listaProveedor.get(proveedor).getNombre() + "\r\nServicio " + ChocAn.listaServicio.get(servicio).getNombre() + "\r\n");
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarReporteMiembro(PrintWriter wr, String nombre, int numero, String direccion, int zip) {
        wr.write("Nombre: " + nombre + "\r\n");//escribimos en el archivo 
        wr.write("Numero: " + numero + "\r\n");//escribimos en el archivo 
        wr.write("Direccion: " + direccion + "\r\n");//escribimos en el archivo 
        wr.write("ciudad: " + nombre + "\r\n");//escribimos en el archivo 
        wr.write("Codigo Zip: " + zip + "\r\n");//escribimos en el archivo 
    }

    public void proveedorFactura() {//Factura
        try {
            String ruta = "Factura";
            File f;
            FileReader fr;
            BufferedReader br;
            File fruta = new File("");
            f = new File(fruta.getCanonicalPath() + "\\src\\Archivos\\" + ruta + ".txt");
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            String tem;
            while ((tem = br.readLine()) != null) {
                char[] linea = tem.toCharArray();
                String numeroProveedor = "";
                int k = 0;
                for (int j = 0; j < tem.length(); j++) {

                    if (k == 0 && linea[j] != ',') {

                    } else if (k == 1 && linea[j] != ',') {

                    } else if (k == 2 && linea[j] != ',') {
                        numeroProveedor += linea[j];

                    } else if (k == 3 && linea[j] != ',') {
                        this.escrivirReporteProveedor(numeroProveedor);
                    } else {
                        k++;
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escrivirReporteProveedor(String numeroProveedor) {

        int numero = Integer.valueOf(numeroProveedor);
        int i = this.buscarProveedor(numero);
        String nombre = ChocAn.listaProveedor.get(i).getNombre();
        String direccion = ChocAn.listaProveedor.get(i).getDireccion();
        String ciudad = ChocAn.listaProveedor.get(i).getCiudad();
        int zip = ChocAn.listaProveedor.get(i).getZip();
        String ruta = "Proveedor " + nombre + this.fechaSistema();
        File archivo;

        try {
            File fruta = new File("");
            archivo = new File(fruta.getCanonicalPath() + "\\src\\Archivos\\Reporte Proveedor\\" + ruta + ".txt");

            FileWriter w = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);

            this.llenarReporteProveedor(wr, nombre, numero, direccion, ciudad, zip);
            this.terminarReporteProveedor(numero, wr);

            wr.close();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private double totalPagar = 0;
    private int numeroConsulta = 0;

    public void terminarReporteProveedor(int numero, PrintWriter wr) {//Factura
        try {
            String ruta = "Factura";
            File f;
            FileReader fr;
            BufferedReader br;
            File fruta = new File("");
            f = new File(fruta.getCanonicalPath() + "\\src\\Archivos\\" + ruta + ".txt");
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            String tem;

            totalPagar = 0;
            numeroConsulta = 0;
            while ((tem = br.readLine()) != null) {

                char[] linea = tem.toCharArray();
                String numeroMiembro = "";
                String numeroProveedor = "";
                String numeroServicio = "";
                String fecha = "";
                String fechaSistema = "";

                int k = 0;
                for (int j = 0; j < tem.length(); j++) {

                    if (k == 0 && linea[j] != ',') {

                    } else if (k == 1 && linea[j] != ',') {//Miembro
                        numeroMiembro += linea[j];
                    } else if (k == 2 && linea[j] != ',') {//Proveedor
                        numeroProveedor += linea[j];
                    } else if (k == 3 && linea[j] != ',' && numero == Integer.valueOf(numeroProveedor)) {//Servicios
                        numeroServicio += linea[j];
                    } else if (k == 4 && linea[j] != ',' && numero == Integer.valueOf(numeroProveedor)) {//Comentario

                    } else if (k == 5 && linea[j] != ',' && numero == Integer.valueOf(numeroProveedor)) {//Fecha Sistema
                        fechaSistema += linea[j];
                    } else if (k == 6 && linea[j] != ',' && numero == Integer.valueOf(numeroProveedor)) {//Fecha de servivio prestado
                        fecha += linea[j];
                    } else {
                        k++;
                    }
                }

                if (!fecha.equalsIgnoreCase("")) {
                    int miembro = this.buscarMiembro(Integer.valueOf(numeroMiembro));
                    int servicio = this.buscarServicio(Integer.valueOf(numeroServicio));
                    this.numeroConsulta++;
                    this.totalPagar += Double.valueOf(ChocAn.listaServicio.get(servicio).getTarifa());
                    wr.write("\r\n\tFecha prestacion Servicio: " + fecha
                            + "\r\n\tFecha del Sistema: " + fechaSistema
                            + "\r\n\tNombre Miembro: " + ChocAn.listaMiembro.get(miembro).getNombre()
                            + "\r\n\tNumero Miembro: " + ChocAn.listaMiembro.get(miembro).getNumero()
                            + "\r\n\tServicio: " + ChocAn.listaServicio.get(servicio).getNumero()
                            + "\r\n\tValor a pagar: " + ChocAn.listaServicio.get(servicio).getTarifa() + "\r\n");
                }
            }

            this.llenarTEF(numero);

            wr.write("\r\nNumero Total Consultas: " + this.numeroConsulta + "\r\nTotal a Pagar en la Semana: " + Math.rint(this.totalPagar * 100) / 100);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarTEF(int numero) {
        try {
            String nombreProveedor = ChocAn.listaProveedor.get(this.buscarProveedor(numero)).getNombre();
            String ruta = "TEF " + nombreProveedor + " " + this.fechaSistema();
            File f;
            File fruta = new File("");
            f = new File(fruta.getCanonicalPath() + "\\src\\Archivos\\TEF\\" + ruta + ".txt");
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            System.out.println(nombreProveedor + " " + numero);
            wr.write("Nombre: " + nombreProveedor + "\r\n");
            wr.write("Numero: " + numero + "\r\n");
            wr.write("Total a ser Transferidad " + Math.rint(this.totalPagar * 100) / 100);

            wr.close();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void llenarReporteProveedor(PrintWriter wr, String nombre, int numero, String direccion, String ciudad, int zip) {
        wr.write("Nombre: " + nombre + "\r\n");//escribimos en el archivo 
        wr.write("Numero: " + numero + "\r\n");//escribimos en el archivo 
        wr.write("Direccion: " + direccion + "\r\n");//escribimos en el archivo 
        wr.write("ciudad: " + ciudad + "\r\n");//escribimos en el archivo 
        wr.write("Codigo Zip: " + zip + "\r\n");//escribimos en el archivo 
    }
    public void actualizarEstadoMiembro(){
        try {
            File f = new File("");
            f = new File(f.getCanonicalPath()+"\\src\\Archivos\\Actualizar Estado Miembro\\Actualizar Estado Miembro.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            System.out.println(f.getCanonicalPath());
            String linea;
            while((linea = br.readLine()) != null){
                char[] c = linea.toCharArray();
                String numero="";
                String estado="";
                int j=0;
                for (int i = 0; i < c.length; i++) {
                  if(c[i] == ','){
                        j++;
                        continue;
                    }
                    switch (j) {
                        case 0:
                            numero+=c[i];
                            break;
                        case 1:
                            estado+=c[i];
                            break;
                        default:
                            System.err.println("¡¿Que paso?!");
                    }  
                }
                ChocAn.listaMiembro.get(this.buscarMiembro(Integer.valueOf(numero))).setEstado(estado);
            }
            this.guardarArchivo("Miembro");
        } catch (Exception ex) {
            System.err.println("Ruta no valida actualizar estado miembro");
        }
    }
    public void reporteGerencia() {
        try {
            int totalProveedor = 0;
            int totalConsultas = 0;
            double totalPagar = 0;

            File carpeta = new File("");
            carpeta = new File(carpeta.getCanonicalPath() + "\\src\\Archivos\\Reporte Proveedor");
            File[] arregloarchivos = carpeta.listFiles();

            File ruta = new File("");
            ruta = new File(ruta.getCanonicalPath() + "\\src\\Archivos\\Reporte Gerencia\\" + "Reporte Gerencia " + this.fechaSistema() + ".txt");
            FileWriter w = new FileWriter(ruta);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write("\t\t\t\t\t\t\t..:::REPORTE GERENCIA:::..\r\n");
            for (int i = 0; i < arregloarchivos.length; i++) {
                //lellendo
                wr.write("\r\n");
                FileReader fr = new FileReader(arregloarchivos[i].toString());
                BufferedReader br = new BufferedReader(fr);
                String tem = "";
                int j = 0;
                totalProveedor++;

                while ((tem = br.readLine()) != null) {
                    j++;
                    if (j == 1) {
                        wr.write("\t" + tem + "\r\n");
                    }
                    if (j == 2) {
                        wr.write("\t" + tem + "\r\n");
                    }
                    if (j == this.contarLinasArchivo(arregloarchivos[i].toString()) - 1) {
                        wr.write("\t" + tem + "\r\n");
                        totalConsultas += Integer.valueOf(this.buscarTotalConsulta(tem));
                    }
                    if (j == this.contarLinasArchivo(arregloarchivos[i].toString())) {
                        wr.write("\t" + tem + "\r\n");
                        totalPagar += Double.valueOf(this.buscarTotal(tem));
                    }
                }

            }
            wr.write("\r\nNUMERO TOTAL DE PROVEEDORES A PAGAR: " + totalProveedor);
            //buscarTotal  totalConsultas
            wr.write("\r\nNUMERO TOTAL DE CONSULTAS: " + totalConsultas);
            wr.write("\r\nTOTAL A PAGAR: " + Math.rint(totalPagar * 100) / 100 );
            wr.close();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String buscarTotal(String tem) {
        String aux = "";

        char[] linea = tem.toCharArray();
        int k = 0, z = 0;
        for (int j = 0; j < tem.length(); j++) {
            if (z == 1) {
                z++;
            }
            if (z == 2) {
                aux += "" + linea[j];
            }
            if (linea[j] == ':') {
                z++;
            } else {
                k++;
            }

        }
        return aux;
    }

    public String buscarTotalConsulta(String tem) {
        String aux = "";
        char[] linea = tem.toCharArray();
        int k = 0, z = 0;
        for (int j = 0; j < tem.length(); j++) {
            if (z == 1) {
                z++;
            } else if (z == 2) {
                aux += "" + linea[j];
            }
            if (linea[j] == ':') {
                z++;
            } else {
                k++;
            }

        }
        return aux;
    }

    public String imprimirReporte(String nombre) {
        String aux = "";
        String texto = "";
        try {
            File abre = new File("");
            abre = new File(abre.getCanonicalPath() + "\\src\\Archivos\\"+nombre );

            /**
             * recorremos el archivo, lo leemos para plasmarlo en el area de texto
             */
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);
                while ((aux = lee.readLine()) != null) {
                    texto += aux + "\n";
                }
                lee.close();
            }
        } catch (IOException ex) {
        }
        return texto;//El texto que almacenaremos en el JTextArea
    }

    public String[] listarReportes(String nombre) {
        String lista[]=new String[0];
        File f = new File("");
        try {
            f = new File(f.getCanonicalPath() + "\\src\\Archivos\\"+nombre);
        } catch (IOException ex) {
            Logger.getLogger(ControlChocan.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (f.exists()) { // Directorio existe 
            File[] ficheros = f.listFiles();
            lista= new String[ficheros.length];
            for (int x = 0; x < ficheros.length; x++) {
                lista [x]= ficheros[x].getName();
            }
        }
        return lista;
    }
    
    /**
     * Hola
     *@author Brayan
     *     
    */ 
    public void controlDatafono(JTextField jTextField1){
        switch (InterfazPrincipalProveedor.etapa) {
            case 0:
                try {
                    if (this.confirmarNumeroProveedor(Integer.valueOf(jTextField1.getText()))) {

                        InterfazPrincipalProveedor.numeroProveedor = ChocAn.listaProveedor.get(this.buscarProveedor(Integer.valueOf(jTextField1.getText()))).getNumero();
                        System.out.println("Numero Proveedor: " + InterfazPrincipalProveedor.numeroProveedor);
                        InterfazPrincipalProveedor.etapa++;
                        jTextField1.setText("Introdusca Codigo Miembro");
                    } else {
                        jTextField1.setText("El Proveedor no Existe");
                    }
                } catch (Exception e) {
                    jTextField1.setText("Numero de Proveedor no valido");
                }
                break;
            case 1:
                try {
                    if (this.cofirmarNumeroMiembro(Integer.valueOf(jTextField1.getText()))) {
                        InterfazPrincipalProveedor.numeroMiembro = ChocAn.listaMiembro.get(this.buscarMiembro(Integer.valueOf(jTextField1.getText()))).getNumero();
                        jTextField1.setText(ChocAn.listaMiembro.get(this.buscarMiembro(Integer.valueOf(jTextField1.getText()))).getNombre()
                                + " Está "
                                + ChocAn.listaMiembro.get(this.buscarMiembro(Integer.valueOf(jTextField1.getText()))).getEstado());
                        System.out.println("Numero Miembro: " + InterfazPrincipalProveedor.numeroMiembro);
                        InterfazPrincipalProveedor.etapa++;
                    } else {
                        jTextField1.setText("Numero de Miembro no Existe");
                    }
                } catch (Exception e) {
                    jTextField1.setText("Numero de Miembro no Valido");
                }
                break;
            case 2:
                jTextField1.setText("Ingrese numero de servicio");
                InterfazPrincipalProveedor.etapa++;
                break;
            case 3:
                try {
                    if (this.cofirmarNumeroServicio(Integer.valueOf(jTextField1.getText()))) {
                        InterfazPrincipalProveedor.numeroServicio = ChocAn.listaServicio.get(this.buscarServicio(Integer.valueOf(jTextField1.getText()))).getNumero();
                        System.out.println("Nnumero de Servicio: " + InterfazPrincipalProveedor.numeroServicio);
                        jTextField1.setText(ChocAn.listaServicio.get(this.buscarServicio(Integer.valueOf(jTextField1.getText()))).getNombre());
                        InterfazPrincipalProveedor.etapa++;

                    } else {
                        jTextField1.setText("Servicio no Existe");
                    }
                } catch (Exception e) {
                    jTextField1.setText("Numero de Servicio no valido");
                }
                break;
            case 4:
                jTextField1.setText("Ingrese fecha de presatacion de servicio MM-DD-AAAA");
                InterfazPrincipalProveedor.etapa++;
                break;
            case 5:
                if (jTextField1.getText().length() == 10) {
                    InterfazPrincipalProveedor.fechaPrestacionServicio = jTextField1.getText();
                    System.out.println("fecha prestacion Servicio: " + InterfazPrincipalProveedor.fechaPrestacionServicio);
                    InterfazPrincipalProveedor.fechaActual = this.fechaSistema();
                    System.out.println("Fecha del sistema: " + InterfazPrincipalProveedor.fechaActual);
                    jTextField1.setText("Inserte comentario ");
                    InterfazPrincipalProveedor.etapa++;
                } else {
                   InterfazPrincipalProveedor.etapa--;
                }
                break;
            case 6:
                InterfazPrincipalProveedor.comentario = jTextField1.getText();
                System.out.println("Comentario: " + InterfazPrincipalProveedor.comentario);
                InterfazPrincipalProveedor.tarifa = Double.valueOf(ChocAn.listaServicio.get(this.buscarServicio(InterfazPrincipalProveedor.numeroServicio)).getTarifa());
                jTextField1.setText("Valor: $" + InterfazPrincipalProveedor.tarifa);
                System.out.println("Tarifa: $" + InterfazPrincipalProveedor.tarifa);
                InterfazPrincipalProveedor.etapa++;
                break;
            default:
        }
    }
    
    public void vaciarTabla(DefaultTableModel modelo){
        while(modelo.getRowCount() != 0){
            modelo.removeRow(0);
        }
    }
}
