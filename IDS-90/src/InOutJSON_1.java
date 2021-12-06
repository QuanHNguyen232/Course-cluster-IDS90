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
	public ArrayList<Double> revByMonth;
	public ArrayList<Double> expByMonth;
	
	// constructor
	public InOutJSON_1() {
		File file = new File(FILE_NAME);
		if (file.exists()) {
			System.out.println("json exists");
//			writeJSON();
			readJSON();
//			getDataByMonth();
		}
		else {
			System.out.println("json not exist");
			months = new ArrayList<Month>();
			revByMonth = new ArrayList<Double>();
			expByMonth = new ArrayList<Double>();
			
			writeJSON();
		}
	}
	
	public void getDataByMonth(ArrayList<Month>  mo) {
		if (mo.size() > 0) {
			for (Month m : mo) {
				revByMonth.add(m.getTotalRevenue());
				expByMonth.add(m.getTotalExpense());
			}
		}
	}
	
	public void writeJSON() {
		ObjectMapper objectMapper = new ObjectMapper();

		String s="";
		try {
			s = objectMapper.writeValueAsString(months);
			System.out.println(s);
		} catch (JsonProcessingException e) {
			System.out.println("objectMapper Obj->Str error");
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
	            System.out.println("read Str->Obj: " + objStr);
	            
	            ObjectMapper objectMapper = new ObjectMapper();
	            
	            List<Month> mList = objectMapper.readValue(objStr, new TypeReference<List<Month>>(){});
	            this.months = (ArrayList<Month>) mList;
	            	            
	            
	            
	            // Using new String w/o totalRevenue and totalExpense (This works properly)
//	            objStr = "[{\"month\":\"0\",\"revenueList\":[1.0],\"expenseList\":[2.0],\"totalRev\":3.0,\"totalExp\":4.0}]";
	            
//	            for (Month m : months) {
//	            	System.out.println(m.toString());
//	            }
	            
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		InOutJSON_1 test = new InOutJSON_1();
//		test.createJSON();
//		test.readJSON();	// try Read Object List From JSON Array String
//		
//		
//	}

	
}
	
