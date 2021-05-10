package lib.tabbedpane;

import bin.Drawer;
import bin.Point2D;
import lib.modals.MapeoWin;
import lib.staticlass.AppProperties;
import lib.staticlass.ShapePoints;
import main.Run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;

public class Tab extends JPanel
{
    private GraphicsRunnable graphicsRunnable;

    private Color defaultBg,defaultLineColor;

    private boolean paintLines,mover;

    private int pixelSize;
    private boolean enable,selectPoint;
    private int points[];
    private JPopupMenu popupMenu;
    private Timer timer;
    private int delay=500;
    private Runnable runner;
    public Drawer drawer;

    private Tab(JFrame f, int figura)
    {
        setLayout(new BorderLayout());
        if(figura==1) {
        	drawer = new Drawer(ShapePoints.getCopyOf(ShapePoints.shape1));
        }else {
        	drawer = new Drawer(ShapePoints.getCopyOf(ShapePoints.shape2));
        }
        

        popupMenu = new JPopupMenu();

        JMenuItem mtReset = new JMenuItem("Restaurar figura");
        mtReset.addActionListener(a-> {
            drawer.restaurar();
            repaint();
        });
        popupMenu.add(mtReset);

        JMenuItem mtSelectPoint = new JMenuItem("Seleccionar punto origen");
        mtReset.addActionListener(a-> {
            cursorSelect();
        });
        popupMenu.add(mtSelectPoint);

        addMouseWheelListener(mw->{
                int sentRueda=mw.getWheelRotation();
                if(sentRueda>=0) {
                    drawer.escalaPuntoH(.995);
                }else {
                    drawer.escalaPuntoH(1.005);
                }
                repaint();
        });

        JLabel lblX = new JLabel("X: 0");
        JLabel lblY = new JLabel("Y: 0");
        JPanel pn = new JPanel(){{add(lblX);add(lblY);}};
        this.add(pn,"North");

        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                if(selectPoint){
                    int x = e.getX();
                    int y = e.getY();
                    if(ShapePoints.isInRange(points,new int[]{x,y}))
                    {
                        drawer.figuraPunt[0] = new Point2D(x,y);
                        selectPoint=false;
                        setCursor(null);
                        repaint();
                    }
                    return;
                }

                int cx=e.getX();
                int cy=e.getY();
                Point pfx=drawer.obtCoordXFigura();
                Point pfy=drawer.obtCoordYFigura();
                if(cx<pfx.x && e.getClickCount()>=2)
                    drawer.rotacionContraH(5);
                else
                if(cx>pfx.y && e.getClickCount()>=2)
                    drawer.rotacionsenConPuntoH(5);
                else
                    showMenu(e);

                if((cx>pfx.x && cx<pfx.y) && (cy>pfy.x && cy<pfy.y)) {
                    mover = true;
                    setCursor(AppProperties.handCursor);
                }
                else {
                    mover = false;
                }

                repaint();

            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
               showMenu(mouseEvent);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if(mover){
                    setCursor(null);
                }
            }
        });

        addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int cx=e.getX();
                int cy=e.getY();
                if(mover) {
                    int pcx=(int)drawer.figuraPunt[0].x;
                    int pcy=(int)drawer.figuraPunt[0].y;
                    int tx=(cx-pcx);
                    int ty=cy-pcy;
                    drawer.trasladar(tx, ty);
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                lblX.setText("X: "+mouseEvent.getX());
                lblY.setText("Y: "+mouseEvent.getY());
            }
        } );

        timer = new Timer(delay,a->runner.run());
    }

    public Tab(JFrame f, GraphicsRunnable gr,int maxWidth,int maxHeight,Color bg,Color line,int pixelSize,int figura)
    {
        this(f,figura);
        graphicsRunnable = gr;
        defaultBg = bg;
        defaultLineColor = line;
        this.paintLines = true;
        this.pixelSize = pixelSize;
        setPreferredSize(new Dimension(maxWidth,maxHeight));
    }

    public Tab(JFrame f, GraphicsRunnable gr,int maxWidth,int maxHeight,Color bg,Color line, int figura)
    {
        this(f,figura);
        graphicsRunnable = gr;
        defaultBg = bg;
        defaultLineColor = line;
        pixelSize = 10;
        setPreferredSize(new Dimension(maxWidth,maxHeight));
    }

    private void showMenu(MouseEvent mouseEvent){
        if(mouseEvent.isPopupTrigger()){
            popupMenu.show(mouseEvent.getComponent(),mouseEvent.getX(),mouseEvent.getY());
        }
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setRunner(Runnable runner) {
        this.runner = runner;
    }

    public Color getDefaultBg() {
        return defaultBg;
    }

    public void setDefaultBg(Color defaultBg) {
        this.defaultBg = defaultBg;
    }

    public Color getDefaultLineColor() {
        return defaultLineColor;
    }

    public void setDefaultLineColor(Color defaultLineColor) {
        this.defaultLineColor = defaultLineColor;
    }

    public boolean isPaintLines() {
        return paintLines;
    }

    public void setPaintLines(boolean paintLines) {
        this.paintLines = paintLines;
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        graphics.setColor(defaultBg);
        graphics.fillRect(0,0, getPreferredSize().width, getPreferredSize().height);

        if (paintLines)
        {
            graphics.setColor(defaultLineColor);

            //dibuja lineas verticales
            for(int x=pixelSize; x<=getWidth(); x+=pixelSize)
            {
                graphics.drawLine(x,0,x,getHeight());
            }

            //dibuja lineas horizontales
            for(int y=pixelSize; y<=getHeight(); y+=pixelSize)
            {
                graphics.drawLine(0,y,getWidth(),y);
            }
        }
        graphicsRunnable.paintCanvas(graphics);

        int maxx=getWidth();
        int maxy=getHeight();

        graphics.setColor(Color.black);
        graphics.fillRect(maxx-300, maxy-200, 300, 200);
        graphics.setColor(new  Color(0xF5F5F6));
        drawer.mapeoVentana(maxx, 900,maxy,450, maxx,maxy, graphics);
        drawer.drawShapePoints(graphics);

        if(enable || selectPoint){
            points = ShapePoints.getRectangleOf(drawer.figuraPunt);
            graphics.setColor(Color.red);

            graphics.drawLine(points[0],points[2], points[1],points[2]);
            graphics.drawLine(points[0],points[2], points[0],points[3]);
            graphics.drawLine(points[1],points[2],points[1],points[3]);
            graphics.drawLine(points[0],points[3],points[1],points[3]);
        }
    }

    public void setStatusTimer(boolean start){
        if(start) timer.start(); else timer.stop();
    }

    public void enableArea(){
        enable = !enable;
        repaint();
    }

    public void cursorSelect(){
        setCursor(AppProperties.selectCursor);
        selectPoint = true;
        repaint();
    }
}
