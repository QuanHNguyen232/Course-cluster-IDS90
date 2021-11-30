import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Draw_BarChart extends Application{	// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/chart/BarChart.html
	// http://tutorials.jenkov.com/javafx/barchart.html
	
	// fields
	public BorderPane borderPane = new BorderPane();
	public Double[] revenue = {0.5, 10.1, 25.9, 30.5, 49.1, 56.1, 63.5, 71.0, 85.2, 98.7};
	public Double[] expenses = {0.2, 15.8, 21.9, 36.5, 42.1, 51.1, 60.5, 76.0, 82.2, 93.7};
	
	public ArrayList<Double> rev, exp;
	
	// constructors
	public Draw_BarChart() {}
	
	public Draw_BarChart(ArrayList<Double> rev, ArrayList<Double> exp) {
		this.rev = rev;
		this.exp = exp;
	}
	
	// methods
	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane pane = new Pane();
		borderPane.setCenter(pane);
		
		BarChart bar = createChart();
		pane.getChildren().add(bar);
		
		Scene scene = new Scene(borderPane, 500, 500);
		primaryStage.setTitle("Main Page");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
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
