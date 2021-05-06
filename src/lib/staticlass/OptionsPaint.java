package lib.staticlass;

public enum OptionsPaint
{
    DEFAULT(-1),
    TRANSALATE(0),
    ESCALE(1),
    ROTATE_SEN(2),
    ROTATE_CON(3),
    SHEAR_X(4),
    ESCALE_NEG(5),
    REFLEJAR_X(6),
    REFLEJAR_Y(7),
    SHEAR_Y(8),
    AREA(9);

    private int opc;
    OptionsPaint(int opc)
    {
        this.opc = opc;
    }

    public int getOpc() {
        return opc;
    }
}
