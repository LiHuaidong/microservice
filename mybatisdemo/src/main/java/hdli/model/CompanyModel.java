package hdli.model;

import java.util.List;

public class CompanyModel {
    private Integer id;

    private String name;

    private String type;

    private List<EmployeeModel> employeeList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public List<EmployeeModel> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeModel> employeeList) {
        this.employeeList = employeeList;
    }
}