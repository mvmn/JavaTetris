package x.mvmn.games.tetris.oop.модель.фігури;

import java.awt.Color;

public abstract class ФігураЗОбертом extends АбстрактнаФігура {

    protected final int поворот;

    public ФігураЗОбертом(Color колір, int поворот) {
        super(колір);
        this.поворот = поворот % кількістьПоложень();
    }

    protected int кількістьПоложень() {
        return 4;
    }

    @Override
    public int ширина() {
        return поворот % 2 == 0 ? 2 : 3;
    }

    @Override
    public int висота() {
        return поворот % 2 == 0 ? 3 : 2;
    }

    @Override
    public boolean заповненість(int x, int y) {
        if (поворот == 1) {
            int xx = x;
            x = y;
            y = ширина() - xx - 1;
        } else if (поворот == 2) {
            x = ширина() - x - 1;
            y = висота() - y - 1;
        } else if (поворот == 3) {
            int xx = x;
            x = висота() - y - 1;
            y = xx;
        }

        return заповненістьВраховуючиОберт(x, y);
    }

    protected abstract boolean заповненістьВраховуючиОберт(int x, int y);
}
