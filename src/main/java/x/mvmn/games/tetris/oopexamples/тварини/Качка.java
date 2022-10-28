package x.mvmn.games.tetris.oopexamples.тварини;

public class Качка extends Тварина {
    public Качка() {
    }

    public Качка(int вік) {
        super(вік);
    }

    @Override
    public void голос() {
        System.out.println("Кря!");
    }
}
