package x.mvmn.games.tetris.oop.модель.фігури;

import java.awt.Color;

public class ФігураГачокПравий extends ФігураЗОбертом {

  public ФігураГачокПравий(Color колір, int поворот) {
    super(колір, поворот);
  }

  @Override
  public ФігураГачокПравий обернути() {
    return new ФігураГачокПравий(колір, поворот + 1);
  }

  @Override
  protected boolean заповненістьВраховуючиОберт(int x, int y) {
    return y == 0 || x < 1;
  }
}
