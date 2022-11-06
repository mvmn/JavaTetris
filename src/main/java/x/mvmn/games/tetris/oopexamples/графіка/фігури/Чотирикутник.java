package x.mvmn.games.tetris.oopexamples.графіка.фігури;

import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Графіка;
import x.mvmn.games.tetris.oopexamples.графіка.допоміжне.Координати;

import java.awt.Color;

public class Чотирикутник extends Прямокутник {

    private final Координати точкаА;
    private final Координати точкаБ;
    private final Координати точкаВ;
    private final Координати точкаГ;

    public Чотирикутник(Координати точкаА, Координати точкаБ, Координати точкаВ, Координати точкаГ) {
        super(null, null);
        this.точкаА = точкаА;
        this.точкаБ = точкаБ;
        this.точкаВ = точкаВ;
        this.точкаГ = точкаГ;
        задатиЛінії();
    }

    public Чотирикутник(Координати точкаА, Координати точкаБ, Координати точкаВ, Координати точкаГ, Color колір) {
        this(точкаА, точкаБ, точкаВ, точкаГ);
        setКолір(колір);
    }

    @Override
    protected void задатиЛінії() {
        лінії[0] = new Пряма(точкаА, точкаБ);
        лінії[1] = new Пряма(точкаБ, точкаВ);
        лінії[2] = new Пряма(точкаВ, точкаГ);
        лінії[3] = new Пряма(точкаГ, точкаА);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (" + точкаА + ", " + точкаБ + "," + точкаВ + "," + точкаГ + "), колір " + колір.getRed() + "/" + колір.getGreen() + "/" + колір.getBlue();
    }
}
