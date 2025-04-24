import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Sprite2D Class
 *
 * @author (Ryan Kee)
 * @version (v1.0 4-19-25)
 */
public class Sprite2D extends Shape2D{
    private BufferedImage[] imageFrames;
    private int frame;

    /**
     * Constructs a Sprite2D object
     * @param xPos - the X position of the shape
     * @param yPos - the Y position of the shape
     * @param imageFrames - a BufferedImage array with the images for the sprite
     */
    public Sprite2D(int xPos, int yPos, BufferedImage[] imageFrames) {
        super(0, xPos, yPos);
        this.imageFrames = new BufferedImage[imageFrames.length];
        for(int i = 0; i < imageFrames.length; i++) {
            this.imageFrames[i] = imageFrames[i];
        }
        frame = 0;
    }

    /**
     * public void Draw(Graphics g)
     *
     * Render the Sprite for both filled and outlined according to the states
     *
     * @param  - Graphics g is the graphics context
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(imageFrames[frame], GetX(), GetY(), null);
        frame++;
        if(frame == imageFrames.length) {
            frame = 0;
        }
    }

}
