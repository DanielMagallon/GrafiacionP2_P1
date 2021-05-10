package lib.tabbedpane;

import javax.swing.*;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import java.awt.*;

public class TabbedPane extends JTabbedPane
{
    private int index;

    private Tab selectedTab;

    public TabbedPane()
    {


        this.setUI(new MetalTabbedPaneUI() {
            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex,
                                            FontMetrics metrics) {
                int width = super.calculateTabWidth(tabPlacement, tabIndex, metrics);
                int extra = tabIndex * 10;
                return width + extra;
            }
            @Override
            protected int calculateMaxTabHeight(int arg0) {
                // TODO Auto-generated method stub
                return 30;
            }

        });

        addChangeListener( c -> getCurrentTab());
    }

    private boolean animation;
    public TabbedPane initTimer(){

        if(isValidPane())
        {
            animation = true;
        }
        return this;
    }

    public void resetShape()
    {
        selectedTab.drawer.restaurar();
    }

    public void updatePaint(){
        if(isValidPane())
            selectedTab.repaint();
    }

    public void translate(int x,int y)
    {
        selectedTab.drawer.trasladar(x,y);
    }

    public void puntoOrigen(){
        selectedTab.cursorSelect();
    }



    public void scale(double esc)
    {
        if(isValidPane()) {
            if (animation) {

                selectedTab.setRunner(()->{

                });

            } else selectedTab.drawer.escalaPuntoH(esc);
        }
    }

    public void enableArea(){
        selectedTab.enableArea();
    }

    public void reflexion(double x, double y)
    {
        selectedTab.drawer.reflexionPuntoH(x,y);
    }

    private int angInc;
    public void rotateSentido(int ang)
    {
        angInc = ang;
        if(isValidPane()) {

            if (animation) {
                selectedTab.setRunner(() -> {

                        selectedTab.drawer.rotacionsenConPuntoH(angInc);
                        selectedTab.repaint();
                });

                selectedTab.setStatusTimer(true);
            }

            else selectedTab.drawer.rotacionsenConPuntoH(ang);
        }
    }

    public void rotateContra(int ang)
    {
        selectedTab.drawer.rotacionContraH(ang);
//        Run.mapeoWin.updatePaint();
    }

    public void deformar(double x, double y){
        selectedTab.drawer.deformarPuntoH(x,y);
    }

    public void showGrid()
    {
         selectedTab.setPaintLines(!selectedTab.isPaintLines());
         selectedTab.repaint();
    }

    public void close(){
        if(isValidPane())
        {
            selectedTab.setStatusTimer(false);
            this.remove(selectedTab);
            index--;
        }
    }

    public boolean isValidPane(){
        return selectedTab != null;
    }

    private void getCurrentTab()
    {
        selectedTab = (Tab) this.getSelectedComponent();
    }

    public void addTab(JFrame f, String title,GraphicsRunnable gr, int maxWidth, int maxHeight, Color bg, Color line,
                        int pixelSize,int figura)
    {
        this.addTab(title, new Tab(f,gr,maxWidth,maxHeight,bg,line,pixelSize,figura));
        selectTab();
    }

    private void selectTab(){
        index++;
        if(index>1){
            this.setSelectedIndex(index-1);
        }

    }

    public void addTab(JFrame f, String title,GraphicsRunnable gr, int maxWidth, int maxHeight, Color bg, Color line,int figura)
    {
        this.addTab(title,new Tab(f,gr,maxWidth,maxHeight,bg,line,figura));
        selectTab();
    }
}
