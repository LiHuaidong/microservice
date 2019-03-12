package hdli;

import hdli.mapper.CompanyMapper;
import hdli.model.CompanyModel;
import hdli.model.EmployeeModel;
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
//            Company company = new Company();
//            company.setName("Kong lina");
//            company.setType("01");
//            int count = companyMapper.insert(company);
//
//            System.out.println("insert " + count + " count(s)");
//            System.out.println("id is " + company.getId());
//
//            session.commit();

            CompanyModel companyModel = companyMapper.getCompanyWithEmployee(1);
            List<EmployeeModel> employeeModelList = companyModel.getEmployeeList();
            if(employeeModelList != null) {
                for(EmployeeModel employeeModel : employeeModelList) {
                    System.out.println("employee name = [" + employeeModel.getName() + "]");
                }
            }

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
