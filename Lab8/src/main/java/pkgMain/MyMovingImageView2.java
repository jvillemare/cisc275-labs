package pkgMain;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * 
 */

public class MyMovingImageView2 extends Application {

	private ImgController2 imc;
	private ImageView iv1;
	private final double WIDTH = 800;
	private final double HEIGHT = 600;
	
	public MyMovingImageView2(){
    	iv1 = new ImageView();
		imc = new ImgController2(this);
	}
		
    @Override
    public void start(Stage stage) {
 
    	Image im1 = new Image(getClass().getResourceAsStream("/img/commonMilkweed.png"));
    	iv1.setImage(im1);
    	iv1.setPreserveRatio(true);
    	iv1.setFitHeight(100);
    	iv1.setOnMouseDragged(imc.getHandlerForDrag());
        
		iv1.setTranslateX(iv1.getLayoutX() - WIDTH/2 + imc.getStartingX());
		iv1.setTranslateY(iv1.getLayoutY() - HEIGHT/2 + imc.getStartingY());
	    
	    Group root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
		
		TilePane tile = new TilePane();
	    tile.setPrefColumns(3);
	    tile.setPrefRows(3);
	    tile.getChildren().add(iv1);
	    tile.setBackground(
	    		new Background(
	    				new BackgroundFill(
	    						Color.rgb(10, 10, 20),
	    						new CornerRadii(0.1),
	    						new Insets(3.0)
	    					)));
	    tile.setMaxWidth(300.0);
	    tile.setPrefSize(200, 200);
	    
	    FlowPane flow = new FlowPane();
	    flow.setPrefWrapLength(200); // preferred width = 300
	    flow.setBackground(
	    		new Background(
	    				new BackgroundFill(
	    						Color.rgb(60, 60, 60),
	    						new CornerRadii(0.1),
	    						new Insets(3.0)
	    					)));
	    flow.setMaxWidth(300.0);
	    flow.setPrefSize(200, 200);
	    
	    TilePane holder = new TilePane();
	    holder.setPadding(new Insets(5, 0, 5, 0));
	    holder.setVgap(4);
	    holder.setHgap(4);
	    holder.setPrefColumns(2);
	    holder.setStyle("-fx-background-color: DAE6F3;");
	    
	    iv1.setLayoutX(500);
	    iv1.setLayoutY(500);
        
        holder.getChildren().addAll(tile, flow);
        root.getChildren().add(iv1);
        root.getChildren().add(holder);
        
        iv1.toFront();
        System.out.println("AHHHHHHHHHHHH)");
        System.out.println("iv1,x=" + iv1.getLayoutX() + 
        		" iv1,y=" + iv1.getLayoutY());
        
        stage.show();
    }
    public void setX(double x) {
    	iv1.setTranslateX(iv1.getLayoutX() - WIDTH/2 + x);
    }
    public void setY(double y) {
    	iv1.setTranslateY(iv1.getLayoutY() - HEIGHT/2 + y);
    }
    public static void main(String[] args) {
        launch();
    }

}