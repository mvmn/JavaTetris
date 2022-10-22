package x.mvmn.games.tetris.oop.відображення;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import x.mvmn.games.tetris.oop.сервіс.СлухачВводуКористувача;

public class ГоловнеВікно extends JFrame {
//  private static final long serialVersionUID = 8318922323317937630L;

  private static final int ширина = 800;
  private static final int висота = 620;

  private final JComponent вміст;
  private final List<КомпонентВідображення> компоненти = new ArrayList<>();

  public ГоловнеВікно() {
    this.getContentPane().setLayout(new BorderLayout());

    вміст = new JComponent() {
      private static final long serialVersionUID = -178839994636617669L;

      @Override
      public void paintComponent(Graphics графіка) {
        відмальовка(графіка);
      }
    };

    вміст.setPreferredSize(new Dimension(ширина, висота));

    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.getContentPane().add(вміст, BorderLayout.CENTER);
    this.pack();
    this.setResizable(false);
  }

  protected void відмальовка(Graphics графіка) {
    for (КомпонентВідображення компонент : компоненти) {
      компонент.промалювати(графіка);
    }
    Toolkit.getDefaultToolkit().sync();
  }

  public void перемалюватиВміст() {
    вміст.repaint();
  }

  public void додатиКомпонент(КомпонентВідображення компонент) {
    компоненти.add(компонент);
  }

  public void додатиСлухачВводу(СлухачВводуКористувача слухачВводу) {
    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
          слухачВводу.натиснутоПробіл();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
          слухачВводу.натиснутоВгору();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          слухачВводу.натиснутоДонизу();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          слухачВводу.натиснутоВліво();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          слухачВводу.натиснутоВправо();
        }
      }
    });

    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        слухачВводу.натиснутоВихід();
      }
    });
  }
}
