import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainProject extends Application {
	// GUI
	// field
	public BorderPane borderPane = new BorderPane();
	private Button click;
	// method
	@Override
	public void start(Stage primaryStage) {
		
		FlowPane pane = new FlowPane();
		borderPane.setCenter(pane);
		pane.setAlignment(Pos.CENTER);
		
//		Button click = new Button("Click Here!");
		click = new Button("Click Here!");
		click.setOnMouseClicked(e -> {
			System.out.println("click");
		});
		
		
		pane.getChildren().add(click);
		
		
		
		Scene scene = new Scene(borderPane, 200, 200);
		primaryStage.setTitle("Main Page");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	
	// Back-end
	// field
	public ArrayList<Month> months;
	// method
	public static void main(String[] args) {
		launch(args);
	}

} //end class OneButton

