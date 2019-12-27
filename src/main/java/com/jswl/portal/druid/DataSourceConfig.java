package com.jswl.portal.druid;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;
@Configuration
public class DataSourceConfig {
	@Value("${spring.datasource.username}")
	private String ds_name;
	
	@Value("${spring.datasource.password}")
	private String ds_password;
	
	@Value("${spring.datasource.url}")
	private String ds_url;
	
	@Value("${spring.datasource.driverClassName}")
	private String driver_class;

	@Value("${spring.datasource.filters}")
	private String filters;

	@Bean
	@Primary
	DruidDataSource dataSource() throws SQLException {
		DruidDataSource ds = new DruidDataSource();
		ds.setUrl(ds_url);
		ds.setUsername(ds_name);
		ds.setPassword(ds_password);
		ds.setDriverClassName(driver_class);
		ds.setFilters(filters);
		return ds;
	}
}