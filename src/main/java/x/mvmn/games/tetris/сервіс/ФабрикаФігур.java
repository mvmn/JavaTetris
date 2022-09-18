package x.mvmn.games.tetris.сервіс;

import java.awt.Color;
import java.util.Random;
import x.mvmn.games.tetris.модель.фігури.Фігура;
import x.mvmn.games.tetris.модель.фігури.ФігураБукваТ;
import x.mvmn.games.tetris.модель.фігури.ФігураГачокЛівий;
import x.mvmn.games.tetris.модель.фігури.ФігураГачокПравий;
import x.mvmn.games.tetris.модель.фігури.ФігураЗигзагЛівий;
import x.mvmn.games.tetris.модель.фігури.ФігураЗигзагПравий;
import x.mvmn.games.tetris.модель.фігури.ФігураКвадрат;
import x.mvmn.games.tetris.модель.фігури.ФігураПряма;

public class ФабрикаФігур {
  private static final Color[] кольори = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN,
      Color.MAGENTA, Color.YELLOW.darker(), Color.ORANGE, Color.PINK};
  private static Random генераторВипадковихЧисел = new Random();

  private Color попереднійКолір;

  public Фігура створитиВипадковуФігуру() {
    Color колір;
    do {
      колір = кольори[генераторВипадковихЧисел.nextInt(кольори.length)];
    } while (попереднійКолір != null && попереднійКолір == колір);

    int випадковеЧисло = генераторВипадковихЧисел.nextInt(7);
    switch (випадковеЧисло) {
      case 0:
        return new ФігураКвадрат(колір);
      case 1:
        return new ФігураПряма(колір);
      case 2:
        return new ФігураБукваТ(колір, 1);
      case 3:
        return new ФігураГачокЛівий(колір, 0);
      case 4:
        return new ФігураГачокПравий(колір, 0);
      case 5:
        return new ФігураЗигзагЛівий(колір, 0);
      default:
        return new ФігураЗигзагПравий(колір, 0);
    }
  }
}
