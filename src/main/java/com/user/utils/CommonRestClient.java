package com.user.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Service
public class CommonRestClient {

	private Logger logger = LoggerFactory.getLogger(CommonRestClient.class);

	@Autowired
	@Qualifier("primaryRestTemplate")
	private RestTemplate restTemplate;

	/**
	 * 
	 * @param input
	 * @param url
	 * @param httpMethod
	 * @param customHeaders
	 * @param logRequest
	 * @param contentType
	 * @param setDefaultContentType
	 * @return
	 */
	public <T> ResponseEntity<String> callAPI(T input, String url, HttpMethod httpMethod, Map<String,String> customHeaders,
			boolean logRequest, MediaType contentType, boolean setDefaultContentType) {
		HttpHeaders headers = new HttpHeaders();

		if (contentType == null && setDefaultContentType) {

			headers.setContentType(MediaType.APPLICATION_JSON);

		} else if (contentType != null) {
			headers.setContentType(contentType);
		}
		if (customHeaders != null) {
			for (Entry<String, String> headerEntry : customHeaders.entrySet()) {
				headers.add(headerEntry.getKey(), headerEntry.getValue());
			}
		}
		HttpEntity<T> entity = new HttpEntity<>(input, headers);
		if(logger.isInfoEnabled() && logRequest) {
			logger.info(" url : " + url);
			}
		ResponseEntity<String> res = restTemplate.exchange(url, httpMethod, entity, String.class);
		if(logger.isInfoEnabled()){
			logger.info("response : " + res.getBody() + ", for request: url : " + url +", : requestBody:" + new Gson().toJson(input));
			logger.info("status : " + res.getStatusCode());
		}
		return res;
	}

	/**
	 * 
	 * @param input
	 * @param url
	 * @param httpMethod
	 * @param customHeaders
	 * @param logRequest
	 * @param contentType
	 * @return
	 */
	public <T> ResponseEntity<String> callAPI(T input, String url, HttpMethod httpMethod, Map<String,String> customHeaders,
			boolean logRequest, MediaType contentType) {
		return callAPI(input, url, httpMethod, customHeaders, logRequest, contentType, true);
	}


	/**
	 * 
	 * @param input
	 * @param url
	 * @param httpMethod
	 * @param customHeaders
	 * @param logRequest
	 * @param contentType
	 * @param setDefaultContentType
	 * @return
	 * @throws Exception 
	 */
	public <T> ResponseEntity<String> callAPIWithRetry(T input, String url, HttpMethod httpMethod, Map<String,String> customHeaders,
			boolean logRequest, MediaType contentType, boolean setDefaultContentType, int retryCount) throws Exception {

		
		Exception ex = null;
		for (int attemptNo = 0; attemptNo < retryCount; attemptNo++) {
			try {
				return callAPI(input, url, httpMethod, customHeaders,
						logRequest, contentType, setDefaultContentType);
			} catch (Exception e) {
				logger.info("callAPIWithRetry:: url: " + url + ", failed for try : " + attemptNo, e);
				ex = e; 
			}
		}
		throw ex;
	}
}
