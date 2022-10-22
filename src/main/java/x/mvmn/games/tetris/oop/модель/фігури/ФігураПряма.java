package x.mvmn.games.tetris.oop.модель.фігури;

import java.awt.*;

public class ФігураПряма extends АбстрактнаФігура {
    private boolean повернута = false;

    public ФігураПряма(Color колір) {
        super(колір);
    }

    @Override
    public int ширина() {
        return повернута ? 4 : 1;
    }

    @Override
    public int висота() {
        return повернута ? 1 : 4;
    }

    @Override
    public boolean заповненість(int x, int y) {
        return true;
    }

    @Override
    public int зміщенняОбертуX() {
        return повернута ? 1 : -1;
    }

    @Override
    public int зміщенняОбертуY() {
        return повернута ? -1 : 1;
    }

    @Override
    public Фігура обернути() {
        ФігураПряма обернутаПряма = new ФігураПряма(колір);
        обернутаПряма.повернута = !повернута;
        return обернутаПряма;
    }
}
