package x.mvmn.games.tetris.oopexamples.графіка;

import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.ГоловнеВікно;
import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Координати;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.ГеомертичнаФігура;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.ЗаповненийПрямокутник;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.Крапка;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.Пряма;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.Чотирикутник;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ГеомертичнаФігура> фігури = Collections.synchronizedList(new ArrayList<>());
        фігури.add(new ЗаповненийПрямокутник(new Координати(50, 50), new Координати(130, 80), new Color(155, 154, 144)));
        фігури.add(new Чотирикутник(new Координати(40, 50),
                new Координати(140, 50),
                new Координати(130, 40),
                new Координати(50, 40), Color.ORANGE));
        for (int i = 0; i < 7; i++) {
            фігури.add(new Пряма(new Координати(60 + i * 10, 40), new Координати(51 + i * 13, 50), Color.ORANGE));
        }
        фігури.add(new ЗаповненийПрямокутник(new Координати(110, 30), new Координати(115, 45), Color.ORANGE));
        фігури.add(new Крапка(new Координати(112, 15), Color.GRAY));
        фігури.add(new Крапка(new Координати(113, 20), Color.GRAY));
        фігури.add(new Крапка(new Координати(112, 25), Color.GRAY));
        фігури.add(new ЗаповненийПрямокутник(new Координати(65, 60), new Координати(75, 70), Color.BLUE));
        фігури.add(new ЗаповненийПрямокутник(new Координати(105, 60), new Координати(115, 70), Color.BLUE));
        фігури.add(new ЗаповненийПрямокутник(new Координати(85, 60), new Координати(95, 80), Color.GRAY));
        ГоловнеВікно.показати(фігури);
    }
}
