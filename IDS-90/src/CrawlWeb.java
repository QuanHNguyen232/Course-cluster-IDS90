import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CrawlWeb {
	
	private static ArrayList<String> currencyList = new ArrayList<String>();
	private static ArrayList<Double> exRate = new ArrayList<Double>();
	public static final String FILE_NAME = "ExchangeRate.json";
	
	public static void main(String[] args) {
//		try {
//			Document doc = Jsoup.connect("https://www.investing.com/currencies/exchange-rates-table").get();
//			Element a = doc.select("tr").get(1);
//			String rate = a.text();
//			rate = rate.substring(rate.indexOf(" "));
//			Scanner inRate = new Scanner(rate);
//			while (inRate.hasNext()) {
//				exRate.add(Double.valueOf(inRate.next()));
//			}
//			storeToJSON();
//			inRate.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		saveRate();
	}
	
	private static void saveRate() {
		Document doc;
		try {
			doc = Jsoup.connect("https://www.exchangerates.org.uk/US-Dollar-USD-currency-table.html").get();
			Element a = doc.select("tbody").get(1);
			String allRate = a.text();
			System.out.println(allRate);
			
			String regExValue = "[0-9]+.[0-9]+";
			Pattern value = Pattern.compile(regExValue, Pattern.CASE_INSENSITIVE);
		    Matcher matcherValue = value.matcher(allRate);
		    
		    String regExTitle = "USD [A-Z]+";
		    Pattern title = Pattern.compile(regExTitle, Pattern.CASE_INSENSITIVE);
		    Matcher matcherTitle= title.matcher(allRate);
		    
		    while (matcherTitle.find() && matcherValue.find()) {
		    	String currency = matcherTitle.group();
		    	Double rate = Double.valueOf(matcherValue.group());
		    	currencyList.add(currency.substring(currency.indexOf(" ")+1));
		    	exRate.add(rate);
		    }
		    storeToJSON();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("unchecked")
	private static void storeToJSON() {
		JSONObject exchangeRate = new JSONObject();
		if (currencyList.size() == exRate.size()) {
			for (int i=0; i<currencyList.size(); i++) {
				exchangeRate.put(currencyList.get(i), exRate.get(i));
			}
		}
		LocalDateTime now = LocalDateTime.now();
		JSONObject currentTime = new JSONObject();
		currentTime.put("current time", now);
		
		
		JSONArray rate_time = new JSONArray();
		rate_time.add(exchangeRate);
		rate_time.add(currentTime);
		
		try {
			FileWriter outToJson = new FileWriter(FILE_NAME);
			outToJson.write(rate_time.toJSONString());
			outToJson.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Created " + FILE_NAME);
	}
	
//	private static void test() {
//		Document doc = Jsoup.connect("https://economictimes.indiatimes.com/markets/forex").get();
//		Element all = doc.select("tbody").get(0);
//		System.out.println(all.toString());
//		
//		String regExValue = "alignR price\">[0-9]+.[0-9]+";
//		Pattern valueString = Pattern.compile(regExValue, Pattern.CASE_INSENSITIVE);
//	    Matcher matcherValue = valueString.matcher(all.toString());
//	    
//	    String regExTitle = "alignR price\">[0-9]+.[0-9]+";
//		Pattern valueTitle = Pattern.compile(regExTitle, Pattern.CASE_INSENSITIVE);
//	    Matcher matcherTitle = valueTitle.matcher(all.toString());
//	    
//	    while (matcherValue.find()) {
//	    	System.out.println(matcherValue.group());
//	    }
//	}
}
