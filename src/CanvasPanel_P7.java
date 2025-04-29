
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

	private final static int TOP_BORDER_YPOS = 70;
	private final static int BOTTOM_BORDER_YPOS = 600;
	private final static int LEFT_BORDER_XPOS = 10;
	private final static int RIGHT_BORDER_XPOS = 640;


	List<Shape2D>   shapesList;
	boolean         action;
	private boolean jumpUp;    // for Sprite
	private boolean fallDown;  // for Sprite
	private int     frameNumber;
	private int     time;

	private int     leftPaddle = 0;
	private int     rightPaddle = 1;
	private int     ball = 2;

	//Paddle movement direction booleans
	private boolean LeftPaddleUp; 
	private boolean LeftPaddleDown; 
	private boolean RightPaddleUp; 
	private boolean RightPaddleDown; 


	public CanvasPanel_P7() {
		shapesList = new ArrayList<>();

		// Callback from keyboard events
		this.setFocusable(true);
		this.addKeyListener(new myActionListener());
		System.out.println("keyboard event registered");

		shapesList.add(new Rectangle2D(Shape2D.WHITE, CanvasPanel_P7.getCanvasXBorder(), (CanvasPanel_P7.getCanvasHeight() / 2), 25, 150));
		shapesList.add(new Rectangle2D(Shape2D.WHITE, CanvasPanel_P7.getCanvasWidth() - 25, (CanvasPanel_P7.getCanvasHeight() / 2), 25, 150));
		shapesList.add(new Circle2D(Shape2D.WHITE, 320, 335, 10));

		//Borders
		shapesList.add(new Rectangle2D(Shape2D.WHITE, 10, 600, 630, 1));//bottom 
		shapesList.add(new Rectangle2D(Shape2D.WHITE, 10, 70, 630, 1));//top
		shapesList.add(new Rectangle2D(Shape2D.WHITE, 10, 70, 1, 530)); //left
		shapesList.add(new Rectangle2D(Shape2D.WHITE, 640, 70 , 1, 530)); //right
		shapesList.add(new Rectangle2D(Shape2D.WHITE, 325, 70 , 1, 530)); //Middle
		
		// Create a render loop
		// Create a Swing Timer that will tick 30 times a second
		// At each tick the ActionListener that was registered via the lambda expression will be invoked
		time = 0;
		Timer renderLoop = new Timer(30, (ActionEvent ev) -> {frameNumber++; Simulate(); repaint();}); // lambda expression for ActionListener implements actionPerformed
		renderLoop.start();
	}

	public void Simulate() {
		if (action) {
			Shape2D shape = null;

			shape = shapesList.get(ball);

			shape.SetSpeed(5, 0);
			shape.Animate();

			shape = shapesList.get(leftPaddle);//Set shape to leftPaddle to check for input
			
			if (LeftPaddleUp && !(shape.GetY() == TOP_BORDER_YPOS)) {			
				//if w key pressed and paddle is not at top of frame, move the paddle
					shape.Move(0, -5);	
			}else{
				//stop paddle movement when key is released
				shape.Move(0, 0);
			}

			if (LeftPaddleDown && !(shape.GetY() + 150 == BOTTOM_BORDER_YPOS)) {			
				//if s key pressed and paddle is not at bottom of frame, move paddle
				shape.Move(0, 5);		
			}else{
				//stop paddle movement when key is released 
				shape.Move(0, 0);
			}


			shape = shapesList.get(rightPaddle);
			if (RightPaddleUp && !(shape.GetY() == TOP_BORDER_YPOS)) {
				//if up arrow pressed and paddle is not at top of frame, move the paddle
				shape.Move(0, -5);		
			}else{
				//stop paddle movement when key is released 

				shape.Move(0, 0);
			}


			shape = shapesList.get(1);
			if (RightPaddleDown && !(shape.GetY() + 150 == BOTTOM_BORDER_YPOS)) {
				//if down arrow pressed and paddle is not at bottom of frame, move paddle
				shape.Move(0, 5);		
			}else{
				//stop paddle movement when key is released 
				shape.Move(0, 0);
			}


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


	public class myActionListener extends KeyAdapter {
		public void keyPressed(KeyEvent e)
		{
			switch (e.getKeyCode())
			{

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
			case KeyEvent.VK_SPACE:
				action = false;
				break;
			default:
				System.out.println("press some other key besides the arrow keys");
			}
		}

		public void keyReleased(KeyEvent e)
		{
			switch (e.getKeyCode())
			{
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
