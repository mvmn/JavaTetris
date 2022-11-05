package x.mvmn.games.tetris.oopexamples.графіка.допоміжне;

import x.mvmn.games.tetris.oopexamples.графіка.фігури.ГеомертичнаФігура;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.List;

public class ГоловнеВікно {
    public static void показати(List<ГеомертичнаФігура> фігури) {
        JFrame вікно = new JFrame();
        вікно.setSize(800, 600);

        вікно.getContentPane().setLayout(new BorderLayout());

        JComponent component = new JComponent() {
            @Override
            public void paintComponent(Graphics graphics) {
                graphics.setColor(Color.BLACK);
                graphics.fillRect(0, 0, 800, 600);

                Графіка графіка = new Графіка(graphics);
                for(ГеомертичнаФігура  фігура : фігури) {
                    фігура.намалювати(графіка);
                    Toolkit.getDefaultToolkit().sync();
                }
            }
        };
        component.setPreferredSize(new Dimension(800, 600));
        вікно.getContentPane().add(component, BorderLayout.CENTER);

        вікно.setResizable(false);
        вікно.pack();
        вікно.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        вікно.setVisible(true);
    }
}
