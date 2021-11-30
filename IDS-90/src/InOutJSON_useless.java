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

public class InOutJSON_useless { //	Source: https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
	// field
	public final static String FILE_NAME = "Data.json";	//"IncomeData.json";
	public String filename = "DataTest.json";
	public ArrayList<Month>  months;
	public ArrayList<String> currencyList;
	public ArrayList<Double> exRate;
	public ArrayList<Double> revByMonth;
	
	// constructor
	public InOutJSON_useless() {
		File file = new File(FILE_NAME);
		if (file.exists()) {
			System.out.println("file exists");
			readJSON();
		}
		else {
			months = new ArrayList<Month>();
			currencyList = new ArrayList<String>();
			exRate = new ArrayList<Double>();
			revByMonth = new ArrayList<Double>();
			createJSON();
		}
//		JSONArray jsonList1 = new JSONArray();
//		jsonList1.add(months);
	}
	
	
	// method
	public ArrayList<Double> getRevByMonth(){
		ArrayList<Double> rev = new ArrayList<Double>();
		
		
		return rev;
	}
	
	public void createJSON() {
//		Month m0 = new Month(0);
//		m0.addRevenue(100.0);
//		m0.addExpense(50.0);
//		
//		Month m1 = new Month(1);
//		m1.addRevenue(200.0);
//		m1.addExpense(40.0);
//		
//		months.add(m0);
//		months.add(m1);
//		
//		JSONObject jsonObj1 = new JSONObject();
//		jsonObj1.put("months", "month 1");
//		jsonObj1.put("hello", "hi");
//		JSONObject jsonObj2 = new JSONObject();
//		jsonObj2.put("months 2", "month 2");
//		jsonObj2.put("hello 2", "hi 2");
//
//		JSONObject jsonObj2 = new JSONObject();
//		jsonObj2.put("hello", "hi");
//		JSONObject jsonObj1 = new JSONObject();
//		jsonObj1.put("First_Name", "FirstName_1");
//		jsonObj1.put("Last_Name", "LastName_1");
//		jsonObj1.put("Date_Of_Birth", "dob_1");
//		jsonObj1.put("Place_Of_Birth", "Place_Of_Birth_1");
//		jsonObj1.put("Country", "Country_2");
//		
//		JSONObject jsonObj2 = new JSONObject();
//		jsonObj2.put("First_Name", "FirstName_2");
//		jsonObj2.put("Last_Name", "LastName_2");
//		jsonObj2.put("Date_Of_Birth", "dob_2");
//		jsonObj2.put("Place_Of_Birth", "Place_Of_Birth_2");
//		jsonObj2.put("Country", "Country_2");
//		JSONArray jsonList = new JSONArray();
//		jsonList.add(jsonObj1);
//		jsonList.add(jsonObj2);
//
//		try {
//			FileWriter outToJson = new FileWriter(FILE_NAME);
//			outToJson.write(jsonList.toJSONString());
//			outToJson.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Created: " + FILE_NAME);
	}
	
	public void readJSON() {
//		JSONParser jsonParser = new JSONParser();
//		
//		try (FileReader reader = new FileReader(FILE_NAME))
//        {
//            //Read JSON file
//            Object obj = jsonParser.parse(reader);
// 
//            JSONArray jsonList = (JSONArray) obj;
//             
//            JSONObject jsonObject1 = (JSONObject) jsonList.get(0);
//            months = (ArrayList<Month>) jsonObject1.get("months");
//        	System.out.println(jsonObject1.get("hello"));
//
//            Iterate over jsonList array
//            for (int i=0; i < jsonList.size(); i++) {
//            	JSONObject jsonObject = (JSONObject) jsonList.get(i);
//            	System.out.println(jsonObject.get("First_Name"));
////            	parseEmployeeObject((JSONObject) employeeList.get(i));
//            }
//            
//            System.out.println("Read: " + FILE_NAME);
//        }
//		catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//		catch (IOException e) {
//            e.printStackTrace();
//        }
//		catch (ParseException e) {
//            e.printStackTrace();
//        }
	}
	
	
	@SuppressWarnings("unused")
	private static void parseEmployeeObject(JSONObject employee) {
        //Get employee object within list
		
//        JSONObject employeeObject = (JSONObject) employee.get("ID");
		JSONObject employeeObject = employee;
		
        //Get employee first name
        String firstName = (String) employeeObject.get("First_Name");    
        System.out.println(firstName);
         
        //Get employee last name
        String lastName = (String) employeeObject.get("Last_Name");  
        System.out.println(lastName);
         
        //Get employee website name
        String country = (String) employeeObject.get("Country");    
        System.out.println(country);
    }
}

//public static void main(String[] args) {
//	readJSON();
//	createJSON();
//}
