import java.util.ArrayList;

public class Month {
	
	// data field
	public String month;
//	public ArrayList<Double> revenueList;
//	public ArrayList<Double> expenseList;
//	public double totalRev;
//	public double totalExp;

	private ArrayList<Double> revenueList;
	private ArrayList<Double> expenseList;
	private double totalRevenue;
	private double totalExpense;
		
	// constructor
	public Month() {
		this.month = "";
		revenueList = new ArrayList<Double>();
		expenseList = new ArrayList<Double>();
	}
	
	public Month(int month, ArrayList<Double> revenueList, ArrayList<Double> expenseList) {
		this.month = String.valueOf(month);
		this.revenueList = revenueList;
		this.expenseList = expenseList;
		this.totalRevenue = calcTotal(revenueList);
		this.totalExpense = calcTotal(expenseList);
	}
	
	public Month(int month, ArrayList<Double> revenueList, ArrayList<Double> expenseList, double totalRev, double totalExp) {
		this.month = String.valueOf(month);
		this.revenueList = revenueList;
		this.expenseList = expenseList;
		this.totalRevenue = totalRev;
		this.totalExpense = totalExp;
	}
		
	// method
	public void addRevenue(Double in) {
		revenueList.add(in);
		totalRevenue = calcTotal(revenueList);
	}
	
	public void addExpense(Double ex) {
		expenseList.add(ex);
		totalExpense = calcTotal(expenseList);
	}
	
	public ArrayList<Double> getRevenueList() {
		if (revenueList.size()>0) {
			ArrayList<Double> revReturn = new ArrayList<Double>();
			for (Double r : revenueList) {
				revReturn.add(r);
			}
			return revReturn;
		}
		return null;
	}
	
	public ArrayList<Double> getExpenseList() {
		if (expenseList.size()>0) {
			ArrayList<Double> exReturn = new ArrayList<Double>();
			for (Double e : expenseList) {
				exReturn.add(e);
			}
			return exReturn;
		}
		return null;
	}
	private double calcTotal(ArrayList<Double> list) {
		Double sum = 0.0;
		if (list.size()>0) {
			for (Double r : list) {
				sum += r;
			}
		}
		return sum;
	}
	
	public double getTotalRevenue() {
		return this.totalRevenue;
	}
	
	public double getTotalExpense() {
		return this.totalExpense;
	}
	
//	public double getTotalRevenue(ArrayList<Double> revenueList) {
//		Double sum = 0.0;
//		if (revenueList.size()>0) {
//			for (Double r : revenueList) {
//				sum += r;
//			}
//		}
//		return sum;
//	}
//	
//	public double getTotalExpense(ArrayList<Double> expenseList) {
//		Double sum = 0.0;
//		if (expenseList.size()>0) {
//			for (Double e : expenseList) {
//				sum += e;
//			}
//		}
//		return sum;
//	}

	@Override
	public String toString() {
		return "Month [month=" + month + ", \n\trevenueList=" + revenueList.toString() + ", \n\texpenseList=" + expenseList.toString() + ", \n\ttotalRev="
				+ totalRevenue + ", totalExp=" + totalExpense + "]";
	}
	
	
	
//	public static void main(String[] args) {
//	}

}
