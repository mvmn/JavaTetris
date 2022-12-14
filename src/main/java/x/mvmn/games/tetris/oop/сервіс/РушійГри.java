package x.mvmn.games.tetris.oop.сервіс;

import x.mvmn.games.tetris.oop.відображення.ГоловнеВікно;
import x.mvmn.games.tetris.oop.відображення.КомпонентНаступнаФігура;
import x.mvmn.games.tetris.oop.відображення.КомпонентПолеТетрісу;
import x.mvmn.games.tetris.oop.відображення.КомпонентЧисловийІндикатор;
import x.mvmn.games.tetris.oop.модель.ПоточнаФігура;
import x.mvmn.games.tetris.oop.модель.СтанГри;
import x.mvmn.games.tetris.oop.модель.фігури.Фігура;

public class РушійГри implements СлухачВводуКористувача {

    private static final int повільністьПочатковогоРівня = 50;
    protected int кроківДоПониженняФігури = повільністьПочатковогоРівня;
    protected СтанГри станГри = new СтанГри();
    protected volatile boolean граЙде = false;
    protected volatile boolean попросилиВихід = false;
    protected volatile boolean пауза = false;

    protected ГоловнеВікно головнеВікно;
    protected КомпонентПолеТетрісу поле;
    protected КомпонентЧисловийІндикатор рахунок;
    protected КомпонентЧисловийІндикатор рівень;
    protected КомпонентЧисловийІндикатор рядківДоНаступногоРівня;
    protected КомпонентНаступнаФігура відображенняНаступноїФігури;

    protected ФабрикаФігур фабрикаФігур = new ФабрикаФігур();

    public РушійГри(ГоловнеВікно головнеВікно) {
        this.головнеВікно = головнеВікно;

        this.поле = new КомпонентПолеТетрісу(станГри.станПоля());
        this.рахунок = new КомпонентЧисловийІндикатор("Рахунок: ", 422, 8, 370, 40, 8, 24);
        this.рівень = new КомпонентЧисловийІндикатор("Рівень: ", 422, 58, 370, 40, 8, 24);
        this.рядківДоНаступногоРівня =
                new КомпонентЧисловийІндикатор("Рядків до level-up: ", 422, 108, 370, 40, 8, 24);
        this.відображенняНаступноїФігури = new КомпонентНаступнаФігура(422, 158, 370);

        головнеВікно.додатиКомпонент(поле);
        головнеВікно.додатиКомпонент(рахунок);
        головнеВікно.додатиКомпонент(рівень);
        головнеВікно.додатиКомпонент(рядківДоНаступногоРівня);
        головнеВікно.додатиКомпонент(відображенняНаступноїФігури);
    }

    @Override
    public void натиснутоПробіл() {
        граЙде = true;
        пауза = false;
    }

    @Override
    public void натиснутоВліво() {
        if (граЙде) {
            int x = станГри.поточнаФігура().x();
            if (x > 0 && !станГри.станПоля().колізія(станГри.поточнаФігура().фігура(), x - 1,
                    станГри.поточнаФігура().y())) {
                станГри.поточнаФігура().x(x - 1);
            }
        }
    }

    @Override
    public void натиснутоВправо() {
        if (граЙде) {
            int x = станГри.поточнаФігура().x();
            if (x + станГри.поточнаФігура().фігура().ширина() < 10 && !станГри.станПоля()
                    .колізія(станГри.поточнаФігура().фігура(), x + 1, станГри.поточнаФігура().y())) {
                станГри.поточнаФігура().x(x + 1);
            }
        }
    }

    @Override
    public void натиснутоВгору() {
        if (граЙде) {
            Фігура фігура = станГри.поточнаФігура().фігура();
            int x = станГри.поточнаФігура().x() + фігура.зміщенняОбертуX();
            int y = станГри.поточнаФігура().y() + фігура.зміщенняОбертуY();
            фігура = фігура.обернути();
            if (x < 0) {
                x = 0;
            }
            if (x + 1 + фігура.ширина() > 10) {
                x = 10 - фігура.ширина();
            }
            if (y < 0) {
                y = 0;
            }
            if (y + 1 + фігура.висота() > 15) {
                y = 15 - фігура.висота();
            }

            if (!станГри.станПоля().колізія(фігура, x, y)) {
                станГри.поточнаФігура().фігура(фігура);
                станГри.поточнаФігура().x(x);
                станГри.поточнаФігура().y(y);
            }
        }
    }

    @Override
    public void натиснутоДонизу() {
        if (граЙде) {
            пониженняФігури();
        }
    }

    @Override
    public void натиснутоВихід() {
        попросилиВихід = true;
    }

    @Override
    public void натиснутоПаузу() {
        пауза = !пауза;
    }

    public void запуск() {
        while (!попросилиВихід) {
            поле.текст("Натисніть ПРОБІЛ щоб почати");
            головнеВікно.перемалюватиВміст();
            while (!граЙде) {
                Thread.yield();
            }
            передПочатокГри();
            пауза = false;
            while (граЙде && !попросилиВихід) {
                поле.текст(!пауза ? null : "Пауза");
                крокГри();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    private void крокГри() {
        if (!пауза) {
            if (станГри.поточнаФігура() == null) {
                наступнаФігура();
            }

            кроківДоПониженняФігури--;
            if (кроківДоПониженняФігури <= 0) {
                пониженняФігури();
            }
        }

        головнеВікно.перемалюватиВміст();
    }

    private void передПочатокГри() {
        кроківДоПониженняФігури = повільністьПочатковогоРівня;
        станГри.скинутиСтан();
        станГри.наступнаФігура(фабрикаФігур.створитиВипадковуФігуру());
        рахунок.стан(станГри.рахунок());
        рівень.стан(станГри.рівень());
        рядківДоНаступногоРівня.стан(СтанГри.рядківДоНаступногоРівня);
        поле.поточнаФігура(null);
        поле.кутБовтання(станГри.рівень());
    }

    private void наступнаФігура() {
        станГри.поточнаФігура(new ПоточнаФігура(станГри.наступнаФігура()));
        станГри.наступнаФігура(фабрикаФігур.створитиВипадковуФігуру());
        поле.поточнаФігура(станГри.поточнаФігура());
        відображенняНаступноїФігури.setФігура(станГри.наступнаФігура());
    }

    private void пониженняФігури() {
        кроківДоПониженняФігури = Math.max(0, повільністьПочатковогоРівня - станГри.рівень());

        if ((станГри.поточнаФігура().y() + станГри.поточнаФігура().фігура().висота() >= 15)
                || станГри.станПоля().колізія(станГри.поточнаФігура().фігура(), станГри.поточнаФігура().x(),
                станГри.поточнаФігура().y() + 1)) {
            int заповненіРядки = станГри.станПоля().приземлитиФігуру(станГри.поточнаФігура());
            if (заповненіРядки > 0) {
                станГри.приЗаповненніРядків(заповненіРядки);
                рахунок.стан(станГри.рахунок());
                рівень.стан(станГри.рівень());
                рядківДоНаступногоРівня.стан(станГри.рядківДоНаступногоРівня());
                поле.кутБовтання(станГри.рівень());
            }
            наступнаФігура();
            if (станГри.станПоля().колізія(станГри.поточнаФігура().фігура(), станГри.поточнаФігура().x(),
                    станГри.поточнаФігура().y())) {
                граЙде = false; // Програш
            }
        } else {
            станГри.поточнаФігура().y(станГри.поточнаФігура().y() + 1);
        }
    }
}
