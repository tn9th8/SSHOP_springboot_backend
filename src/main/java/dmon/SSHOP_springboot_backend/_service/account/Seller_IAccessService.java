package dmon.SSHOP_springboot_backend._service.account;


import dmon.SSHOP_springboot_backend.dto.request.account.Seller_LoginRequest;
import dmon.SSHOP_springboot_backend.dto.response.account.Seller_AccessResponse;
import org.springframework.stereotype.Service;

@Service
public interface Seller_IAccessService {
    public Seller_AccessResponse login(Seller_LoginRequest request);
    public void logout();
}
