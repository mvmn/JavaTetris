package x.mvmn.games.tetris.oopexamples.графіка.фігури;

import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Графіка;
import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Координати;

import java.awt.Color;

public class Пряма extends ГеомертичнаФігура {

    private final Координати точкаА;

    private final Координати точкаБ;

    public Пряма(Координати точкаА, Координати точкаБ) {
        this.точкаА = точкаА;
        this.точкаБ = точкаБ;
    }

    public Пряма(Координати точкаА, Координати точкаБ, Color колір) {
        this(точкаА, точкаБ);
        setКолір(колір);
    }

    @Override
    public void намалювати(Графіка графіка) {
        int deltaX = Math.abs(точкаБ.getX() - точкаА.getX());
        int deltaY = Math.abs(точкаБ.getY() - точкаА.getY());

        Координати точкаА = this.точкаА;
        Координати точкаБ = this.точкаБ;

        boolean горизонтальна = deltaX > deltaY;
        if (горизонтальна) {
            if (точкаБ.getX() < точкаА.getX()) {
                точкаА = this.точкаБ;
                точкаБ = this.точкаА;
            }

            double коефіцієнт;
            if (deltaX != 0) {
                коефіцієнт = (double) deltaY / deltaX;
            } else {
                коефіцієнт = 0;
            }
            int знак = точкаА.getY() <= точкаБ.getY() ? 1 : -1;
            for (int i = 0; i <= (точкаБ.getX() - точкаА.getX()); i++) {
                int y = (int) (точкаА.getY() + знак * коефіцієнт * i);
                графіка.намалюватиПіксель(точкаА.getX() + i, y, getКолір());
            }
        } else {
            if (точкаБ.getY() < точкаА.getY()) {
                точкаА = this.точкаБ;
                точкаБ = this.точкаА;
            }
            double коефіцієнт;
            if (deltaY != 0) {
                коефіцієнт = (double) deltaX / deltaY;
            } else {
                коефіцієнт = 0;
            }
            int знак = точкаА.getX() <= точкаБ.getX() ? 1 : -1;
            for (int i = 0; i <= (точкаБ.getY() - точкаА.getY()); i++) {
                int x = (int) (точкаА.getX() + знак * коефіцієнт * i);
                графіка.намалюватиПіксель(x, точкаА.getY() + i, getКолір());
            }
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (" + точкаА + " - " + точкаБ + "), колір " + колір.getRed() + "/" + колір.getGreen() + "/" + колір.getBlue();
    }
}
