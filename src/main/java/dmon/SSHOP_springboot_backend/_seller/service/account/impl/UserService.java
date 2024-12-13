package dmon.SSHOP_springboot_backend._seller.service.account.impl;

import dmon.SSHOP_springboot_backend._seller.dto.request.UserCreateRequest;
import dmon.SSHOP_springboot_backend._seller.dto.response.UserResponse;
import dmon.SSHOP_springboot_backend.entity.account.User;

import dmon.SSHOP_springboot_backend.mapper.account.Seller_IUserMapper;
import dmon.SSHOP_springboot_backend.repository.account.Seller_IAccountRepository;
import dmon.SSHOP_springboot_backend.repository.account.Seller_IUserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    Seller_IUserRepository userRepo;
    Seller_IAccountRepository accountRepo;
    Seller_IUserMapper userMapper;

    public UserResponse createOne(UserCreateRequest body) {
        //todo: refer to account
        User user = this.userRepo.save(
                this.userMapper.toEntity(body));
        return this.userMapper.toResponse(user);
    }
}
