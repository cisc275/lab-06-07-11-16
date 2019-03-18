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
    public int getX(){
    	return xloc;
    	}
    public int getY(){
    	return yloc;
    	}
    public Direction getDirect(){
    	return d;
    	}
    
    public void updateLocationAndDirection(){

    	int frameXSize = frameWidth - imgWidth; //adjust for size of image
    	int frameYSize = frameHeight - imgHeight;
    	
    	if(yloc <= 0) {//if reach the upside edge
    		if(d == Direction.NORTH) {//if current direction is north
    			d = Direction.SOUTH;
    		}
    		else if(d ==Direction.NORTHEAST) {
    			d = Direction.SOUTHEAST; //if currently moving in south east
    		}
    		else if (d == Direction.NORTHWEST) { 
    			d = Direction.SOUTHWEST;
    		}
    	}
    	else if(yloc >= frameYSize) { //if touch the bottom edge
    		if(d == Direction.SOUTH) {
    			d = Direction.NORTH;
    		}
    		else if(d ==Direction.SOUTHEAST) {
    			d = Direction.NORTHEAST;
    		}
    		else if (d == Direction.SOUTHWEST) {
    			d = Direction.NORTHWEST;
    		}
    	}

    	if(xloc <= 0) {//if reach the left side edge
    		if(d == Direction.WEST) {//if current direction is west
    			d = Direction.EAST; //chance the direction to east
    		}
    		else if(d ==Direction.SOUTHWEST) {//if currently moving in south west
    			d = Direction.SOUTHEAST; //change the direction to south east
    		}
    		else if (d == Direction.NORTHWEST) {  // if moving north west
    			d = Direction.NORTHEAST; //change to north east
    		}
    	}
    	else if(xloc >= frameXSize) {//if reach the right side edge
    		if(d == Direction.EAST) {//if current direction is east
    			d = Direction.WEST; //chance the direction to west
    		}
    		else if(d ==Direction.SOUTHEAST) {//if currently moving in south west
    			d = Direction.SOUTHWEST; //change the direction to south east
    		}
    		else if (d == Direction.NORTHEAST) {  // if moving north west
    			d = Direction.NORTHWEST; //change to north east
    		}
    	}


	
	switch(d){
	case NORTH:
	    yloc -= yIncr;
	    break;
	case SOUTH:
	    yloc += yIncr;
	    break;
	case NORTHEAST:
	    xloc += xIncr;
	    yloc -= yIncr;
	    break;
        case NORTHWEST:
	    xloc -= xIncr;
	    yloc -= yIncr;
	    break;
	case SOUTHEAST:
	    xloc += xIncr;
	    yloc += yIncr;
	    break;
	case SOUTHWEST:
	    xloc -= xIncr;
	    yloc += yIncr;
	    break;
	case EAST:
	    xloc += xIncr;
	    break;
	case WEST:
	    xloc -= xIncr;
	    break;
	}

    }

}
