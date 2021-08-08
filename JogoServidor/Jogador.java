import java.awt.Rectangle;

class Tamanho {
  int larg, altu;

  Tamanho(int largura, int altura) {
    larg = largura;
    altu = altura;
  }
}

public class Jogador {
  static final int PARADO = 0;
  static final int ANDA0 = 1;
  static final int ANDA1 = 2;
  static final int SOCO0 = 3;
  static final int SOCO1 = 4;
  static final int SOCO2 = 5;

  int x, y; // o y indica a posicao do pe' do jogador
  /** contem a posicao onde a imagem sera desenhada */
  Rectangle rectColisao;
  /** indica se a imagem deve ser desenhada invertida */
  boolean invertido = false;
  int estado = PARADO;
  /** tamanhos obtidos das imagens do jogador */
  Tamanho[] tamanho = new Tamanho[6];
  /** deslocamento até o centro da imagem */
  int dxCentroJogador = 32;
  /** pontos marcados */
  int pontos = 0;

  Jogador() {
    inicia();
  }

  Jogador(int player) {
    
    inicia();

    if(player == 1) {
      posicao(x, y);
    } else if(player == 2) {
      posicao(x, y);
    }

  }

  void inicia() {
    defineTamanhoJogador();
    rectColisao = new Rectangle(0, 0, tamanho[estado].larg, tamanho[estado].altu);
    estado(PARADO);
  }

  void defineTamanhoJogador() {
    tamanho[PARADO] = new Tamanho(66, 242);
    tamanho[ANDA0] = new Tamanho(64, 242);
    tamanho[ANDA1] = new Tamanho(65, 246);
    tamanho[SOCO0] = new Tamanho(79, 242);
    tamanho[SOCO1] = new Tamanho(104, 242);
    tamanho[SOCO2] = new Tamanho(120, 242);
  }

  void inverte(boolean invertido) {
    this.invertido = invertido;
    posicao(x);
  }

  void estado(int estado) {
    this.estado = estado;
    rectColisao.setSize(tamanho[estado].larg, tamanho[estado].altu);
    posicao(x, y); // se invertido, ajusta a nova posicao do retangulo
  }

  void posicao(int x, int y) {
    this.y = y;
    rectColisao.y = y - rectColisao.height; // faz o y ser o pe' da figura
    posicao(x);
  }

  void posicao(int x) {
    this.x = x;
    if (invertido) {
      rectColisao.setLocation(x - tamanho[estado].larg + dxCentroJogador, rectColisao.y);
    } else {
      rectColisao.setLocation(x - dxCentroJogador, rectColisao.y);
    }
  }

  boolean verificaColisao(Jogador j) {
    return rectColisao.intersects(j.rectColisao);
  }
}
