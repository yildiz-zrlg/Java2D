package Transparency;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Surface extends JPanel implements ActionListener {

    private Image img;
    private Timer timer;
    private float alpha = 1f;

    private int DELAY = 40;
    private int INITIAL_DELAY = 500;

    public Surface() {

        loadImage();
        setSurfaceSize();
        initTimer();
    }

    private void loadImage() {

        img = new ImageIcon("harry.jpg").getImage();
    }

    private void setSurfaceSize() {

        int h = img.getHeight(this);
        int w = img.getWidth(this);
        setPreferredSize(new Dimension(w, h));
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.setInitialDelay(INITIAL_DELAY);
        timer.start();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        alpha += -0.01f;

        if (alpha <= 0) {

            alpha = 0;
            baştan();

        }
        AlphaComposite acomp = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(acomp);
        g2d.drawImage(img, 0, 0, null);

        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }



    private void baştan() {
        alpha=1f;
        DELAY = 40;
        INITIAL_DELAY = 500;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
}

public class DemoKarart extends JFrame {

    public DemoKarart() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        pack();

        setTitle("Fade out");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                DemoKarart ex = new DemoKarart();
                ex.setVisible(true);
            }
        });
    }
}
