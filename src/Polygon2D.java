import java.awt.*;

/**
 * Polygon2D Class
 *
 * @author (Ryan Kee)
 * @version (v1.0 4-19-25)
 */
public class Polygon2D extends Shape2D {

    int[] xCoords;
    int[] yCoords;
    int[] txCoords;
    int[] tyCoords;

    /**
     * Constructs the Polgygon2D Object
     * @param fillColorIndex - the int value of the color that will fill the shape
     * @param xPos - the X position of the shape
     * @param yPos - the Y position of the Shape
     * @param xCoords - int array of the x Coordinates of the shape
     * @param yCoords - int array of the y Coordinates of the shape
     */
    public Polygon2D(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords) {
        super(fillColorIndex, xPos, yPos);
        this.xCoords = new int[xCoords.length];
        this.yCoords = new int[yCoords.length];
        this.txCoords = new int[xCoords.length];
        this.tyCoords = new int[yCoords.length];

        for(int i = 0; i < xCoords.length; i++) {
            this.xCoords[i] = xCoords[i];
        }
        for (int i = 0; i < yCoords.length; i++) {
            this.yCoords[i] = yCoords[i];
        }
        for (int i = 0; i < xCoords.length; i++) {
            this.txCoords[i] = xCoords[i];
        }
        for (int i = 0; i < yCoords.length; i++) {
            this.tyCoords[i] = yCoords[i];
        }
    }

    /**
     * Transforms the Polygon depending on the scale values and Z rotate value
     */
    private void Transform() {
        double degs = super.GetZRotate();
        double rads = Math.toRadians(degs);
        double Sx = super.GetScaleX();
        double Sy = super.GetScaleY();
        for (int i = 0; i < xCoords.length; i++)
        {
            double x = Sx * this.xCoords[i];
            double y = Sy * this.yCoords[i];
            this.txCoords[i] = (int)(((x * Math.cos(rads) - y * Math.sin(rads)) +
                    super.GetX()) + 0.5);
            this.tyCoords[i] = (int)(((x * Math.sin(rads) + y * Math.cos(rads)) +
                    super.GetY()) + 0.5);
        }
    }

    /**
     * public void Draw(Graphics g)
     *
     * Render the Polygon for both filled and outlined according to the states
     *
     * @param  - Graphics g is the graphics context
     */
    @Override
    public void Draw(Graphics g) {
        Transform();
        g.setColor(super.GetFillColor());
        g.fillPolygon(this.txCoords, this.tyCoords, this.txCoords.length);

        if (super.hasOutline()) {
            g.setColor(super.GetOutlineColor());
            g.drawPolygon(this.txCoords, this.tyCoords, this.txCoords.length);
        }
    }
}
