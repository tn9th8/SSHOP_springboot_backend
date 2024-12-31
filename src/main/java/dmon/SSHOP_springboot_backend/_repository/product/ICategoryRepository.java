package dmon.SSHOP_springboot_backend._repository.product;

import dmon.SSHOP_springboot_backend.entity.product.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, String>, JpaSpecificationExecutor<Category> {
    //LIST//
    Page<ICategoryProjection> findAllProjectedBy(Pageable pageable); //or findPagedProjectedBy(Pageable pageable) are right
    List<ICategoryProjection> findAllProjectedBy(Sort sort); //or findAllProjectedByOrderByPositionAsc() are right

    //FIND//
    Optional<Category> findFirstByName(String name);
    Optional<Category> findFirstByNameAndIdNot(String name, String cateId);
}
