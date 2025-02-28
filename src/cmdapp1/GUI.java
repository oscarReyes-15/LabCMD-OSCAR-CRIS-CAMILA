/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmdapp1;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;


public final class GUI extends JFrame{
    JPanel contenido;
    JTextArea texto;
    ImageIcon icono;
    input key;
    JTextPane cmd;
    
    public GUI () {
        // cosas basicas de frames siempre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        
        // hay que inicializar variables pues
        contenido = new JPanel();
        texto = new JTextArea();
        icono = new ImageIcon("src\\Imagenes\\Grock2.png");
        key = new input(this);
        cmd = new JTextPane();
        
        //
        configurarPanel();
        configurarTexto();
        configurarTxtPn();
            
        // agregamos los compontentes      
        setIconImage(icono.getImage());
        contenido.add(texto, BorderLayout.CENTER);
        contenido.add(cmd, BorderLayout.SOUTH);
        add(contenido);
        System.out.println(contenido.getWidth() + " " + contenido.getHeight());
        //
        
        // set visible - sino no se ve 
        setVisible(true);
        revalidate();
        repaint();
        
    }
    
    void configurarPanel(){
        contenido.setBackground(Color.BLACK);
        
        contenido.setLayout(new BorderLayout());
    }
    
    void configurarTexto () {
        // astetico
        texto.setForeground(Color.WHITE);
        texto.setBackground(Color.BLACK);
        texto.setSize(getWidth() - 20, getHeight() - 20);
        texto.setAlignmentX(LEFT_ALIGNMENT);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        
        texto.setEditable(false);
    
        texto.append("Microsoft Windows [Versión 10.0.19045.5487]\n" +
        "(c) Microsoft Corporation. Todos los derechos reservados. \n");
    }
    
    void configurarTxtPn () {
        cmd.setForeground(Color.WHITE);
        cmd.setBackground(Color.DARK_GRAY);
        
        cmd.addKeyListener(new KeyAdapter () {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    texto.append(key.checkInput(cmd.getText())); 
                    e.consume();
                    cmd.setText("");
                }
            } 
        });
    }
}
