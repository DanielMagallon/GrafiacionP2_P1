package lib.frame;

import javax.swing.*;
import java.awt.*;

public class DefaultFrame extends JFrame
{
    public DefaultFrame(String title, int w, int h) throws HeadlessException
    {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        setSize(w,h);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public DefaultFrame(String title) throws HeadlessException
    {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        getContentPane().setLayout(new BorderLayout());
        setVisible(true);
    }

    public DefaultFrame() throws HeadlessException
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        getContentPane().setLayout(new BorderLayout());
        setVisible(true);
    }


}
