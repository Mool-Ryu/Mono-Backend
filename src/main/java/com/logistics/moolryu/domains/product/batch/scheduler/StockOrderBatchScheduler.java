package com.logistics.moolryu.domains.product.batch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockOrderBatchScheduler {
	private final JobLauncher jobLauncher;
	private final Job stockOrderJob;

	@Scheduled(cron = "0 */10 * * * *") // 10분마다 실행
	public void runStockOrderBatch() {
		try {
			jobLauncher.run(stockOrderJob, new JobParameters());
		} catch (Exception e) {
			log.error("StockOrder 배치 실행 중 오류 발생 체크", e);
		}
	}
}
