
package lib.modals;

import lib.fieldregex.TextPattern;
import lib.staticlass.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class TranslateWin extends JDialog {
	int argTras[];
	JLabel et1,et2,et3;
	TextPattern txtX,txtY;
	JButton bac,bca;

	public TranslateWin(JFrame v, boolean modal) {
		super(v,modal);
		setTitle("Traslacion de la figura");
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());

		et1=new JLabel(ImageLoader.trasR);
		et2=new JLabel("Trasladar en x");
		et3= new JLabel("Trasladar en y");

		txtX =new TextPattern("^-?[0-9]*",5);
        txtY =new TextPattern("^-?[0-9]*",5);

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
                argTras = new int[2];
                argTras[0] = Integer.parseInt(txtX.getText());
                argTras[1] = Integer.parseInt(txtY.getText());
            }catch(NumberFormatException ex){
		        argTras = null;
            }

            dispose();
		});
		
		bca.addActionListener(e -> {

             argTras =null;
             dispose();
        });

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		pack();
	}
		
	
public int[] mostrar() {
		
		setVisible(true);
		return argTras;
	}
}
