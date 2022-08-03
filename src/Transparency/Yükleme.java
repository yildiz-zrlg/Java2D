package Transparency;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.EventQueue;
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
    private int count=0;
    private final int INITIAL_DELAY = 100;
    private final int DELAY = 100;
    private final int NUMBER_OF_LINES = 8;
    private final int STROKE_WIDTH = 3;

    private final double[][] trs = {
            {0.0, 0.15, 0.30, 0.5, 0.65, 0.80, 0.9, 1.0},
            {1.0, 0.0, 0.15, 0.30, 0.5, 0.65, 0.8, 0.9},
            {0.9, 1.0, 0.0, 0.15, 0.3, 0.5, 0.65, 0.8},
            {0.8, 0.9, 1.0, 0.0, 0.15, 0.3, 0.5, 0.65},
            {0.65, 0.8, 0.9, 1.0, 0.0, 0.15, 0.3, 0.5},
            {0.5, 0.65, 0.8, 0.9, 1.0, 0.0, 0.15, 0.3},
            {0.3, 0.5, 0.65, 0.8, 0.9, 1.0, 0.0, 0.15},
            {0.15, 0.3, 0.5, 0.65, 0.8, 0.9, 1.0, 0.0}
    };

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

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        int width = getWidth();
        int height = getHeight();

        g2d.setStroke(new BasicStroke(STROKE_WIDTH, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        g2d.translate(width / 2, height / 2);

        for (int i = 0; i < NUMBER_OF_LINES; i++) {

            float alpha = (float) trs[count % NUMBER_OF_LINES][i];
            AlphaComposite acomp = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(acomp);
//
            g2d.rotate(Math.PI / 4f);
            g2d.drawLine(0, -10, 0, -40);
        }

        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
        count++;
    }
}

public class Yükleme extends JFrame {

    public Yükleme() {

        initUI();
    }

    private void initUI() {

        add(new Surfaces());

        setTitle("Waiting");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

 //       EventQueue.invokeLater(new Runnable() {
 //           @Override
 //           public void run() {

                Yükleme ex = new Yükleme();
                ex.setVisible(true);
            }
 //       });
    }
//}
