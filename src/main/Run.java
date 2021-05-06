package main;

import lib.frame.DefaultFrame;
import lib.modals.*;
import lib.sidebar.SideBar;
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
    private static TabbedPane tabbedPane;
    private static SideBar sideBar;
    private static JScrollPane sc;
    private static RotateWin modalRotar;
    private static ScaleWin modalEscalar;
    private static ShearWin modalShear;
    private static TranslateWin modalTras;
    private static Autores modalAutores;
    private static Help modalHelp;
    private static OptionsPaint optionPaint = OptionsPaint.DEFAULT;

    private static JMenuBar barraM;
    private static JMenu Menu1,Menu2;
    private static JMenuItem opcRest,opcEsc,opcRotar,opcDef, opcRefX,opcRefY,opcTras,opcSalir,opcDes,opcAyu;

    private static void initJMenuBar(){
        barraM=new JMenuBar();
        frame.setJMenuBar(barraM);
        Menu1=new JMenu("Transformaciones");
        Menu2=new JMenu("Acerca de");
        barraM.add(Menu1);
        barraM.add(Menu2);

        opcRest=new JMenuItem("Restaurar");
        opcRest.setMnemonic('R');
        opcRest.setToolTipText("Restaura la figura a la original");
        URL rutaIm= Run.class.getResource("/rsc/menuimg/rest.png");
        opcRest.setIcon(new ImageIcon(rutaIm));
        opcRest.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.ALT_MASK));

        opcEsc=new JMenuItem("Escalar");
        opcEsc.setMnemonic('E');
        opcEsc.setToolTipText("Escala la figura al tamano deseado");
        rutaIm= Run.class.getResource("/rsc/menuimg/esca.png");
        opcEsc.setIcon(new ImageIcon(rutaIm));
        opcEsc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.ALT_MASK));

        opcRotar=new JMenuItem("Rotacion");
        opcRotar.setToolTipText("Rota la figura en el sentido deseado");
        rutaIm= Run.class.getResource("/rsc/menuimg/rotar.png");
        opcRotar.setIcon(new ImageIcon(rutaIm));
        opcRotar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.ALT_MASK));

        opcDef=new JMenuItem("Deformar");
        opcDef.setMnemonic('D');
        opcDef.setToolTipText("Deformar la figura a la cantidad deseada");
        rutaIm= Run.class.getResource("/rsc/menuimg/def.png");
        opcDef.setIcon(new ImageIcon(rutaIm));
        opcDef.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.ALT_MASK));

        opcRefX = new JMenuItem("Refleccion en X");
        opcRefX.setMnemonic('X');
        opcRefX.setToolTipText("Refleccion en X a la figura en sus cuadrantes");
        rutaIm= Run.class.getResource("/rsc/menuimg/refle.png");
        opcRefX.setIcon(new ImageIcon(rutaIm));
        opcRefX.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.ALT_MASK));

        opcRefY = new JMenuItem("Refleccion en Y");
        opcRefY.setMnemonic('Y');
        opcRefY.setToolTipText("Refleccion en Y a la figura en sus cuadrantes");
        rutaIm= Run.class.getResource("/rsc/menuimg/refle.png");
        opcRefY.setIcon(new ImageIcon(rutaIm));
        opcRefY.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,InputEvent.ALT_MASK));

        opcTras =new JMenuItem("Trasladar");
        opcTras.setMnemonic('T');
        opcTras.setToolTipText("Trasladar la figura a unas coordenadas deseadas");
        rutaIm= Run.class.getResource("/rsc/menuimg/mov.png");
        opcTras.setIcon(new ImageIcon(rutaIm));
        opcTras.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.ALT_MASK));

        opcSalir=new JMenuItem("Salir");
        opcSalir.setMnemonic('S');
        opcSalir.setToolTipText("Salir del programa");
        rutaIm= Run.class.getResource("/rsc/menuimg/s.png");
        opcSalir.setIcon(new ImageIcon(rutaIm));
        opcSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.ALT_MASK));

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

        opcAyu=new JMenuItem("Ayuda");
        opcAyu.setMnemonic('A');
        opcAyu.setToolTipText("Ayuda del programa");
        rutaIm= Run.class.getResource("/rsc/menuimg/ayuda.png");
        opcAyu.setIcon(new ImageIcon(rutaIm));
        opcAyu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.ALT_MASK));
        Menu2.add(opcDes);
        Menu2.add(opcAyu);
        //imagen de fondo


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
//        UIManager.put("ToggleButton.foreground",Color.white);
//        UIManager.put("Label.background", new Color(0x07F356));
        UIManager.put("Label.foreground", new Color(0xF3B407));
//        UIManager.put("OptionPane.foreground", Color.white);
        UIManager.put("Panel.background", new Color(0x1A2136));
//        UIManager.put("OptionPane.background",ColorManager.ESCALA_AZUL.getColorScaleOf(1));

    }

    private static final int WIDTH_SB = 165,HEIGHT_SB=1000;
    private static void init()
    {
        initUI();
        frame = new DefaultFrame("Proyecto 1 -- Unidad 2");
        tabbedPane = new TabbedPane();
        modalAutores = new Autores(frame,true);
        modalHelp = new Help(frame,true);
        modalRotar = new RotateWin(frame,true);
        modalEscalar = new ScaleWin(frame,true);
        modalShear = new ShearWin(frame,true);
        modalTras = new TranslateWin(frame,true);

        initJMenuBar();

        sc = new JScrollPane();


        sideBar = new SideBar(Run::actionPerformed);
        sideBar.setOrientation(true,WIDTH_SB,HEIGHT_SB);
        sc.setViewportView(sideBar);

//        mapeoWin = new MapeoWin(frame,300,200);

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

            case "AN_ROTC":
                return;

            default:
                optionPaint = OptionsPaint.DEFAULT;
        }

        tabbedPane.updatePaint();

    }

    public static void addNewTab()
    {
            tabbedPane.addTab(frame,"Figura X",Run::paintCanvas,1000,600,
                    new Color(0x1A2136),new Color(0x325180),25);
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
