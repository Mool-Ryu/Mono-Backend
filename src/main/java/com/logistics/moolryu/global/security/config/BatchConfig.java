package com.logistics.moolryu.global.security.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

	private final DataSource dataSource;

	@Bean
	public JobRepository jobRepository() throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(dataSource);
		factory.setTransactionManager(factory.getTransactionManager());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager(){
		return new ResourcelessTransactionManager();
	}

}
