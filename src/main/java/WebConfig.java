import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "by.nagula")
public class WebConfig {

  @Bean
    public DataSource source(){
      DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
      driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
      driverManagerDataSource.setUrl("jdbc:mysql://localhost/testhibernate?serverTimezone=UTC");
      driverManagerDataSource.setUsername("root");
      driverManagerDataSource.setPassword("5454136RbHbKk");
      return driverManagerDataSource;
  }

  @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(){
      LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
      localSessionFactoryBean.setDataSource(source());
      localSessionFactoryBean.setPackagesToScan("by.nagula.entity");
      localSessionFactoryBean.setHibernateProperties(propertiesHib());
      return localSessionFactoryBean;
  }

  @Bean
    public PlatformTransactionManager transactionManager(){
      HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
     hibernateTransactionManager.setSessionFactory(localSessionFactoryBean().getObject());
     return hibernateTransactionManager;
  }


    private Properties propertiesHib(){
      Properties properties = new Properties();
      properties.setProperty("show_sql", "true");
      properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
      properties.setProperty("hibernate.hbm2ddl.auto", "update");
      return properties;
  }

}
