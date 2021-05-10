package lib.staticlass;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ImageLoader
{
    public static ImageIcon newTabImage = getImageIcon("material/nuevo36px.png");
    public static ImageIcon newTabRImage = getImageIcon("material/nuevo26px.png");

    public static ImageIcon rotateDImage = getImageIcon("material/derrotar36px.png");
    public static ImageIcon rotateDRImage = getImageIcon("material/derrotar26px.png");

    public static ImageIcon rotateLImage = getImageIcon("material/izqrotar36px.png");
    public static ImageIcon rotateLRImage = getImageIcon("material/izqrotar26px.png");

    public static ImageIcon enableGridImage = getImageIcon("material/grid36px.png");
    public static ImageIcon enableGridRImage = getImageIcon("material/grid26px.png");

    public static ImageIcon mostrarArea = getImageIcon("material/mosarea36px.png");
    public static ImageIcon mostrarAreaR = getImageIcon("material/mosarea26px.png");

    public static ImageIcon refX = getImageIcon("material/refx36px.png");
    public static ImageIcon refXR = getImageIcon("material/refx26px.png");

    public static ImageIcon refY = getImageIcon("material/refy36px.png");
    public static ImageIcon refYR = getImageIcon("material/refy26px.png");

    public static ImageIcon reset = getImageIcon("material/restaurar36px.png");
    public static ImageIcon resetR = getImageIcon("material/restaurar26px.png");

    public static ImageIcon escalarNega = getImageIcon("material/escnega36px.png");
    public static ImageIcon escalarNegaR = getImageIcon("material/escnega26px.png");

    public static ImageIcon escalarPos = getImageIcon("material/escposi36px.png");
    public static ImageIcon escalarPosR = getImageIcon("material/escposi26px.png");

    public static ImageIcon defX = getImageIcon("material/defx36px.png");
    public static ImageIcon defXR = getImageIcon("material/defx26px.png");

    public static ImageIcon defY = getImageIcon("material/defy36px.png");
    public static ImageIcon defYR = getImageIcon("material/defy26px.png");

    public static ImageIcon cerrar = getImageIcon("material/cerrar36px.png");
    public static ImageIcon cerrarR = getImageIcon("material/cerrar26px.png");

    public static ImageIcon stop = getImageIcon("material/stop36px.png");
    public static ImageIcon stopR = getImageIcon("material/stop26px.png");

    public static ImageIcon tras = getImageIcon("material/move36px.png");
    public static ImageIcon trasR = getImageIcon("material/move26px.png");

    public static ImageIcon origen = getImageIcon("material/origen36px.png");
    public static ImageIcon origenR = getImageIcon("material/origen26px.png");

    public static ImageIcon config = getImageIcon("material/settings36px.png");
    public static ImageIcon configR = getImageIcon("material/settings26px.png");

    public static ImageIcon fauto = getImageIcon("material/fondo.jpg");

    public static ImageIcon getImageIcon(String path)
    {
        return new ImageIcon(Objects.requireNonNull(ImageLoader.class.getResource("/rsc/" + path)));
    }
    public static Image getSprite(String path)
    {
        try {

            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource("/rsc/" + path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
