/**
 * Paddle Bot
 *
 *
 * @author (Ryan Kee and Alberto Rodriguez)
 * @version (v1.0 5-1-25)
 */
public class Bot {
	private Rectangle2D paddle;
	private Circle2D ball;
	private int paddleYPos;
	private int ballYPos;

	/**
	 * Creates a Paddle Bot
	 * @param paddle - The paddle to be controlled
	 * @param ball - the ball that the paddle will follow
	 */
	public Bot(Rectangle2D paddle, Circle2D ball) {
		this.paddle = paddle;
		this.ball = ball;
		paddleYPos = paddle.GetY();
		ballYPos = ball.GetY();
	}

	/**
	 * Moves the paddle according to the position of the ball
	 */
	public void botMove() {
		int difference = ballYPos - paddleYPos;
		if(difference > 30 && !(paddleYPos + paddle.GetHeight() == 600)) {
			paddle.SetSpeed(0, 5);
			paddle.Animate();
		}
		if(difference < -30 && !(paddleYPos == 70)) {
			paddle.SetSpeed(0, -5);
			paddle.Animate();
		}
		
		if(difference == 0) {
			paddle.SetSpeed(0, 0);
			paddle.Animate();
		}
		
	}

}
