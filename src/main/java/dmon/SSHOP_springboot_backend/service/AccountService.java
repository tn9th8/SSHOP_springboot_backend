package dmon.SSHOP_springboot_backend.service;

import dmon.SSHOP_springboot_backend.dto.request.AccountCreateRequest;
import dmon.SSHOP_springboot_backend.dto.response.AccountResponse;
import dmon.SSHOP_springboot_backend.entity.Account;
import dmon.SSHOP_springboot_backend.mapper.IAccountMapper;
import dmon.SSHOP_springboot_backend.repository.IAccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    IAccountRepository accountRepo;
    IAccountMapper accountMapper;

    public AccountResponse createOne(AccountCreateRequest body) {
        //todo: check that email, phone should be unique
        //todo: hash password
        Account account = this.accountRepo.save(this.accountMapper.toEntity(body));
        return this.accountMapper.toResponse(account);
    }

    public List<Account> listAll() {
        List<Account> accounts;
        accounts = this.accountRepo.findAll();
        return accounts;
    }
}
