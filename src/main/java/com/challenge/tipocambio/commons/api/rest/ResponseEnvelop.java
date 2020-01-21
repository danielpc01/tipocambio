package com.challenge.tipocambio.commons.api.rest;

import java.util.ArrayList;
import java.util.List;

public class ResponseEnvelop<T> {

    private T response;

    private List<T> errors = new ArrayList<>();

    public ResponseEnvelop() {
    }

    public ResponseEnvelop(T result) {
        super();
        this.response = result;
    }



    public void addError(T error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }

    /**
     * @return the result
     */
    public T getResponse() {
        return this.response;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResponse(T result) {
        this.response = result;
    }

    /**
     * @return the errors
     */
    public List<T> getErrors() {
        return this.errors;
    }

    /**
     * @param errors
     *            the errors to set
     */
    public void setErrors(List<T> errors) {
        this.errors = errors;
    }

}