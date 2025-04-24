/**
 * 2D CanvasPanel
 * 
 *
 * @author (Prof R)
 * @version (v1.0 11-17-22)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
// For Sprites
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CanvasPanel_P7 extends JPanel
{
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;
    private final static int CANVAS_WIDTH = 600;
    private final static int CANVAS_HEIGHT = 600;


    List<Shape2D>   shapesList;
    boolean         action;
    private boolean jumpUp;    // for Sprite
    private boolean fallDown;  // for Sprite
    private int     frameNumber;
    private int     time;

    private int     leftPaddle = 0;
    private int     rightPaddle = 1;
    private int     ball = 2;

    public CanvasPanel_P7() {
        shapesList = new ArrayList<>();

        // Callback from keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener());
        System.out.println("keyboard event registered");

        shapesList.add(new Rectangle2D(Shape2D.WHITE, CanvasPanel_P7.getCanvasXBorder(), (CanvasPanel_P7.getCanvasHeight() / 2), 25, 150));
        shapesList.add(new Rectangle2D(Shape2D.WHITE, CanvasPanel_P7.getCanvasWidth() - 25, (CanvasPanel_P7.getCanvasHeight() / 2), 25, 150));
        shapesList.add(new Circle2D(Shape2D.RED, CanvasPanel_P7.getCanvasWidth() / 2, CanvasPanel_P7.getCanvasHeight() / 2, 10));


        shapesList.get(ball).SetSpeed(5, 0);

        // Create a render loop
        // Create a Swing Timer that will tick 30 times a second
        // At each tick the ActionListener that was registered via the lambda expression will be invoked
        time = 0;
        Timer renderLoop = new Timer(30, (ActionEvent ev) -> {frameNumber++; Simulate(); repaint();}); // lambda expression for ActionListener implements actionPerformed
        renderLoop.start();
    }

    public void Simulate() {
        if (action) {
            Shape2D ball = shapesList.get(this.ball);
            Shape2D leftPaddle = shapesList.get(this.leftPaddle);
            Shape2D rightPaddle = shapesList.get(this.rightPaddle);




            if (paddleCollision(ball, rightPaddle)) {
                ball.SetSpeed(-5,0);
            }

            if (paddleCollision(ball, leftPaddle)) {
                ball.SetSpeed(5, 0);
            }

            ball.Animate();


        }
    }

    // This method is called by renderloop
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set window background to black
        g.setColor(Color.BLACK);
        g.fillRect(0,0,CANVAS_WIDTH + 2 * X_CORNER, CANVAS_HEIGHT + 2 * Y_CORNER); //draw the black border

        // Set canvas background to grey
        g.setColor(Color.BLACK);
        g.fillRect(X_CORNER, Y_CORNER, CANVAS_WIDTH, CANVAS_HEIGHT); //make the canvas white

        // Display frame number
        g.setColor(Color.white);   
        g.setFont(new Font("Consolas", Font.PLAIN, 30));
        g.drawString(Integer.toString(frameNumber), 300, 70);

        // Render all the shapes in the shapes list
        for (Shape2D shape : shapesList)
        {
            shape.Draw(g);
        }
    }
    

    public static int getCanvasWidth()
    {
        return CANVAS_WIDTH;
    }

    public static int getCanvasHeight()
    {
        return CANVAS_HEIGHT;
    }

    public static int getCanvasXBorder()
    {
        return X_CORNER;
    }

    public static int getCanvasYBorder()
    {
        return Y_CORNER;
    }

    private static boolean ballOutOfBounds(Circle2D ball) {
        int ballXPos = ball.GetX();
        return ballXPos > CanvasPanel_P7.getCanvasWidth() || ballXPos < CanvasPanel_P7.getCanvasXBorder();
    }

    private static boolean paddleCollision(Shape2D circle, Shape2D rectangle) {
        Circle2D ball = (Circle2D) circle;
        Rectangle2D paddle = (Rectangle2D) rectangle;


        int ballLeftBoundary = ball.GetX();
        int ballRightBoundary = ball.GetX() + (ball.GetDiameter());

        int paddleLeftBoundary = paddle.GetX();
        int paddleRightBoundary = paddle.GetX() + paddle.GetWidth();

        return !(ballLeftBoundary > paddleRightBoundary || ballRightBoundary < paddleLeftBoundary);
    }

    public class myActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:

                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("press down arrow");
                    break;
                case KeyEvent.VK_LEFT:
                    System.out.println("press left arrow");
                    break;
                case KeyEvent.VK_RIGHT:

                    break;
                case KeyEvent.VK_A:
                    action = true;
                    break;
                case KeyEvent.VK_S:
                    action = false;
                    break;
                default:
                    System.out.println("press some other key besides the arrow keys");
            }
        }

        public void keyReleased(KeyEvent e)
        {
            //System.out.println("released");
        }
    }
}
