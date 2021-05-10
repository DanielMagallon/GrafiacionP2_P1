package main;

import lib.frame.DefaultFrame;
import lib.modals.*;
import lib.sidebar.SideBar;
import lib.staticlass.ImageLoader;
import lib.staticlass.OptionsPaint;
import lib.staticlass.ShapePoints;
import lib.tabbedpane.TabbedPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Arrays;

public class Run
{
    public static DefaultFrame frame;
    public static TabbedPane tabbedPane;
    private static SideBar sideBar;
    private static JScrollPane sc;
    private static RotateWin modalRotar;
    private static ScaleWin modalEscalar;
    private static ShearWin modalShear;
    private static TranslateWin modalTras;
    private static Autores modalAutores;
    private static Help modalHelp;
    public static OptionsPaint optionPaint = OptionsPaint.DEFAULT;

    private static Figuraopcion escojerfig;
    private static JMenuBar barraM;
    private static JMenu Menu1,Menu2;
    private static JMenuItem opcRest,opcEsc,opcRotar,opcDef, opcRefX,opcRefY,opcTras,opcSalir,opcDes,opcAyu;

    private static void initJMenuBar(){
        barraM=new JMenuBar();
        frame.setJMenuBar(barraM);
        Menu1=new JMenu("Transformaciones");
        Menu2=new JMenu("Acerca de");


        opcRest=new JMenuItem("Restaurar");
        opcRest.setMnemonic('R');
        opcRest.setToolTipText("Restaura la figura a la original");
        opcRest.setIcon(ImageLoader.resetR);
        opcRest.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.ALT_MASK));

        opcEsc=new JMenuItem("Escalar");
        opcEsc.setMnemonic('E');
        opcEsc.setToolTipText("Escala la figura al tamano deseado");
        opcEsc.setIcon(ImageLoader.escalarPosR);
        opcEsc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.ALT_MASK));

        opcRotar=new JMenuItem("Rotacion");
        opcRotar.setToolTipText("Rota la figura en el sentido deseado");
        opcRotar.setIcon(ImageLoader.rotateDRImage);
        opcRotar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.ALT_MASK));

        opcDef=new JMenuItem("Deformar");
        opcDef.setMnemonic('D');
        opcDef.setToolTipText("Deformar la figura a la cantidad deseada");
        opcDef.setIcon(ImageLoader.defXR);
        opcDef.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.ALT_MASK));

        opcRefX = new JMenuItem("Refleccion en X");
        opcRefX.setMnemonic('X');
        opcRefX.setToolTipText("Refleccion en X a la figura en sus cuadrantes");
        opcRefX.setIcon(ImageLoader.refXR);
        opcRefX.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.ALT_MASK));

        opcRefY = new JMenuItem("Refleccion en Y");
        opcRefY.setMnemonic('Y');
        opcRefY.setToolTipText("Refleccion en Y a la figura en sus cuadrantes");
        opcRefY.setIcon(ImageLoader.refYR);
        opcRefY.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,InputEvent.ALT_MASK));

        opcTras =new JMenuItem("Trasladar");
        opcTras.setMnemonic('T');
        opcTras.setToolTipText("Trasladar la figura a unas coordenadas deseadas");
        opcTras.setIcon(ImageLoader.trasR);
        opcTras.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.ALT_MASK));

        opcSalir=new JMenuItem("Salir");
        opcSalir.setMnemonic('S');
        opcSalir.setToolTipText("Salir del programa");
        URL rutaIm= Run.class.getResource("/rsc/menuimg/s.png");
        opcSalir.setIcon(new ImageIcon(rutaIm));
        opcSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.ALT_MASK));
        opcSalir.addActionListener(a->System.exit(0));
        Menu1.add(opcRest);
        Menu1.add(opcEsc);
        Menu1.add(opcRotar);
        Menu1.add(opcDef);
        Menu1.add(opcRefX);
        Menu1.add(opcRefY);
        Menu1.add(opcTras);
        Menu1.addSeparator();
        Menu1.add(opcSalir);

        opcDes=new JMenuItem("Autor(es)");
        opcDes.setToolTipText("Muestra los autores de la apliaccion");
        rutaIm= Run.class.getResource("/rsc/menuimg/refle.png");
        opcDes.setIcon(new ImageIcon(rutaIm));
        opcDes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.ALT_MASK));
        opcAyu=new JMenuItem("Ayuda");
        opcAyu.setMnemonic('A');
        opcAyu.setToolTipText("Ayuda del programa");
        rutaIm= Run.class.getResource("/rsc/menuimg/ayuda.png");
        opcAyu.setIcon(new ImageIcon(rutaIm));
        opcAyu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
        Menu2.add(opcDes);
        Menu2.add(opcAyu);
        //imagen de fondo

        JMenu archivo = new JMenu("Archivo");
        archivo.add(getMenuItem("Nueva pestania", Run::addNewTab,ImageLoader.newTabRImage,
                KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK),'N'));
        archivo.add(getMenuItem("Cerrar pestania", ()->tabbedPane.close(), ImageLoader.cerrarR,
                KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK),'C'));
        archivo.addSeparator();
        archivo.add(getMenuItem("Mostrar grid", ()->tabbedPane.showGrid(),ImageLoader.enableGridRImage,
                KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK),'M'));
        archivo.add(getMenuItem("Restaurar figura", ()->{tabbedPane.resetShape();tabbedPane.updatePaint();},
                ImageLoader.resetR,
                KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK),'R'));
        archivo.add(getMenuItem("Seleccionar punto origen", ()->tabbedPane.puntoOrigen(),ImageLoader.origenR,
                KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK),'P'));
        archivo.add(getMenuItem("Mostrar area/contorno  de la figura", ()->tabbedPane.enableArea(),
                ImageLoader.mostrarAreaR,KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK),'A'));
        barraM.add(archivo);
        barraM.add(Menu1);
        barraM.add(Menu2);

        opcDes.addActionListener(Run::menuListener);
        opcAyu.addActionListener(Run::menuListener);
        opcTras.addActionListener(Run::menuListener);
        opcRefX.addActionListener(Run::menuListener);
        opcRefY.addActionListener(Run::menuListener);
        opcDef.addActionListener(Run::menuListener);
        opcEsc.addActionListener(Run::menuListener);
        opcRest.addActionListener(Run::menuListener);
        opcRotar.addActionListener(Run::menuListener);
    }

    private static JMenuItem getMenuItem(String text,Runnable handler, ImageIcon ic,KeyStroke keyStroke,
                                         char mnemonic){
        JMenuItem mt = new JMenuItem(text);
        mt.setIcon(ic);
        mt.addActionListener(a->handler.run());
        mt.setMnemonic(mnemonic);
        mt.setAccelerator(keyStroke);

        return mt;
    }


    private static void menuListener(ActionEvent e){

            if(e.getSource()==opcAyu){
                modalHelp.setVisible(true);
            }else if(e.getSource()==opcDes){
                modalAutores.setVisible(true);
            }
            else if(e.getSource()==opcTras)
            {
                int res[] = modalTras.mostrar();

                if (res!=null)
                {
                    tabbedPane.translate(res[0],res[1]);
                    tabbedPane.updatePaint();
                }
            }
            if(e.getSource() == opcRefY) {
                optionPaint = OptionsPaint.REFLEJAR_Y;
                tabbedPane.updatePaint();
            }
            else if(e.getSource() == opcRefX){
                optionPaint = OptionsPaint.REFLEJAR_X;
                tabbedPane.updatePaint();
            }else if(e.getSource() == opcDef){
                double res[] = modalShear.mostrar();

                if (res!=null)
                {
                    tabbedPane.deformar(res[0],res[1]);
                    tabbedPane.updatePaint();
                }
            }
            else if(e.getSource()==opcEsc) {
                double res= modalEscalar.mostrar();
                tabbedPane.scale(res);
                tabbedPane.updatePaint();
            }else if(e.getSource()==opcRest){
                tabbedPane.resetShape();
                tabbedPane.updatePaint();
            }else if(e.getSource()==opcRotar) {

                double res[] = modalRotar.mostrar();
                if (res!=null) {
                    if(res[0]==0) {
                        tabbedPane.rotateContra((int)res[1]);
                    }else {
                        tabbedPane.rotateSentido((int)res[1]);
                    }
                    tabbedPane.updatePaint();
                }
            }
    }

    private static void initUI(){
        UIManager.put("TabbedPane.selected",new Color(0x7A97F1));
        UIManager.put("TabbedPane.foreground",new Color(0x000000));
        UIManager.put("Button.background",new Color(0xFFFFFF, true));
        UIManager.put("MenuBar.background", new Color(0x1A2136));
        UIManager.put("Menu.foreground", new Color(0xF8F8F8));
        UIManager.put("MenuItem.background", new Color(0x313955));
        UIManager.put("Menu.font", new Font(Font.MONOSPACED,Font.PLAIN,18));
        UIManager.put("MenuItem.font", new Font(Font.MONOSPACED,Font.PLAIN,16));
        UIManager.put("MenuItem.foreground", new Color(0xFFFFFF));
        UIManager.put("RadioButton.foreground", Color.white);
        UIManager.put("RadioButton.background",new Color(0x1A2136) );
        UIManager.put("Button.foreground",Color.white);
        UIManager.put("Label.foreground", new Color(0xF3B407));
        UIManager.put("Panel.background", new Color(0x1A2136));
    }

    private static final int WIDTH_SB = 165,HEIGHT_SB=1000;
    private static void init()
    {
        initUI();
        frame = new DefaultFrame("Proyecto 1 -- Unidad 2");
        frame.setSize(1400, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        tabbedPane = new TabbedPane();
        modalAutores = new Autores(frame,true);
        modalHelp = new Help(frame,true);
        modalRotar = new RotateWin(frame,true);
        modalEscalar = new ScaleWin(frame,true);
        modalShear = new ShearWin(frame,true);
        modalTras = new TranslateWin(frame,true);
        escojerfig=new  Figuraopcion (frame,true);
        initJMenuBar();

        sc = new JScrollPane();


        sideBar = new SideBar(Run::actionPerformed);
        sideBar.setOrientation(true,WIDTH_SB,HEIGHT_SB);
        sc.setViewportView(sideBar);

        frame.getContentPane().add(sc,"West");
        frame.getContentPane().add(tabbedPane);
    }

    public static void actionPerformed(ActionEvent actionEvent)
    {
        switch(actionEvent.getActionCommand())
        {
            case "NEW":
                addNewTab();
                break;

            case "CLOSE":
                tabbedPane.close();
                return;

            case "RD":
                optionPaint = OptionsPaint.ROTATE_SEN;
                break;

            case "DEFX":
                optionPaint = OptionsPaint.SHEAR_X;
                break;

            case "DEFY":
                optionPaint = OptionsPaint.SHEAR_Y;
                break;

            case "ESC":
                optionPaint = OptionsPaint.ESCALE;
                break;

            case "REST":
                tabbedPane.resetShape();
                break;

            case "ORIGIN":
                tabbedPane.puntoOrigen();
                return;

            case "NESC":
                optionPaint = OptionsPaint.ESCALE_NEG;
                break;

            case "AREA":
                optionPaint = OptionsPaint.AREA;
                break;

            case "STOP":
                tabbedPane.stop();
                return;

            case "RI":
                optionPaint = OptionsPaint.ROTATE_CON;
                break;

            case "REFX":
                optionPaint = OptionsPaint.REFLEJAR_X;
                break;

            case "REFY":
                optionPaint = OptionsPaint.REFLEJAR_Y;
                break;

            case "GR":
                tabbedPane.showGrid();
                return;

            case "AN_ROTS":
                tabbedPane.initTimer().rotateSentido(10);
                return;

            case "AN_REF":
                tabbedPane.initTimer().reflexion(0,0);
                return;

            case "AN_ROTC":
                tabbedPane.initTimer().rotateContra(10);
                return;

            case "AN_ESC+":
                tabbedPane.initTimer().scale(1.1);
                return;

            case "AN_ESC-":
                tabbedPane.initTimer().scale(0.9);
                return;

            case "AN_DEFX":
                tabbedPane.initTimer().deformar(0.1,0);
                return;

            case "AN_DEFY":
                tabbedPane.initTimer().deformar(0,0.1);
                return;

            case "AN_TRA":
                tabbedPane.initTimer().translate(0,0);
                return;

            default:
                optionPaint = OptionsPaint.DEFAULT;
        }

        tabbedPane.updatePaint();

    }

    public static void addNewTab()
    {

    		int figuranumero= escojerfig.mostrar();
            tabbedPane.addTab(frame,"Figura "+figuranumero,Run::paintCanvas,1000,600,
                    new Color(0x1A2136),new Color(0x325180),25,figuranumero);
    }

    public static void paintCanvas(Graphics g)
    {
        switch (optionPaint)
        {
            case ESCALE:
                tabbedPane.scale(2);
                break;

            case AREA:
                tabbedPane.enableArea();
                break;

            case ROTATE_SEN:
                tabbedPane.rotateSentido(10);
                break;

            case REFLEJAR_X:
                tabbedPane.reflexion(1,-1);
                break;

            case REFLEJAR_Y:
                tabbedPane.reflexion(-1,1);
                break;

            case ESCALE_NEG:
                tabbedPane.scale(0.5);
                break;

            case ROTATE_CON:
                tabbedPane.rotateContra(10);
                break;

            case SHEAR_X:
                tabbedPane.deformar(0.5,0);
                break;

            case SHEAR_Y:
                tabbedPane.deformar(0,0.5);
                break;
        }

        optionPaint = OptionsPaint.DEFAULT;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(Run::init);
    }
}
