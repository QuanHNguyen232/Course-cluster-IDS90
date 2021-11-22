import java.util.ArrayList;

public class Month {
	
	// data field
	public String month;
	ArrayList<Double> revenues;
	ArrayList<Double> expenses;
	
	
	// constructor
	public Month(int month) {
		this.month = String.valueOf(month);
	}
		
	// method
	public void addIncome(Double in) {
		revenues.add(in);
		System.out.println("Add income: " + in);
	}
	public void addExpense(Double ex) {
		expenses.add(ex);
		System.out.println("Add expensex: " + ex);
	}
	public void getAllIncome() {
		System.out.print("getAllIncome: ");
		for (Double i : revenues) {
			System.out.println(i + " ");
		}
		System.out.println();
	}
	public void getAllExpense() {
		System.out.print("getAllExpense: ");
		for (Double e : expenses) {
			System.out.println(e + " ");
		}
		System.out.println();
	}
	public void getTotalIncome() {
		Double sum = 0.0;
		for (Double i : revenues) {
			sum += i;
		}
		System.out.println("getTotalIncome: "+sum);
	}
	public void getTotalExpense() {
		Double sum = 0.0;
		for (Double e : expenses) {
			sum += e;
		}
		System.out.println("getTotalExpense: "+sum);
	}
	
	private void saveJSON() {
		
	}
	
	
//	public static void main(String[] args) {
//	}

}
