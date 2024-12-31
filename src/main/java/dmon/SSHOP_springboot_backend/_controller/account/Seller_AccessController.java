package dmon.SSHOP_springboot_backend._controller.account;

import dmon.SSHOP_springboot_backend.dto.request.account.Seller_LoginRequest;
import dmon.SSHOP_springboot_backend.dto.response.account.Seller_AccessResponse;
import dmon.SSHOP_springboot_backend._service.account.Seller_IAccessService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("seller/api/v1/access")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Seller_AccessController {
    Seller_IAccessService accessService;

    //LOG IN/OUT//
    @PostMapping("/login")
    public ResponseEntity<Seller_AccessResponse> login(@RequestBody Seller_LoginRequest request) {
        return ResponseEntity
                .ok()
                .body(this.accessService.login(request));
    }

    @PostMapping("/logout")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Void> logout() {
        this.accessService.logout();
        return ResponseEntity
                .noContent()
                .build();
    }
}
