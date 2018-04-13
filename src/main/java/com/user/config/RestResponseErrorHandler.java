package com.user.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Handles the response in case of an error. We don't throw an exception when 
 * there’s an HTTP error, as exception bubbles out RestTemplate call and we don't 
 * get the error object we want to read. So we just need to replace it with 
 * a custom handler that doesn’t throw the exception
 * 
 *
 */
public class RestResponseErrorHandler implements ResponseErrorHandler 
{
	/**
     * We don't throw exception, just log it and let ResponseExtractor do 
     * everything else
     */
    public void handleError(final ClientHttpResponse response) throws IOException 
    {
    	// no implementation
    }

    /**
     * Indicates whether the given response has any errors
     */
    public boolean hasError(final ClientHttpResponse response) throws IOException 
    {
        return isError(response.getStatusCode());
    }

    public static boolean isError(final HttpStatus status) {
        final HttpStatus.Series series = status.series();
        return HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series);
    }
}