import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class JogoCliente extends JFrame {
  Rectangle[] rect;
  Rede rede = new Rede();

  Ambiente ambiente;

  JogoCliente() {
    super("Jogo");
    ambiente = new Ambiente(this);
    rede.recebeDadosIniciais(ambiente);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    add(ambiente);
    pack();
    setResizable(false);
    setVisible(true);

    addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          rede.enviaComandosEDadosDoJogador(Jogador.ANDA0);
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
          rede.enviaComandosEDadosDoJogador(Jogador.PARADO);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
          rede.enviaComandosEDadosDoJogador(Jogador.SOCO0);
        }
      }
    });

    new Thread(new Runnable() {
      public void run() {
        while (true) {
          rede.recebeSituacao(ambiente.jogador);
          rede.recebeSituacao(ambiente.adversario);
          rede.recebePlacar(ambiente);
          ambiente.repaint();
        }
      }
    }).start();
  }

  static public void main(String[] args) {
    new JogoCliente();
  }
}
