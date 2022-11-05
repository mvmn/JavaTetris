package x.mvmn.games.tetris.oopexamples.графіка.фігури;

import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Графіка;

import java.awt.Color;

public abstract class ГеомертичнаФігура {

    protected Color колір = Color.WHITE;

    public Color getКолір() {
        return колір;
    }

    public void setКолір(Color колір) {
        this.колір = колір;
    }

    public abstract void намалювати(Графіка графіка);

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", колір " + колір.getRed() + "/" + колір.getGreen() + "/" + колір.getBlue();
    }
}
