package x.mvmn.games.tetris.oopexamples.графіка.допоміжне;

import x.mvmn.games.tetris.oopexamples.графіка.фігури.ГеомертичнаФігура;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.ЗаповненийПрямокутник;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.Пряма;
import x.mvmn.games.tetris.oopexamples.графіка.фігури.Прямокутник;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ГоловнеВікно {

    private static volatile ГеомертичнаФігура поточнаФігура;
    private static volatile Color колірМалювання = Color.WHITE;

    private static volatile ГеомертичнаФігура обранаФігура;


    public static void показати(List<ГеомертичнаФігура> фігури) {
        JFrame вікно = new JFrame();

        JComboBox<String> типМалювання = new JComboBox<>(new String[]{"Пряма", "Прямокутник", "Заповнений прямокутник"});
        типМалювання.setBorder(BorderFactory.createTitledBorder("Фігура"));
        JButton deleteShape = new JButton("Видалити фігуру");
        deleteShape.setEnabled(false);

        JSlider sliderColorRed = new JSlider(0, 255, 255);
        JSlider sliderColorGreen = new JSlider(0, 255, 255);
        JSlider sliderColorBlue = new JSlider(0, 255, 255);
        sliderColorRed.setBorder(BorderFactory.createTitledBorder("Червоний"));
        sliderColorGreen.setBorder(BorderFactory.createTitledBorder("Зелений"));
        sliderColorBlue.setBorder(BorderFactory.createTitledBorder("Синій"));
        ColorPreview colorPreview = new ColorPreview();
        colorPreview.setColor(Color.WHITE);
        colorPreview.setBorder(BorderFactory.createTitledBorder("Колір"));

        ChangeListener colorChangeListener = e -> {
            колірМалювання = new Color(sliderColorRed.getValue(),
                    sliderColorGreen.getValue(), sliderColorBlue.getValue());
            colorPreview.setColor(колірМалювання);
            colorPreview.invalidate();
            colorPreview.revalidate();
            colorPreview.repaint();
        };
        sliderColorRed.addChangeListener(colorChangeListener);
        sliderColorGreen.addChangeListener(colorChangeListener);
        sliderColorBlue.addChangeListener(colorChangeListener);

        JSlider sliderZoom = new JSlider(1, 50);
        sliderZoom.setValue(1);
        JSlider sliderAltColorRed = new JSlider(0, 255);
        JSlider sliderAltColorGreen = new JSlider(0, 255);
        JSlider sliderAltColorBlue = new JSlider(0, 255);
        sliderAltColorRed.setEnabled(false);
        sliderAltColorGreen.setEnabled(false);
        sliderAltColorBlue.setEnabled(false);

        ColorPreview altColorPreview = new ColorPreview();
        altColorPreview.setColor(Color.WHITE);
        altColorPreview.setBorder(BorderFactory.createTitledBorder("Колір"));

        sliderZoom.setBorder(BorderFactory.createTitledBorder("Збільшення"));
        sliderAltColorRed.setBorder(BorderFactory.createTitledBorder("Червоний"));
        sliderAltColorGreen.setBorder(BorderFactory.createTitledBorder("Зелений"));
        sliderAltColorBlue.setBorder(BorderFactory.createTitledBorder("Синій"));

        DefaultListModel<ГеомертичнаФігура> даніСпискуФігур = new DefaultListModel<>();
        for (ГеомертичнаФігура фігура : фігури) {
            даніСпискуФігур.addElement(фігура);
        }
        JList<ГеомертичнаФігура> списокФігур = new JList<>(даніСпискуФігур);
        списокФігур.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        списокФігур.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                обранаФігура = списокФігур.getSelectedValue();
                if (обранаФігура != null) {
                    Color колір = обранаФігура.getКолір();
                    sliderAltColorRed.setEnabled(true);
                    sliderAltColorGreen.setEnabled(true);
                    sliderAltColorBlue.setEnabled(true);
                    sliderAltColorRed.setValue(колір.getRed());
                    sliderAltColorGreen.setValue(колір.getGreen());
                    sliderAltColorBlue.setValue(колір.getBlue());
                    deleteShape.setEnabled(true);
                } else {
                    sliderAltColorRed.setEnabled(false);
                    sliderAltColorGreen.setEnabled(false);
                    sliderAltColorBlue.setEnabled(false);
                    deleteShape.setEnabled(false);
                }
                sliderAltColorRed.invalidate();
                sliderAltColorGreen.invalidate();
                sliderAltColorBlue.invalidate();
            }
        });

        JComponent component = new JComponent() {
            @Override
            public void paintComponent(Graphics graphics) {
                graphics.setColor(Color.BLACK);
                int zoom = sliderZoom.getValue();
                graphics.fillRect(0, 0, 800 * zoom, 600 * zoom);

                Графіка графіка = new Графіка(graphics, sliderZoom.getValue());
                for (int i = 0; i < даніСпискуФігур.getSize(); i++) {
                    даніСпискуФігур.getElementAt(i).намалювати(графіка);
                }
                if (поточнаФігура != null) {
                    поточнаФігура.намалювати(графіка);
                }
                Toolkit.getDefaultToolkit().sync();
            }
        };

        MouseAdapter mouseListener = new MouseAdapter() {
            private volatile Координати початок = null;

            @Override
            public void mousePressed(MouseEvent e) {
                початок = координатиМиші(e);
                поточнаФігура = створитиФігуру(типМалювання.getSelectedIndex(), початок, початок, колірМалювання);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (поточнаФігура != null) {
                    даніСпискуФігур.addElement(поточнаФігура);
                    перемалювати();
                }
                початок = null;
                поточнаФігура = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (початок != null) {
                    поточнаФігура = створитиФігуру(типМалювання.getSelectedIndex(), початок, координатиМиші(e), колірМалювання);
                }
                перемалювати();
            }

            private Координати координатиМиші(MouseEvent e) {
                int x = e.getX();
                if (x < 0) {
                    x = 0;
                }
                int y = e.getY();
                if (y < 0) {
                    y = 0;
                }
                return new Координати(x / sliderZoom.getValue(), y / sliderZoom.getValue());
            }

            private void перемалювати() {
                component.invalidate();
                component.revalidate();
                component.repaint();
            }
        };
        component.addMouseListener(mouseListener);
        component.addMouseMotionListener(mouseListener);

        ChangeListener altColorChangeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (обранаФігура != null) {
                    Color колір = new Color(sliderAltColorRed.getValue(), sliderAltColorGreen.getValue(), sliderAltColorBlue.getValue());
                    обранаФігура.setКолір(колір);
                    component.invalidate();
                    component.revalidate();
                    component.repaint();
                    списокФігур.invalidate();

                    altColorPreview.setColor(колір);
                    altColorPreview.invalidate();
                    altColorPreview.revalidate();
                    altColorPreview.repaint();
                }
            }
        };
        sliderAltColorRed.addChangeListener(altColorChangeListener);
        sliderAltColorGreen.addChangeListener(altColorChangeListener);
        sliderAltColorBlue.addChangeListener(altColorChangeListener);
        sliderZoom.addChangeListener(e -> {
            int zoom = sliderZoom.getValue();
            component.setPreferredSize(new Dimension(800 * zoom, 600 * zoom));
            component.invalidate();
            component.revalidate();
            component.repaint();
        });

        deleteShape.addActionListener(actEvent -> {
            if (списокФігур.getSelectedIndex() >= 0) {
                даніСпискуФігур.removeElementAt(списокФігур.getSelectedIndex());
                component.invalidate();
                component.revalidate();
                component.repaint();
            }
        });

        component.setPreferredSize(new Dimension(800, 600));
        JPanel pnlAlterColor = new JPanel(new GridLayout(1, 4));
        pnlAlterColor.add(sliderAltColorRed);
        pnlAlterColor.add(sliderAltColorGreen);
        pnlAlterColor.add(sliderAltColorBlue);
        pnlAlterColor.add(altColorPreview);
        pnlAlterColor.setBorder(BorderFactory.createTitledBorder("Колір обраної фігури"));

        JPanel topPanel = new JPanel(new GridLayout(2, 3));
        topPanel.add(sliderZoom);
        topPanel.add(типМалювання);
        topPanel.add(colorPreview);
        topPanel.add(sliderColorRed);
        topPanel.add(sliderColorGreen);
        topPanel.add(sliderColorBlue);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JScrollPane(списокФігур), BorderLayout.CENTER);
        rightPanel.add(deleteShape, BorderLayout.SOUTH);

        вікно.getContentPane().setLayout(new BorderLayout());
        вікно.getContentPane().add(new JScrollPane(component), BorderLayout.CENTER);
        вікно.getContentPane().add(rightPanel, BorderLayout.EAST);
        вікно.getContentPane().add(pnlAlterColor, BorderLayout.SOUTH);
        вікно.getContentPane().add(topPanel, BorderLayout.NORTH);


        вікно.pack();
        вікно.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SwingUtilities.invokeLater(() -> вікно.setVisible(true));
    }

    public static ГеомертичнаФігура створитиФігуру(int тип, Координати к1, Координати к2, Color колір) {
        switch (тип) {
            default:
            case 0:
                return new Пряма(к1, к2, колір);
            case 1:
                return new Прямокутник(к1, к2, колір);
            case 2:
                return new ЗаповненийПрямокутник(к1, к2, колір);
        }
    }
}
