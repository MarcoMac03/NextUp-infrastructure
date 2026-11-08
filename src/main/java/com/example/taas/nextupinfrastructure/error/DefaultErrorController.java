package com.example.taas.nextupinfrastructure.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.webmvc.error.ErrorAttributes;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

@RestController
public class DefaultErrorController implements ErrorController {
    ErrorAttributes errorAttributes;

    public DefaultErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public ErrorDTO handleError(HttpServletRequest req) {
        Integer statusCode = (Integer)req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Throwable error = errorAttributes.getError(new ServletWebRequest(req));
        return new ErrorDTO(
                statusCode,
                HttpStatus.valueOf(statusCode).getReasonPhrase(),
                error != null ? error.getMessage() : "",
                req.getAttribute(RequestDispatcher.ERROR_REQUEST_URI).toString()
        );
    }
}
