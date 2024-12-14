package dmon.SSHOP_springboot_backend.base;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    //SYSTEM EXCEPTION//
    UNCATEGORIZED_EXCEPTION(9000, "An uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1101, "Not allowed", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1102, "No permission", HttpStatus.FORBIDDEN),
    KEY_INVALID(1103, "Can not get value of enum key", HttpStatus.INTERNAL_SERVER_ERROR),
    ID_INVALID(1104, "Id is incorrect format", HttpStatus.INTERNAL_SERVER_ERROR),
    //DYNAMIC EXCEPTION//
    OBJECT_NOT_FOUND(1001, "not found", HttpStatus.NOT_FOUND),
    OBJECT_EXISTED(1002, "existed", HttpStatus.CONFLICT),
    //VALIDATION EXCEPTION//
    EMAIL_INVALID(2001, "Email should follow the email format", HttpStatus.BAD_REQUEST),
    PHONE_INVALID(2002, "Phone should follow the phone format", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(2003, "Password should be at least 4 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(2004, "Password should be at least 6 characters", HttpStatus.BAD_REQUEST),
    //OTHERS//
    ;

    private int code;
    private String message;
    private HttpStatusCode status;
}
