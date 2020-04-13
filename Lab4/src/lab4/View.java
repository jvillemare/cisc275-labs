package lab4;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class View { // create the stage
	
	private Stage theStage;
	
	private final int canvasCount = 10;
	private final static int canvasWidth = 500;
	private final static int canvasHeight = 300;
	private final static int imgWidth = 165;
	private final static int imgHeight = 165;
	
	private int picInd = 0;
	
	private Group root;
	private Scene theScene;
	private Canvas canvas;
	private GraphicsContext gc;
	
	private Image[] orcs = new Image[Direction.values().length];
	
	public View(Stage theStage) {
		for(Direction d : Direction.values())
			orcs[d.ordinal()] = new Image(getImagePath(d.getName()));
		
		this.theStage = theStage;
		
		this.theStage.setTitle("Orc with Direction enum");

        this.root = new Group();
        this.theScene = new Scene(this.root);
        this.theStage.setScene(theScene);

        this.canvas = new Canvas(canvasWidth, canvasHeight);
        this.root.getChildren().add(this.canvas);
        this.gc = this.canvas.getGraphicsContext2D();
	}
	
	private String getImagePath(String direction) {
        return "images/orc_forward_" + direction + ".png";   	
    }

	public int getWidth() {
		return this.canvasWidth;
	}

	public int getHeight() {
		return this.canvasHeight;
	}

	public int getImageWidth() {
		return this.imgWidth;
	}

	public int getImageHeight() {
		return this.imgHeight;
	}

	public void update(double x, double y, Direction d) {
		gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // draw the subimage from the original png to animate the orc's motion
        gc.drawImage(orcs[d.ordinal()], imgWidth*picInd, 0, imgWidth, imgHeight,
                            x, y, imgWidth, imgHeight);
        picInd = (picInd + 1) % canvasCount;
	}

}
