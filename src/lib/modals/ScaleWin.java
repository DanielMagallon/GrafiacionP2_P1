package lib.modals;
import lib.staticlass.ImageLoader;

import javax.swing.*;

import java.awt.FlowLayout;

public class ScaleWin extends JDialog {
	double cantEsc=0.0;
	JLabel et1,et2;
	JTextField ct1;
	JButton bac,bca;

	public ScaleWin(JFrame v, boolean modal) {
		super(v,modal);
		setTitle("Escalar una figura");
		setSize(600,80);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout() );
		et1=new JLabel(ImageLoader.escalarPosR);
		et2=new JLabel("Cantidad a escalar: ");
		ct1=new JTextField(10);
		bac =new JButton("Aceptar");
		bca =new JButton("Cancelar");
		add(et1);add(et2);
		add(ct1);add(bac);add(bca);
		bac.addActionListener(arg0 -> {
         String res=ct1.getText();
         try {
             cantEsc=Double.parseDouble(res);
         }catch(NumberFormatException e1) {
             cantEsc=1.0;
         }
         setVisible(false);
         dispose();
        });
		
		bca.addActionListener(e -> {
            cantEsc=1.0;
            setVisible(false);
             dispose();
        });
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	public double mostrar() {
		
		setVisible(true);
		return cantEsc;
	}
}
