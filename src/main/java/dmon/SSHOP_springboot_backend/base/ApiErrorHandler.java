package dmon.SSHOP_springboot_backend.base;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiErrorHandler {
    /**
     * handle app exception threw in the service tier
     * @param: AppException
     * @return: ApiResponse
     */
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<Object>> handleException(AppException exception){
        ApiResponse<Object> apiError = ApiResponse.builder()
                .code(exception.getCode())
                .error(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiError);
    }

    /**'
     * handle exception threw in the service tier by spring security
     * @param: AccessDeniedException
     * @return ApiResponse
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse<Object>> handleException(AccessDeniedException exception){
        ExceptionCode exceptionCode = ExceptionCode.SECURITY__UNAUTHORIZED;
        return ResponseEntity
                .status(exceptionCode.getStatus())
                .body(ApiResponse.builder()
                        .code(exceptionCode.getCode())
                        .error(exceptionCode.getMessage())
                        .build()
        );
    }

    /**
     * handle app exception uncategorized
     * @param: RuntimeException
     * @return: ApiResponse
     */
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<Object>> handleException(RuntimeException exception) {
        ExceptionCode exceptionCode = ExceptionCode.SYSTEM__UNHANDLED_EXCEPTION;
        return ResponseEntity
                .status(exceptionCode.getStatus())
                .body(ApiResponse.builder()
                        .success(false)
                        .code(exceptionCode.getCode())
                        .error(exceptionCode.getMessage())
                        .build()
                );
    }

    /**
     * handle exception threw in the validation tier
     * @params: RuntimeException
     * @return: ApiResponse
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Object>> handleException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ExceptionCode exceptionCode = ExceptionCode.SYSTEM__ENUM_KEY_INVALID;
        try {
            exceptionCode = ExceptionCode.valueOf(enumKey);
        } catch (IllegalArgumentException ignored){ }

        ApiResponse<Object> apiError = ApiResponse.builder()
                .success(false)
                .code(exceptionCode.getCode())
                .error(exceptionCode.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiError);
    }
}
