
package lib.modals;
import lib.fieldregex.TextPattern;
import lib.staticlass.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.regex.Pattern;

public class RotateWin extends JDialog {
	double argRotar[];
	JLabel et1,et2,et3;
	JRadioButton rb1,rb2;
	ButtonGroup bg;
	TextPattern ct;
	JButton bac,bca;
	
	public RotateWin(JFrame v, boolean modal) {
		super(v,modal);
		setTitle("Rotar una figura en cualquier sentido");
		setSize(500,60);
		setLocation(650,600);
		setLayout(new FlowLayout());

		et1=new JLabel(ImageLoader.rotateDRImage);
		et2=new JLabel(ImageLoader.rotateLRImage);
		et3= new JLabel("Cantidad a rotar");
		bg= new ButtonGroup();
		rb1=new JRadioButton("",true);
		rb2=new JRadioButton();
		bg.add(rb1);bg.add(rb2);

		ct=new TextPattern("^-?[0-9]*",5);

		bac =new JButton("Aceptar");
		bca =new JButton("Cancelar");
		add(et1);add(rb1);
		add(et2);add(rb2);
		add(et3);add(ct);
		add(bac);add(bca);
		//eventos
		bac.addActionListener(arg0 -> {
         argRotar=new double[2];
         if(rb1.isSelected()) {
             argRotar[0]=1;
         }else {
             argRotar[0]=0;
         }
            String res=ct.getText();
         try {
             argRotar[1]=Double.parseDouble(res);
         }catch(NumberFormatException e1) {
             argRotar=null;
         }
         dispose();
        });
		
		bca.addActionListener(e -> {

             argRotar=null;
             dispose();
        });
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		pack();
	}
		
	
public double[] mostrar() {
		
		setVisible(true);
		return argRotar;
	}
}
