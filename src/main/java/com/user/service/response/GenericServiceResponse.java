package com.user.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.user.response.ErrorResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericServiceResponse<T> {

    private Integer status;
    private T result;
    private ErrorResponse error;
    
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public T getResult() {
        return result;
    }
    public void setResult(T resoult) {
        this.result = resoult;
    }
    public ErrorResponse getError() {
        return error;
    }
    public void setError(ErrorResponse error) {
        this.error = error;
    }
    
}
