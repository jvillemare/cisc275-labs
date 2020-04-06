package lab6;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class Controller extends Application { //data fields hold Model and View
	private Model model;
	private View view;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
	public void start(Stage theStage) {
        view = new View(theStage);
		model = new Model(view.getWidth(), view.getHeight(), 
                view.getImageWidth(), view.getImageHeight());
        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
                //increment the x and y coordinates, alter direction if necessary
                model.updateLocationandDirection();
                //input the x coordinates, y coordinates, and direction picture
                view.update(model.getX(), model.getY(), model.getDirection(),
                		model.getBassMode());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        theStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
            	/* if(keyEvent.getCode() == KeyCode.W) { } */
                switch(keyEvent.getCode()) {
                	case W: // KeyCode.W
                		model.changeDirection(Direction.NORTH);
                		break;
                	case S:
                		model.changeDirection(Direction.SOUTH);
                		break;
                	case A:
                		model.changeDirection(Direction.WEST);
                		break;
                	case D:
                		model.changeDirection(Direction.EAST);
                		break;
                	case DIGIT1: // 1 == confuse mode, half speed
                		model.changeMode(BassMode.CONFUSE);
                		break;
                	case DIGIT2: // 2 == attac mode, double speed
                		model.changeMode(BassMode.ATTAC);
                		break;
                	case DIGIT3: // 3 == default mode, regular speed
                		model.changeMode(BassMode.DEFAULT);
                		break;
                }
            }
        });
        theStage.show();
    }

}
