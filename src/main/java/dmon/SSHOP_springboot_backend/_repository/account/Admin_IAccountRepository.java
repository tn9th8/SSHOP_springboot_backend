package dmon.SSHOP_springboot_backend._repository.account;

import dmon.SSHOP_springboot_backend.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Admin_IAccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByUsername(String s);
}
