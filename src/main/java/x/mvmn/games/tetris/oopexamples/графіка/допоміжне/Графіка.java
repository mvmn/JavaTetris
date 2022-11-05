package x.mvmn.games.tetris.oopexamples.графіка.допоміжне;

import java.awt.Color;
import java.awt.Graphics;

public class Графіка {

    private final Graphics graphics;

    private final int розмірПікселя;

    public Графіка(Graphics graphics, int розмірПікселя) {
        this.graphics = graphics;
        this.розмірПікселя = розмірПікселя;
    }

    public void намалюватиПіксель(int x, int y, Color колір) {
        graphics.setColor(колір);
        graphics.fillRect(x * розмірПікселя, y * розмірПікселя, розмірПікселя, розмірПікселя);
    }
}
