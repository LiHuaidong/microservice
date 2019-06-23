package hdli.junitdemo;

public class EmpBusinessLogic {

	// Calculate the yearly salary of employee
	public double calculateYearlySalary(EmployeeDetails employeeDetails) {
		return employeeDetails.getMonthlySalary() * 12;
	}

	// Calculate the appraisal amount of employee
	public double calculateAppraisal(EmployeeDetails employeeDetails){
		return employeeDetails.getMonthlySalary() < 10000 ? 500 : 1000;
	}

}
