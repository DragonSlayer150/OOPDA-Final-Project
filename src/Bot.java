public class Bot {
	private Rectangle2D paddle;
	private Circle2D ball;
	private int paddleYPos;
	private int ballYPos;
	
	public Bot(Rectangle2D paddle, Circle2D ball) {
		this.paddle = paddle;
		this.ball = ball;
		paddleYPos = paddle.GetY();
		ballYPos = ball.GetY();
	}
	
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
