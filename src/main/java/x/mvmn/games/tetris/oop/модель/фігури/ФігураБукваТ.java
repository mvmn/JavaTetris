package x.mvmn.games.tetris.oop.модель.фігури;

import java.awt.Color;

public class ФігураБукваТ extends ФігураЗОбертом {

    public ФігураБукваТ(Color колір, int поворот) {
        super(колір, поворот);
    }

    @Override
    public ФігураБукваТ обернути() {
        return new ФігураБукваТ(колір, поворот + 1);
    }

    @Override
    protected boolean заповненістьВраховуючиОберт(int x, int y) {
        return y == 1 || x == 0;
    }

    @Override
    public int зміщенняОбертуX() {
        if (поворот == 0) {
            return -1;
        } else if (поворот == 3) {
            return 1;
        }
        return 0;
    }

    @Override
    public int зміщенняОбертуY() {
        if (поворот == 0) {
            return 1;
        } else if (поворот == 1) {
            return -1;
        }
        return 0;
    }
}
