package x.mvmn.games.tetris.oopexamples.тварини;

public class Кіт extends Тварина {
    public Кіт() {
    }

    public Кіт(int вік) {
        super(вік);
    }

    @Override
    public void голос() {
        System.out.println("Няв!");
    }

    public void мурчати() {
        System.out.println("Мур!");
    }
}
