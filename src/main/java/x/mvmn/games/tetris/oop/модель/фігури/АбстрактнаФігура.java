package x.mvmn.games.tetris.oop.модель.фігури;

import java.awt.Color;

public abstract class АбстрактнаФігура implements Фігура {

    protected final Color колір;

    public АбстрактнаФігура(Color колір) {
        this.колір = колір;
    }

    @Override
    public boolean обертається() {
        return true;
    }

    @Override
    public int зміщенняОбертуX() {
        return 0;
    }

    @Override
    public int зміщенняОбертуY() {
        return 0;
    }

    @Override
    public Color колір() {
        return колір;
    }

    public static void main(String[] args) {
        Фігура f = new ФігураБукваТ(Color.RED, 1);
        for (int i = 0; i < 10; i++) {
            for (int y = 0; y < f.висота(); y++) {
                for (int x = 0; x < f.ширина(); x++) {
                    System.out.print(f.заповненість(x, y) ? "#" : " ");
                }
                System.out.println();
            }
            System.out.println();
            f = f.обернути();
        }
    }
}
