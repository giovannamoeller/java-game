import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class PongJogo extends JFrame
{
    PongJogo()
    {
        Painel painel;

        painel = new Painel();
        this.add(painel);
        this.setTitle("popo");
        this.setResizable(false); // não pode ajustar a janela
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // janela aparece no meio da tela
    
    }

    public static void main(String[] args)
    {
        new PongJogo();
    }
}