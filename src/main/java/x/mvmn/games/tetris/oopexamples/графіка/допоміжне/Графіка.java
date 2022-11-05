package x.mvmn.games.tetris.oopexamples.графіка.допоміжне;

import java.awt.Color;
import java.awt.Graphics;

public class Графіка {

    private final Graphics graphics;

    public Графіка(Graphics graphics) {
        this.graphics = graphics;
    }

    public void намалюватиПіксель(int x, int y, Color колір) {
        graphics.setColor(колір);
        graphics.fillRect(x * 4, y * 4, 4, 4);
    }
}
