import java.awt.*;

/**
 * Circle2D Shape
 *
 * @author (Ryan Kee)
 * @version (v1.0 4-14-25)
 */
public class Circle2D extends Shape2D {
    private int     diameter;

    /**
     * Constructor for objects of class Circle
     */
    public Circle2D() {
        super(Shape2D.GREEN, 0, 0);
        this.diameter       = 40;
    }

    /**
     * Constructor for objects of class Circle2D
     */
    public Circle2D(int fillColorIndex, int xPosition, int yPosition, int diameter) {
        super(fillColorIndex, xPosition, yPosition);
        this.diameter       = diameter;
    }

    public int GetDiameter() {
        return diameter;
    }


    /**
     * public void Draw(Graphics g)
     *
     * Render the circle for both filled and outlined according to the states
     *
     * @param  - Graphics g is the graphics context
     * @return - void
     */
    public void Draw(Graphics g) {

        g.setColor(super.GetFillColor());
        g.fillOval(super.GetX(), super.GetY(), this.diameter, this.diameter);

        if(super.hasOutline()) {
            g.setColor(super.GetOutlineColor());
            g.drawOval(super.GetX(), super.GetY(), this.diameter, this.diameter);
        }
    }

}
