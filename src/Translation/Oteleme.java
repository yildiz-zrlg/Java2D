package Translation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Surface extends JPanel {

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setPaint(new Color(150, 150, 150));
        g2d.fillRect(20, 20, 80, 50);
        //önceki dikdörtgenin x de 100 y de 50 öteden çizilmesini sağlar
        g2d.translate(100, 50);
        g2d.fillRect(20, 20, 80, 50);

        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class Oteleme extends JFrame {

    public Oteleme() {

        initUI();
    }

    private void initUI() {

        add(new Surfaces());

        setTitle("Translation");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Oteleme ex = new Oteleme();
                ex.setVisible(true);
            }
        });
    }
}
