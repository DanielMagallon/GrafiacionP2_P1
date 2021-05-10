package lib.modals;
import javax.swing.*;

import lib.fieldregex.TextPattern;

import java.awt.*;
import java.net.URL;
public class Figuraopcion extends JDialog {
	int opcionfigura=0;
	JButton fig1,fig2;
	public Figuraopcion(JFrame v, boolean modal) {
		super(v,modal);
		setTitle("Selccione cual figura pintar");
		setSize(800,100);
		this.setLocationRelativeTo(null);
        setResizable(false);
		setLayout(new FlowLayout());
		//componentes
		//URL ruta=getClass().getResource("/rsc/menuimg/rotarLeft.png")

		fig1 =new JButton("Figura 1: una paloma");
		fig2 =new JButton("Figura 2: un dragon");	
		add(fig1);
		add(fig2);
		fig1.addActionListener(arg0 -> {

		  opcionfigura=1;

            dispose();
		});
		
		fig2.addActionListener(e -> {

             opcionfigura=2;
             dispose();
        });

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	
	}	
	
public int mostrar() {
		setVisible(true);
		return opcionfigura;
	}
}
