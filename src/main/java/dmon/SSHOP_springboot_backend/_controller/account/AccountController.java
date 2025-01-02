package dmon.SSHOP_springboot_backend._controller.account;

import dmon.SSHOP_springboot_backend.dto.request.account.AccountCreateRequest;
import dmon.SSHOP_springboot_backend.dto.request.account.AccountUpdateRequest;
import dmon.SSHOP_springboot_backend.dto.response.account.AccountResponse;
import dmon.SSHOP_springboot_backend.entity.account.Account;
import dmon.SSHOP_springboot_backend._service.account.impl.AccountService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    //CREATE//
    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createOne(@Valid @RequestBody AccountCreateRequest body){
        return ResponseEntity
                .ok()
                .body(this.accountService.createOne(body));
    }

    //UPDATE//
    @PatchMapping("/update/{accountId}")
    public ResponseEntity<AccountResponse> updateOne(@PathVariable String accountId, @RequestBody AccountUpdateRequest body) {
        return ResponseEntity
                .ok()
                .body(this.accountService.updateOne(accountId, body));
    }

    //LIST ALL//
    @GetMapping("/list")
    public ResponseEntity<List<Account>> listAll() {
        return ResponseEntity
                .ok()
                .body(this.accountService.listAll());
    }

    //FIND ONE//
    @GetMapping("/find/{accountId}")
    public ResponseEntity<AccountResponse> findOne(@PathVariable String accountId)  {
        return ResponseEntity
                .ok()
                .body(this.accountService.findOne(accountId));
    }

    @GetMapping("/my")
    public ResponseEntity<AccountResponse> findMyOne()  {
        return ResponseEntity
                .ok()
                .body(this.accountService.findMyOne());
    }

    //DELETE//
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable("id") String accountId) {
        this.accountService.deleteOne(accountId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
