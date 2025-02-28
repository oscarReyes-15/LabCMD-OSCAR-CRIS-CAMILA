/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmdapp1;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class input {
    private final String[] cmds = {"dir", "del", "crear", "cd", "info"};
    private GUI G;
    private fileManagement files;
    private String direccion;
    
    public input (GUI G){
        this.G = G;
        files = new fileManagement();
    }
    
    // chequee si el comando esta escrito correctamente
    String checkInput (String input) {
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
    
    String executeCMD (String cmd) {
        try{
            switch (cmd) {
                case "dir" -> {
                    

                }
                case "del" -> {

                }
                case "crear" -> {
                    
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
                default -> {
                    return "cmd no se ejecuto correctamente\n";
                }

            }
        } 
        catch (NullPointerException e) {}
            
        return "algo salio mal";
    }
    
}
