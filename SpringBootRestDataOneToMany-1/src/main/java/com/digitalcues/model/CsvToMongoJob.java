package com.digitalcues.model;
/*
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import com.digitalcues.model.Employee;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@ConditionalOnClass(PlatformTransactionManager.class)
@EnableBatchProcessing
@Configuration
public class CsvToMongoJob {
 
  @Autowired
  public JobBuilderFactory jobBuilderFactory;
  @Autowired
  public StepBuilderFactory stepBuilderFactory;
 
  @Autowired
  public MongoTemplate mongoTemplate;
 
  @Bean
  public Job readCSVFile() {
    return jobBuilderFactory.get("readCSVFile").incrementer(new RunIdIncrementer()).start(step1())
        .build();
  }
 
  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1").<Employee, Employee>chunk(10).reader(reader())
        .writer(writer()).build();
  }
 
  @Bean
  public FlatFileItemReader<Employee> reader() {
    FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource("person.csv"));
    reader.setLineMapper(new DefaultLineMapper<Employee>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[]{"firstName", "lastName"});
 
      }});
      setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {{
        setTargetType(Employee.class);
      }});
    }});
    return reader;
  }
 
  @Bean
  public MongoItemWriter<Employee> writer() {
    MongoItemWriter<Employee> writer = new MongoItemWriter<Employee>();
    writer.setTemplate(mongoTemplate);
    writer.setCollection("Employee");
    return writer;
  }
  
  @Bean
  public ItemProcessor<Employee, Employee> processor() {
      return new DataProcessor();
  }
  
 /* @Bean(name="Javax.sql.DataSource")
  public  MongoDbFactory mongoDbFactory() {
  	MongoClient mongoClient = new MongoClient("localhost",27017);
    return new SimpleMongoDbFactory(mongoClient, "concretepage");
  }

}

*/
