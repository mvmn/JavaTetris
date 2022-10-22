package x.mvmn.games.tetris.oop;

import javax.swing.SwingUtilities;
import x.mvmn.games.tetris.oop.відображення.ГоловнеВікно;
import x.mvmn.games.tetris.oop.сервіс.РушійГри;

public class Tetris {

  public static void main(String[] args) {
    ГоловнеВікно головнеВікно = new ГоловнеВікно();
    РушійГри рушій = new РушійГри(головнеВікно);
    головнеВікно.додатиСлухачВводу(рушій);
    головнеВікно.pack();
    SwingUtilities.invokeLater(() -> головнеВікно.setVisible(true));
    рушій.запуск();
  }
}
