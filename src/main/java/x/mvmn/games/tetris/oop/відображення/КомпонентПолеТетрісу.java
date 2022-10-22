package x.mvmn.games.tetris.oop.відображення;

import x.mvmn.games.tetris.oop.модель.ПоточнаФігура;
import x.mvmn.games.tetris.oop.модель.СтанПоляТетрісу;
import x.mvmn.games.tetris.oop.модель.фігури.Фігура;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class КомпонентПолеТетрісу implements КомпонентВідображення {

  private String текст;
  private final СтанПоляТетрісу станПоля;
  private ПоточнаФігура фігура;
  private static final int відступ_X = 8;
  private static final int відступ_Y = 8;

  public КомпонентПолеТетрісу(СтанПоляТетрісу станПоля) {
    this.станПоля = станПоля;
  }

  @Override
  public void промалювати(Graphics графіка) {

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
          замалюватиКвадратик(графіка, колір, відступ_X + 40 * x, відступ_Y + 40 * y);
        }
      }
    }

    if (фігура != null && фігура.фігура() != null) {
      намалюватиФігуру(графіка, фігура.фігура(), фігура.x(), фігура.y());
    }

    if (текст != null) {
      Font шрифт = графіка.getFont();
      графіка.setFont(шрифт.deriveFont(шрифт.getSize() + 10.0f));
      Rectangle2D розмірТексту = графіка.getFontMetrics().getStringBounds(текст, графіка);
      int ширинаТексту = (int) розмірТексту.getWidth();
      int висотаТексту = (int) розмірТексту.getHeight();
      графіка.setColor(Color.BLACK);
      графіка.fillRoundRect(відступ_X + (404 - ширинаТексту - 10) / 2,
          відступ_Y + 300 - 5 + (int) розмірТексту.getY(), ширинаТексту + 10, висотаТексту + 10, 30,
          30);
      графіка.setColor(Color.WHITE);
      графіка.drawString(текст, відступ_X + (404 - ширинаТексту) / 2, відступ_Y + 300);
      графіка.setFont(шрифт);
    }
  }

  private void намалюватиФігуру(Graphics графіка, Фігура фігура, int fx, int fy) {
    for (int y = 0; y < фігура.висота(); y++) {
      for (int x = 0; x < фігура.ширина(); x++) {
        if (фігура.заповненість(x, y)) {
          замалюватиКвадратик(графіка, фігура.колір(), відступ_X + 40 * x + fx * 40,
              відступ_Y + 40 * y + fy * 40);
        }
      }
    }
  }

  private void замалюватиКвадратик(Graphics графіка, Color колір, int x, int y) {
    графіка.setColor(колір);
    графіка.fillRect(x + 4, y + 4, 36, 36);
    графіка.setColor(Color.WHITE);
    графіка.fillRect(x + 8, y + 8, 28, 28);
    графіка.setColor(колір);
    графіка.fillRect(x + 14, y + 14, 16, 16);
  }

  public void текст(String текст) {
    this.текст = текст;
  }

  public void поточнаФігура(ПоточнаФігура фігура) {
    this.фігура = фігура;
  }
}
