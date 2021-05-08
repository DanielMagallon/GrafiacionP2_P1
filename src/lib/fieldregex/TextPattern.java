package lib.fieldregex;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

public class TextPattern extends JTextField
{
    private Pattern pattern;

    public TextPattern(String regex, int cols)
    {
        this.pattern = Pattern.compile(regex);
        setColumns(cols);

        addKeyListener(new KeyAdapter() {
           
            public void keyTyped(KeyEvent keyEvent) {
                if(!pattern.matcher(TextPattern.this.getText()+keyEvent.getKeyChar()).matches())
                    keyEvent.consume();
            }
        });
    }
}
