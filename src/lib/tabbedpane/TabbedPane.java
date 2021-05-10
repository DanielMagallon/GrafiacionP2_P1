package lib.tabbedpane;

import lib.staticlass.AnimationStatus;
import lib.staticlass.OptionsPaint;
import main.Run;

import javax.swing.*;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import java.awt.*;

public class TabbedPane extends JTabbedPane {
    private int index;

    private Tab selectedTab;

    public TabbedPane() {


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

        addChangeListener(c -> getCurrentTab());
    }

    public TabbedPane initTimer() {

        if (isValidPane()) {
            selectedTab.animation = true;
        }
        return this;
    }

    public void resetShape() {
        if (isValidPane()) {

            if (selectedTab.isAnimated("el reset"))
                return;

            selectedTab.drawer.restaurar();
        }
    }

    public void updatePaint() {
        if (isValidPane())
            selectedTab.repaint();
    }

    public void translate(int x, int y) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la traslacion"))
                return;

            if (selectedTab.animation) {

                selectedTab.setRunner(() -> {
                    int[] xy = selectedTab.getRandomIncTranslate();
                    selectedTab.drawer.trasladar(xy[0], xy[1]);
                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);

            } else selectedTab.drawer.trasladar(x, y);
        }
    }

    public void puntoOrigen() {
        if (isValidPane()) {

            if (selectedTab.isAnimated("seleccion de punto origen"))
                return;

            selectedTab.cursorSelect();
        }
    }

    public void scale(double esc) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la escalacion"))
                return;

            if (selectedTab.animation) {

                selectedTab.setRunner(() -> {

                    if (selectedTab.isReflectionX())
                        selectedTab.drawer.escalaPuntoH(esc);
                    else
                        selectedTab.drawer.escalaPuntoH(esc);

                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);

            } else selectedTab.drawer.escalaPuntoH(esc);
        }
    }

    public void stop() {
        if (isValidPane())
            selectedTab.setStatusTimer(AnimationStatus.STOP);
    }

    public void enableArea() {
        if (isValidPane()) selectedTab.enableArea();
    }

    public void reflexion(double x, double y) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la reflexion"))
                return;

            if (selectedTab.animation) {
                selectedTab.setRunner(() -> {

                    if (selectedTab.isReflectionX())
                        selectedTab.drawer.reflexionPuntoH(1, -1);
                    else
                        selectedTab.drawer.reflexionPuntoH(-1, 1);

                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);
            } else selectedTab.drawer.reflexionPuntoH(x, y);
        }
    }

    public void rotateSentido(int ang) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la rotacion"))
                return;

            if (selectedTab.animation) {
                selectedTab.setRunner(() -> {

                    selectedTab.drawer.rotacionsenConPuntoH(ang);
                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);
            } else selectedTab.drawer.rotacionsenConPuntoH(ang);
        }
    }

    public void rotateContra(int ang) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la rotacion"))
                return;

            if (selectedTab.animation) {
                selectedTab.setRunner(() -> {

                    selectedTab.drawer.rotacionContraH(ang);
                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);
            } else selectedTab.drawer.rotacionContraH(ang);
        }
    }

    public void deformar(double x, double y) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la deformacion"))
                return;

            if (selectedTab.animation) {
                selectedTab.setRunner(() -> {
                    selectedTab.drawer.deformarPuntoH(x, y);
                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);
            } else selectedTab.drawer.deformarPuntoH(x, y);
        }
    }

    public void showGrid() {
        if (isValidPane()) {
            selectedTab.setPaintLines(!selectedTab.isPaintLines());
            selectedTab.repaint();
        }
    }

    public void close() {
        if (isValidPane()) {
            selectedTab.setStatusTimer(AnimationStatus.STOP);
            this.remove(selectedTab);
            repaint();
            validate();
            index--;
        }
    }

    public boolean isValidPane() {
        if(selectedTab!=null)
            return true;

        Run.optionPaint = OptionsPaint.DEFAULT;
        return false;
    }

    private void getCurrentTab() {
        if (selectedTab != null) {
            if (selectedTab.animationStatus == AnimationStatus.START) {
                selectedTab.setStatusTimer(AnimationStatus.SUSPEND);
            }
        }

        selectedTab = (Tab) this.getSelectedComponent();

        if (selectedTab!=null && selectedTab.animationStatus == AnimationStatus.SUSPEND) {
            selectedTab.setStatusTimer(AnimationStatus.START);
        }
    }

    public void addTab(JFrame f, String title, GraphicsRunnable gr, int maxWidth, int maxHeight, Color bg, Color line,
                       int pixelSize) {
        this.addTab(title, new Tab(f, gr, maxWidth, maxHeight, bg, line, pixelSize));
        selectTab();
    }

    private void selectTab() {
        index++;
        if (index > 1) {
            this.setSelectedIndex(index - 1);
        }

    }

}
