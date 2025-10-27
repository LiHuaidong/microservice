package hdli;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;


public class FullFieldWorkItemInserter {
    // 数据库配置
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/work_db?serverTimezone=Asia/Shanghai";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    // 随机数据生成器
    private static final Random RANDOM = new Random();
    private static final String[] TYPES = {"需求", "缺陷", "任务", "优化"};
    private static final String[] STATUS = {"待处理", "处理中", "已完成"};

    // 批量处理参数
    private static final int BATCH_SIZE = 100;
    private static final int THREAD_COUNT = 2;  // 根据CPU核心数调整
    private static final int TOTAL_RECORDS = 10_000_000;

    public static void main(String[] args) throws ClassNotFoundException {
        AtomicLong total = new AtomicLong(0);

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(getInsertSql());

            conn.setAutoCommit(false);
            Random random = ThreadLocalRandom.current();

            for (long j = 0; j < TOTAL_RECORDS; j++) {
                setAllFields(pstmt, random);
                pstmt.addBatch();

                total.getAndIncrement();
                if (total.get() % BATCH_SIZE == 0) {
                    pstmt.executeBatch();
                    conn.commit();
                    logProgress(total.get());
                }
            }

            // 5. 处理剩余数据
            pstmt.executeBatch();
            conn.commit();
            logProgress(total.get());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getInsertSql() {
        return "INSERT INTO work_db.work_item (`type`, title, assignee, status, description, value, " +
                "product_team, product_team_code, agile_team, agile_team_code, business_l0, business_l1, business_l2, business_l3, business_l4, " +
                "business_l0_code, business_l1_code, business_l2_code, business_l3_code, business_l4_code, create_time, update_time) " +
                " VALUES(?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);";
    }

    private static void setAllFields(PreparedStatement pstmt, Random random) throws SQLException {
        // 基础字段
        pstmt.setString(1, TYPES[random.nextInt(TYPES.length)]);
        pstmt.setString(2, generateRandomChineseTitle(random));
        pstmt.setString(3, generateRandomString(10));
        pstmt.setString(4, STATUS[random.nextInt(STATUS.length)]);
        pstmt.setString(5, generateRandomChineseDescription(random, 200));
        pstmt.setBigDecimal(6, new BigDecimal(random.nextDouble() * 10000));

        // 团队相关字段
        pstmt.setString(7, "产品团队-" + random.nextInt(100));
        pstmt.setString(8, "PROD_" + String.format("%04d", random.nextInt(10000)));
        pstmt.setString(9, "敏捷团队-" + random.nextInt(100));
        pstmt.setString(10, "AGILE_" + String.format("%04d", random.nextInt(10000)));

        pstmt.setString(11, "业务L0-" + random.nextInt(5));
        pstmt.setString(12, "业务L1-" + random.nextInt(5));
        pstmt.setString(13, "业务L2-" + random.nextInt(5));
        pstmt.setString(14, "业务L3-" + random.nextInt(5));
        pstmt.setString(15, "业务L4-" + random.nextInt(5));

        pstmt.setString(16, "L0_" + String.format("%04d", random.nextInt(10000)));
        pstmt.setString(17, "L1_" + String.format("%04d", random.nextInt(10000)));
        pstmt.setString(18, "L2_" + String.format("%04d", random.nextInt(10000)));
        pstmt.setString(19, "L3_" + String.format("%04d", random.nextInt(10000)));
        pstmt.setString(20, "L4_" + String.format("%04d", random.nextInt(10000)));

        // 时间字段
        // pstmt.setTimestamp(21, new Timestamp(System.currentTimeMillis()));
        // pstmt.setTimestamp(22, new Timestamp(System.currentTimeMillis()));
    }

    private static void setBusinessLevel(PreparedStatement pstmt, int index, Random random, String prefix) throws SQLException {
        pstmt.setString(index, prefix + "-" + random.nextInt(20));
        pstmt.setString(index + 1, prefix + "_" + String.format("%04d", random.nextInt(10000)));
    }

    private static String generateRandomString(int length) {
        return ThreadLocalRandom.current()
                .ints(48, 123)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    // 常用简体中文字符集
    private static final String COMMON_CHINESE_CHARS = 
            "优化修复实新改新增添完善增强重实现管理用户登录流程数据同步报表功能系统性能权限" +
            "模块数据库查询架构文档安全监控缓存机制消息推送服务体验漏洞导出导入算法加密" +
            "日志记录错误处理批量操作接口开发部署测试上线生产环境版本控制代码审查需求分析" +
            "设计开发集成培训支持维护升级迭代优化改进流程提升效率降低成本提高质量保证稳定";

    private static String generateRandomChineseTitle(Random random) {
        String[] templates = {
            "优化用户登录流程",
            "修复数据同步问题",
            "实现新的报表功能",
            "改进系统性能",
            "新增权限管理模块",
            "优化数据库查询",
            "重构代码架构",
            "完善API文档",
            "增强安全性功能",
            "添加实时监控功能",
            "优化缓存机制",
            "实现消息推送服务",
            "改进用户体验",
            "修复安全漏洞",
            "新增导出功能",
            "优化搜索算法",
            "实现数据加密",
            "添加日志记录功能",
            "改进错误处理机制",
            "实现批量操作功能"
        };
        int suffixLength = random.nextInt(15) + 1;
        StringBuilder sb = new StringBuilder(templates[random.nextInt(templates.length)]);
        // 追加一些随机汉字
        for (int i = 0; i < suffixLength; i++) {
            sb.append(COMMON_CHINESE_CHARS.charAt(random.nextInt(COMMON_CHINESE_CHARS.length())));
        }
        return sb.toString();
    }

    private static String generateRandomChineseDescription(Random random, int maxLength) {
        StringBuilder sb = new StringBuilder();
        int length = random.nextInt(maxLength / 2) + maxLength / 2; // 50%-100% 的长度
        for (int i = 0; i < length; i++) {
            sb.append(COMMON_CHINESE_CHARS.charAt(random.nextInt(COMMON_CHINESE_CHARS.length())));
        }
        return sb.toString();
    }

    private static String generateRandomDescription(int maxLength) {
        return ThreadLocalRandom.current()
                .ints(97, 123)
                .limit(maxLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static void logProgress(long count) {
        System.out.printf("进度: %d/%d 条 | 耗时: %d 秒\n",
                count, TOTAL_RECORDS, System.currentTimeMillis() / 1000);
    }
}