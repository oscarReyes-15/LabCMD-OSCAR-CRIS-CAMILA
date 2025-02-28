/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmdapp1;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class input {
    private final String[] cmds = {"dir", "del", "crear", "cd", "info", "wr", "Time", "Date", "..." , "salir"};
    private GUI G;
    private fileManagement files;
    private String direccion, texto;
    
    public input (GUI G){
        this.G = G;
        files = new fileManagement();
    }
    
    // chequee si el comando esta escrito correctamente
    String checkInput (String input) throws IOException {
        String[] userCMD = null;
        userCMD = input.split("[\\s]");
        String comando = userCMD[0];
        
        if (userCMD.length > 1){
            direccion = userCMD[1];
        }

        //
        System.out.println("1. " + Arrays.toString(userCMD));
        boolean valido = checkCMD(comando);
        if (valido == true){
            return executeCMD(comando);     
        } else {
            return "\nno se registro como cmd, escribalo correctamente";
        } 
    }
    
    boolean checkCMD (String input) {
        for (String cmd : cmds) {
            if (cmd.equals(input)){
                System.out.println(input);
                return true;
            }
        }
        return false;
    }
    
    void setText(String texto) {
        this.texto = texto;
    }
    
    String executeCMD (String cmd) throws IOException {
        try{
            switch (cmd) {
                case "dir" -> {
                   return files.dir();
                }
                case "del" -> {
                    System.out.println(cmd + " " + direccion);
                    if (files.CheckTipo()) {
                        return "Se ha borrado correctamente";                    
                    }
                    else{
                        return "No se borro nada";
                    }
                }
                
                case "crear" -> {
                    if(files.crearFile()){
                        return "Se creo el archivo";
                    }
                    else{
                        return "No se creo nada";
                    }
                }
                case "wr" -> {
                    texto = "texto";
                    G.escribirTXT();
               
                    if (files.escribir(texto)) {
                        return ("Texto escrito correctamente.");
                    } else {
                        return ("No se pudo escribir el texto.");
                    }
                }
                case "..." ->{
                    files.setFile(files.getFile().getAbsoluteFile().getParent());
                    return "Regresado con exito";
                }
                case "Date" ->{
                }
                case "Time" -> {
                }
                case "cd" -> {
                    System.out.println(cmd + " " + direccion);
                    files.setFile(direccion);
                    return direccion + "\n";
                }
                case "info" -> {
                    System.out.println(cmd + " " + direccion);
                    return direccion + "\n" + files.info() + "\n";
                }
                case "salir" -> {
                    System.exit(0);
                }
                default -> {
                    return "cmd no se ejecuto correctamente\n";
                }

            }
        }catch (NullPointerException e) {
            return "\nFile esta null";
        
        }
            
        return "\nalgo salio mal";
    }
    
}
