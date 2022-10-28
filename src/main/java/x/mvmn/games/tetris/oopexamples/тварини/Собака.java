package x.mvmn.games.tetris.oopexamples.тварини;

public class Собака extends Тварина {

    public Собака() {
    }

    public Собака(int вік) {
        super(вік);
    }

    @Override
    public void голос() {
        System.out.println("Гав!");
    }

    public void вити() {
        System.out.println("Вууууу!");
    }
}
