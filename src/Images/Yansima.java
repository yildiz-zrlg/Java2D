package Images;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Surfacess extends JPanel {

    private BufferedImage image;
    private BufferedImage refImage;
    private int img_w;
    private int img_h;
    private final int SPACE = 0;

    public Surfacess() {

        loadImage();
        getImageSize();
        createReflectedImage();
    }

    private void loadImage() {

        try {

            image = ImageIO.read(new File("harry.jpg"));
        } catch (Exception ex) {

            Logger.getLogger(Surfacess.class.getName()).log(
                    Level.WARNING, null, ex);
        }
    }

    private void getImageSize() {

        img_w = image.getWidth();
        img_h = image.getHeight();
    }

    private void createReflectedImage() {

        float opacity = 0.4f;
        float fadeHeight = 0.3f;

        refImage = new BufferedImage(img_w, img_h,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D rg = refImage.createGraphics();
        rg.drawImage(image, 0, 0, null);
        rg.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
        rg.setPaint(new GradientPaint(0, img_h * fadeHeight,
                new Color(0.0f, 0.0f, 0.0f, 0.0f), 0, img_h,
                new Color(0.0f, 0.0f, 0.0f, opacity)));

        rg.fillRect(0, 0, img_w, img_h);
        rg.dispose();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        int gap = 0;

        g2d.setPaint(new GradientPaint(0, 0, Color.black, 0,
                height, Color.darkGray));
        g2d.fillRect(0, 0, width, height);
        g2d.translate((width - img_w) / 2, height / 2 - img_h);
        g2d.drawImage(image, 0, 0, null);
        g2d.translate(0, 2 * img_h + gap);
        g2d.scale(1, -1);

        g2d.drawImage(refImage, 0, 0, null);

        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(img_w + 2 * SPACE, 2 * img_h + 3 * SPACE);
    }
}

public class Yansima extends JFrame {

    public Yansima() {

        initUI();
    }

    private void initUI() {

        add(new Surfacess());
        pack();

        setTitle("Reflection");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Yansima ex = new Yansima();
                ex.setVisible(true);
            }
        });
    }
}
