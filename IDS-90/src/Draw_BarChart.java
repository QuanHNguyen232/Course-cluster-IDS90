import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Draw_BarChart extends Application{	// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/chart/BarChart.html
	// http://tutorials.jenkov.com/javafx/barchart.html
	
	// fields
	public BorderPane borderPane;
	public Double[] revenue = {0.5, 10.1, 25.9, 30.5, 49.1, 56.1, 63.5, 71.0, 85.2, 98.7};
	public Double[] expenses = {0.2, 15.8, 21.9, 36.5, 42.1, 51.1, 60.5, 76.0, 82.2, 93.7};
	public static final String imagePath = "report_	table.png";
	public ArrayList<Double> rev, exp;
//	public BarChart bar;
	public Scene scene;
	public Pane pane;

	// constructors
	public Draw_BarChart() {}
	public Draw_BarChart(ArrayList<Double> rev, ArrayList<Double> exp) {
		this.rev = rev;
		this.exp = exp;
	}
	
	// methods
	@Override
	public void start(Stage primaryStage) throws Exception {
		borderPane = new BorderPane();
		scene = new Scene(borderPane, 500, 500);

		pane = new Pane();
//		bar = createChart();
		pane.getChildren().add(createChart());
		borderPane.getChildren().add(pane);

		savePaneImage();
		
		primaryStage.setTitle("Main Page");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void savePaneImage() {
		WritableImage imgborderPane = this.borderPane.snapshot(null, null);
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(imgborderPane, null), "PNG", new File(imagePath));
			System.out.println("save img as " +imagePath);
		} catch (IOException e) {
			System.out.println("savePaneImage error");
			e.printStackTrace();
		}
	}
	
	public BarChart createChart() {
		// Create BarChart
		CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");
		NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Dollars");
		BarChart bar = new BarChart(xAxis, yAxis);
		// Create charts for REVENUES
		XYChart.Series dataSeries1 = new XYChart.Series();
		dataSeries1.setName("revenue");
		for (int i=0; i < revenue.length; i++) {
			dataSeries1.getData().add(new XYChart.Data(String.valueOf(i), revenue[i]));
		}
		// Create charts for EXPENSE
		XYChart.Series dataSeries2 = new XYChart.Series();
		dataSeries2.setName("expenses");
		for (int i=0; i < expenses.length; i++) {
			dataSeries2.getData().add(new XYChart.Data(String.valueOf(i), (-expenses[i])));
		}
		
		bar.getData().add(dataSeries1);
		bar.getData().add(dataSeries2);
		
		return bar;
	}
}
