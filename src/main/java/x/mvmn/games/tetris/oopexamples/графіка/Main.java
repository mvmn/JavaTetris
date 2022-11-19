package x.mvmn.games.tetris.oopexamples.графіка;

import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.ГоловнеВікно;
import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Координати;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.Багатокутник;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.ГеомертичнаФігура;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.ЗаповненийПрямокутник;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.Крапка;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.Пряма;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ГеомертичнаФігура> фігури = Collections.synchronizedList(new ArrayList<>());
        фігури.add(new ЗаповненийПрямокутник(new Координати(50, 50), new Координати(130, 80), new Color(155, 154, 144)));
        фігури.add(new Багатокутник(new Координати[]{new Координати(40, 50),
                new Координати(140, 50),
                new Координати(130, 40),
                new Координати(50, 40)}, Color.ORANGE));
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

        int відступ = 2;
        for (int i = 0; i < 255; i++) {
            фігури.add(new Багатокутник(new Координати[] {
                    new Координати(106 + i * відступ, 134),
                    new Координати(126 + i * відступ, 120),
                    new Координати(145 + i * відступ, 127),
                    new Координати(154 + i * відступ, 146),
                    new Координати(143 + i * відступ, 166),
                    new Координати(121 + i * відступ, 171),
                    new Координати(106 + i * відступ, 159)
                }, new Color(i, 255 - i, i)));
        }

        for (int i = 0; i < 255; i++) {
            int зміщенняX = (int) (Math.log(i + 10) * 200) - 550;
            int зміщенняY = 100+(int) (50 * Math.sin(зміщенняX / 255.0d * Math.PI));
            фігури.add(new Багатокутник(new Координати[] {
                    new Координати(106 + зміщенняX, 234 + зміщенняY),
                    new Координати(126 + зміщенняX, 220 + зміщенняY),
                    new Координати(145 + зміщенняX, 227 + зміщенняY),
                    new Координати(164 + зміщенняX, 246 + зміщенняY),
                    new Координати(143 + зміщенняX, 266 + зміщенняY),
                    new Координати(121 + зміщенняX, 271 + зміщенняY),
                    new Координати(106 + зміщенняX, 259 + зміщенняY),
                    new Координати(125 + зміщенняX, 248 + зміщенняY)
                }, Color.getHSBColor((255-i) / 255.0f, 1f, 1f)));
        }

        ГоловнеВікно.показати(фігури);
    }
}
