package br.com.drv.deliveryrouter;


import java.util.Arrays;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        
//		ResultSetHandler<Object[]> h = new ResultSetHandler<Object[]>() {
//			public Object[] handle(ResultSet rs) throws SQLException {
//				if (!rs.next()) {
//					return null;
//				}
//
//				ResultSetMetaData meta = rs.getMetaData();
//				int cols = meta.getColumnCount();
//				Object[] result = new Object[cols];
//
//				for (int i = 0; i < cols; i++) {
//					result[i] = rs.getObject(i + 1);
//				}
//
//				return result;
//			}
//		};
//		Class.forName("org.h2.Driver");
//		Connection conn = DriverManager.getConnection("jdbc:h2:mem:test", "test", "test");
//		Statement stm = conn.createStatement();
//		stm.execute("create table pawn (name varchar(20))");
//
//		QueryRunner qr = new QueryRunner();
//
//		stm.execute("insert into pawn(name) values('adadadax0')");
//		stm.execute("insert into pawn(name) values('adadadax1')");
//
//		// for (int i = 0; i < 11; i++) {
//		// System.out.println(stm.execute("insert into pawn(name)
//		// values('adadadax" + i + "')"));
//		// System.out.println("insert into pawn(name) values('adadadax" + i +
//		// "')");
//		// }
//
//		Object[] result = qr.query(conn, "select * from pawn", h);
//
//		// ResultSet rs = stm.executeQuery("select * from pawn");
//
//		System.out.println(result);
    }
    
    @Bean
    public ServletRegistrationBean h2Servlet() {
    	ServletRegistrationBean servletBean = new ServletRegistrationBean();
    	servletBean.addUrlMappings("/h2/*");
    	servletBean.setServlet(new WebServlet());
    	return servletBean;
    }

}