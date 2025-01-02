package dmon.SSHOP_springboot_backend._controller.account;

import dmon.SSHOP_springboot_backend.dto.request.account.UserCreateRequest;
import dmon.SSHOP_springboot_backend.dto.response.account.UserResponse;
import dmon.SSHOP_springboot_backend.utils.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @ApiMessage("create an user")
    @PostMapping("/create")
    public ResponseEntity<UserResponse> createOne(@Valid @RequestBody UserCreateRequest body){
        return ResponseEntity
                .ok()
                .body(null);
    }
}
