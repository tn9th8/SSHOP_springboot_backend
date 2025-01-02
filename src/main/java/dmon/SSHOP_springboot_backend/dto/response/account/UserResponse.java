package dmon.SSHOP_springboot_backend.dto.response.account;

import dmon.SSHOP_springboot_backend.enums.GenderEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String name;
    String photo;
    Date dob;
    GenderEnum gender;


}
