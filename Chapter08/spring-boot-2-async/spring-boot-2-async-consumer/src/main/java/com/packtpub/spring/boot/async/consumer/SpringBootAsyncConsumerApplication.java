package com.packtpub.spring.boot.async.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packtpub.spring.boot.async.model.ImageResizeRequest;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class SpringBootAsyncConsumerApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpringBootAsyncConsumerApplication.class);

    public static void main(String... args) {
        new SpringApplicationBuilder(SpringBootAsyncConsumerApplication.class).web(WebApplicationType.NONE).build().run(args);
    }

    @KafkaListener(id="server", topics = "asyncRequests")
    @SendTo
    public String listen(String input) {
        try {
            ImageResizeRequest imageResizeRequest = objectMapper().readValue(input, ImageResizeRequest.class);
            File imageFile = new File(imageResizeRequest.getInputFile());
            String[] nameParts = imageFile.getName().split("\\.");
            BufferedImage image = ImageIO.read(imageFile);
            ImageIO.write(resize(image, imageResizeRequest.getWidth(), imageResizeRequest.getHeight()), "png", new File(imageFile.getParent() + "/Done", imageResizeRequest.getWidth() + "x" + imageResizeRequest.getHeight() + "-" + nameParts[0] + ".png"));
        } catch (IOException e) {
            LOGGER.error("Error while processing input {}", input, e);
        }
        return input;
    }

    @Bean
    public NewTopic asyncRequests() {
        return new NewTopic("asyncRequests", 10, (short) 2);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image temporaryImage = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(temporaryImage, 0, 0, null);
        g2d.dispose();

        return newImage;
    }

}
