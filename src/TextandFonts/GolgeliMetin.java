package TextandFonts;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GolgeliMetin extends JFrame {

    private final int width = 490;
    private final int height = 150;

    private final String yazi = "Disciplin ist macht";
    private TextLayout textLayout;

    public GolgeliMetin() {

        initUI();
    }

    private void initUI() {

        setTitle("Shadowed Text");

        BufferedImage image = createImage();
        add(new JLabel(new ImageIcon(image)));

        setSize(490, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setRenderingHints(Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    }

    private BufferedImage createImage()  {

        int x = 30;
        int y = 90;

        Font font = new Font("Georgia", Font.ITALIC, 50);

        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g1d = image.createGraphics();
        setRenderingHints(g1d);
        textLayout = new TextLayout(yazi, font, g1d.getFontRenderContext());
        g1d.setPaint(Color.WHITE);
        g1d.fillRect(0, 0, width, height);

        g1d.setPaint(new Color(150, 150, 150));
        textLayout.draw(g1d, x+3, y+3);
        g1d.dispose();

        float[] kernel = {
                1f / 9f, 1f / 9f, 1f / 9f,
                1f / 9f, 1f / 9f, 1f / 9f,
                1f / 9f, 1f / 9f, 1f / 9f
        };

        ConvolveOp op =  new ConvolveOp(new Kernel(3, 3, kernel),
                ConvolveOp.EDGE_NO_OP, null);
 //Bulan??kla??t??rma efektini ilk g??r??nt??ye uygular??z ve sonucu ikinci arabelle??e al??nan g??r??nt??ye kopyalar??z.
        BufferedImage image2 = op.filter(image, null);

        Graphics2D g2d = image2.createGraphics();
        setRenderingHints(g2d);
        g2d.setPaint(Color.BLACK);
        textLayout.draw(g2d, x, y);

        g2d.dispose();

        return image2;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                GolgeliMetin ex = new GolgeliMetin();
                ex.setVisible(true);
            }
        });
    }
}
