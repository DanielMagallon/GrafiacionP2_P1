package bin;

import lib.staticlass.ShapePoints;

import java.awt.*;

public class Drawer
{
    public Point2D figuraPunt[],auxShape[];
    private Point2D figMapeo[];
    public Drawer(Point2D shape[])
    {
        setShape(shape);
    }

    public void setShape(Point2D shape[])
    {
        figuraPunt  = shape;
        auxShape = ShapePoints.getCopyOf(shape);
        figMapeo=new Point2D[figuraPunt.length];
        for(int i=0;i<figMapeo.length;i++) {
            figMapeo[i]=new Point2D(0,0);
        }
    }

    public void restaurar() {
        figuraPunt=ShapePoints.getCopyOf(auxShape);
    }

    public void drawShapePoints(Graphics g) {
        g.setColor(Color.cyan);

        for(int p1=1,p2=2; p1<figuraPunt.length-1;p1+=1,p2+=1) {
            g.drawLine((int)figuraPunt[p1].x, (int)figuraPunt[p1].y, (int)figuraPunt[p2].x, (int)figuraPunt[p2].y);
        }
    }

    public void escalar(double esc) {
        for(int i=0; i<figuraPunt.length;i++) {
            figuraPunt[i].x=(figuraPunt[i].x*esc);
            figuraPunt[i].y=(figuraPunt[i].y*esc);
        }
    }

    public void escalarPunto(double esc) {
        int tx=(int)figuraPunt[0].x;
        int ty=(int)figuraPunt[0].y;
        trasladar(-tx,-ty);
        escalar(esc);
        trasladar(tx,ty);
    }

    public void deformacion(double defx,double defy) {
        for(int i=0; i<figuraPunt.length;i++) {
            double cx=figuraPunt[i].x;
            figuraPunt[i].x=figuraPunt[i].x+defx*figuraPunt[i].y;
            figuraPunt[i].y=defy*cx+figuraPunt[i].y;
        }
    }
    public void deformacionPunto(double defx,double defy) {
        int tx=(int)figuraPunt[0].x;
        int ty=(int)figuraPunt[0].y;
        trasladar(-tx,-ty);
        deformacion(defx, defy);
        trasladar(tx,ty);
    }

    public void rotacionSenMane(int ang) {
        double angRad=Math.toRadians(ang);
        double seno=Math.sin(angRad);
        double coseno=Math.cos(angRad);

        for(int i=0; i<figuraPunt.length;i++) {
            double x=figuraPunt[i].x;
            double y=figuraPunt[i].y;
            figuraPunt[i].x=x*coseno-y*seno;
            figuraPunt[i].y=x*seno+y*coseno;
        }

    }

    public void rotacionPunto(int ang) {
        int tx=(int)figuraPunt[0].x;
        int ty=(int)figuraPunt[0].y;
        trasladar(-tx,-ty);
        rotacionConMane(ang);
        trasladar(tx,ty);
    }

    public void rotacionConMane(int ang) {
        double angRad=Math.toRadians(ang);
        double seno=Math.sin(angRad);
        double coseno=Math.cos(angRad);

        for(int i=0; i<figuraPunt.length;i++) {
            double x=figuraPunt[i].x;
            double y=figuraPunt[i].y;
            figuraPunt[i].x=x*coseno+y*seno;
            figuraPunt[i].y=-x*seno+y*coseno;
        }

    }

    public void trasladar(int tx, int ty) {
        for(int i=0; i<figuraPunt.length;i++) {
            figuraPunt[i].x=figuraPunt[i].x+tx;
            figuraPunt[i].y=figuraPunt[i].y+ty;
        }
    }


    public void reflexion(int rfx, int rfy) {
        for(int i=0; i<figuraPunt.length;i++) {
            figuraPunt[i].x=(figuraPunt[i].x*rfx);
            figuraPunt[i].y=(figuraPunt[i].y*rfy);
        }
    }

    //Cordenadas homogeneas
    public void escalaPuntoH(double esc) {
        // xSx-TxSx+Tx,ySy-TySy+Ty
        int Tx=(int)figuraPunt[0].x;
        int Ty=(int)figuraPunt[0].y;
        for(int i=0; i<figuraPunt.length;i++) {
            figuraPunt[i].x=figuraPunt[i].x*esc-Tx*esc+Tx;
            figuraPunt[i].y=figuraPunt[i].y*esc-Ty*esc+Ty;
        }
    }

    //sentido contrario del RELOJ
    public void rotacionContraH(int ang) {
        //xcos+ysen-Txcos-Tysen+Tx,-xsen+ycos+Txsen-Tycos+Ty
        int Tx=(int)figuraPunt[0].x;
        int Ty=(int)figuraPunt[0].y;
        double angRad=Math.toRadians(ang);
        double seno=Math.sin(angRad);
        double coseno=Math.cos(angRad);
        for(int i=0; i<figuraPunt.length;i++) {
            double x=figuraPunt[i].x;
            double y=figuraPunt[i].y;
            figuraPunt[i].x=x*coseno+y*seno-Tx*coseno-Ty*seno+Tx;
            figuraPunt[i].y=-x*seno+y*coseno+Tx*seno-Ty*coseno+Ty;
        }
    }

