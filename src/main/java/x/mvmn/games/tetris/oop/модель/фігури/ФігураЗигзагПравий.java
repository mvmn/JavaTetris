package x.mvmn.games.tetris.oop.модель.фігури;

import java.awt.Color;

public class ФігураЗигзагПравий extends ФігураЗОбертом {

  public ФігураЗигзагПравий(Color колір, int поворот) {
    super(колір, поворот);
  }

  @Override
  public ФігураЗигзагПравий обернути() {
    return new ФігураЗигзагПравий(колір, поворот + 1);
  }

  @Override
  protected boolean заповненістьВраховуючиОберт(int x, int y) {
    return y == 1 || (y == 0 && x == 0) || (y == 2 && x == 1);
  }

  @Override
  protected int кількістьПоложень() {
    return 2;
  }
}
