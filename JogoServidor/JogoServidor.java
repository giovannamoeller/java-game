import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JogoServidor {
  public static void main(String[] args) {
    ServerSocket serverSocket = null;
    int porta = 12345;

    try {
      serverSocket = new ServerSocket(porta);
    } catch (IOException e) {
      System.out.println("Nao pode escutar a porta: " + porta + ".\n" + e);
      System.exit(1);
    }

    while (true) {
      IJogo jogo = new Jogo(2); // 2 jogadores
      int numMaximoJogadores = jogo.numMaximoJogadores();
      for (int i = 0; i < numMaximoJogadores; i++) {
        Socket clientSocket = null;
        try {
          System.out.println("Esperando conexao de um jogador.");
          clientSocket = serverSocket.accept();
        } catch (IOException e) {
          System.out.println("Accept falhou: " + porta + ".\n" + e);
          System.exit(1);
        }
        System.out.println("Accept Funcionou!");
        jogo.adicionaJogador(clientSocket);
      }
      jogo.iniciaLogica(new Logica(jogo));
      jogo.inicia();
    }
  }
}
