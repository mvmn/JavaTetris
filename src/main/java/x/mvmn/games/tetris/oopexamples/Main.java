package x.mvmn.games.tetris.oopexamples;

import x.mvmn.games.tetris.oopexamples.тварини.Качка;
import x.mvmn.games.tetris.oopexamples.тварини.Кіт;
import x.mvmn.games.tetris.oopexamples.тварини.Риба;
import x.mvmn.games.tetris.oopexamples.тварини.Собака;
import x.mvmn.games.tetris.oopexamples.тварини.Тварина;

public class Main {
    public static void main(String[] args) {
        Тварина[] тварини = new Тварина[6];

        Кіт кіт = new Кіт(5);
        кіт.голос();
        кіт.мурчати();
        Собака собака = new Собака();
        собака.голос();
        собака.вити();
        System.out.println(" --- ");

        тварини[0] = new Собака();
        тварини[1] = кіт;
        тварини[2] = new Качка(3);
        тварини[3] = new Собака(7);
        тварини[4] = new Качка(4);
        тварини[5] = new Риба(2);

        всімГолос(тварини);

    }

    public static void всімГолос(Тварина[] тварини) {
        for (int i = 0; i < тварини.length; i++) {
            Тварина тварина = тварини[i];
            System.out.println("Вік = " + тварина.getВік());
            тварина.голос();
        }
    }
}
