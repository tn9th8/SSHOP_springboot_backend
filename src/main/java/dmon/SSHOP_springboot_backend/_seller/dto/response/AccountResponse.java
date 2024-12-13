package dmon.SSHOP_springboot_backend._seller.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    String id;
    String username;
    String email;
    String phone;
    Set<String> roles;
}
