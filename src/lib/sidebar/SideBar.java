package lib.sidebar;

import lib.staticlass.AppProperties;
import lib.staticlass.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SideBar extends JPanel
{
    private int currentX,currentY,sizeIcon;
    private final int xdef=20;
    private ActionListener handler;
    private ArrayList<JButton> buttonActions;

    public SideBar(ActionListener handler)
    {
        setLayout(null);
        this.handler = handler;
        buttonActions = new ArrayList<>();
    }


    private void addButtons()
    {
        {
            addSection("Transformaciones").addSideComponent(ImageLoader.rotateDImage, ImageLoader.rotateDRImage, handler, "Rotar hacia la derecha",
                    "RD",true);
            addSideComponent(ImageLoader.rotateLImage, ImageLoader.rotateLRImage, handler, "Rotar hacia la izquierda",
                    "RI",true);
            addSideComponent(ImageLoader.escalarPos, ImageLoader.escalarPosR, handler, "Escalar/Zoom (+)",
                    "ESC",true);
            addSideComponent(ImageLoader.escalarNega, ImageLoader.escalarNegaR, handler, "Escalar/Zoom (-)",
                    "NESC",true);

            addSideComponent(ImageLoader.refX, ImageLoader.refXR, handler, "Reflejar sobre eje de las X",
                    "REFX",true);

            addSideComponent(ImageLoader.refY, ImageLoader.refYR, handler, "Reflejar sobre eje de las Y",
                    "REFY",true);

            addSideComponent(ImageLoader.defX, ImageLoader.defXR, handler, "Deformar sobre eje de las X",
                    "DEFX",true);

            addSideComponent(ImageLoader.defY, ImageLoader.defYR, handler, "Deformar sobre eje de las Y",
                    "DEFY",true);
        }

        {
            addSection("Propieadades").addSideComponent(ImageLoader.newTabImage, ImageLoader.newTabRImage, handler, "Nueva pestaña",
                    "NEW",false);

            addSideComponent(ImageLoader.cerrar, ImageLoader.cerrarR, handler,
                    "Cerrar pestaña", "CLOSE",false);

            addSideComponent(ImageLoader.reset, ImageLoader.resetR, handler, "Restaurar figura",
                    "REST",true);

            addSideComponent(ImageLoader.newTabImage, ImageLoader.newTabRImage, handler, "Seleccionar punto origen",
                    "ORIGIN",true);

            addSideComponent(ImageLoader.mostrarArea, ImageLoader.mostrarAreaR, handler, "Mostrar area",
                    "AREA",false);

            addSideComponent(ImageLoader.enableGridImage, ImageLoader.enableGridRImage, handler,
                    "Habilitar/Deshabilitar grid", "GR",false);

        }

        {
            addSection("Animaciones").addSideComponent(ImageLoader.rotateLImage, ImageLoader.rotateLRImage, handler,
                    "Parar animacion", "STOP",false);

            addSideComponent(ImageLoader.rotateLImage, ImageLoader.rotateLRImage, handler,
                    "Rotacion sentido horario", "AN_ROTS",true);

            addSideComponent(ImageLoader.rotateLImage, ImageLoader.rotateLRImage, handler,
                    "Rotacion sentido antihorario", "AN_ROTC",true);

            addSideComponent(ImageLoader.rotateLImage, ImageLoader.rotateLRImage, handler,
                    "Escalamiento", "AN_ESC",true);

            addSideComponent(ImageLoader.rotateLImage, ImageLoader.rotateLRImage, handler,
                    "Traslacion", "AN_TRA",true);

            addSideComponent(ImageLoader.rotateLImage, ImageLoader.rotateLRImage, handler,
                    "Deformacion", "AN_DEFX",true);

            addSideComponent(ImageLoader.rotateLImage, ImageLoader.rotateLRImage, handler,
                    "Deformacion", "AN_DEFY",true);

            addSideComponent(ImageLoader.rotateLImage, ImageLoader.rotateLRImage, handler,
                    "Reflexion", "AN_REF",true);
        }
    }

    private boolean vertical;

    public void setOrientation(boolean vertical,int w, int h)
    {
        this.vertical = vertical;
        currentX = xdef;
        currentY = 20;
        sizeIcon = 32;
        setPreferredSize(new Dimension(w, h));
        this.removeAll();
        addButtons();
        this.validate();
    }

    private SideBar addSection(String section)
    {
        if(currentX!=xdef) {
            currentX = xdef;
            currentY += sizeIcon + 40;
        }
        else if(currentY!=20)
            currentY += 10;

        if(vertical)
        {
            JLabel label = new JLabel(section);
            label.setHorizontalAlignment(JLabel.LEFT);
            label.setBounds(currentX,currentY,150,20);
            this.add(label);
            currentY += 40;
        }

        return this;
    }

    private void addSideComponent(ImageIcon image,ImageIcon imageSel,ActionListener listener,String toolText,
                                  String actCommand,boolean add)
    {
        JButton btn = new JButton();

        if(add)
            buttonActions.add(btn);

        if(image!=null){
            btn.setIcon(image);
            btn.setRolloverIcon(imageSel);
        }else{
            btn.setText("P");
        }

        btn.setActionCommand(actCommand);
        btn.setContentAreaFilled(false);
        btn.addActionListener(listener);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(AppProperties.handCursor);
        btn.setToolTipText(toolText);
        btn.setBounds(currentX,currentY,sizeIcon,sizeIcon);
        currentX += sizeIcon + 30;

        if(vertical) {
            if (currentX >= 140) {
                currentX = xdef;
                currentY += sizeIcon + 30;
            }
        }
        this.add(btn);
    }
}
