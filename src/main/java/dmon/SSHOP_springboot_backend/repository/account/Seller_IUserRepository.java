package dmon.SSHOP_springboot_backend.repository.account;

import dmon.SSHOP_springboot_backend.entity.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Seller_IUserRepository extends JpaRepository<User, String> {

}
