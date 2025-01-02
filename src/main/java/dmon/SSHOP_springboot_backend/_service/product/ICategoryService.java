package dmon.SSHOP_springboot_backend._service.product;

import dmon.SSHOP_springboot_backend._repository.product.ICategoryProjection;
import dmon.SSHOP_springboot_backend.base.PageRes;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryCreateReq;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryUpdateReq;
import dmon.SSHOP_springboot_backend.dto.response.product.CategoryRes;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
    //CREATE//
    CategoryRes create(CategoryCreateReq cateDto);

    //UPDATE//
    CategoryRes update(CategoryUpdateReq cateDto, String cateId);

    //LIST//
    PageRes<ICategoryProjection> findAll(Pageable pageable);

    List<ICategoryProjection> findTree();

    //FIND//
    CategoryRes find(String cateId);

    //DELETE//
    Void delete(String cateId);
}
