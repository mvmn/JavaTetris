package x.mvmn.games.tetris.procedural;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class ProceduralTetris {

    private static JComponent вміст;
    private static int рахунок;
    private static int рівень;
    private static int кількістьКроківДоПониженняФігури;
    private static int поточнаКількістьКроківДоПониженняФігури;
    private static volatile boolean попросилиВихід = false;
    private static boolean[][] наступнаФігура;
    private static Color наступнаФігура_Колір;
    private static boolean[][] поточнаФігура;
    private static Color поточнаФігура_Колір;
    private static int поточнаФігура_X;
    private static int поточнаФігура_Y;
    private static volatile boolean граВПроцесі;
    private static volatile boolean пауза;
    private static Color[][] наскладаніКубики = new Color[10][15];
    private static Color[] кольори = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA,
            Color.YELLOW.darker(), Color.ORANGE, Color.PINK};
    private static Random генераторВипадковихЧисел = new Random();

    private static final int кількістьРядківДоНаступогоРівня = 10;
    private static final int повільністьПочатковогоРівня = 50;

    private static int поточнаКількістьРядківДоНаступогоРівня = кількістьРядківДоНаступогоРівня;

    public static void main(String[] args) {
        створитиВікноГри();
        граВПроцесі = false;
        while (!попросилиВихід) {
            вміст.repaint();
            while (!граВПроцесі && !попросилиВихід) {
                Thread.yield();
            }
            передПочатокГри();
            while (граВПроцесі && !попросилиВихід) {
                крокГри();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    private static void створитиВікноГри() {
        int ширина = 800;
        int висота = 620;

        JFrame вікно = new JFrame();
        вікно.getContentPane().setLayout(new BorderLayout());

        вміст = new JComponent() {
            private static final long serialVersionUID = -178839994636617669L;

            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                відмальовка(g);
                Toolkit.getDefaultToolkit().sync();
            }
        };

        вміст.setPreferredSize(new Dimension(ширина, висота));
        вікно.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (!граВПроцесі) {
                        граВПроцесі = true;
                    } else if (пауза) {
                        пауза = false;
                    }
                } else if (граВПроцесі) {
                    if (e.getKeyCode() == KeyEvent.VK_P) {
                        пауза = !пауза;
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        обeртФігури();
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        пониженняФігури();
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (поточнаФігура_X > 0 && !колізіяЗНаскладанимиКубіками(поточнаФігура,
                                поточнаФігура_X - 1, поточнаФігура_Y)) {
                            поточнаФігура_X--;
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (поточнаФігура_X + ширинаФігури(поточнаФігура) < 10
                                && !колізіяЗНаскладанимиКубіками(поточнаФігура, поточнаФігура_X + 1,
                                поточнаФігура_Y)) {
                            поточнаФігура_X++;
                        }
                    }
                }
            }
        });

        вікно.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        вікно.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                вихід();
            }
        });
        вікно.getContentPane().add(вміст, BorderLayout.CENTER);
        вікно.pack();
        вікно.setResizable(false);
        SwingUtilities.invokeLater(() -> вікно.setVisible(true));
    }

    private static void відмалюватиФігуру(Graphics g, int зміщенняX, int зміщенняY, boolean[][] фігура, Color колірФігури, boolean анімація) {
        for (int y = 0; y < фігура.length; y++) {
            for (int x = 0; x < фігура[y].length; x++) {
                if (фігура[y][x]) {
                    замалюватиКвадратикФігури(g, колірФігури,
                            зміщенняX + 40 * x,
                            зміщенняY + 40 * y, анімація);
                }
            }
        }
    }

    private static void відмальовка(Graphics графіка) {
        int відступ_X = 8;
        int відступ_Y = 8;

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

        // Рахунок і рівень
        графіка.fill3DRect(422, 8, 370, 40, true);
        графіка.fill3DRect(422, 58, 370, 40, true);
        графіка.fill3DRect(422, 108, 370, 370, true);

        графіка.setColor(Color.WHITE);
        графіка.drawString("Рахунок: " + рахунок, 430, 32);
        графіка.drawString("Рівень: " + рівень, 430, 82);
        графіка.drawString("Наступна фігура: ", 430, 132);

        // Поточна фігура
        if (граВПроцесі) {
            int зміщенняX = відступ_X + поточнаФігура_X * 40;
            int зміщенняY = відступ_Y + поточнаФігура_Y * 40;
            відмалюватиФігуру(графіка, зміщенняX, зміщенняY, поточнаФігура, поточнаФігура_Колір, true);
            відмалюватиФігуру(графіка, 422 + 370 / 2 - ширинаФігури(наступнаФігура) * 40 / 2,
                    108 + 370 / 2 - висотаФігури(наступнаФігура) * 40 / 2,
                    наступнаФігура, наступнаФігура_Колір, false);
        }

        // Наскладані кубики
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 10; x++) {
                Color колір = наскладаніКубики[x][y];
                if (колір != null) {
                    замалюватиКвадратикФігури(графіка, колір, відступ_X + 40 * x, відступ_Y + 40 * y, false);
                }
            }
        }

        // Текст
        if (!граВПроцесі || пауза) {
            Font шрифт = графіка.getFont();
            графіка.setFont(шрифт.deriveFont(шрифт.getSize() + 10.0f));
            String текст = !граВПроцесі ? "Натисніть ПРОБІЛ щоб почати" : "Пауза";
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
    }

    private static void крокГри() {
        вміст.repaint();

        if (пауза) {
            Thread.yield();
            return;
        }

        // Опускаєм фігуру
        поточнаКількістьКроківДоПониженняФігури--;
        if (поточнаКількістьКроківДоПониженняФігури <= 0) {
            пониженняФігури();
        }
    }

    private static void пониженняФігури() {
        поточнаКількістьКроківДоПониженняФігури = кількістьКроківДоПониженняФігури;

        if (колізіяЗНаскладанимиКубіками(поточнаФігура, поточнаФігура_X, поточнаФігура_Y + 1)
                || (поточнаФігура_Y + висотаФігури(поточнаФігура) == 15)) {
            приземлення();
            перевіркаНаЗаповненіРядки();
            наступнаФігура();
            if (колізіяЗНаскладанимиКубіками(поточнаФігура, поточнаФігура_X, поточнаФігура_Y)) {
                програш();
            }
        } else {
            поточнаФігура_Y += 1;
        }
    }

    private static void передПочатокГри() {
        рахунок = 0;
        рівень = 0;
        кількістьКроківДоПониженняФігури = повільністьПочатковогоРівня;
        поточнаКількістьКроківДоПониженняФігури = кількістьКроківДоПониженняФігури;
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 15; k++) {
                наскладаніКубики[i][k] = null;
            }
        }
        наступнаФігура();
        пауза = false;
    }

    private static void створитиНаступнуФігуру() {
        наступнаФігура = Фігури.ФІГУРИ[генераторВипадковихЧисел.nextInt(Фігури.ФІГУРИ.length)];
        Color попереднійКолір = наступнаФігура_Колір;
        do {
            наступнаФігура_Колір = кольори[генераторВипадковихЧисел.nextInt(кольори.length)];
        } while (попереднійКолір != null && попереднійКолір == наступнаФігура_Колір);
    }

    private static void наступнаФігура() {
        if (наступнаФігура == null) {
            створитиНаступнуФігуру();
        }

        поточнаФігура = наступнаФігура;
        поточнаФігура_Колір = наступнаФігура_Колір;
        поточнаФігура_X = 5 - ширинаФігури(поточнаФігура) / 2;
        поточнаФігура_Y = 0;
        створитиНаступнуФігуру();
    }

    private static void наступнийРівень() {
        рахунок += 100 * рівень;
        рівень++;
        кількістьКроківДоПониженняФігури -= 5;
        if (кількістьКроківДоПониженняФігури < 0) {
            кількістьКроківДоПониженняФігури = 0;
        }
        поточнаКількістьКроківДоПониженняФігури = кількістьКроківДоПониженняФігури;
        поточнаКількістьРядківДоНаступогоРівня = кількістьРядківДоНаступогоРівня;
    }

    private static void вихід() {
        попросилиВихід = true;
    }

    private static void замалюватиКвадратикФігури(Graphics g, Color колір, int x, int y, boolean анімація) {
        if (анімація) {
            int animationValue = Math.abs(32 - (int) (System.currentTimeMillis() / 10 % 64));
            g.setColor(колір);
            g.fillRoundRect(x + 4, y + 4, 36, 36, animationValue / 2, animationValue / 2);
            float[] hsb = Color.RGBtoHSB(колір.getRed(), колір.getGreen(), колір.getBlue(), null);
            g.setColor(Color.getHSBColor(0.5f - hsb[0], 0 + animationValue / 64.f, 1f));
            g.fillRoundRect(x + 8, y + 8, 28, 28, animationValue / 2, animationValue / 2);
            g.setColor(колір);
            g.fillOval(x + 14 + animationValue / 8, y + 14 + animationValue / 8, 16 - animationValue / 4, 16 - animationValue / 4);
        } else {
            g.setColor(колір);
            g.fillRoundRect(x + 4, y + 4, 36, 36, 8, 8);
            g.setColor(Color.WHITE);
            g.fillRoundRect(x + 8, y + 8, 28, 28, 16, 16);
            g.setColor(колір);
            g.fillOval(x + 14, y + 14, 16, 16);
        }
    }

    private static int висотаФігури(boolean[][] фігура) {
        return фігура.length;
    }

    private static int ширинаФігури(boolean[][] фігура) {
        return фігура[0].length;
    }

    private static void обeртФігури() {
        if (висотаФігури(поточнаФігура) == 2 && ширинаФігури(поточнаФігура) == 2) {
            // Якщо квадрат - обертати не треба
            return;
        }
        if (висотаФігури(поточнаФігура) == 4) {
            поточнаФігура_X--;
            if (поточнаФігура_X < 0) {
                поточнаФігура_X = 0;
            }
            поточнаФігура_Y++;
        } else if (ширинаФігури(поточнаФігура) == 4) {
            поточнаФігура_X++;
            поточнаФігура_Y--;
            if (поточнаФігура_Y < 0) {
                поточнаФігура_Y = 0;
            }
        }
        boolean[][] повернутаФігура = new boolean[поточнаФігура[0].length][поточнаФігура.length];
        for (int y = 0; y < поточнаФігура.length; y++) {
            for (int x = 0; x < поточнаФігура[y].length; x++) {
                повернутаФігура[x][поточнаФігура.length - y - 1] = поточнаФігура[y][x];
            }
        }
        if (поточнаФігура_X + ширинаФігури(повернутаФігура) > 10) {
            поточнаФігура_X = 10 - ширинаФігури(повернутаФігура);
        }
        if (!колізіяЗНаскладанимиКубіками(повернутаФігура, поточнаФігура_X, поточнаФігура_Y)) {
            поточнаФігура = повернутаФігура;
        }
        відмальовка(вміст.getGraphics());
    }

    private static boolean колізіяЗНаскладанимиКубіками(boolean[][] фігура, int fx, int fy) {
        for (int y = 0; y < фігура.length; y++) {
            for (int x = 0; x < фігура[y].length; x++) {
                if (fy + y < 15 && наскладаніКубики[fx + x][fy + y] != null && фігура[y][x]) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void приземлення() {
        for (int y = 0; y < поточнаФігура.length; y++) {
            for (int x = 0; x < поточнаФігура[y].length; x++) {
                if (поточнаФігура[y][x]) {
                    наскладаніКубики[поточнаФігура_X + x][поточнаФігура_Y + y] = поточнаФігура_Колір;
                }
            }
        }
    }

    private static void перевіркаНаЗаповненіРядки() {
        for (int y = 0; y < наскладаніКубики[0].length; y++) {
            boolean рядокЗаповнений = true;
            for (int x = 0; x < наскладаніКубики.length; x++) {
                if (наскладаніКубики[x][y] == null) {
                    рядокЗаповнений = false;
                    break;
                }
            }
            if (рядокЗаповнений) {
                опрацюватиЗаповненийРядок(y);
            }
        }
    }

    private static void опрацюватиЗаповненийРядок(int заповненийРядок_y) {
        for (int y = заповненийРядок_y; y > 0; y--) {
            for (int x = 0; x < наскладаніКубики.length; x++) {
                наскладаніКубики[x][y] = наскладаніКубики[x][y - 1];
            }
        }
        for (int x = 0; x < наскладаніКубики.length; x++) {
            наскладаніКубики[x][0] = null;
        }
        рахунок += (рівень + 1) * 10;

        поточнаКількістьРядківДоНаступогоРівня--;
        if (поточнаКількістьРядківДоНаступогоРівня < 1) {
            наступнийРівень();
        }
    }

    private static void програш() {
        граВПроцесі = false;
    }
}