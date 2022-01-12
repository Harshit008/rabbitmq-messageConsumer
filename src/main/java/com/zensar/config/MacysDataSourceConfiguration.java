package com.zensar.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "macysEntityManager", 
		transactionManagerRef = "macysTransactionManager", 
		basePackages = "com.zensar.repo"
)
public class MacysDataSourceConfiguration {

	public static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
	public static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	public static final String PROPERTY_NAME_HIBERNATE_FMT_SQL = "hibernate.format_sql";
	public static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = { "com.zensar.pd.domain" };

	public static final String DB_URL = "app.datasource.pd.url";
	public static final String DB_USER = "app.datasource.pd.username";
	public static final String DB_PASSWORD = "app.datasource.pd.password";
	public static final String DB_DRIVER = "app.datasource.pd.driver";
	public static final String DB_DIALECT = "app.datasource.pd.dialect";

	@Autowired
	private Environment env;

	@Bean(name="macys")
	public AnnotationMBeanExporter annotationMBeanExporter() {
		AnnotationMBeanExporter annotationMBeanExporter = new AnnotationMBeanExporter();
		annotationMBeanExporter.addExcludedBean("macysMysqlDataSource");
		annotationMBeanExporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
		return annotationMBeanExporter;
	}

	@Bean(name = "macysDataSource", destroyMethod = "close")
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(env.getProperty(DB_DRIVER));

		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(env.getProperty(DB_URL));
		dataSource.setUser(env.getProperty(DB_USER));
		dataSource.setPassword(env.getProperty(DB_PASSWORD));
		dataSource.setMaxPoolSize(100);
		dataSource.setMinPoolSize(0);
		//dataSource.setMaxIdleTime(300);
		dataSource.setAcquireIncrement(2);
		dataSource.setMaxStatements(0);
		dataSource.setInitialPoolSize(1);
		dataSource.setPreferredTestQuery("SELECT 1");
		dataSource.setTestConnectionOnCheckin(true);
		dataSource.setTestConnectionOnCheckout(true);
		dataSource.setAcquireRetryAttempts(3);
		dataSource.setAcquireRetryDelay(100);
		dataSource.setBreakAfterAcquireFailure(false);
		dataSource.setForceIgnoreUnresolvedTransactions(false);
		dataSource.setAutoCommitOnClose(false);
		dataSource.setMaxIdleTimeExcessConnections(3600);
		dataSource.setIdleConnectionTestPeriod(3600);
		dataSource.setMaxIdleTime(3600);
		dataSource.setNumHelperThreads(20);
		return dataSource;
	}

	@Bean(name = "macysTransactionManager")
	public JpaTransactionManager jpaTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}

	@Bean(name = "macysEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPersistenceUnitManager(persistenceUnitManager());
		entityManagerFactoryBean.setPersistenceUnitName("pd");
		entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
		return entityManagerFactoryBean;
	}

	@Bean(name = "macyspersistenceUnitManager")
	public DefaultPersistenceUnitManager persistenceUnitManager() {
		DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
		persistenceUnitManager.setDefaultDataSource(dataSource());
		persistenceUnitManager.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		return persistenceUnitManager;
	}

	private HibernateJpaVendorAdapter vendorAdaptor() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabasePlatform(env.getProperty(DB_DIALECT));
		vendorAdapter.setShowSql(false);
		return vendorAdapter;
	}

	private Properties jpaHibernateProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_FMT_SQL, env.getProperty(PROPERTY_NAME_HIBERNATE_FMT_SQL));
		properties.put(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE,
				env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		return properties;
	}
}
