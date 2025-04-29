import java.awt.*;
/**
 * Shape2D Class
 *
 * @author (Ryan Kee)
 * @version (v1.0 4-14-25)
 */
public abstract class Shape2D {
    public final static int RED = 0;
    public final static int GREEN = 1;
    public final static int BLUE = 2;
    public final static int BLACK = 3;
    public final static int GREY = 4;
    public final static int WHITE = 5;
    public final static int YELLOW = 6;
    public final static int CYAN = 7;
    public final static int MAGENTA = 8;
    public final static int BROWN = 9;

    public static final Color[] COLORS = {
            //         R     G    B
            new Color(255,   0,   0),  // Red     0
            new Color(  0, 255,   0),  // Green   1
            new Color(  0,   0, 255),  // Blue    2
            new Color(  0,   0,   0),  // Black   3
            new Color(128, 128, 128),  // Grey    4
            new Color(255, 255, 255),  // White   5
            new Color(255, 255,   0),  // Yellow  6
            new Color(  0, 255, 255),  // Cyan    7
            new Color(255,   0, 255),  // Magenta 8
            new Color(165,  42,  42),  // Brown   9
            new Color(255,  38,  38),
            new Color(255, 168,  38),
            new Color(212, 255,  38),
            new Color( 82, 255,  38),
            new Color( 38, 255, 125),
            new Color( 38, 255, 255),
            new Color( 38, 125, 255),
            new Color( 82,  38, 255),
            new Color(212,  38, 255),
            new Color(255,  38, 168),
    };

    private int     xPos;               // xPos
    private int     yPos;               // yPos
    private int     xVel;
    private int     yVel;

    private double  Sx = 1;
    private double  Sy = 1;
    private double  rotAngleZ = 1;

    private Color   fillColor;          // the color of the sphere
    private int     fillColorIndex;

    private boolean outline;
    private Color   outlineColor;
    private int     outlineColorIndex;

    /**
     * Creates a Shape2D Object
     * @param fillColorIndex
     * @param xPos
     * @param yPos
     */
    public Shape2D( int fillColorIndex, int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.fillColor = COLORS[fillColorIndex];
        this.fillColorIndex = fillColorIndex;
        this.outline = false;
        this.outlineColor = null;
        this.xVel = 0;
        this.yVel = 0;
    }

    //Animation and Movement:

    /**
     * Moves the shape by an amount (xDelta, yDelta)
     *
     * Move - translates the shape by an amount (xDelta, yDelta)
     *
     * @param  xDelta - amount to translate along the x axis
     * @param  yDelta - amount to translate along the y axis
     */
    public void Move(int xDelta, int yDelta) {
        //move the shape
        this.xPos += xDelta;
        this.yPos += yDelta;
    }

    /**
     * Moves the shape by the set X and Y Velocity
     */
    public void Animate() {
        xPos += xVel;
        yPos += yVel;
    }

    /**
     * Sets the X and Y velocities of the shape
     * @param xVel - the X velocity
     * @param yVel - the Y velovity
     */
    public void SetSpeed(int xVel, int yVel) {
        this.xVel = xVel;
        this.yVel = yVel;
    }

    /**
     * Returns the X Velocity
     * @return xVel
     */
    public int GetXVel() {
        return xVel;
    }

    /**
     * Returns the Y Velocity
     * @return yVel
     */
    public int GetYVel() {
        return yVel;
    }

    /**
     * Sets the position of the Shape to the specified coordinates
     * @param xPos - X position to be moved to
     * @param yPos - Y Position to be moved to
     */
    public void SetPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Returns the X position of the shape
     * @return xPos
     */
    public int GetX() {
        return this.xPos;
    }

    /**
     * Returns the Y position of the shape
     * @return yPos
     */
    public int GetY() {
        return this.yPos;
    }

    /**
     * Returns the X Scale of the Shape
     * @return Sx - the X scale
     */
    public double GetScaleX() {
        return this.Sx;
    }

    /**
     * Returns the Y Scale of the Shape
     * @return Sy - the Y scale
     */
    public double GetScaleY() {
        return this.Sy;
    }

    /**
     * Returns the Z rotate angle of the shape
     * @return rotAngleZ - the Z rotate angle
     */
    public double GetZRotate() {
        return this.rotAngleZ;
    }

    //Fill Getters and Setters

    /**
     * Returns the fill color of the shape
     * @return fillColor
     */
    public Color GetFillColor() {
        return this.fillColor;
    }

    /**
     * Set the fill colour of the Shape
     * @param fillColorIndex - the integer value of the colour to be picked
     */
    public void SetFillColor(int fillColorIndex) {
        this.fillColorIndex = fillColorIndex;
        this.fillColor = COLORS[fillColorIndex];
    }

    //Outline Getters and Setters

    /**
     * Sets whether or not the Shape has an outline
     * @param hasOutline - does the Shape have an outline?
     */
    public void SetOutline(boolean hasOutline) {
        this.outline = hasOutline;
    }

    /**
     * Sets the outline color of the Shape
     * @param outlineColorIndex - int value of the color to be picked
     */
    public void SetOutlineColor(int outlineColorIndex) {
        this.outlineColorIndex = outlineColorIndex;
        this.outlineColor = COLORS[outlineColorIndex];
    }

    /**
     * Returns the outline color of the Shape
     * @return outlineColor - the color of the Outline
     */
    public Color GetOutlineColor() {
        return this.outlineColor;
    }

    /**
     * Returns whether or not the Shape has an outline
     * @return outline
     */
    public boolean hasOutline() {
        return this.outline;
    }

    /**
     * {Abstract} Draws the shape to the Graphics g
     * @param g - the graphic to be drawn to
     */
    public abstract void Draw(Graphics g);



}