    //sentido del reloj
    public void rotacionsenConPuntoH(int ang) {
        //xcos-ysen-Txcos+Tysen+Tx,xsen+ycos-Txsen-Tycos+Ty,1
        int Tx=(int)figuraPunt[0].x;
        int Ty=(int)figuraPunt[0].y;
        double angRad=Math.toRadians(ang);
        double seno=Math.sin(angRad);
        double coseno=Math.cos(angRad);
        for(int i=0; i<figuraPunt.length;i++) {
            double x=figuraPunt[i].x;
            double y=figuraPunt[i].y;
            figuraPunt[i].x=x*coseno-y*seno-Tx*coseno+Ty*seno+Tx;
            figuraPunt[i].y=x*seno+y*coseno-Tx*seno-Ty*coseno+Ty;
        }
    }
    public void deformarPuntoH(double A, double B) {
        //x+Ay-Aty,xB+y-TxB
        int Tx=(int)figuraPunt[0].x;
        int Ty=(int)figuraPunt[0].y;
        for(int i=0; i<figuraPunt.length;i++) {
            double x=figuraPunt[i].x;
            figuraPunt[i].x=x+A*figuraPunt[i].y-A*Ty;
            figuraPunt[i].y=x*B+figuraPunt[i].y-Tx*B;
        }

    }

    //la matriz es igual a laa del escalamiento casi
    public void reflexionPuntoH(double Rx,double Ry) {
        //xRx-TxRx+Tx,yRy-TyRy+Ty
        int Tx=(int)figuraPunt[0].x;
        int Ty=(int)figuraPunt[0].y;
        for(int i=0; i<figuraPunt.length;i++) {
            figuraPunt[i].x=figuraPunt[i].x*Rx-Tx*Rx+Tx;
            figuraPunt[i].y=figuraPunt[i].y*Ry-Ty*Ry+Ty;

        }
    }

    //operacion de mapeo d eventana
    public void mapeoVentana(int xvmax,int xvmin, int yvmax,int yvmin,int xwmax,int ywmax, Graphics g,Color c ) {
        double sx=((double)(xvmax-xvmin)/(double)(xwmax));
        double sy=((double)(yvmax-yvmin)/(double)(ywmax));
        for(int i=0; i<figuraPunt.length;i++) {
            figMapeo[i].x=(figuraPunt[i].x*sx);
            figMapeo[i].y=(figuraPunt[i].y*sy);
            figMapeo[i].x+=xvmin;
            figMapeo[i].y+=yvmin;
        }
        rotacionPuntos(180,figMapeo);
        g.setColor(c);

        for(int p1=1,p2=2; p1<figMapeo.length-1;p1++,p2++) {
            g.drawLine((int)figMapeo[p1].x, (int)figMapeo[p1].y, (int)figMapeo[p2].x, (int)figMapeo[p2].y);
        }
    }

    public void rotacionPuntos(int ang,Point2D figuraPunt[]) {
        //xcos-ysen-Txcos+Tysen+Tx,xsen+ycos-Txsen-Tycos+Ty,1
        int Tx=(int)figuraPunt[0].x;
        int Ty=(int)figuraPunt[0].y;
        double angRad=Math.toRadians(ang);
        double seno=Math.sin(angRad);
        double coseno=Math.cos(angRad);
        for(int i=0; i<figuraPunt.length;i++) {
            double x=figuraPunt[i].x;
            double y=figuraPunt[i].y;
            figuraPunt[i].x=x*coseno-y*seno-Tx*coseno+Ty*seno+Tx;
            figuraPunt[i].y=x*seno+y*coseno-Tx*seno-Ty*coseno+Ty;
        }
    }

    public Point obtCoordXFigura() {
        Point p=new Point((int)figuraPunt[0].x,(int)figuraPunt[0].x);
        for(int i=1; i<figuraPunt.length;i++) {
            if(figuraPunt[i].x<p.x) {
                p.x=(int)figuraPunt[i].x;
            }
            if(figuraPunt[i].x>p.y) {
                p.y=(int)figuraPunt[i].x;
            }

        }
        return p;
    }

    public void mapeoVentana(int xvmax,int xvmin, int yvmax,int yvmin,int xwmax,int ywmax, Graphics g ) {
        double sx=((double)(xvmax-xvmin)/(double)(xwmax-0));
        double sy=((double)(yvmax-yvmin)/(double)(ywmax-0));
        for(int i=0; i<figuraPunt.length;i++) {
            figMapeo[i].x=figuraPunt[i].x*sx;
            figMapeo[i].y=figuraPunt[i].y*sy;
            figMapeo[i].x+=xvmin;
            figMapeo[i].y+=yvmin;
        }
        g.setColor(Color.white);

        for(int p1=1,p2=2; p1<figMapeo.length-1;p1+=1,p2+=1) {
            g.drawLine((int)figMapeo[p1].x, (int)figMapeo[p1].y, (int)figMapeo[p2].x, (int)figMapeo[p2].y);
        }
    }

    public Point obtCoordYFigura() {
        Point p=new Point((int)figuraPunt[0].y,(int)figuraPunt[0].y);
        for(int i=1; i<figuraPunt.length;i++) {
            if(figuraPunt[i].y<p.x) {
                p.x=(int)figuraPunt[i].y;
            }
            if(figuraPunt[i].y>p.y) {
                p.y=(int)figuraPunt[i].y;
            }

        }
        return p;
    }
}