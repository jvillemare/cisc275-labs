package lab2;

//T Harvey
// Loosely based on https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development/blob/master/Example3.java 

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import java.lang.Math;

import java.io.IOException;
import java.io.File;

// Animation of Orc walking
public class Animation extends Application {
	
    final int canvasCount = 10;
    int picInd = 0;
    double xloc = 1;
    double yloc = 1;
    final double xIncr = 8;
    final double yIncr = 2;
    final static int canvasWidth = 500;
    final static int canvasHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    final static int canvasMargin = 165;
    
    boolean xFlag = true; // true = traveling east, false = west
    boolean yFlag = true; // true = traveling south, false = north
    
    int currentDirection = 3;
    int futureDirection = 3;
    boolean changingDirection = false;

    // DONE: Change the code so that at least eight orc animation pngs are loaded

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Orc");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Image[] orcs = {
        	createImage("north"),
        	createImage("northeast"),
            createImage("east"),
            createImage("southeast"),
            createImage("south"),
            createImage("southwest"),
            createImage("west"),
            createImage("northwest")
        };

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1e9; 

                //xloc += xIncr;
                //yloc += yIncr;
                
                if(xloc >= canvasWidth - canvasMargin) {
                	System.out.println("Hit east wall");
                	
                	xFlag = false;
                	changingDirection = true;
                } else if(xloc <= 0) {
                	System.out.println("Hit west wall");
                	
                	xFlag = true;
                	changingDirection = true;
                } else if(yloc >= canvasHeight - canvasMargin) {
                	System.out.println("Hit south wall");
                	
                	yFlag = false;
                	changingDirection = true;
                } else if(yloc <= 0) {
                	System.out.println("Hit north wall");
                	
                	yFlag = true;
                	changingDirection = true;
                }
                
                if(xFlag)
                	xloc += xIncr;
                else
                	xloc -= xIncr;
                
                if(yFlag)
                	yloc += yIncr;
                else
                	yloc -= yIncr;
                
                if(changingDirection) {
                	futureDirection = calculateDirection(xFlag, yFlag);
                	changingDirection = false;
                }

                // Clear the canvas
                gc.clearRect(0, 0, canvasWidth, canvasHeight);

                // draw the subimage from the original png to animate the orc's motion
                gc.drawImage(orcs[currentDirection], imgWidth*picInd, 0, imgWidth, imgHeight,
                                    xloc, yloc, imgWidth, imgHeight);
                picInd = (picInd + 1) % canvasCount;
                
                if(currentDirection != futureDirection)
                	currentDirection = (currentDirection + 1) % orcs.length;
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                // DONE: Keep the orc from walking off-screen, turn around 
                // when bouncing off walls.
                // Be sure that animation picture direction matches what 
                // is happening on screen.
                
            }
        }.start();
        theStage.show();
    }
    
    private int calculateDirection(boolean xFlag, boolean yFlag) {
    	// xFlag; true = traveling east, false = west
        // yFlag; true = traveling south, false = north
    	
    	// TODO: Rotate to new position?
    	
    	if(xFlag && yFlag) {
    		// traveling south east
    		return 3;
    	} else if(xFlag && !yFlag) {
    		// traveling north east
    		return 1;
    	} else if(!xFlag && yFlag) {
    		// traveling south west
    		return 5;
    	} else if(!xFlag && !yFlag) {
    		// traveling north west
    		return 7;
    	}
    	
    	return 0;
    }

    // Read image from file and return
    private Image createImage(String direction) {
        return new Image("images/orc_forward_" + direction + ".png");   	
    	// DONE: Change this method so you can load other orc animation 
        // bitmaps
    }
}


