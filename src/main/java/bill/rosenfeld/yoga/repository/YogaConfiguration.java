package bill.rosenfeld.yoga.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "bill.rosenfeld.yoga.repository",
        entityManagerFactoryRef = "yogaEntityManager",
        transactionManagerRef = "yogaTransactionManager")
public class YogaConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.yoga-datasource")
    public DataSource yogaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean yogaEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(yogaDataSource());
        em.setPackagesToScan("bill.rosenfeld.yoga.repository");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    public PlatformTransactionManager yogaTransactionManager() {
        return new JpaTransactionManager(yogaEntityManager().getObject());
    }

}
