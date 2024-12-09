package dmon.SSHOP_springboot_backend.controller;

import dmon.SSHOP_springboot_backend.dto.request.AccountCreateRequest;
import dmon.SSHOP_springboot_backend.dto.response.AccountResponse;
import dmon.SSHOP_springboot_backend.entity.Account;
import dmon.SSHOP_springboot_backend.dto.response.UserResponse;
import dmon.SSHOP_springboot_backend.service.AccountService;
import dmon.SSHOP_springboot_backend.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    @ApiMessage("create an account")
    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createOne(@Valid @RequestBody AccountCreateRequest body){
        return ResponseEntity
                .ok()
                .body(this.accountService.createOne(body));
    }

//    @ApiMessage("update an account")
//    @PatchMapping("/update")
//    public ResponseEntity<ResUpdatedAccountDto> updateOne(@RequestBody Account body) {
//        return ResponseEntity
//                .ok()
//                .body(null);
//    }
//
//    @GetMapping("/list")
//    @ApiMessage("list all accounts")
//    public ResponseEntity<ArrayList<ResAccountDto>> listAll() {
//        return ResponseEntity
//                .ok()
//                .body(null);
//    }
//
//    @GetMapping("/{id}")
//    @ApiMessage("get an account")
//    public ResponseEntity<ResAccountDto> getOne(@PathVariable("id") String sId)  {
//        return ResponseEntity
//                .ok()
//                .body(null);
//    }

    @DeleteMapping("/{id}")
    @ApiMessage("delete an account")
    public ResponseEntity<Void> deleteOne(@PathVariable("id") String sId) {
        return ResponseEntity
                .noContent()
                .build();
    }
}
