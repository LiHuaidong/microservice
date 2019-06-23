package hdli.model;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:04 2019/3/12
 */
public class EmployeeModel {

	private Integer id;

	private String name;

	private Integer companyId;

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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
