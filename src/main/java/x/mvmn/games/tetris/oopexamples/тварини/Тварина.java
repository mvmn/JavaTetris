package x.mvmn.games.tetris.oopexamples.тварини;

public abstract class Тварина {

    private int вік;

    public Тварина() {
    }

    public Тварина(int вік) {
        this.setВік(вік);
    }

    public abstract void голос();

    public void setВік(int вік) {
        if (вік < 0) {
            вік = 0;
        }
        this.вік = вік;
    }

    public int getВік() {
        return вік;
    }
}
