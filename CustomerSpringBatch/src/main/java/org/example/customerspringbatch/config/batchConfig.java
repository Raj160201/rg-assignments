package org.example.customerspringbatch.config;
import jakarta.persistence.EntityManagerFactory;
import org.example.customerspringbatch.entity.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class batchConfig {

    @Bean
    public Job CustomerJobBean(JobRepository jobRepo, JobCompleteNotificationListener listener, Step steps) {
        return new JobBuilder("TransformCustomerData", jobRepo)
                .listener(listener)
                .start(steps).build();
    }

    @Bean
    public  Step steps(JobRepository jobRepo,
                       PlatformTransactionManager transactionManager,
                       ItemReader<Customer> reader,
                       ItemProcessor<Customer, Customer> processor,
                       ItemWriter<Customer> writer){

        return new StepBuilder("JobStep", jobRepo)
                .<Customer,Customer>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public FlatFileItemReader<Customer> reader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("itemReader")
                .resource(new ClassPathResource("customers.csv"))
                .linesToSkip(1)
                .lineMapper(lineMapper())
                .build();
    }

    private LineMapper<Customer> lineMapper() {
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob");

        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }


    @Bean
    public ItemProcessor<Customer, Customer> itemProcessor() {
        return new CustomCustomerProcessor();
    }

    @Bean
    public JpaItemWriter<Customer> itemWriter(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<Customer> writer = new JpaItemWriter<Customer>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
