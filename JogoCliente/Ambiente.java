import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Ambiente extends JPanel {
  static final int FUNDO = 0;
  static final int ARBUSTO = 1;
  static final int GRAMA = 2;

  int largJogo = 300;
  int altuJogo = 200;
  JogadorDesenho jogador;
  JogadorDesenho adversario;
  Image[] imagens = new Image[3];

  Ambiente(JogoCliente jogo) {
    carregaImagens();
    jogador = new JogadorDesenho(jogo);
    adversario = new JogadorDesenho(jogo);
    adversario.inverte(true);
  }

  void carregaImagens() {
    try {
      imagens[FUNDO] = ImageIO.read(new File("fundo.jpeg"));
      imagens[ARBUSTO] = ImageIO.read(new File("arbusto.png"));
      imagens[GRAMA] = ImageIO.read(new File("grama.gif"));
    } catch (IOException e) {
      Mensagem.erroFatalExcecao(this, "A imagem não pode ser carregada!", e);
    }
  }

  public void ajustaTamanho(int largJogo, int altuJogo) {
    this.largJogo = largJogo;
    this.altuJogo = altuJogo;
    setPreferredSize(new Dimension(largJogo, altuJogo));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.clipRect(5, 5, getWidth() - 10, getHeight() - 10);

    g.drawImage(imagens[FUNDO], 0, 0, largJogo, altuJogo, this);

    jogador.desenha(g);
    adversario.desenha(g);

    g.drawImage(imagens[ARBUSTO], 300, altuJogo - imagens[ARBUSTO].getHeight(this) - 10, this);
    g.drawImage(imagens[GRAMA], 0, 0, largJogo, altuJogo, this);

    desenhaPlacar(g);

    Toolkit.getDefaultToolkit().sync();
  }

  void desenhaPlacar(Graphics g) {
    Font f = new Font("Arial", Font.BOLD, 50);
    g.setFont(f);
    String s = jogador.pontos + " x " + adversario.pontos;
    FontMetrics fm = g.getFontMetrics();
    int x = (largJogo - fm.stringWidth(s)) / 2;
    g.drawString(s, x - 1, fm.getHeight() - 1);
    g.setColor(Color.RED);
    g.drawString(s, x + 1, fm.getHeight() + 1);
  }

}