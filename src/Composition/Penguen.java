package Composition;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


class Surfaces extends JPanel {

    private final int RADIUS = 50;
    private Image image;
    private int x;
    private int y;
    private boolean mouseIn;

    public Surfaces() {

        initUI();
    }

    private void initUI() {

        loadImage();

        addMouseMotionListener(new MyMouseAdapter());
        addMouseListener(new MyMouseAdapter());
    }

    private void loadImage() {

        image = new ImageIcon("penguen.jpg").getImage();
    }


    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        int width=getWidth();
        int height=getHeight();


        BufferedImage bi = new BufferedImage(getWidth(),
                getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bigr = bi.createGraphics();

        if (mouseIn) {
            bigr.setPaint(Color.white);
            bigr.fillOval(x - RADIUS, y - RADIUS, RADIUS * 2,
                    RADIUS * 2);
            bigr.setComposite(AlphaComposite.SrcAtop);
            bigr.drawImage(image,0,0,width,height, this);
        }

        bigr.setComposite(AlphaComposite.SrcOver.derive(0.1f));//image üzeri şeffaf olmasını sağlar
        bigr.drawImage(image,0,0,width,height, this);
        bigr.dispose();

        g2d.drawImage(bi, 0, 0, width,height, this);

        g2d.dispose();
    }

    private class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mouseExited(MouseEvent e) {
            mouseIn = false;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            mouseIn = true;
        }

        @Override
        public void mouseMoved(MouseEvent e) {

            x = e.getX();
            y = e.getY();

            repaint();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

}

public class Penguen extends JFrame {

    public Penguen() {

        initUI();
    }

    private void initUI() {

        add(new Surfaces());

        setSize(500, 400);
        setTitle("Spotlight");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Penguen ex = new Penguen();
                ex.setVisible(true);
            }
        });
    }
}