package lib.modals;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Autores extends JDialog {
	Image img,efrayo,misteryeo;
	Font Fuente;
    public Autores(Frame frame, boolean b) {
        super(frame, b);
        setTitle("Autores- Efrain Tovar Meza - Edgar Daneiel Magallon Villanueva");
        setSize(900,506);
       this.setLocationRelativeTo(null);
        setResizable(false);
        
    }
    
    public void paint(Graphics g){
    	//super.paintComponent(g);
    	super.paint(g);
        Dimension tamanio = getSize();
        URL ruta = getClass().getResource("/rsc/material/fondo.jpg");
        img=new ImageIcon(ruta).getImage();
        g.drawImage(img, 0, 0,tamanio.width, tamanio.height, null);
        ruta = getClass().getResource("/rsc/material/yoefra.jpg");
        efrayo=new ImageIcon(ruta).getImage();
        g.drawImage(efrayo,100,100,null);
        ruta = getClass().getResource("/rsc/material/yeos.jpeg");
        misteryeo=new ImageIcon(ruta).getImage();
        g.drawImage(misteryeo,100,300,null);
        Fuente=new Font("Arial",Font.PLAIN,25);
        g.setFont(Fuente);
        g.drawString("..:::::AUTORES:::::..",200, 70);
        g.drawString("Efrain Tovar Meza", 250, 150);
        g.drawString("No.Ctrl: 17420619", 250, 190);
        g.drawString("Edgar Daniel Magallón Villanueva", 250, 350);
        g.drawString("No.Ctrl: 17420517", 250, 390);
     }
}
