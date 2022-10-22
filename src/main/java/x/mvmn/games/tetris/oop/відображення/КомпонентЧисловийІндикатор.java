package x.mvmn.games.tetris.oop.відображення;

import java.awt.*;

public class КомпонентЧисловийІндикатор implements КомпонентВідображення {

    protected int стан;
    protected String текст;
    protected int x;
    protected int y;
    private int ширина;
    private int висота;
    private int відступТекстуX;
    private int відступТекстуY;

    public КомпонентЧисловийІндикатор(String текст, int x, int y, int ширина, int висота,
                                      int відступТекстуX, int відступТекстуY) {
        this.текст = текст;
        this.x = x;
        this.y = y;
        this.ширина = ширина;
        this.висота = висота;
        this.відступТекстуX = відступТекстуX;
        this.відступТекстуY = відступТекстуY;
    }

    @Override
    public void промалювати(Graphics графіка) {
        графіка.setColor(Color.BLACK);
        графіка.fillRoundRect(x, y, ширина, висота, 25, 25);
        графіка.setColor(Color.WHITE);
        графіка.drawString(текст + стан, x + відступТекстуX, y + відступТекстуY);
    }

    public void стан(int значення) {
        this.стан = значення;
    }
}
