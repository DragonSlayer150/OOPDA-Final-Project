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

public class CanvasPanel_P7 extends JPanel
{
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;
    private final static int CANVAS_WIDTH = 600;
    private final static int CANVAS_HEIGHT = 600;


    List<Shape2D>   shapesList;
    boolean         action;
    private boolean leftPaddleUp;
    private boolean leftPaddleDown;
    private boolean rightPaddleUp;
    private boolean rightPaddleDown;

    private int     frameNumber;

    private final int     leftPaddle = 0;
    private final int     rightPaddle = 1;
    private final int     ball = 2;

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
        Timer renderLoop = new Timer(30, (ActionEvent ev) -> {frameNumber++; Simulate(); repaint();}); // lambda expression for ActionListener implements actionPerformed
        renderLoop.start();
    }

    public void Simulate() {
        if (action) {
            Circle2D ball = (Circle2D) shapesList.get(this.ball);
            Rectangle2D leftPaddle = (Rectangle2D) shapesList.get(this.leftPaddle);
            Rectangle2D rightPaddle = (Rectangle2D) shapesList.get(this.rightPaddle);


            if (leftPaddleDown) {
                leftPaddle.SetSpeed(0, 5);
                leftPaddle.Animate();
            }

            if (leftPaddleUp) {
                leftPaddle.SetSpeed(0, -5);
                leftPaddle.Animate();
            }

            if (rightPaddleDown) {
                rightPaddle.SetSpeed(0, 5);
                rightPaddle.Animate();
            }

            if (rightPaddleUp) {
                rightPaddle.SetSpeed(0, -5);
                rightPaddle.Animate();
            }

            if (leftPaddle.paddleCollision(ball)) {
                ball.SetSpeed(5, 0);
            }

            if (rightPaddle.paddleCollision(ball)) {
                ball.SetSpeed(-5,0);
            }

            if (ballOutOfBounds(ball)) {
                ball.SetPos(CanvasPanel_P7.getCanvasWidth() / 2, CanvasPanel_P7.getCanvasHeight() / 2);
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
        return ballXPos > (CanvasPanel_P7.getCanvasWidth() + 50) || ballXPos < (CanvasPanel_P7.getCanvasXBorder() - 50);
    }

    public class myActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    rightPaddleUp = true;
                    break;
                case KeyEvent.VK_DOWN:
                    rightPaddleDown = true;
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
                        leftPaddleDown = true;
                    break;
                case KeyEvent.VK_W:
                        leftPaddleUp = true;
                        break;
                default:
                    System.out.println("press some other key besides the arrow keys");
            }
        }

        public void keyReleased(KeyEvent e)
        {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_S:
                    leftPaddleDown = false;
                    break;
                case KeyEvent.VK_W:
                    leftPaddleUp = false;
                    break;
                case KeyEvent.VK_UP:
                    rightPaddleUp = false;
                case KeyEvent.VK_DOWN:
                    rightPaddleDown = false;
                default:
            }
        }
    }
}
