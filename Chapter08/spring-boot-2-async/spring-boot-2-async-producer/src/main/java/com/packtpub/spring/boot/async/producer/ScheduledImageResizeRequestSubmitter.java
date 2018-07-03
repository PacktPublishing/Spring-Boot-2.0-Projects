package com.packtpub.spring.boot.async.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packtpub.spring.boot.async.model.ImageResizeRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.io.File;

@Component
public class ScheduledImageResizeRequestSubmitter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledImageResizeRequestSubmitter.class);

    private final ReplyingKafkaTemplate<String, String, String> template;
    private final ObjectMapper objectMapper;
    private final String imagesDirectory;

    public ScheduledImageResizeRequestSubmitter(ReplyingKafkaTemplate<String, String, String> template, ObjectMapper objectMapper, @Value("${images.input.directory}") String imagesInputDirectory) {
        this.template = template;
        this.objectMapper = objectMapper;
        this.imagesDirectory = imagesInputDirectory;
    }

    public void scheduleTaskWithCronExpression() {
        Flux.just(new File(imagesDirectory).listFiles()).filter(File::isFile).subscribe(
            f -> {
                Flux.just(new Dimension(800, 600), new Dimension(180, 180), new Dimension(1200, 630)).subscribe(d -> {
                    try {
                        ImageResizeRequest imageResizeRequest = new ImageResizeRequest((int) d.getWidth(), (int) d.getHeight(), f.getAbsolutePath());
                        ProducerRecord<String, String> record = new ProducerRecord<>("asyncRequests", objectMapper.writeValueAsString(imageResizeRequest));
                        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, "asyncReplies".getBytes()));
                        RequestReplyFuture<String, String, String> replyFuture = template.sendAndReceive(record);
                        ConsumerRecord<String, String> consumerRecord = replyFuture.get();
                    } catch (Exception e) {
                        LOGGER.error("Error while sending message", e);
                    }
                },
                e -> LOGGER.error("Error while running lambda"),
                () -> f.renameTo(new File(f.getParent() + "/Done", f.getName())));
            }
        );
    }

}
