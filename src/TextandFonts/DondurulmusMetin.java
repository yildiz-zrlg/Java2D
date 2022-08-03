package TextandFonts;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Surfaces extends JPanel {

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        String s = "ZetCode, tutorials for programmers";

        Font font = new Font("Courier", Font.PLAIN, 16);

        g2d.translate(40, 40);

        FontRenderContext frc = g2d.getFontRenderContext();
//Stringin boyutunu belirlemek için
        GlyphVector gv = font.createGlyphVector(frc, s);
        int length = gv.getNumGlyphs();

        for (int i = 0; i < length; i++) {

            Point2D konum = gv.getGlyphPosition(i);
            double theta = (double) i / (double) (length - 1) * Math.PI / 3;
            AffineTransform at = AffineTransform.getTranslateInstance(konum.getX(),
                    konum.getY());
            at.rotate(theta);

            Shape glif = gv.getGlyphOutline(i);
            Shape dönüştürülmüşglif = at.createTransformedShape(glif);
            g2d.fill(dönüştürülmüşglif);
        }

        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class DondurulmusMetin extends JFrame {

    public DondurulmusMetin() {

        initUI();
    }

    private void initUI() {

        add(new Surfaces());

        setTitle("Rotated text");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                DondurulmusMetin ex = new DondurulmusMetin();
                ex.setVisible(true);
            }
        });
    }
}
