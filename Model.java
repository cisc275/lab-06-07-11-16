import java.awt.event.KeyEvent;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/


class Model {
	
	final static int FIRE_LENGTH = 4;
	final static int JUMP_LENGTH = 8;
	
	Direction d = Direction.SOUTHEAST;
	
	final int frameWidth;
	final int frameHeight;
	final int imgWidth;
	final int imgHeight ;
	
	int xloc = 0;
	int yloc = 0;
	boolean moving = true;
	final int xIncr = 8; //used as speed basically
	final int yIncr = 6;
	int xChange = xIncr;
	int yChange = yIncr;
	KeyEvent e;
	
	//Three state for the Orc
	private int jumping = 0;
	private int firing = 0;
	
	 
	public Model(int w, int h, int iw, int ih){
		frameWidth = w;
		frameHeight = h;
		imgWidth = iw;
		imgHeight = ih;
	}
	
	
	//model.getX(), model.getY(), model.getDirect());
	public int getX() {
		return xloc;
	}
	public int getY() {
		return yloc;
	}
	public Direction getDirect() {
		return d;
	}
	public boolean getMoving() {
		return moving;
	}
	
	public boolean isJumping() {
		return jumping > 0;
	}
	
	public boolean isFiring() {
		return firing > 0;
	}
	
	public void updateLocationAndDirection() {
		if (isJumping()) {
			jumping ++;
			if (jumping > JUMP_LENGTH)
				jumping = 0;
		}
		if (isFiring()) {
			firing ++;
			if (firing > FIRE_LENGTH)
				firing = 0;
		}
		if (moving && !isFiring()) {
			int frameXSize = frameWidth - imgWidth; //adjust for size of image
			int frameYSize = frameHeight - imgHeight;
			if (xloc < 0)
				d = Direction.dirTo(1, d.getDY());
			if (xloc > frameXSize)
				d = Direction.dirTo(-1, d.getDY());
			if (yloc < 0)
				d = Direction.dirTo(d.getDX(), 1);
			if (yloc > frameYSize)
				d = Direction.dirTo(d.getDX(), -1);
			xloc += xChange * d.getDX();
			yloc += yChange * d.getDY();
		}
    }
    
	//if pressed J, start jumping and change firing state back to false (if the orc was firing)
	public void jump() {
		if (!isJumping() && !isFiring())
			jumping = 1;
	}
	
	//if pressed F, start firing and change jump state back to false (if the orc was jumping)
	public void fire() {
		if (!isJumping() && !isFiring())
			firing = 1;
	}
	
	//if pressed R and the orc is firing or jumping, return to running state;
	//change the boolean value for jumping and firing back to false
	public void run() {
		
	}
	
	
	//return the state of the orc
	public String getState() {
		if (isJumping())
			return "jump";
		else if (isFiring())
			return "fire";
		else
			return "forward";
	}
	
    public void startStop() {
		this.moving = !this.moving;
		/*if (buttonOn) {
		    System.out.println("turned on");
		} else {
		    System.out.println("turned off");
		}*/
    }

}
