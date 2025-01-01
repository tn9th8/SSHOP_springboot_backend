package dmon.SSHOP_springboot_backend._service.account.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import dmon.SSHOP_springboot_backend.dto.request.account.Admin_LoginRequest;
import dmon.SSHOP_springboot_backend.dto.response.account.Admin_AccessResponse;
import dmon.SSHOP_springboot_backend._repository.account.Admin_IAccountRepository;
import dmon.SSHOP_springboot_backend._service.account.Admin_IAccessService;
import dmon.SSHOP_springboot_backend.base.AppException;
import dmon.SSHOP_springboot_backend.base.ExceptionCode;
import dmon.SSHOP_springboot_backend.entity.account.Account;
import dmon.SSHOP_springboot_backend.enums.RoleEnum;
import dmon.SSHOP_springboot_backend.security.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class Admin_AccessService implements Admin_IAccessService {
    Admin_IAccountRepository accountRepo;

    @NonFinal
    @Value("${jwt.secretKey}")
    protected  String SECRET_KEY;

    //IMPLEMENT//
    @Override
    public Admin_AccessResponse login(Admin_LoginRequest request) {
        Account account = this.accountRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ExceptionCode.SECURITY__LOGIN_FAILED));

        if (!account.getRoles().contains(RoleEnum.ADMIN.toString()))
            throw new AppException(ExceptionCode.SECURITY__LOGIN_FAILED);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (!passwordEncoder.matches(request.getPassword(), account.getPassword()))
            throw new AppException(ExceptionCode.SECURITY__LOGIN_FAILED);

        var token = generateToken(account);

        return Admin_AccessResponse
                .builder()
                .authenticated(true)
                .token(token)
                .build();
    }

    @Override
    public void logout() {
        String accountId = SecurityUtil.getCurrentAccountLogin()
                .orElseThrow(() -> new AppException(ExceptionCode.SYSTEM__UNHANDLED_EXCEPTION));
        System.out.println(accountId);
        this.accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ExceptionCode.ACCOUNT__NOT_FOUND));
    }

    //HELPER//
    private String generateToken(Account account) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getId())
                .issuer("sshop.dmon.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(400, ChronoUnit.DAYS).toEpochMilli()
                ))
                .claim("scope", buildScope(account.getRoles()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(Set<String> roles) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(roles))
            roles.forEach(stringJoiner::add);

        return stringJoiner.toString();
    }
}
