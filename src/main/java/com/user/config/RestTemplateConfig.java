package com.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
    /**
     * Creates a bean for RestTemplate to be used for synchronous client-side
     * HTTP access across application
     * 
     * @return RestTemplate object
     */
    @Bean(name = { "primaryRestTemplate" })
    @Primary
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.setErrorHandler(new RestResponseErrorHandler());
        return restTemplate;
    }

    /**
     * Prepares a HTTP request factory to be used with RestTemplate
     * 
     * @return ClientHttpRequestFactory object
     */
    private ClientHttpRequestFactory clientHttpRequestFactory() {
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(60000);
        factory.setConnectTimeout(60000);
        return factory;
    }

}
