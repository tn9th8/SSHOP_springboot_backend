package dmon.SSHOP_springboot_backend.base;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    /**
     * Handle app exception threw in the service tier
     *
     * @param: AppException
     * @return: ApiResponse
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiRes<Object>> handleException(AppException exception){
        ApiRes<Object> apiError = ApiRes.builder()
                .code(exception.getCode())
                .error(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiError);
    }

    /**
     * Handle exception threw in the service tier by spring security
     *
     * @param: AccessDeniedException
     * @return ApiResponse
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiRes<Object>> handleException(AccessDeniedException exception){
        ExceptionCode exceptionCode = ExceptionCode.ACCESS__UNAUTHORIZED;
        return ResponseEntity
                .status(exceptionCode.getStatus())
                .body(ApiRes.builder()
                        .code(exceptionCode.getCode())
                        .error(exceptionCode.getMessage())
                        .build()
        );
    }

    /**
     * Handle app exception uncategorized
     *
     * @param: RuntimeException
     * @return: ApiResponse
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiRes<Object>> handleException(RuntimeException exception) {
        ExceptionCode exceptionCode = ExceptionCode.SYSTEM__UNHANDLED_EXCEPTION;
        return ResponseEntity
                .status(exceptionCode.getStatus())
                .body(ApiRes.builder()
                        .success(false)
                        .code(exceptionCode.getCode())
                        .error(exceptionCode.getMessage())
                        .build()
                );
    }

    /**
     * Handle exception threw in the validation tier
     *
     * @params: RuntimeException
     * @return: ApiResponse
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRes<Object>> handleException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ExceptionCode exceptionCode = ExceptionCode.SYSTEM__ENUM_KEY_INVALID;
        try {
            exceptionCode = ExceptionCode.valueOf(enumKey);
        } catch (IllegalArgumentException ignored){ }

        ApiRes<Object> apiError = ApiRes.builder()
                .success(false)
                .code(exceptionCode.getCode())
                .error(exceptionCode.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiError);
    }
}
