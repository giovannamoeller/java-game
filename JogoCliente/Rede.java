import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Rede {
  DataOutputStream os = null;
  Socket socket = null;
  DataInputStream is = null;

  Rede() {
    try {
      socket = new Socket("127.0.0.1", 12345);
      os = new DataOutputStream(socket.getOutputStream());
      is = new DataInputStream(socket.getInputStream());
    } catch (UnknownHostException e) {
      Mensagem.erroFatalExcecao("Servidor desconhecido!", e);
    } catch (IOException e) {
      Mensagem.erroFatalExcecao("A conexao com o servidor não pode ser criada!", e);
    }
  }

  public void enviaComandosEDadosDoJogador(int estado) {
    try {
      os.writeInt(estado);
      os.flush();
    } catch (IOException e) {
      Mensagem.erroFatalExcecao("A imagem não pode ser enviada!", e);
    }
  }

  public void recebeSituacao(Jogador jogador) {
    try {
      // System.out.print("pos ");
      jogador.posicao(is.readInt());
      // System.out.print(jogador.x + " est ");
      jogador.estado(is.readInt());
      // System.out.print(jogador.estado + " inv ");
      jogador.inverte(is.readBoolean());
      // System.out.println(jogador.invertido + " Ok ");
    } catch (IOException e) {
      Mensagem.erroFatalExcecao("Jogo terminado pelo servidor.", e);
    }
  }

  public void recebePlacar(Ambiente ambiente) {
    try {
      ambiente.jogador.pontos = is.readInt();
      ambiente.adversario.pontos = is.readInt();
    } catch (IOException e) {
      Mensagem.erroFatalExcecao("Jogo terminado pelo servidor.", e);
    }
  }

  public void recebeDadosIniciais(Ambiente ambiente) {
    try {
      int largJogo = is.readInt();
      int altuJogo = is.readInt();
      ambiente.jogador.posicao(50, altuJogo - 15);
      ambiente.adversario.posicao(largJogo - 50, altuJogo - 15);
      ambiente.ajustaTamanho(largJogo, altuJogo);
    } catch (IOException e) {
      Mensagem.erroFatalExcecao(e);
    }
  }

}
