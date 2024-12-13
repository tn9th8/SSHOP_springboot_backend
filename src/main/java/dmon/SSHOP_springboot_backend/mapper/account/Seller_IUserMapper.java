package dmon.SSHOP_springboot_backend.mapper.account;

import dmon.SSHOP_springboot_backend._seller.dto.request.UserCreateRequest;
import dmon.SSHOP_springboot_backend._seller.dto.response.UserResponse;
import dmon.SSHOP_springboot_backend.entity.account.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Seller_IUserMapper {
    User toEntity(UserCreateRequest request);
    UserResponse toResponse(User model);
}
