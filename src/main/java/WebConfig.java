import by.nagula.interceptor.MyInterceptor;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "by.nagula")
public class WebConfig implements WebMvcConfigurer {
  private final ApplicationContext applicationContext;

  public WebConfig(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Bean
    public DataSource source(){
      DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
      driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
      driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/test");
      driverManagerDataSource.setUsername("postgres");
      driverManagerDataSource.setPassword("admin");
      return driverManagerDataSource;
  }

//  @Bean
//    public LocalSessionFactoryBean localSessionFactoryBean(){
//      LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//      localSessionFactoryBean.setDataSource(source());
//      localSessionFactoryBean.setPackagesToScan("by.nagula.entity");
//      localSessionFactoryBean.setHibernateProperties(propertiesHib());
//      return localSessionFactoryBean;
//  }

//  @Bean
//  public PlatformTransactionManager transactionManager(){
//    HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
//    hibernateTransactionManager.setSessionFactory(localSessionFactoryBean().getObject());
//    return hibernateTransactionManager;
//  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(source());
    entityManagerFactoryBean.setJpaProperties(propertiesHib());
    entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
    entityManagerFactoryBean.setPackagesToScan("by.nagula.entity");
    return entityManagerFactoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager(){
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
    return transactionManager;
  }

  @Bean
    public Properties propertiesHib(){
      Properties properties = new Properties();
      properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
      return properties;
  }

  @Bean
  public SpringResourceTemplateResolver templateResolver(){
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setApplicationContext(this.applicationContext);
    templateResolver.setPrefix("/pages/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setCacheable(false);
    return templateResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine(){
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    templateEngine.setEnableSpringELCompiler(true);
    return templateEngine;
  }

  @Bean
  public ThymeleafViewResolver viewResolver(){
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine());
    return viewResolver;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new MyInterceptor()).addPathPatterns("/account");
  }
}
