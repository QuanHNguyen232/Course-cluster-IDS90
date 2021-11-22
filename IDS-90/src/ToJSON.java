import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ToJSON {
	
	// data field
	private JSONObject jsonObject = new JSONObject();
	
	// constructor
	public ToJSON() {
		
	}
	
	
	// method
	public void saveData() {
		
	}
	
	public void getData() {
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ID", "1");
		jsonObject.put("First_Name", "Shikhar");
		jsonObject.put("Last_Name", "Dhawan");
		jsonObject.put("Date_Of_Birth", "1981-12-05");
		jsonObject.put("Place_Of_Birth", "Delhi");
		jsonObject.put("Country", "India");
		
		JSONObject jsonObj2 = new JSONObject();
		jsonObj2.put("ID", "2");
		jsonObj2.put("First_Name", "Def");
		jsonObj2.put("Last_Name", "Abc");
		jsonObj2.put("Date_Of_Birth", "1234-12-03");
		jsonObj2.put("Place_Of_Birth", "HCM");
		jsonObj2.put("Country", "VN");
		
//		jsonObj2.
		
		JSONArray jsonList = new JSONArray();
		jsonList.add(jsonObject);
		jsonList.add(jsonObj2);

		saveJSON(jsonList);
	}
	
	private static void saveJSON(JSONArray list) {
		String filename = "IncomeData.json";
		try {
			FileWriter outToJson = new FileWriter(filename);
			outToJson.write(list.toJSONString());
			outToJson.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Created" + filename);

	}
}
