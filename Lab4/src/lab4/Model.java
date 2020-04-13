package lab4;

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
	
	private int width, height, imageWidth, imageHeight;

	public Model(int width, int height, int imageWidth, int imageHeight) {
		this.width = width;
		this.height = height;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
	}

	public void updateLocationandDirection() {
		if(xloc >= width - imageWidth) {
        	System.out.println("Hit east wall");
        	
        	xFlag = false;
        	changingDirection = true;
        } else if(xloc <= 0) {
        	System.out.println("Hit west wall");
        	
        	xFlag = true;
        	changingDirection = true;
        } else if(yloc >= height - imageHeight) { // TODO: might be imageWidth instead
        	System.out.println("Hit south wall");
        	
        	yFlag = false;
        	changingDirection = true;
        } else if(yloc <= 0) {
        	System.out.println("Hit north wall");
        	
        	yFlag = true;
        	changingDirection = true;
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

	public double getX() {
		return this.xloc;
	}

	public double getY() {
		return this.yloc;
	}

}
