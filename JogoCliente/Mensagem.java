import java.awt.Component;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;

public class Mensagem {
  static void erroFatalExcecao(String msg, Exception ex) {
    erroFatalExcecao(null, msg, ex);
  }

  static void erroFatalExcecao(Exception ex) {
    erroFatalExcecao(null, null, ex);
  }

  static void erroFatalExcecao(Component janela, String msg, Exception ex) {
    StringWriter str = new StringWriter();
    ex.printStackTrace(new PrintWriter(str));
    if (msg == null) {
      msg = str.toString();
    } else {
      msg += "\n" + str;
    }
    JOptionPane.showMessageDialog(janela, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    System.exit(10);
  }
}
