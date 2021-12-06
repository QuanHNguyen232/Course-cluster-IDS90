import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainProject extends Application {
	// field
	public static final String IMAGE_PATH = "report_table.png";
	public BorderPane borderPane = new BorderPane();
	public GridPane gridPane = new GridPane();
	private Button exportBtn;
	public InOutJSON_1 inOutJSON = new InOutJSON_1();
	public ArrayList<TextField> textFieldList = new ArrayList<TextField>();
	public ArrayList<ComboBox> rev_expComboBoxList = new ArrayList<ComboBox>();
	public ArrayList<ComboBox> monthComboBoxList = new ArrayList<ComboBox>();
	public ArrayList<Month> months = inOutJSON.months;
	
	// method
	@Override
	public void start(Stage primaryStage) {
		borderPane.setCenter(gridPane);
		
		// newData button
		Button newData = new Button("new data");
		newData.setOnMouseClicked(e -> {
			addTextField();
		});
		gridPane.add(newData, 0, 0);

		// updateData button
		Button updateData = new Button("update data");
		updateData.setOnMouseClicked(e -> {
			updateMonths();
		});
		gridPane.add(updateData, 1, 0);
		
		// cancelData button
		Button cancelData = new Button("cancel data");
		cancelData.setOnMouseClicked(e -> {
			
		});
		gridPane.add(cancelData, 2, 0);

	
		// ComboBox
//		ComboBox exportcomboBox = new ComboBox();
		
		// Export Button
		exportBtn = new Button("Export report");
		borderPane.setBottom(exportBtn);
		exportBtn.setOnMouseClicked(e -> {
			ExportWindow();
		});
		
		// check this.months
		for (Month m : this.months) {
			System.out.println(m.toString());
		}
		
		// default
		Scene scene = new Scene(borderPane, 500, 500);
		primaryStage.setTitle("Main Page");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void cancelBtnEvent() {
		
	}
	
	public void addTextField() {
		TextField textField = new TextField();
		textFieldList.add(textField);
		gridPane.add(textField, 0, textFieldList.size()+1);
		
		// add rev/exp choices
		ComboBox rev_expComboBox = new ComboBox();
		rev_expComboBoxList.add(rev_expComboBox);
		gridPane.add(rev_expComboBox, 1, textFieldList.size()+1);
		rev_expComboBox.getItems().add("Revenue");
		rev_expComboBox.getItems().add("Expense");
		rev_expComboBox.setOnAction(e -> {
			int valueIndex = rev_expComboBox.getSelectionModel().getSelectedIndex();
			System.out.println("rev_expComboBox valueIndex: " + valueIndex);
		});
		
		// add month choices
		ComboBox monthComboBox = new ComboBox();
		monthComboBoxList.add(monthComboBox);
		gridPane.add(monthComboBox, 2, textFieldList.size()+1);
		if (this.months.size()>0) {
			for (int i = 0; i < this.months.size(); i++) {
				monthComboBox.getItems().add("month: " + i);
			}
			monthComboBox.getItems().add("new month");
		}
		monthComboBox.setOnAction(e -> {
			int valueIndex = monthComboBox.getSelectionModel().getSelectedIndex();
			System.out.println("monthComboBox valueIndex: " + valueIndex);
		});
	}
	public void addMonth(int rev_expIndex, double value) {
		Month m = new Month();
		if (rev_expIndex == 0) {
			m.addRevenue(value);
		}
		if (rev_expIndex == 1) {
			m.addExpense(value);
		}
		this.months.add(m);
		System.out.println("Added new month");
	}
	public void updateMonth(int monthIndex, int rev_expIndex, double value) {
		// update data into months ArrayList
		if (rev_expIndex == 0) {
			months.get(monthIndex).addRevenue(value);
		}
		if (rev_expIndex == 1) {
			months.get(monthIndex).addExpense(value);
		}
		System.out.println("Updated month");
	}
	public void updateMonths() {
		for (int i=0; i<textFieldList.size(); i++) {
			int monthIndex = monthComboBoxList.get(i).getSelectionModel().getSelectedIndex();
			int rev_expIndex = rev_expComboBoxList.get(i).getSelectionModel().getSelectedIndex();
			double value;
			if (textFieldList.get(i).getText().length()>0) {
				value = Double.valueOf(textFieldList.get(i).getText());
				
				if (monthIndex == this.months.size() ) {
					addMonth(rev_expIndex, value);
				}
				else {
					updateMonth(monthIndex, rev_expIndex, value);
				}
				System.out.printf("monthIndex: %d; rev_expIndex: %d; value: %f\n", monthIndex, rev_expIndex, value);
			}

			// check if my code works
			for (Month m : inOutJSON.months) {
				System.out.println(m.toString());
			}
			
			// store data in json
			inOutJSON.writeJSON();
			
//			if (monthIndex == this.months.size() ) {
//			Month m = new Month();
//			if (rev_expIndex == 0) {
//				m.addRevenue(value);
//			}
//			if (rev_expIndex == 1) {
//				m.addExpense(value);
//			}
//			this.months.add(m);
//			System.out.println("Added new month");
//		}
//		else {
//			if (rev_expIndex == 0) {
//				months.get(monthIndex).addRevenue(value);
//			}
//			else if (rev_expIndex == 1) {
//				months.get(monthIndex).addExpense(value);
//			}
//		}

		}
	}
	
	public void ExportWindow() {
		// setup window
		BorderPane newBorder = new BorderPane();
		GridPane newGrid = new GridPane();
		newBorder.setCenter(newGrid);
		
		// get Currency and Rate
		CrawlWeb crawl = new CrawlWeb();
		ArrayList<Double> exRate_export = crawl.getExchangeRate();
		ArrayList<String> currencyList_export = crawl.getCurrencyList();
		crawl.printCurr_Rate();
		
		// setup comboBox
		ComboBox exportcomboBox = new ComboBox();
		for (String s : currencyList_export) {
			exportcomboBox.getItems().add(s);
		}
		exportcomboBox.getItems().add("USD");
		newGrid.add(exportcomboBox, 1, 0);
		
//		exportcomboBox.setOnAction(e -> {
//			int index = exportcomboBox.getSelectionModel().getSelectedIndex();
//			System.out.println("index: " + index);
//		});

		
		// Label for comboBox
		Label label = new Label("Currency: ");
		label.setLabelFor(exportcomboBox);
		newGrid.add(label, 0, 0);
		
		// setup exportBtn
		Button exportBtn = new Button("Export PDF");
		newBorder.setBottom(exportBtn);
		exportBtn.setOnMouseClicked(e -> {
			Draw_BarChart_2 db2 = new Draw_BarChart_2(this.months);
			
			double exchRate = 1;
			String currency = "USD";
			int valueIndex = exportcomboBox.getSelectionModel().getSelectedIndex();
			System.out.println("valueIndex: " + valueIndex);
			System.out.println("currencyList_export.size()= " + currencyList_export.size());
			if (valueIndex < currencyList_export.size()) {
				exchRate = exRate_export.get(valueIndex);
				currency = currencyList_export.get(valueIndex);
			}

			String timeZone = "US/Eastern";
			LocalDate date = LocalDate.now(ZoneId.of(timeZone));
			LocalDateTime dateTime = LocalDateTime.now(ZoneId.of(timeZone));
			WritePDF wPDF1 = new WritePDF(IMAGE_PATH, this.months, currency, exchRate, dateTime, timeZone);
			try {
				wPDF1.toPDF();
			} catch (IOException e1) {
				System.out.println("error in write PDF");
				e1.printStackTrace();
			}
		});
		
		
		Scene scene1 = new Scene(newBorder, 250, 250);
		Stage primaryStage1 = new Stage();
		primaryStage1.setTitle("Export Window");
		primaryStage1.setScene(scene1);
		primaryStage1.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	class ControlPane {
		
//		public void btn() {
//			//		Button click = new Button("Click Here!");
//			exportBtn = new Button("Add");
//			exportBtn.setOnMouseClicked(e -> {
//				value = textField.getText();
//				System.out.println(value);
//			});
//		}
	}

//	public void ReportPane() {
//		WritePDF toPDF = new WritePDF(IMAGE_PATH);
//	}
	
	class Draw_BarChart_2 {
		public BorderPane borderPane = new BorderPane();
		public ArrayList<Double> revByMonth = new ArrayList<Double>();
		public ArrayList<Double> expByMonth = new ArrayList<Double>();
		public Scene scene = new Scene(borderPane, 500, 500);
		public Pane pane = new Pane();
		public ArrayList<Month> months_chart = new ArrayList<Month>();
		
//		public Draw_BarChart_2() {
//			pane.getChildren().add(createChart2());
//			borderPane.getChildren().add(pane);
//			savePaneImage();
//		}
		
		public Draw_BarChart_2(ArrayList<Month> mons) {
			this.months_chart = mons;
			getRev_ExpByMonth(mons);
			
			pane.getChildren().add(createChart2());
			borderPane.getChildren().add(pane);
			
			savePaneImage();
		}
		
		public void getRev_ExpByMonth(ArrayList<Month> monthList) {
			if (monthList.size() >0) {
				for (Month m : monthList) {
					revByMonth.add(m.getTotalRevenue());
					expByMonth.add(m.getTotalExpense());
				}
			}
		}
		
		public void savePaneImage() {
			WritableImage imgborderPane = this.borderPane.snapshot(null, null);
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(imgborderPane, null), "PNG", new File(IMAGE_PATH));
				System.out.println("save img as " + IMAGE_PATH);
			} catch (IOException e) {
				System.out.println("savePaneImage error");
				e.printStackTrace();
			}
		}
		public BarChart createChart2() {
			// Create BarChart
			CategoryAxis xAxis = new CategoryAxis();
	        xAxis.setLabel("Month");
			NumberAxis yAxis = new NumberAxis();
	        yAxis.setLabel("Dollars");
			BarChart bar = new BarChart(xAxis, yAxis);
			// Create charts for REVENUES
			XYChart.Series dataSeries1 = new XYChart.Series();
			dataSeries1.setName("revenue");
			for (int i=0; i < revByMonth.size(); i++) {
				dataSeries1.getData().add(new XYChart.Data(String.valueOf(i), revByMonth.get(i)));
			}
			// Create charts for EXPENSE
			XYChart.Series dataSeries2 = new XYChart.Series();
			dataSeries2.setName("expenses");
			for (int i=0; i < expByMonth.size(); i++) {
				dataSeries2.getData().add(new XYChart.Data(String.valueOf(i), (-1 * expByMonth.get(i))));
			}
			
			bar.getData().add(dataSeries1);
			bar.getData().add(dataSeries2);
			
			return bar;
		}


		
	}
	
}
