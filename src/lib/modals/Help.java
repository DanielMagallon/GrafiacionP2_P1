package lib.modals;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Help extends JDialog
{
	Image img;
	Font Fuente;
	JScrollPane scrollPane = new JScrollPane();
	JLabel lblContent;
    public Help(Frame frame, boolean b) {
        super(frame, b);
        setTitle("......:::::::AYUDA::::::......");
        setSize(900,636);
       
       this.setLocationRelativeTo(null);
        setResizable(false);
       
    }
    
    public void paint(Graphics g){
    	//super.paintComponent(g);
       super.paint(g);
        Dimension tamanio = getSize();
        URL ruta = getClass().getResource("/rsc/material/fondohelp.jpg");
        img=new ImageIcon(ruta).getImage();
        g.drawImage(img, 0, 0,tamanio.width, tamanio.height, null);
        Fuente=new Font("Arial",Font.PLAIN,30);
        g.setFont(Fuente);
        g.drawString("AYUDA",30, 100);
        g.drawString("Teclas aceleradoras",270, 200);
        ruta = getClass().getResource("/rsc/material/teclas.png");
        img=new ImageIcon(ruta).getImage();
        g.drawImage(img, 0, 210, null);
        ruta = getClass().getResource("/rsc/material/funciones.png");
        img=new ImageIcon(ruta).getImage();
        g.drawString("Descripcion de botones",270, 400);
        g.drawImage(img, 0, 370, null);
    }
}
