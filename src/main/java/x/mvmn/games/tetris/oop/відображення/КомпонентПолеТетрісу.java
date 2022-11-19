package x.mvmn.games.tetris.oop.відображення;

import x.mvmn.games.tetris.oop.модель.ПоточнаФігура;
import x.mvmn.games.tetris.oop.модель.СтанПоляТетрісу;
import x.mvmn.games.tetris.oop.модель.фігури.Фігура;
import x.mvmn.games.tetris.oop.утиліти.УтилітиЗображень;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class КомпонентПолеТетрісу extends КомпонентІзФігурою {

    private String текст;
    private final СтанПоляТетрісу станПоля;
    private ПоточнаФігура фігура;
    private static final int відступ_X = 8;
    private static final int відступ_Y = 8;
    private volatile int кутБовтання = 0;

    private final BufferedImage img = new BufferedImage(40 * 10 + 2 * 11 + відступ_X * 2, 40 * 15 + 2 * 16 + відступ_Y * 2, BufferedImage.TYPE_INT_ARGB);

    public КомпонентПолеТетрісу(СтанПоляТетрісу станПоля) {
        this.станПоля = станПоля;
    }

    @Override
    public void промалювати(Graphics g) {
        Graphics графіка = img.getGraphics();
        Graphics2D g2 = (Graphics2D) графіка;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Фон
        графіка.setColor(Color.DARK_GRAY);
        графіка.fillRect(відступ_X, відступ_Y, 404, 604);
        графіка.setColor(Color.BLACK);
        for (int i = 0; i < 11; i++) {
            графіка.fillRect(відступ_X + 1 + i * 40, відступ_Y + 1, 2, 602);
        }
        for (int i = 0; i < 16; i++) {
            графіка.fillRect(відступ_X + 1, відступ_Y + 1 + i * 40, 402, 2);
        }

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 10; x++) {
                Color колір = станПоля.колірБлоку(x, y);
                if (колір != null) {
                    замалюватиКвадратик(графіка, колір, відступ_X + 40 * x, відступ_Y + 40 * y, false);
                }
            }
        }

        // Фігура
        if (фігура != null && фігура.фігура() != null) {
            намалюватиФігуру(графіка, фігура.фігура(), фігура.x()*40 + відступ_X, фігура.y()*40 + відступ_Y);
        }

        // Текст
        if (текст != null) {
            Font шрифт = графіка.getFont();
            графіка.setFont(шрифт.deriveFont(шрифт.getSize() + 10.0f));
            Rectangle2D розмірТексту = графіка.getFontMetrics().getStringBounds(текст, графіка);
            int ширинаТексту = (int) розмірТексту.getWidth();
            int висотаТексту = (int) розмірТексту.getHeight();
            графіка.setColor(Color.BLACK);
            графіка.fillRoundRect(відступ_X + (404 - ширинаТексту - 10) / 2,
                    відступ_Y + 300 - 5 + (int) розмірТексту.getY(), ширинаТексту + 10, висотаТексту + 10, 30,
                    30);
            графіка.setColor(Color.WHITE);
            графіка.drawString(текст, відступ_X + (404 - ширинаТексту) / 2, відступ_Y + 300);
            графіка.setFont(шрифт);
        }

        BufferedImage image = img;
        int downscalePixels = 0;
        if (кутБовтання > 0) {
            int speed = кутБовтання < 8 ? (64 - кутБовтання * 8) : 4;
            downscalePixels = Math.abs(кутБовтання * 16 - (int) (System.currentTimeMillis() / speed % (кутБовтання * 32)));
            if (downscalePixels > image.getWidth() / 2) {
                downscalePixels = image.getWidth() / 2;
            }
            image = УтилітиЗображень.toBufferedImage(image.getScaledInstance(image.getWidth() - downscalePixels, image.getHeight() - downscalePixels, Image.SCALE_SMOOTH));
        }
        if (кутБовтання > 0) {
            int кут;
            if (кутБовтання >= 30) {
                кут = (int) (System.currentTimeMillis() / 10 % 360);
            } else {
                int коливання = 100 - Math.abs(200 - (int) (System.currentTimeMillis() / 4 % 400));
                кут = кутБовтання * коливання / 50;
            }
            image = УтилітиЗображень.оберт(image, кут);
        }
        g.drawImage(image, downscalePixels / 2, downscalePixels / 2, null);
    }



    public void текст(String текст) {
        this.текст = текст;
    }

    public void поточнаФігура(ПоточнаФігура фігура) {
        this.фігура = фігура;
    }

    public void кутБовтання(int кутБовтання) {
        this.кутБовтання = кутБовтання;
    }
}
