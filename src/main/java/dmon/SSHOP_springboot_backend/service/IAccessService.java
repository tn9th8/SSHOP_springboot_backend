package dmon.SSHOP_springboot_backend.service;

import dmon.SSHOP_springboot_backend.dto.request.AccessRequest;
import dmon.SSHOP_springboot_backend.dto.response.AccessResponse;
import dmon.SSHOP_springboot_backend.entity.Account;
import org.springframework.stereotype.Service;

@Service
public interface IAccessService {
    public AccessResponse authenticate(AccessRequest request);
}
