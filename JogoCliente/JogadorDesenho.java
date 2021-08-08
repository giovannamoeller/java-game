import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

class JogadorDesenho extends Jogador {
  JFrame janelaDona;
  Image[] imagens = new Image[6];
  /** mostra pequeno movimento para avatar parecer vivo */
  int pequenoMovimento = 0;

  JogadorDesenho(JFrame janelaDona) {
    this.janelaDona = janelaDona;
    carregaImagens();
    iniciaTimerPequenoMovimento();
  }

  JogadorDesenho(int x, int y, JFrame janelaDona) {
    super(x, y);
    this.janelaDona = janelaDona;
    carregaImagens();
    iniciaTimerPequenoMovimento();
  }

  void iniciaTimerPequenoMovimento() {
    new Timer(500, new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        pequenoMovimento = (int)(Math.random()*5);
        janelaDona.repaint();
      }
    }).start();
  }


  void carregaImagens() {
    try {
      imagens[PARADO] = ImageIO.read(new File("parado.gif"));
      imagens[ANDA0] = ImageIO.read(new File("anda0.gif"));
      imagens[ANDA1] = ImageIO.read(new File("anda1.gif"));
      imagens[SOCO0] = ImageIO.read(new File("soco0.gif"));
      imagens[SOCO1] = ImageIO.read(new File("soco1.gif"));
      imagens[SOCO2] = ImageIO.read(new File("soco2.gif"));
    } catch (IOException e) {
      Mensagem.erroFatalExcecao(janelaDona, "A imagem não pode ser carregada!", e);
    }
  }

  void desenha(Graphics g) {
    int larg = rectColisao.width;
    int x = rectColisao.x;
    if (invertido) {
      larg = -larg;
      x -= larg;
    }
    g.drawImage(imagens[estado], x, rectColisao.y - pequenoMovimento, larg, rectColisao.height + pequenoMovimento,
        janelaDona);
    g.drawRect(rectColisao.x, rectColisao.y, rectColisao.width, rectColisao.height);
  }
}
