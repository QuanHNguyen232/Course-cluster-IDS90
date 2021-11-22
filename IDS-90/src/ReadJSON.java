import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSON {
//	Source: https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
	
	public static void main(String[] args) {
		JSONParser jsonParser = new JSONParser();
		String filename = "IncomeData.json";
		File file = new File(filename);
		if (file.exists()) {
			System.out.println("yes");
		}
		else {
			System.out.println("no");
		}
		
		try (FileReader reader = new FileReader(filename))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);
             
            //Iterate over employee array
            for (int i=0; i < employeeList.size(); i++) {
            	parseEmployeeObject((JSONObject) employeeList.get(i));
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
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
