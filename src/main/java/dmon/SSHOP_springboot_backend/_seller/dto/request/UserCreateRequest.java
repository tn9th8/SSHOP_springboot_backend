package dmon.SSHOP_springboot_backend._seller.dto.request;

import dmon.SSHOP_springboot_backend.enums.GenderEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    String name;
    String photo;
    Date dob;
    GenderEnum gender;
}
