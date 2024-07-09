package org.example.customerspringbatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;


@Component
public class JobCompleteNotificationListener implements JobExecutionListener {

    private Logger logger= LoggerFactory.getLogger(JobCompleteNotificationListener.class);
    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Job Started");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus()== BatchStatus.COMPLETED){
            logger.info("Job Completed");
        }
    }
}