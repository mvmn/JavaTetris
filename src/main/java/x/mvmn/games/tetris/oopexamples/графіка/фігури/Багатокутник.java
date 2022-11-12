package x.mvmn.games.tetris.oopexamples.графіка.фігури;

import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Графіка;
import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Координати;

import java.awt.Color;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Багатокутник extends ГеомертичнаФігура {

    private final Координати[] точки;
    private Пряма[] лінії;

    public Багатокутник(Координати[] точки) {
        this.точки = точки;
        задатиЛінії();
    }

    public Багатокутник(Координати[] точки, Color колір) {
        this(точки);
        setКолір(колір);
    }

    protected void задатиЛінії() {
        this.лінії = new Пряма[this.точки.length];
        for (int i = 0; i < this.точки.length; i++) {
            if (i == this.точки.length - 1) {
                this.лінії[i] = new Пряма(this.точки[i], this.точки[0]);
            } else {
                this.лінії[i] = new Пряма(this.точки[i], this.точки[i + 1]);
            }
        }
    }

    @Override
    public void намалювати(Графіка графіка) {
        for (int i = 0; i < this.лінії.length; i++) {
            this.лінії[i].setКолір(колір);
            this.лінії[i].намалювати(графіка);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + Stream.of(точки).map(Object::toString).collect(Collectors.joining(",")) + "), rgb:" + колір.getRed() + "," + колір.getGreen() + "," + колір.getBlue();
    }
}
