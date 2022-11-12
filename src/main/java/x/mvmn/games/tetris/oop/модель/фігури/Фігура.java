package x.mvmn.games.tetris.oop.модель.фігури;

import java.awt.Color;

public interface Фігура {

    int ширина();

    int висота();

    boolean заповненість(int x, int y);

    boolean обертається();

    int зміщенняОбертуX();

    int зміщенняОбертуY();

    Фігура обернути();

    Color колір();
}
