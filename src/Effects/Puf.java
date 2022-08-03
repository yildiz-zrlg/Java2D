package Effects;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


class Surfaces extends JPanel implements ActionListener {

    private Timer timer;
    private int x = 1;
    private float alpha = 1;
    private int DELAY = 40;
    private int INITIAL_DELAY = 200;

    public Surfaces() {

        initTimer();
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.setInitialDelay(INITIAL_DELAY);
        timer.start();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        x += 1;

        if (x > 10)
            alpha -= 0.01;

        if (alpha <= 0.01)
            bastan();
        RenderingHints rh =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Font font = new Font("Dialog", Font.PLAIN, x);
        g2d.setFont(font);

        FontMetrics fm = g2d.getFontMetrics();
        String s = "ZetCode";
        Dimension size = getSize();

        int w = (int) size.getWidth();
        int h = (int) size.getHeight();

        int stringWidth = fm.stringWidth(s);
        AlphaComposite ac = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(ac);

        g2d.drawString(s, (w - stringWidth) / 2, h / 2);

        g2d.dispose();
    }

    private void bastan() {
        x=1;
        alpha=1;
        DELAY = 40;
        INITIAL_DELAY = 200;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void step() {

        x += 1;

        if (x > 40)
            alpha -= 0.01;

        if (alpha <= 0.01)
            timer.stop();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

//        step();
        repaint();
    }
}

public class Puf extends JFrame {

    public Puf() {

        initUI();
    }

    private void initUI() {

        setTitle("Puff");

        add(new Surfaces());

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Puf ex = new Puf();
                ex.setVisible(true);
            }
        });
    }
}
