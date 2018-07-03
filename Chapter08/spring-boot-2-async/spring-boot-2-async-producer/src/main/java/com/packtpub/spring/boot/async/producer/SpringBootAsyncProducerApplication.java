package com.packtpub.spring.boot.async.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@SpringBootApplication
public class SpringBootAsyncProducerApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder(SpringBootAsyncProducerApplication.class).web(WebApplicationType.NONE).build().run(args);
    }

    @Bean
    public ReplyingKafkaTemplate<String, String, String> kafkaTemplate(
            ProducerFactory<String, String> pf,
            KafkaMessageListenerContainer<String, String> replyContainer) {
        return new ReplyingKafkaTemplate<>(pf, replyContainer);
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> replyContainer(
            ConsumerFactory<String, String> cf) {
        ContainerProperties containerProperties = new ContainerProperties("asyncReplies");
        containerProperties.setGroupId("async");
        return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }

    @Bean
    public NewTopic asyncRequests() {
        return new NewTopic("asyncRequests", 10, (short) 2);
    }

    @Bean
    public NewTopic asyncReplies() {
        return new NewTopic("asyncReplies", 10, (short) 2);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(ScheduledImageResizeRequestSubmitter scheduledImageResizeRequestSubmitter) {
        MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        methodInvokingJobDetailFactoryBean.setTargetObject(scheduledImageResizeRequestSubmitter);
        methodInvokingJobDetailFactoryBean.setTargetMethod("scheduleTaskWithCronExpression");

        return methodInvokingJobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean trigger(JobDetail job, @Value("${images.cron}") String imagesCron) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setCronExpression(imagesCron);
        cronTriggerFactoryBean.setJobDetail(job);
        return cronTriggerFactoryBean;
    }

}
