package dmon.SSHOP_springboot_backend.util;

import dmon.SSHOP_springboot_backend.dto.response.ApiResponse;
import dmon.SSHOP_springboot_backend.util.annotation.ApiMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ApiResponseFormat implements ResponseBodyAdvice<Object> {
    /**
     * Check if it is overwritten/format the response before sending
     * Return true is all response is formatted
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        //get apiStatus
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int apiStatus = servletResponse.getStatus();
        //get apiMessage
        ApiMessage apiMessageObject = returnType.getMethodAnnotation(ApiMessage.class);
        String apiMessage = apiMessageObject.value() != null ? apiMessageObject.value() : "SSHOP api response";
        //case: body is a string
        if (!MediaType.APPLICATION_JSON.equals(selectedContentType))
            return body;
        // case: swagger
        String path = request.getURI().getPath();
        if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui"))
            return body;
        //case: throw exception
        if (apiStatus >= 400)
            return body;
        //case: success
        return ApiResponse.builder()
                .status(apiStatus)
                .message(apiMessage)
                .data(body)
                .build();
    }

}
