public enum Direction {

	NORTH("north", 0, 0, -1),
	NORTHEAST("northeast", 1, 1, -1),
	EAST("east", 2, 1, 0),
	SOUTHEAST("southeast", 3, 1, 1),
	SOUTH("south", 4, 0, 1),
	SOUTHWEST("southwest", 5, -1, 1),
	WEST("west", 6, -1, 0),
	NORTHWEST("northwest", 7, -1, -1);
	
	private String name = null;
	private int index;
	private int dx = 0;
	private int dy = 0;
	
	private Direction(String s, int h, int i, int j){
		name = s;
		index = h;
		dx = i;
		dy = j;
	}
	public String getName() {
		return name;
	}
	public int getIndex() {
		return index;
	}
	public int getDX() {
		return dx;
	}
	public int getDY() {
		return dy;
	}
	/**
	 * 
	 * @param dex the index
	 * @return the direction corresponding to that index
	 */
	public static Direction atIndex(int dex) {
		switch (dex % 8) {
			case 0: return NORTH; 
			case 1: return NORTHEAST; 
			case 2: return EAST; 
			case 3: return SOUTHEAST; 
			case 4: return SOUTH; 
			case 5: return SOUTHWEST; 
			case 6: return WEST; 
			case 7: return NORTHWEST; 
			default: return SOUTH;
		}
	}
	
	public static Direction dirTo(int dx, int dy) {
		if (dy < 0) {
			if (dx < 0)
				return NORTHWEST;
			else if (dx == 0)
				return NORTH;
			else
				return NORTHEAST;
		} else if (dy == 0) {
			if (dx < 0)
				return WEST;
			else if (dx == 0)
				return SOUTH; //default or something
			else
				return EAST;
		} else {
			if (dx < 0)
				return SOUTHWEST;
			else if (dx == 0)
				return SOUTH;
			else
				return SOUTHEAST;
		}
	}
}
