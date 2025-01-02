package dmon.SSHOP_springboot_backend.base;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ExceptionCode {
    //SUCCESS CODE: 1 //
    //SYSTEM EXCEPTION 1000+//
    SYSTEM__UNHANDLED_EXCEPTION(1000, "An unhandled error", HttpStatus.INTERNAL_SERVER_ERROR),
    SYSTEM__ENUM_KEY_INVALID(1001, "Enum key is invalid", HttpStatus.INTERNAL_SERVER_ERROR),
    SYSTEM__FILE_EMPTY(1002, "File is empty. Please upload a file", HttpStatus.BAD_REQUEST),
    SYSTEM__FILE_OUT_EXTENSIONS(1003, "File extensions should be pdf, jpg, jpeg, png, doc or docx", HttpStatus.BAD_REQUEST),
    SYSTEM__FILE_CREATE_DIRECTORY(1004, "An error occurred while creating a media directory.", HttpStatus.INTERNAL_SERVER_ERROR),
    SYSTEM__FILE_CREATE_FOLDER(1005, "An error occurred while creating a media folder.", HttpStatus.INTERNAL_SERVER_ERROR),
    SYSTEM__FILE_CREATE_PATH(1006, "An error occurred while creating a file path.", HttpStatus.INTERNAL_SERVER_ERROR),
    SYSTEM__FILE_STREAM(1007, "An error occurred while stream the file.", HttpStatus.INTERNAL_SERVER_ERROR),
    //ACCOUNT 1100+//
    ACCOUNT__USERNAME_MIN(1100, "Username should be at least 4 characters", HttpStatus.BAD_REQUEST),
    ACCOUNT__PASSWORD_MIN(1101, "Password should be at least 6 characters", HttpStatus.BAD_REQUEST),
    ACCOUNT__EMAIL_NOT_MATCHED(1102, "Email should follow the email format", HttpStatus.BAD_REQUEST),
    ACCOUNT__PHONE_NOT_MATCHED(1103, "Phone should follow the phone format", HttpStatus.BAD_REQUEST),
    ACCOUNT__NOT_FOUND(1104, "Account is not found", HttpStatus.BAD_REQUEST),
    ACCOUNT__USERNAME_UNIQUE(1504, "Username already existed. Please choose a different one.", HttpStatus.BAD_REQUEST),
    ACCOUNT__EMAIL_UNIQUE(1505, "Email already existed. Please choose a different one.", HttpStatus.BAD_REQUEST),
    ACCOUNT__PHONE_UNIQUE(1506, "Phone already existed. Please choose a different one.", HttpStatus.BAD_REQUEST),
    //ACCESS 1200+//
    ACCESS__UNAUTHENTICATED(1200, "You are not allowed to access this resource.", HttpStatus.UNAUTHORIZED),
    ACCESS__UNAUTHORIZED(1201, "You are not permitted to access this resource.", HttpStatus.FORBIDDEN),
    ACCESS__LOGIN_FAILED(1202, "Username or password doesn't match any our records. Try again.", HttpStatus.BAD_REQUEST),
    ACCESS__NOT_SELLER(1203, "Account is not registered as a seller. Please sign up.", HttpStatus.BAD_REQUEST),
    //USER 1300+//
    //SELLER 1400+//

    //CATEGORY 1500+//
    CATEGORY__NAME_OUT_SIZE(1500, "Name should be between 2 and 40 characters", HttpStatus.BAD_REQUEST),
    CATEGORY__DESCRIPTION_OUT_SIZE(1501, "Description should be up to 255 characters", HttpStatus.BAD_REQUEST),
    CATEGORY__PHOTO_OUT_SIZE(1502, "Photo should be up to 255 characters", HttpStatus.BAD_REQUEST),
    CATEGORY__NAME_NOT_EMPTY(1503, "Name should not be empty", HttpStatus.BAD_REQUEST),
    CATEGORY__NAME_UNIQUE(1504, "Name already existed. Please choose a different one.", HttpStatus.BAD_REQUEST),
    CATEGORY__NOT_FOUND(1505, "Category is not found", HttpStatus.BAD_REQUEST),
    CATEGORY__POSITION_MIN(1506, "Position should be higher or equal to 1", HttpStatus.BAD_REQUEST)
    //PRODUCT 1600+//
    //SKU 1700+//

    ;

    int code;
    String message;
    HttpStatusCode status;
}
