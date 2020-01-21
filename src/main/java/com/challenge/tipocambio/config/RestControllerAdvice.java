package com.challenge.tipocambio.config;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

import java.util.LinkedHashMap;

import com.challenge.tipocambio.commons.api.rest.ResponseEnvelop;

import com.challenge.tipocambio.commons.exception.AbstractRestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@SuppressWarnings("all")
public class RestControllerAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerAdvice.class);

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter arg1, MediaType arg2,
                                  Class<? extends HttpMessageConverter<?>> arg3,
                                  ServerHttpRequest arg4, ServerHttpResponse arg5) {

        if (body instanceof LinkedHashMap<?, ?>) {
            LOGGER.error("Error in: " + body.toString());
        }
        return body instanceof ResponseEnvelop ? body : new ResponseEnvelop<>(body);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getContainingClass().getName().contains("swagger");
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ResponseEnvelop> handle(AbstractRestException exception) {
        LOGGER.error(exception.getMessage(), exception);
        ResponseEnvelop data = new ResponseEnvelop();
        setUnsuccessful(data);
        addErrors(exception, data);
        HttpStatus responseStatus = exception.getHttpStatus();
        if (responseStatus == null) {
            responseStatus = resolveAnnotatedResponseStatus(exception);
        }
        return new ResponseEntity<>(data, responseStatus);
    }

    private void setUnsuccessful(ResponseEnvelop data) {
    }

    private void addErrors(AbstractRestException ex, ResponseEnvelop data) {
        Throwable cause = ex;
        while (cause != null) {
            ResponseEnvelopErrorDto error = ResponseEnvelopErrorDto
                    .builder()
                    .code(ex.getErrorCode().getCode())
                    .message(ex.getErrorCode().getMessage())
                    .title(ex.getErrorCode().getTitle())
                    .build();

            data.addError(error);
            cause = cause.getCause();
        }
    }

    HttpStatus resolveAnnotatedResponseStatus(Exception exception) {
        ResponseStatus annotation = findMergedAnnotation(exception.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
