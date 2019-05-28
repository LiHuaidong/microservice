package hdli;

import hdli.mapper.CompanyMapper;
import hdli.po.Company;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

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
            Company company = new Company();
            company.setName("Kong lina");
            company.setType("01");
            int count = companyMapper.insert(company);

            System.out.println("insert " + count + " count(s)");
            System.out.println("id is " + company.getId());

            session.commit();
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
