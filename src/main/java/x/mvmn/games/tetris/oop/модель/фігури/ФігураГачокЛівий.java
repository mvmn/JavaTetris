package x.mvmn.games.tetris.oop.модель.фігури;

import java.awt.Color;

public class ФігураГачокЛівий extends ФігураЗОбертом {

  public ФігураГачокЛівий(Color колір, int поворот) {
    super(колір, поворот);
  }

  @Override
  public ФігураГачокЛівий обернути() {
    return new ФігураГачокЛівий(колір, поворот + 1);
  }

  @Override
  protected boolean заповненістьВраховуючиОберт(int x, int y) {
    return y == 0 || x > 0;
  }
}
