import java.awt.*;

/**
 * Rectangle2D Shape
 *
 * @author (Ryan Kee)
 * @version (v1.0 4-14-25)
 */
public class Rectangle2D extends Shape2D{
    private int width;
    private int length;

    /**
     * Constructor for objects of class Rectangle2D
     */
    public Rectangle2D() {
        super(Shape2D.GREEN, 0, 0);
        this.width = 40;
        this.length = 40;
    }

    /**
     * Constructor for objecst of the class Rectangle2D
     * @param fillColorIndex - the int value for the color to fill the shape
     * @param xPosition - the X position of the shape
     * @param yPosition - the Y position of the shape
     * @param width - the width of the Rectangle
     * @param length - the Length of the Rectangle
     */
    public Rectangle2D(int fillColorIndex, int xPosition, int yPosition, int width, int length) {
        super(fillColorIndex, xPosition, yPosition);
        this.length = length;
        this.width = width;
    }

    public int GetWidth() {
        return width;
    }

    /**
     * public void Draw(Graphics g)
     *
     * Render the rectangle for both filled and outlined according to the states
     *
     * @param  - Graphics g is the graphics context
     */
    @Override
    public void Draw(Graphics g) {
        g.setColor(super.GetFillColor());
        g.fillRect(super.GetX(), super.GetY(), this.width, this.length);

        if(super.hasOutline()) {
            g.setColor(super.GetOutlineColor());
            g.drawRect(super.GetX(), super.GetY(), this.width, this.length);
        }
    }
}
