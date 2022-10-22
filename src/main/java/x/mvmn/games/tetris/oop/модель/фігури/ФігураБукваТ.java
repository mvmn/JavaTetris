package x.mvmn.games.tetris.oop.модель.фігури;

import java.awt.Color;

public class ФігураБукваТ extends ФігураЗОбертом {

  public ФігураБукваТ(Color колір, int поворот) {
    super(колір, поворот);
  }

  @Override
  public ФігураБукваТ обернути() {
    return new ФігураБукваТ(колір, поворот + 1);
  }

  @Override
  protected boolean заповненістьВраховуючиОберт(int x, int y) {
    return y == 1 || x == 1;
  }
}
