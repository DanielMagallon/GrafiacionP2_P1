package lib.modals;

import lib.tabbedpane.GraphicsRunnable;

import javax.swing.*;
import java.awt.*;

public class MapeoWin extends JDialog
{
    private JPanel canvas;
    private GraphicsRunnable gr;

    public JToggleButton arrastrar;

    public MapeoWin(JFrame f, int w, int h)
    {
        super(f,false);
//        setUndecorated(true);
        canvas =    new JPanel(){
           
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                if(gr!=null)
                    gr.paintCanvas(graphics);
            }
        };
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


        setLocation(f.getWidth()-w-50,f.getHeight()-h-50);
        getContentPane().add(canvas);
        canvas.setBackground(Color.white);
        setSize(w,h);
    }

    public void setGraphicsRunnable(GraphicsRunnable gr)
    {
        this.gr = gr;
    }

    public void updatePaint(){
        canvas.repaint();
    }
}
