import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Test {

	public static void main(String[] args) {
//		ArrayList<Double> arr3 = new ArrayList<Double>();
//		ArrayList<Double> arr4 = new ArrayList<Double>();
//		arr3.add(10.0);
//		arr3.add(9.0);
//		arr4.add(8.0);
//		arr4.add(7.0);
//		Draw_BarChart bc1 = new Draw_BarChart(arr3, arr4);	// does not show chart
		
		CrawlWeb cw = new CrawlWeb();
		System.out.println(cw.getCurrencyList());
		System.out.println(cw.getExchangeRate());
	}

}
