package lab4;

public enum Direction {
	
	/*
	NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST,
		WEST, NORTHWEST;
	
	@SuppressWarnings("unused")
	private String[] filenames = {
		"north",
		"northeast",
		"east",
		"southeast",
		"south",
		"southwest",
		"west",
		"northwest"
	};
	
	public String getMyName() {
		return this.filenames[this.ordinal()];
	}
	*/
	
	
	NORTH("north"), NORTHEAST("northeast"), EAST("east"),
		SOUTHEAST("southeast"), SOUTH("south"), SOUTHWEST("southwest"),
		WEST("west"), NORTHWEST("northwest");

	private String name = null;

	private Direction(String s) {
	name = s;}

	public String getName() {
		return name;
	}

}
