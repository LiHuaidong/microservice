package hdli;

import com.github.pagehelper.PageHelper;
import hdli.mapper.CompanyMapper;
import hdli.mapper.EmployeeMapper;
import hdli.model.CompanyModel;
import hdli.model.EmployeeModel;
import hdli.po.Company;
import hdli.po.CompanyExample;
import hdli.po.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        SqlSession session = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            session = sqlSessionFactory.openSession();

            session.getConnection().setAutoCommit(false);

            CompanyMapper companyMapper = session.getMapper(CompanyMapper.class);
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
//            Company company = new Company();
//            company.setName("Kong lina");
//            company.setType("01");
//            int count = companyMapper.insert(company);
//
//            System.out.println("insert " + count + " count(s)");
//            System.out.println("id is " + company.getId());
//
//            session.commit();

//            CompanyModel companyModel = companyMapper.getCompanyWithEmployee(1);
//            List<EmployeeModel> employeeModelList = companyModel.getEmployeeList();
//            if(employeeModelList != null) {
//                for(EmployeeModel employeeModel : employeeModelList) {
//                    System.out.println("employee name = [" + employeeModel.getName() + "]");
//                }
//            }

//            CompanyExample example = new CompanyExample();
//            example.setDistinct(true);
//            CompanyExample.Criteria criteria = example.createCriteria();
//            criteria.andNameEqualTo("lihuaidong");
//            List<Company> companyList = companyMapper.selectByExample(example);
//            if(companyList != null && companyList.size() > 0) {
//                for(Company company : companyList) {
//                    System.out.println("company = [" + company.getName() + "]");
//                }
//            }
            PageHelper.startPage(1, 2);
            List<Employee> employeeList = employeeMapper.selectEmployeeList("li");
            System.out.println(employeeList.size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
