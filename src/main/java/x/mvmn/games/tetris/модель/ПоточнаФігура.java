package x.mvmn.games.tetris.модель;

import x.mvmn.games.tetris.модель.фігури.Фігура;

public class ПоточнаФігура {
  private Фігура фігура;
  private int x;
  private int y;

  public ПоточнаФігура() {}

  public ПоточнаФігура(Фігура фігура) {
    this.фігура = фігура;

    x = 5 - фігура.ширина() / 2;
    y = 0;
  }

  public Фігура фігура() {
    return фігура;
  }

  public void фігура(Фігура фігура) {
    this.фігура = фігура;
  }

  public int x() {
    return x;
  }

  public void x(int x) {
    this.x = x;
  }

  public int y() {
    return y;
  }

  public void y(int y) {
    this.y = y;
  }
}
