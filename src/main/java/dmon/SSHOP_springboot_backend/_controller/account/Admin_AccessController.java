package dmon.SSHOP_springboot_backend._controller.account;


import dmon.SSHOP_springboot_backend.dto.request.account.Admin_LoginRequest;
import dmon.SSHOP_springboot_backend.dto.response.account.Admin_AccessResponse;
import dmon.SSHOP_springboot_backend._service.account.Admin_IAccessService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/api/v1/access")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Admin_AccessController {
    Admin_IAccessService accessService;

    //LOG IN/OUT//
    @PostMapping("/login")
    public ResponseEntity<Admin_AccessResponse> login(@RequestBody Admin_LoginRequest request) {
        return ResponseEntity
                .ok()
                .body(this.accessService.login(request));
    }

    @PostMapping("/logout")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> logout() {
        this.accessService.logout();
        return ResponseEntity
                .noContent()
                .build();
    }
}
