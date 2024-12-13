package dmon.SSHOP_springboot_backend.mapper.account;

import dmon.SSHOP_springboot_backend._seller.dto.request.AccountCreateRequest;
import dmon.SSHOP_springboot_backend._seller.dto.request.AccountUpdateRequest;
import dmon.SSHOP_springboot_backend._seller.dto.response.AccountResponse;
import dmon.SSHOP_springboot_backend.entity.account.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Seller_IAccountMapper {
    Account toEntity(AccountCreateRequest body);
    Account toEntity(AccountUpdateRequest body);
    AccountResponse toResponse(Account entity);
}
