package dmon.SSHOP_springboot_backend.base;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    //SUCCESS CODE: 1 //
    //SYSTEM EXCEPTION, DYNAMIC EXCEPTION: 1000+ //
    UNCATEGORIZED_EXCEPTION(1000, "An uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR), //todo: remove
    UNHANDLED_EXCEPTION(1000, "An uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    KEY_INVALID(1001, "Can not get value of enum key", HttpStatus.INTERNAL_SERVER_ERROR),
    ID_INVALID(1002, "Id is incorrect format", HttpStatus.INTERNAL_SERVER_ERROR),
    OBJECT_NOT_FOUND(1003, "not found", HttpStatus.NOT_FOUND), //todo: remove
    OBJECT_EXISTED(1004, "existed", HttpStatus.CONFLICT),
    //ACCOUNT 1100+//
    USERNAME_INVALID(1100, "Password should be at least 4 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1101, "Password should be at least 6 characters", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1102, "Email should follow the email format", HttpStatus.BAD_REQUEST), //todo: remove
    PHONE_INVALID(1103, "Phone should follow the phone format", HttpStatus.BAD_REQUEST),
    ACCOUNT__NOT_FOUND(1104, "Account is not found", HttpStatus.BAD_REQUEST),
    //SECURITY-ACCESS 1200+//
    SECURITY__UNAUTHENTICATED(1200, "You are not allowed to access this resource.", HttpStatus.UNAUTHORIZED),
    SECURITY__UNAUTHORIZED(1201, "You are not permitted to access this resource.", HttpStatus.FORBIDDEN),
    SECURITY__LOGIN_FAILED(1202, "Username or password doesn't match any our records. Try again.", HttpStatus.BAD_REQUEST),
    SECURITY__NOT_SELLER(1203, "Account is not registered as a seller. Please sign up.", HttpStatus.BAD_REQUEST),
    //USER 1300+. Current: 1300//
    //SELLER 1400. Current: 1400//

    ;

    private int code;
    private String message;
    private HttpStatusCode status;
}
