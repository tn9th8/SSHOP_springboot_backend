package dmon.SSHOP_springboot_backend._service.account.impl;

import dmon.SSHOP_springboot_backend.dto.request.account.AccountCreateRequest;
import dmon.SSHOP_springboot_backend.dto.request.account.AccountUpdateRequest;
import dmon.SSHOP_springboot_backend.dto.response.account.AccountResponse;
import dmon.SSHOP_springboot_backend.entity.account.Account;
import dmon.SSHOP_springboot_backend.base.AppException;
import dmon.SSHOP_springboot_backend.base.ExceptionCode;

import dmon.SSHOP_springboot_backend.mapper.account.Seller_IAccountMapper;
import dmon.SSHOP_springboot_backend._repository.account.Seller_IAccountRepository;
import dmon.SSHOP_springboot_backend.enums.RoleEnum;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    Seller_IAccountRepository accountRepo;
    Seller_IAccountMapper accountMapper;
    PasswordEncoder passwordEncoder;

    //CREATE//
    public AccountResponse createOne(AccountCreateRequest request) {
        //check that email, phone should be not existed
        if (this.accountRepo.existsByUsername(request.getEmail()))
            throw new AppException(ExceptionCode.ACCOUNT__USERNAME_UNIQUE);
        if (this.accountRepo.existsByEmail(request.getEmail()))
            throw new AppException(ExceptionCode.ACCOUNT__EMAIL_UNIQUE);
        if (this.accountRepo.existsByPhone(request.getPhone()))
            throw new AppException(ExceptionCode.ACCOUNT__PHONE_UNIQUE);

        Account account = this.accountMapper.toEntity(request);

        //hash password
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        //assign the user role
        HashSet<String> roles = new HashSet<>();
        roles.add(RoleEnum.USER.name());
        account.setRoles(roles);

        return this.accountMapper.toResponse(this.accountRepo.save(account));
    }

    //UPDATE//
    public AccountResponse updateOne(String accountId, AccountUpdateRequest body) {
        //check
        this.findOne(accountId);
        Account account = this.accountMapper.toEntity(body);
        account.setId(accountId);
        account = this.accountRepo.save(account);
        return this.accountMapper.toResponse(account);
    }

    //DELETE//
    public void deleteOne(String accountId){
        accountRepo.deleteById(accountId);
    }

    //LIST ALL//
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> listAll() {
        List<Account> accounts;
        accounts = this.accountRepo.findAll();
        return accounts;
    }

    //FIND ONE//
    @PostAuthorize("returnObject.accountId == authentication.name")
    public AccountResponse findOne(String accountId) {
        Account account = this.accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ExceptionCode.ACCOUNT__NOT_FOUND));
        return this.accountMapper.toResponse(account);
    }

    public AccountResponse findMyOne() {
        var context = SecurityContextHolder.getContext();
        String accountId = context.getAuthentication().getName();

        return this.findOne(accountId);
    }
}
