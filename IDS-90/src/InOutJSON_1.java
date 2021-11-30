import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InOutJSON_1 {
	// field
	public final static String FILE_NAME = "InOutJSON_1_data.json";	//"IncomeData.json";
	public ArrayList<Month>  months;
	public ArrayList<String> currencyList;
	public ArrayList<Double> exRate;
	public ArrayList<Double> revByMonth;
	
	// constructor
	public InOutJSON_1() {
		months = new ArrayList<Month>();
		currencyList = new ArrayList<String>();
		exRate = new ArrayList<Double>();
		revByMonth = new ArrayList<Double>();
		
	}
	
	public void createJSON() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		ArrayList<Double> arr1 = new ArrayList<Double>();
		ArrayList<Double> arr2 = new ArrayList<Double>();
		arr1.add(1.0);
		arr2.add(2.0);
//		Month m0 = new Month(0, arr1, arr2, 3.0, 4.0);
		Month m0 = new Month(0, arr1, arr2);
		
		ArrayList<Double> arr3 = new ArrayList<Double>();
		ArrayList<Double> arr4 = new ArrayList<Double>();
		arr3.add(10.0);
		arr3.add(9.0);
		arr4.add(8.0);
		arr4.add(7.0);
//		Month m1 = new Month(11, arr3, arr4, 6.0, 5.0);
		Month m1 = new Month(11, arr3, arr4);
		
		months.add(m0);
		months.add(m1);
		String s="";
		try {
			s = objectMapper.writeValueAsString(months);
			System.out.println(s);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObj1 = new JSONObject();
		jsonObj1.put("month data", s);
		
		JSONArray jsonList = new JSONArray();
		jsonList.add(jsonObj1);
		
		try {
			FileWriter outToJson = new FileWriter(FILE_NAME);
			outToJson.write(jsonList.toJSONString());
			outToJson.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Created: " + FILE_NAME);
	}
	
	public void readJSON() {
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader = new FileReader(FILE_NAME);
			try {
				Object obj = jsonParser.parse(reader);
	            JSONArray jsonList = (JSONArray) obj;
	             
	            JSONObject jsonObject1 = (JSONObject) jsonList.get(0);
	            String objStr = jsonObject1.get("month data").toString();
	            System.out.println(objStr);
	            
	            ObjectMapper objectMapper = new ObjectMapper();
	            
	            // Using new String w/o totalRevenue and totalExpense (This works properly)
//	            objStr = "[{\"month\":\"0\",\"revenueList\":[1.0],\"expenseList\":[2.0],\"totalRev\":3.0,\"totalExp\":4.0}]";
	            
	            List<Month> mList = objectMapper.readValue(objStr, new TypeReference<List<Month>>(){});
	            
	            for (Month m : mList) {
	            	System.out.println(m.toString());
	            }
	            
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		InOutJSON_1 test = new InOutJSON_1();
		test.createJSON();
		test.readJSON();	// try Read Object List From JSON Array String
		
		
	}

	
}
	
class Student {
	public String name;
	public double gpa;
	public int year;
	
//	public Student(String n, double avg, int yr){
//		name = n;
//		gpa = avg;
//		year = yr;
//	}
}
