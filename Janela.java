import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Janela extends JFrame {

    Painel painel;

    Janela() {
        painel = new Painel();
        this.add(painel);
        this.setTitle("Jogo do Pong");
        this.setResizable(false); // não pode ajustar a janela
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // janela aparece no meio da tela
    }
}