package com.logistics.moolryu.domains.product.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.logistics.moolryu.domains.product.entity.StockOrder;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class StockOrderBatchJobConfig {

	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;
	private final EntityManagerFactory entityManagerFactory;

	@Bean
	public Job stockOrderJob() {
		return new JobBuilder("stockOrderJob", jobRepository)
			.incrementer(new RunIdIncrementer())
			.start(processStockOrderStep())
			.build();
	}

	@Bean
	public Step processStockOrderStep() {
		return new StepBuilder("processStockOrderStep", jobRepository)
			.<StockOrder, StockOrder>chunk(10, transactionManager)
			.reader(stockOrderReader())
			.processor(stockOrderProcessor())
			.writer(stockOrderWriter())
			.build();
	}

	@Bean
	public ItemReader<StockOrder> stockOrderReader() {
		return new JpaPagingItemReaderBuilder<StockOrder>()
			.name("stockOrderReader")
			.entityManagerFactory(entityManagerFactory)
			.queryString("SELECT so FROM StockOrder so WHERE so.status = 'PENDING'")
			.pageSize(10)
			.build();
	}

	@Bean
	public ItemProcessor<StockOrder, StockOrder> stockOrderProcessor() {
		return stockOrder -> {
			stockOrder.processStock();
			return stockOrder;
		};
	}

	@Bean
	public ItemWriter<StockOrder> stockOrderWriter() {
		return new JpaItemWriterBuilder<StockOrder>()
			.entityManagerFactory(entityManagerFactory)
			.build();
	}

}
