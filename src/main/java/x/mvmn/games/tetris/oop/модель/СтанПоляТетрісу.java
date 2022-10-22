package x.mvmn.games.tetris.oop.модель;

import java.awt.Color;
import x.mvmn.games.tetris.oop.модель.фігури.Фігура;

public class СтанПоляТетрісу {

  protected Color[][] стан = new Color[10][15];

  public void скинути() {
    for (int x = 0; x < стан.length; x++) {
      for (int y = 0; y < стан[x].length; y++) {
        стан[x][y] = null;
      }
    }
  }

  public Color колірБлоку(int x, int y) {
    return стан[x][y];
  }

  public int приземлитиФігуру(ПоточнаФігура поточнаФігура) {
    Фігура фігура = поточнаФігура.фігура();
    int fx = поточнаФігура.x();
    int fy = поточнаФігура.y();

    for (int x = 0; x < фігура.ширина(); x++) {
      for (int y = 0; y < фігура.висота(); y++) {
        if (фігура.заповненість(x, y)) {
          стан[fx + x][fy + y] = фігура.колір();
        }
      }
    }

    return перевіркаНаЗаповненіРядки();
  }

  private int перевіркаНаЗаповненіРядки() {
    int результат = 0;
    for (int y = 0; y < стан[0].length; y++) {
      boolean рядокЗаповнений = true;
      for (int x = 0; x < стан.length; x++) {
        if (стан[x][y] == null) {
          рядокЗаповнений = false;
          break;
        }
      }
      if (рядокЗаповнений) {
        опрацюватиЗаповненийРядок(y);
        результат++;
      }
    }
    return результат;
  }

  private void опрацюватиЗаповненийРядок(int заповненийРядок_y) {
    for (int y = заповненийРядок_y; y > 0; y--) {
      for (int x = 0; x < стан.length; x++) {
        стан[x][y] = стан[x][y - 1];
      }
    }
    for (int x = 0; x < стан.length; x++) {
      стан[x][0] = null;
    }
  }

  public boolean колізія(Фігура фігура, int fx, int fy) {
    for (int x = 0; x < фігура.ширина(); x++) {
      for (int y = 0; y < фігура.висота(); y++) {
        if (стан[fx + x][fy + y] != null && фігура.заповненість(x, y)) {
          return true;
        }
      }
    }

    return false;
  }
}
