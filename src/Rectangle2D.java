import java.awt.*;

/**
 * Rectangle2D Shape
 *
 * @author (Ryan Kee)
 * @version (v1.0 4-14-25)
 */
public class Rectangle2D extends Shape2D{
    private int width;
    private int height;

    /**
     * Constructor for objects of class Rectangle2D
     */
    public Rectangle2D() {
        super(Shape2D.GREEN, 0, 0);
        this.width = 40;
        this.height = 40;
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
        this.height = length;
        this.width = width;
    }

    public int GetWidth() {
        return width;
    }
    public int GetHeight() {
        return height;
    }

    public void SetHeight(int height) {
    	this.height = height;
    }
    
    public boolean paddleCollision(Shape2D circle) {
        Circle2D ball = (Circle2D) circle;

        int ballLeftBoundary = ball.GetX();
        int ballRightBoundary = ball.GetX() + ball.GetDiameter();
        int ballTopBoundary = ball.GetY();
        int ballBottomBoundary = ball.GetY() + ball.GetDiameter();


        int paddleLeftBoundary = this.GetX();
        int paddleRightBoundary = this.GetX() + this.width;
        int paddleTopBoundary = this.GetY();
        int paddleBottomBoundary = this.GetY() + this.height;


        return !(ballLeftBoundary > paddleRightBoundary || ballRightBoundary < paddleLeftBoundary) && !(ballTopBoundary > paddleBottomBoundary || ballBottomBoundary < paddleTopBoundary);
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
        g.fillRect(super.GetX(), super.GetY(), this.width, this.height);

        if(super.hasOutline()) {
            g.setColor(super.GetOutlineColor());
            g.drawRect(super.GetX(), super.GetY(), this.width, this.height);
        }
    }
}
