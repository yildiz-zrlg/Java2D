package Composition;

import java.awt.AlphaComposite;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Surface extends JPanel implements ActionListener {

    private Image sun;
    private Image cloud;
    private Timer timer;
    private float alpha = 1f;

    private final int DELAY = 600;

    public Surface() {

        loadImages();
        initTimer();
    }

    private void loadImages() {

        sun = new ImageIcon("sun.jpg").getImage();
        cloud = new ImageIcon("cloud.jpg").getImage();
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void doDrawing(Graphics g) {
        int width=getWidth();
        int height=getHeight();

        Graphics2D g2d = (Graphics2D) g.create();

        BufferedImage buffImg = new BufferedImage(220, 140,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbi = buffImg.createGraphics();

        AlphaComposite ac = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);

        gbi.drawImage(sun,0,0, 230, 150, null);
        gbi.setComposite(ac);
        gbi.drawImage(cloud, 0, 0,230,150, null);

        g2d.drawImage(buffImg, 0, 0, width,height,null);

        gbi.dispose();
        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void step() {

        alpha -= 0.1;

        if (alpha <= 0) {

            alpha = 0;
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        step();
        repaint();
    }
}

public class GüneşBulut extends JFrame {

    public GüneşBulut() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        setTitle("Sun and cloud");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                GüneşBulut ex = new GüneşBulut();
                ex.setVisible(true);
            }
        });
    }
}