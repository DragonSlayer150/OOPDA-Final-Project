
/**
 * 2D CanvasPanel
 * 
 *
 * @author (Ryan Kee and Alberto Rodriguez)
 * @version (v1.0 5-2-25)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class CanvasPanel_FinalProject extends JPanel {
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;
    private final static int CANVAS_WIDTH = 600;
    private final static int CANVAS_HEIGHT = 600;

    private final static int TOP_BORDER_YPOS = 70;
    private final static int BOTTOM_BORDER_YPOS = 600;
    private final static int LEFT_BORDER_XPOS = 10;
    private final static int RIGHT_BORDER_XPOS = 640;

    List<Shape2D> shapesList;
    boolean action;

    private final int LEFT_PADDLE = 0;
    private final int RIGHT_PADDLE = 1;
    private final int BALL = 2;

    //Paddle movement direction booleans
    private boolean LeftPaddleUp;
    private boolean LeftPaddleDown;
    private boolean RightPaddleUp;
    private boolean RightPaddleDown;

    private boolean gameReset;
    private boolean gameOver;
    private boolean botActive;
    private Thread hitSound;

	private static int p1Score = 0;
    private static int p2Score = 0;

    public CanvasPanel_FinalProject() {
        shapesList = new ArrayList<>();

        // Callback from keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener());
        System.out.println("keyboard event registered");

        shapesList.add(new Rectangle2D(Shape2D.WHITE, CanvasPanel_FinalProject.getCanvasXBorder(), (CanvasPanel_FinalProject.getCanvasHeight() / 2), 25, 150));
        shapesList.add(new Rectangle2D(Shape2D.WHITE, CanvasPanel_FinalProject.getCanvasWidth(), (CanvasPanel_FinalProject.getCanvasHeight() / 2), 25, 150));
        shapesList.add(new Circle2D(Shape2D.WHITE, 320, 335, 10));

        //Borders
        shapesList.add(new Rectangle2D(Shape2D.WHITE, 10, 600, 630, 1));//bottom
        shapesList.add(new Rectangle2D(Shape2D.WHITE, 10, 70, 630, 1));//top
        shapesList.add(new Rectangle2D(Shape2D.WHITE, 10, 70, 1, 530)); //left
        shapesList.add(new Rectangle2D(Shape2D.WHITE, 640, 70, 1, 530)); //right
        shapesList.add(new Rectangle2D(Shape2D.WHITE, 325, 70, 1, 530)); //Middle

        gameOver = false;
        gameReset = false;

        shapesList.get(BALL).SetSpeed(5, 3);
        // Create a render loop
        // Create a Swing Timer that will tick 30 times a second
        // At each tick the ActionListener that was registered via the lambda expression will be invoked
        Timer renderLoop = new Timer(30, (ActionEvent ev) -> {
            Simulate();
            repaint();
        }); // lambda expression for ActionListener implements actionPerformed
        renderLoop.start();
    }

    /**
     * The simulation loop to be ran every 30 ms
     */
    public void Simulate() {
        Circle2D ball = (Circle2D) shapesList.get(BALL);
        Rectangle2D leftPaddle = (Rectangle2D) shapesList.get(LEFT_PADDLE);
        Rectangle2D rightPaddle = (Rectangle2D) shapesList.get(RIGHT_PADDLE);
	    Bot paddleBot = new Bot(rightPaddle, ball);



        if (action) {

            if (LeftPaddleUp && !(leftPaddle.GetY() == TOP_BORDER_YPOS)) {
                //if w key pressed and paddle is not at top of frame, move the paddle
                leftPaddle.SetSpeed(0, -5);
                leftPaddle.Animate();
            } else {
                //stop paddle movement when key is released
                leftPaddle.SetSpeed(0, 0);
            }

            if (LeftPaddleDown && !(leftPaddle.GetY() + leftPaddle.GetHeight() == BOTTOM_BORDER_YPOS)) {
                //if s key pressed and paddle is not at bottom of frame, move paddle
                leftPaddle.SetSpeed(0, 5);
                leftPaddle.Animate();
            } else {
                //stop paddle movement when key is released
                leftPaddle.SetSpeed(0, 0);
            }

            if (RightPaddleUp && !(rightPaddle.GetY() == TOP_BORDER_YPOS)) {
                //if up arrow pressed and paddle is not at top of frame, move the paddle
                rightPaddle.SetSpeed(0, -5);
                rightPaddle.Animate();
            } else {
                //stop paddle movement when key is released
                rightPaddle.SetSpeed(0, 0);
            }

            if (RightPaddleDown && !(rightPaddle.GetY() + rightPaddle.GetHeight() == BOTTOM_BORDER_YPOS)) {
                //if down arrow pressed and paddle is not at bottom of frame, move paddle
                rightPaddle.SetSpeed(0, 5);
                rightPaddle.Animate();
            } else {
                //stop paddle movement when key is released
                rightPaddle.SetSpeed(0, 0);
            }

            if (leftPaddle.paddleCollision(ball)) {
                ball.SetSpeed(5, ball.GetYVel() + leftPaddle.GetYVel());
                hitSound = new Thread(new AudioPlayer("pong_hit.wav"));
                hitSound.start();
            }

            if (rightPaddle.paddleCollision(ball)) {
                ball.SetSpeed(-5, (ball.GetYVel() + rightPaddle.GetYVel()));
                hitSound = new Thread(new AudioPlayer("pong_hit.wav"));
                hitSound.start();
            }

            if (!(ball.GetY() > TOP_BORDER_YPOS)) {
                ball.SetSpeed(ball.GetXVel(), 5);
            }

            if (!(ball.GetY() < BOTTOM_BORDER_YPOS)) {
                ball.SetSpeed(ball.GetXVel(), -5);
            }

            if (ballOutOfBounds(ball)) {
                action = false;
		        if(ball.GetX() > RIGHT_BORDER_XPOS) {
                	p1Score++;
                	shrinkPaddle(rightPaddle);
                }
                else if(ball.GetX() < LEFT_BORDER_XPOS) {
                	p2Score++;
                	shrinkPaddle(leftPaddle);
                }
                ball.SetPos(315, 335);
		        leftPaddle.SetPos(CanvasPanel_FinalProject.getCanvasXBorder(), (CanvasPanel_FinalProject.getCanvasHeight() / 2));
                rightPaddle.SetPos(CanvasPanel_FinalProject.getCanvasWidth(), (CanvasPanel_FinalProject.getCanvasHeight() / 2));
            }

            if (leftPaddle.GetHeight() <= 0 || rightPaddle.GetHeight() <= 0) {
                gameOver = true;

            }


	    if(botActive) {
	    	paddleBot.botMove();
	    }
            ball.Animate();
        }

        if (gameReset) {
            action = false;
            p1Score = 0;
            p2Score = 0;
            resetPaddle(leftPaddle);
            resetPaddle(rightPaddle);
            leftPaddle.SetPos(CanvasPanel_FinalProject.getCanvasXBorder(), (CanvasPanel_FinalProject.getCanvasHeight() / 2));
            rightPaddle.SetPos(CanvasPanel_FinalProject.getCanvasWidth(), (CanvasPanel_FinalProject.getCanvasHeight() / 2));
            ball.SetPos(315, 335);

            gameOver = false;
            gameReset = false;
        }
    }

    /**
     * The things to be painted to the screen every 30 ms
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set window background to black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, CANVAS_WIDTH + 2 * X_CORNER, CANVAS_HEIGHT + 2 * Y_CORNER); //draw the black border

        // Set canvas background to grey
        g.setColor(Color.BLACK);
        g.fillRect(X_CORNER, Y_CORNER, CANVAS_WIDTH, CANVAS_HEIGHT); //make the canvas white

        // Display frame number
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 30));
        //g.drawString(Integer.toString(frameNumber), 300, 70);
        g.drawString(Integer.toString(p1Score), 150, 70);
        g.drawString(Integer.toString(p2Score), 450, 70);   

        if (!action) {
            g.setFont(new Font("Consolas", Font.PLAIN, 20));
            g.drawString("Press A to Start", 230, 50);
        }

        if (gameOver) {
            g.setFont(new Font("Consolas", Font.BOLD, 50));
            g.drawString("Game Over", 200, 300);
            g.setFont(new Font("Consolas", Font.PLAIN, 30));
            g.drawString("Press R to Start Over", 150, 350);
        }

        // Render all the shapes in the shapes list
        for (Shape2D shape : shapesList) {
            shape.Draw(g);
        }
    }

    /**
     * Returns the canvas width
     * @return CANVAS_WIDTH
     */
    public static int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    /**
     * Returns the canvas height
     * @return CANVAS_HEIGHT
     */
    public static int getCanvasHeight() {
        return CANVAS_HEIGHT;
    }

    /**
     * Returns the Canvas X border
     * @return X_CORNER
     */
    public static int getCanvasXBorder() {
        return X_CORNER;
    }

    /**
     * Returns the canvas Y border
     * @return Y_CORNER
     */
    public static int getCanvasYBorder() {
        return Y_CORNER;
    }

    /**
     * Tests if the ball has gone out of bounds
     * @param ball - the ball to be tested
     * @return isOutOfBounds
     */
    private static boolean ballOutOfBounds(Circle2D ball) {
        int ballXPos = ball.GetX();
        return ballXPos > RIGHT_BORDER_XPOS || ballXPos < LEFT_BORDER_XPOS;
    }

    /**
     * Shrinks the height of the paddle
     * @param paddle - the paddle to be shrank
     */
    private static void shrinkPaddle(Rectangle2D paddle) {
        paddle.SetHeight(paddle.GetHeight()-30);
    }

    /**
     * Resets the height of the paddle
     * @param paddle - the paddle to be reset
     */
    private static void resetPaddle(Rectangle2D paddle) {
        paddle.SetHeight(150);
    }

    public class myActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_W:
                    LeftPaddleUp = true;
                    break;
                case KeyEvent.VK_S:
                    LeftPaddleDown = true;
                    break;
                case KeyEvent.VK_UP:
                    RightPaddleUp = true;
                    break;
                case KeyEvent.VK_DOWN:
                    RightPaddleDown = true;
                    break;


                case KeyEvent.VK_A:
                    action = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    action = false;
                    break;
                case KeyEvent.VK_R:
                    gameReset = true;
                    break;           
		            case KeyEvent.VK_B:
                	 botActive = !botActive;
                	break;

                default:

            }
        }

        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    LeftPaddleUp = false;
                    break;
                case KeyEvent.VK_S:
                    LeftPaddleDown = false;
                    break;
                case KeyEvent.VK_UP:
                    RightPaddleUp = false;
                    break;
                case KeyEvent.VK_DOWN:
                    RightPaddleDown = false;
                    break;
            }
        }
    }

}
