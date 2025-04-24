
/**
 * StarPoly2D Class
 *
 * @author (Ryan Kee)
 * @version (v1.0 4-19-25)
 */
public class StarPoly2D extends Polygon2D{

    private static int[] xCoords = {0, 2, 10, 2, 0, -2 ,-10, -2};
    private static int[] yCoords = {-10, -2, 0, 2, 10, 2, 0, -2};

    /**
     * Constructs a StarPoly2D object
     * @param fillColorIndex - int value of the color to fill the shape
     * @param xPos - the X position of the shape
     * @param yPos - the Y position of the shape
     */
    public StarPoly2D(int fillColorIndex, int xPos, int yPos) {
        super(fillColorIndex, xPos, yPos, xCoords, yCoords);
    }
}
