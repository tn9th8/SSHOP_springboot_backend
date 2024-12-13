package dmon.SSHOP_springboot_backend.service.account;


import dmon.SSHOP_springboot_backend.dto.request.account.Admin_LoginRequest;
import dmon.SSHOP_springboot_backend.dto.response.account.Admin_AccessResponse;
import org.springframework.stereotype.Service;

@Service
public interface Admin_IAccessService {
    public Admin_AccessResponse login(Admin_LoginRequest request);
    public void logout();
}
