package dmon.SSHOP_springboot_backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {
    private Long accountId;
    private String email;
    private String phone;
}
