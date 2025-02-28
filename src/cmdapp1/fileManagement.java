/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmdapp1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * Manejo de Archivos - Dentro de la unidad de memoria secundaria
 *
 * Tipos de referencias 1. Clase utilitaria (file y folder): File 2. Clases para
 * archivos de texto:FileWriter y FileReader 3. Clases para archivos binarios:
 * RandomAccessFile
 *
 * //////////////////////////////////////////////////////////////////// La
 * clase File Sirve para operaciones de utileria sobre un archivo o directorio:
 * - crear, borrar, renombrear o ver info acerca del nombre o tamano de algun
 * file o directory - *** no lee o escribe * Formato: " File obj - new File
 * (direccion); "
 *
 * La direccion del archivo o directorio que se quiere manejar, y puede ser de
 * dos tipos: 1. Abstractas o relativas - Le toca a Java intuir su direccion
 * raiz, ej: patito.txt 2. Absolutas - Toda la direccion completa:
 * c:\algo\patito.pdf
 *
 * SOLAMENTE en el constructor se indica la direccion del archivo o directorio
 * que se quiere manejar, no existe ninguna funcion para cambiarla...
 *
 * Funciones basicas:
 * -------------------------------------------------------------------------------
 * |- boolean exists() |- Si el archivo existe o no. |- boolean isFile() o
 * isDirectory() |- Si es archivo o directorio |- long length() |- Especial para
 * archivos: su tamano en bytes |- long lastModified() |- La fecha en
 * milisegundos de su ultima modificacion |- boolean isHidden() |- Si esta
 * escondido o no |- boolean canWrite() o canRead() |- Si pueden escribir o leer
 * |- String getName() |- El nombre del archivo o directorio |- String
 * getAbsolutePath() |- La dirrecion absoluta |- String getParent() |- La
 * direccion padre. OJO: Solo si esta incluida en el getPath()
 * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
 *
 */
public class fileManagement {

    private File myFile = null;
    private Calendar fecha = Calendar.getInstance();

    void setFile(String direccion) {
        myFile = new File(direccion);
    }
    
    File getFile(){
        return myFile;
    }

    String info() {

        if (myFile.exists()) {
            return "\nNombre: " + myFile.getName()
                    + "\nPath: " + myFile.getPath()
                    + "\nAbsoluta: " + myFile.getAbsolutePath()
                    + "\nBytes: " + myFile.length()
                    + "\nModificado en: " + new Date(myFile.lastModified())
                    + "\nPadre: " + myFile.getAbsoluteFile().getParentFile().getName()
                    + (myFile.isFile() ? "\nES FILE\n" : (myFile.isDirectory() ? "\nES UN FOLDER\n" : "\nQUE ES ESTO\n"));

        } else {
            return "\nNO EXISTE\n";
        }
    }

    boolean crearFile() throws IOException {
        return myFile.createNewFile();
    }

    boolean crearFolder() {
        return myFile.mkdir();
    }

    boolean borrar() {
        return myFile.delete();
    }

    public boolean borrarCarpeta() {
        if (myFile.isDirectory()) {
            borrarCarpeta(myFile);
            return myFile.delete();
        }
        return false;
    }

    boolean CheckTipo() {
        if (myFile.isDirectory()) {
            System.out.println("Se borro correctamente");
            return borrarCarpeta();
        } else if (myFile.isFile()) {
            System.out.println("Se borro correctamente");
            return borrar();

        } else {
            System.out.println("No se borro nada");
        }
        return false;
    }

    private void borrarCarpeta (File file) {
        for (File f : file.listFiles()) {
            if (f.isDirectory() == true) {
                if (f.listFiles() != null) {
                    borrarCarpeta(f);
                }
                f.delete();
            } else if (f.isFile() == true) {
                f.delete();
            }
        }
    }

    String dir() {
        if (myFile.isDirectory()) {

            String output = "";
            output += "Folder: " + myFile.getName();
            int dirs = 0, files = 0, bytes = 0;

            for (File child : myFile.listFiles()) {
                System.out.println(new Date(child.lastModified()));
                if (child.isDirectory()) {
                    output += "\n \t<DIR>\t";
                    dirs++;
                }
                if (child.isFile()) {
                    output += "\n \t<FILE>\t";
                    output += "\n" + child.length();
                    files++;
                    bytes += child.length();
                }
                output += "\t" + child.getName();
            }

            output += "\n(" + files + ")" + " files y (" + dirs + ") dirs";
            output += "bytes: " + bytes;
            
            return output;

        } else {
            return "Accion no permitida";
        }
        
    }
    
    public String fecha_actual() {
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH);
        int año = fecha.get(Calendar.YEAR);
        
        return " Fecha actual: " + dia + "/" + mes + "/" + año;
    }
    
    public String hora_actual() {
        int hora = fecha.get(Calendar.HOUR);
        int min = fecha.get(Calendar.MINUTE);
        int sec = fecha.get(Calendar.SECOND);
        int ampm = fecha.get(Calendar.AM_PM);
        
        return " Hora actual: " + hora + ":" + min + ":" + sec + " " + ampm;
    }

    public boolean escribir(String texto) throws IOException {
        if (texto != null) {
            FileWriter escritor = new FileWriter(myFile, true);
            BufferedWriter buffer = new BufferedWriter(escritor);

            buffer.write(texto);
            buffer.newLine();

            buffer.close();
            escritor.close();
            return true;
        } else {
            return false;
        }
    }
    
    public String leer() throws FileNotFoundException {
        if (myFile.isFile()){
            try {
                FileReader lector = new FileReader(myFile);
                BufferedReader buffer = new BufferedReader(lector);
                String linea;
                
                while ((linea = buffer.readLine()) != null){
                    return linea;
                }
                
                buffer.close();
                lector.close();
            }catch (IOException f) {
                return "Ocurrio un error " + f.getMessage();
            }
            
            } else if(myFile.isDirectory()){
                return "Esta direccion no es un archivo legible";
            }
            return "Ocurrio un error al leer esto";
        }
    }

