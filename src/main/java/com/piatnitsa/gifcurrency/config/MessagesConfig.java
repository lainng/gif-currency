package com.piatnitsa.gifcurrency.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@Configuration
public class MessagesConfig {

    /**
     * Create bean of {@link MessageSource} implementation which will be used to get info from properties files.
     * @return the {@link MessageSource} bean implementation.
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
