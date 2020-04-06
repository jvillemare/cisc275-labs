package lab6;

public class Model { // update the stage
	
	private int picInd = 0;
	private double xloc = 1;
	private double yloc = 1;
	private final double xIncr = 8;
	private final double yIncr = 2;
	
	private boolean xFlag = true; // true = traveling east, false = west
	private boolean yFlag = true; // true = traveling south, false = north
    
	private Direction currentDirection = Direction.SOUTHEAST;
	private Direction futureDirection = Direction.SOUTHEAST;
	private boolean changingDirection = false;
	private Direction hitWall = Direction.WEST;
	
	private int width, height, imageWidth, imageHeight;
	
	private BassMode bassMode;
	private boolean overrideDirection = false;

	public Model(int width, int height, int imageWidth, int imageHeight) {
		this.width = width;
		this.height = height;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.bassMode = BassMode.DEFAULT;
	}

	public void updateLocationandDirection() {
		double multiplier = 1.0;
		switch(this.bassMode) {
			case DEFAULT:
				multiplier = 1.0;
				break;
			case ATTAC:
				multiplier = 2.0;
				break;
			case CONFUSE:
				multiplier = 0.5;
				break;
		}
		double xIncr = this.xIncr * multiplier;
		double yIncr = this.yIncr * multiplier;
		
		if(xloc >= width - imageWidth) {
        	System.out.println("Hit east wall");
        	
        	xFlag = false;
        	changingDirection = true;
        	hitWall = Direction.EAST;
        } else if(xloc <= 0) {
        	System.out.println("Hit west wall");
        	
        	xFlag = true;
        	changingDirection = true;
        	hitWall = Direction.WEST;
        } else if(yloc >= height - imageHeight) { // TODO: might be imageWidth instead
        	System.out.println("Hit south wall");
        	
        	yFlag = false;
        	changingDirection = true;
        	hitWall = Direction.SOUTH;
        } else if(yloc <= 0) {
        	System.out.println("Hit north wall");
        	
        	yFlag = true;
        	changingDirection = true;
        	hitWall = Direction.NORTH;
        } else {
        	hitWall = null;
        }
		
		if(overrideDirection) {
			switch(currentDirection) {
				case NORTH:
					if(Direction.NORTH.equals(hitWall))
						return;
					else
						yloc -= yIncr;
					break;
				case SOUTH:
					if(Direction.SOUTH.equals(hitWall))
						return;
					else
						yloc += yIncr;
					break;
				case EAST:
					if(Direction.EAST.equals(hitWall))
						return;
					else
						xloc += xIncr;
					break;
				case WEST:
					if(Direction.WEST.equals(hitWall))
						return;
					else
						xloc -= xIncr;
					break;
			}
			return;
		}
		
		if(changingDirection) { 
			if(xFlag && yFlag)
	    		futureDirection = Direction.SOUTHEAST;
	    	else if(xFlag && !yFlag)
	    		futureDirection = Direction.NORTHEAST;
	    	else if(!xFlag && yFlag)
	    		futureDirection = Direction.SOUTHWEST;
	    	else if(!xFlag && !yFlag)
	    		futureDirection = Direction.NORTHWEST;
	    	
	    	changingDirection = false;
		}
        
        if(xFlag)
        	xloc += xIncr;
        else
        	xloc -= xIncr;
        
        if(yFlag)
        	yloc += yIncr;
        else
        	yloc -= yIncr;
        
        if(!currentDirection.equals(futureDirection)) {
        	int newCurrentDirection = (currentDirection.ordinal() + 1) 
        			% Direction.values().length;
        	
        	for(Direction d : Direction.values())
        		if(d.ordinal() == newCurrentDirection)
        			currentDirection = d;
        	
        	// currentDirection = ...
        }
	}

	public Direction getDirection() {
		return this.currentDirection;
	}
	
	public BassMode getBassMode() {
		return this.bassMode;
	}

	public double getX() {
		return this.xloc;
	}

	public double getY() {
		return this.yloc;
	}
	
	public void changeMode(BassMode bassMode) {
		System.out.println("New bassMode: " + bassMode);
		this.bassMode = bassMode;
	}
	
	public void changeDirection(Direction direction) {
		this.currentDirection = direction;
		this.overrideDirection = true;
	}

}
