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


class Model{

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
	
	public void updateLocationAndDirection() {
		if (moving) {
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
    

    public void startStop() {
		this.moving = !this.moving;
		/*if (buttonOn) {
		    System.out.println("turned on");
		} else {
		    System.out.println("turned off");
		}*/
    }

}
