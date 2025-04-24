import java.awt.*;

/**
 * Oval2D Shape
 *
 * @author (Ryan Kee)
 * @version (v1.0 4-14-25)
 */
public class Oval2D extends Shape2D{
    private int width;
    private int length;

    /**
     * Constructor for objects of class Oval2D
     */
    public Oval2D() {
        super(Shape2D.GREEN, 0, 0);
        this.width = 20;
        this.length = 40;
    }

    /**
     * Constructor for objects of the class Oval2D
     * @param fillColorIndex - the int value of the color to fill the shape
     * @param xPosition - the X position of the shape
     * @param yPosition - the Y position of the shape
     * @param width - the width of the oval
     * @param length - the length of the oval
     */
    public Oval2D(int fillColorIndex, int xPosition, int yPosition, int width, int length) {
        super(fillColorIndex, xPosition, yPosition);
        this.length = length;
        this.width = width;
    }

    /**
     * public void Draw(Graphics g)
     *
     * Render the Oval for both filled and outlined according to the states
     *
     * @param  - Graphics g is the graphics context
     */
    @Override
    public void Draw(Graphics g) {
        g.setColor(super.GetFillColor());
        g.fillOval(super.GetX(), super.GetY(), this.width, this.length);

        if(super.hasOutline()) {
            g.setColor(super.GetOutlineColor());
            g.drawOval(super.GetX(), super.GetY(), this.width, this.length);
        }
    }


}
