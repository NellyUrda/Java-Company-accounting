package conta;

import java.util.HashMap;
import java.util.Map.Entry;

public class Employee extends Person {

	private String employeeID, jobTitle;
	private int hoursExtra = 0;;
	private double salary = 0;
	private double entrySalary = 0;

	HashMap<String, Double> positions = new HashMap<String, Double>();

	public Employee() {

	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setJobTitle(String job) {
		this.jobTitle = job;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setHoursExtra(int hours) {
		this.hoursExtra = hours;
	}

	public int getHoursExtra() {
		return hoursExtra;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public double calculateEntrySalary(String jobTitle) {

		positions.put("manager", (double) 5000);
		positions.put("accountant", (double) 1500);
		positions.put("janitor", (double) 1000);
		positions.put("junior java dev", (double) 1500);
		positions.put("senior java dev", (double) 2500);
		positions.put("team-leader", (double) 3000);

		for (Entry<String, Double> entry : positions.entrySet()) {
			if (entry.getKey().equals(jobTitle)) {
				entrySalary = entry.getValue();
			}
		}
		return entrySalary;

	}

	public double calculateSalary(String jobTitle, int hoursExtra) {
		if (jobTitle.equals("manager")) {
			salary = calculateEntrySalary(jobTitle) + 20.0 * hoursExtra;
		} else if (jobTitle.equals("accountant")) {
			salary = calculateEntrySalary(jobTitle) + 14.0 * hoursExtra;
		} else if (jobTitle.equals("janitor")) {
			salary = calculateEntrySalary(jobTitle) + 10.0 * hoursExtra;
		} else if (jobTitle.equals("junior java dev")) {
			salary = calculateEntrySalary(jobTitle) + 13.0 * hoursExtra;
		} else if (jobTitle.equals("senior java dev")) {
			salary = calculateEntrySalary(jobTitle) + 18.0 * hoursExtra;
		} else if (jobTitle.equals("team-leader")) {
			salary = calculateEntrySalary(jobTitle) + 16.0 * hoursExtra;
		}

		return salary;
	}

}