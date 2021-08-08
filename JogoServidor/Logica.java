public class Logica implements ILogica {
  Jogo jogo;
  Jogador jogador;
  Jogador adversario;

  Logica(IJogo jogo) { 
    this.jogo = (Jogo)jogo;
    jogador = this.jogo.jogadores[0];
    adversario = this.jogo.jogadores[1];
  }

  public void executa() {
    verificaAnda(jogador, 10);
    verificaAnda(adversario, -10);
    verificaSoco(jogador);
    verificaSoco(adversario);
    trataColisao();
  }

  boolean trataColisaoLimites(Jogador jogador) {
    if (jogador.x + jogador.rectColisao.width + 5 > Jogo.LARG_JOGO) {
      jogador.posicao(Jogo.LARG_JOGO - jogador.rectColisao.width - 5);
      return true;
    } else if (jogador.x < 5) {
      jogador.posicao(5);
      return true;
    }
    return false;
  }

  void trataColisao() {
    int dx = 10;
    int dxJogador = dx;
    int dxAdversario = dx;
    if (trataColisaoLimites(jogador)) {
      dxJogador = 0;
      dxAdversario = 2 * dx;
    }
    if (trataColisaoLimites(adversario)) {
      dxJogador = 2 * dx;
      dxAdversario = 0;
    }
    if (jogador.verificaColisao(adversario)) {
      atualizaPlacar();
      if (jogador.x < adversario.x) {
        jogador.posicao(jogador.x - dxJogador);
        adversario.posicao(adversario.x + dxAdversario);
      } else {
        jogador.posicao(jogador.x + dxJogador);
        adversario.posicao(adversario.x - dxAdversario);
      }
    }
  }

  void atualizaPlacar() {
    if (jogador.estado == Jogador.SOCO2 && adversario.estado != Jogador.SOCO2) {
      jogador.pontos++;
    }
    if (adversario.estado == Jogador.SOCO2 && jogador.estado != Jogador.SOCO2) {
      adversario.pontos++;
    }
  }

  void verificaAnda(Jogador jogador, int tamPasso) {
    if (jogador.estado == Jogador.ANDA0) {
      jogador.estado(Jogador.ANDA1);
      jogador.posicao(jogador.x + tamPasso);
    } else if (jogador.estado == Jogador.ANDA1) {
      jogador.estado(Jogador.ANDA0);
      jogador.posicao(jogador.x + tamPasso);
    }
  }

  void verificaSoco(Jogador jogador) {
    if (jogador.estado == Jogador.SOCO0) {
      jogador.estado(Jogador.SOCO1);
    } else if (jogador.estado == Jogador.SOCO1) {
      jogador.estado(Jogador.SOCO2);
    } else if (jogador.estado == Jogador.SOCO2) {
      jogador.estado(Jogador.PARADO);
    }
  }
}
