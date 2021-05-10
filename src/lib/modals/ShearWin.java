
package lib.modals;

import lib.fieldregex.TextPattern;
import lib.staticlass.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ShearWin extends JDialog {
	double argShear[];
	JLabel et1,et2,et3;
	TextPattern txtX,txtY;
	JButton bac,bca;

	public ShearWin(JFrame v, boolean modal) {
		super(v,modal);
		setTitle("Deformar una figura en cualquier sentido");
		setLocation(650,600);
		setLayout(new FlowLayout());

		et1=new JLabel(ImageLoader.defXR);
		et2=new JLabel("Deformacion en x");
		et3= new JLabel("Deformacion en y");

		txtX =new TextPattern("^-?[0-9]*|^-?[0-9]*\\.[0-9]*",5);
        txtY =new TextPattern("^-?[0-9]*|^-?[0-9]*\\.[0-9]*",5);

		bac =new JButton("Aceptar");
		bca =new JButton("Cancelar");

		add(et2);
        add(txtX);
        add(et3);
        add(txtY);
		add(bac);
		add(bca);


		bac.addActionListener(arg0 -> {

		    try{
                argShear = new double[2];
                argShear[0] = Double.parseDouble(txtX.getText());
                argShear[1] = Double.parseDouble(txtY.getText());
            }catch(NumberFormatException ex){
		        argShear = null;
            }

            dispose();
		});
		
		bca.addActionListener(e -> {

             argShear =null;
             dispose();
        });

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		pack();
	}
		
	
public double[] mostrar() {
		
		setVisible(true);
		return argShear;
	}
}
