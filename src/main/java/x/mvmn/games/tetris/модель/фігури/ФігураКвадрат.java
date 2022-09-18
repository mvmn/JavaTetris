package x.mvmn.games.tetris.модель.фігури;

import java.awt.Color;

public class ФігураКвадрат extends АбстрактнаФігура {

  public ФігураКвадрат(Color колір) {
    super(колір);
  }

  @Override
  public int ширина() {
    return 2;
  }

  @Override
  public int висота() {
    return 2;
  }

  @Override
  public boolean заповненість(int x, int y) {
    return true;
  }

  @Override
  public Фігура обернути() {
    return this;
  }

  @Override
  public boolean обертається() {
    return false;
  }
}
