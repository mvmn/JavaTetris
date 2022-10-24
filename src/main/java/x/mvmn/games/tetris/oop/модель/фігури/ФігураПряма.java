package x.mvmn.games.tetris.oop.модель.фігури;

import java.awt.Color;

public class ФігураПряма extends ФігураЗОбертом {
    private boolean повернута = false;

    public ФігураПряма(Color колір, int поворот) {
        super(колір, поворот);
        this.повернута = поворот != 0;
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
    protected int кількістьПоложень() {
        return 2;
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
        return new ФігураПряма(колір, (поворот + 1) % 2);
    }

    @Override
    protected boolean заповненістьВраховуючиОберт(int x, int y) {
        return true;
    }
}
