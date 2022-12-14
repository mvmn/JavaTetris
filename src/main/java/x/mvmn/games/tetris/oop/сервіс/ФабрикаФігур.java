package x.mvmn.games.tetris.oop.сервіс;

import x.mvmn.games.tetris.oop.модель.фігури.Фігура;
import x.mvmn.games.tetris.oop.модель.фігури.ФігураБукваТ;
import x.mvmn.games.tetris.oop.модель.фігури.ФігураГачокЛівий;
import x.mvmn.games.tetris.oop.модель.фігури.ФігураГачокПравий;
import x.mvmn.games.tetris.oop.модель.фігури.ФігураЗигзагЛівий;
import x.mvmn.games.tetris.oop.модель.фігури.ФігураЗигзагПравий;
import x.mvmn.games.tetris.oop.модель.фігури.ФігураКвадрат;
import x.mvmn.games.tetris.oop.модель.фігури.ФігураПряма;

import java.awt.Color;
import java.util.Random;

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
                return new ФігураПряма(колір, 0);
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
