package x.mvmn.games.tetris.oop.відображення;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import x.mvmn.games.tetris.oop.модель.ПоточнаФігура;
import x.mvmn.games.tetris.oop.модель.СтанПоляТетрісу;

public class КомпонентПолеТетрісу implements КомпонентВідображення {

  private String текст;
  private final СтанПоляТетрісу станПоля;
  private ПоточнаФігура фігура;

  public КомпонентПолеТетрісу(СтанПоляТетрісу станПоля) {
    this.станПоля = станПоля;
  }

  @Override
  public void промалювати(Graphics графіка) {
    int відступ_X = 8;
    int відступ_Y = 8;

    // Фон
    графіка.setColor(Color.DARK_GRAY);
    графіка.fillRect(відступ_X, відступ_Y, 404, 604);
    графіка.setColor(Color.BLACK);
    for (int i = 0; i < 11; i++) {
      графіка.fillRect(відступ_X + 1 + i * 40, відступ_Y + 1, 2, 602);
    }
    for (int i = 0; i < 16; i++) {
      графіка.fillRect(відступ_X + 1, відступ_Y + 1 + i * 40, 402, 2);
    }

    for (int y = 0; y < 15; y++) {
      for (int x = 0; x < 10; x++) {
        Color колір = станПоля.колірБлоку(x, y);
        if (колір != null) {
          замалюватиКвадратикФігури(графіка, колір, відступ_X + 40 * x, відступ_Y + 40 * y);
        }
      }
    }

    if (фігура != null && фігура.фігура() != null) {
      for (int y = 0; y < фігура.фігура().висота(); y++) {
        for (int x = 0; x < фігура.фігура().ширина(); x++) {
          if (фігура.фігура().заповненість(x, y)) {
            замалюватиКвадратикФігури(графіка, фігура.фігура().колір(),
                відступ_X + 40 * x + фігура.x() * 40, відступ_Y + 40 * y + фігура.y() * 40);
          }
        }
      }
    }

    if (текст != null) {
      Font шрифт = графіка.getFont();
      графіка.setFont(шрифт.deriveFont(шрифт.getSize() + 10.0f));
      int ширинаТексту = (int) графіка.getFontMetrics().getStringBounds(текст, графіка).getWidth();
      графіка.setColor(Color.BLACK);
      графіка.drawString(текст, 7 + (404 - ширинаТексту) / 2, 299);
      графіка.drawString(текст, 9 + (404 - ширинаТексту) / 2, 301);
      графіка.drawString(текст, 7 + (404 - ширинаТексту) / 2, 301);
      графіка.drawString(текст, 9 + (404 - ширинаТексту) / 2, 299);
      графіка.setColor(Color.WHITE);
      графіка.drawString(текст, 8 + (404 - ширинаТексту) / 2, 300);
      графіка.setFont(шрифт);
    }
  }

  public void текст(String текст) {
    this.текст = текст;
  }

  public void поточнаФігура(ПоточнаФігура фігура) {
    this.фігура = фігура;
  }

  private static void замалюватиКвадратикФігури(Graphics графіка, Color колір, int x, int y) {
    графіка.setColor(колір);
    графіка.fillRect(x + 4, y + 4, 36, 36);
    графіка.setColor(Color.WHITE);
    графіка.fillRect(x + 8, y + 8, 28, 28);
    графіка.setColor(колір);
    графіка.fillRect(x + 14, y + 14, 16, 16);
  }
}
