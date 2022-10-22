package x.mvmn.games.tetris.oop.модель.фігури;

import java.awt.*;

public class ФігураЗигзагЛівий extends ФігураЗОбертом {

    public ФігураЗигзагЛівий(Color колір, int поворот) {
        super(колір, поворот);
    }

    @Override
    public ФігураЗигзагЛівий обернути() {
        return new ФігураЗигзагЛівий(колір, поворот + 1);
    }

    @Override
    protected boolean заповненістьВраховуючиОберт(int x, int y) {
        return y == 1 || (y == 0 && x == 1) || (y == 2 && x == 0);
    }

    @Override
    protected int кількістьПоложень() {
        return 2;
    }
}
