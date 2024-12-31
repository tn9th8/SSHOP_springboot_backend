package dmon.SSHOP_springboot_backend._service.product;

import dmon.SSHOP_springboot_backend._repository.product.ICategoryProjection;
import dmon.SSHOP_springboot_backend.base.PageResponse;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryCreateRequest;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryUpdateRequest;
import dmon.SSHOP_springboot_backend.dto.response.product.CategoryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
    //CREATE//
    CategoryResponse create(CategoryCreateRequest cateDto);

    //UPDATE//
    CategoryResponse update(CategoryUpdateRequest cateDto, String cateId);

    //LIST//
    PageResponse<ICategoryProjection> findAll(Pageable pageable);

    List<ICategoryProjection> findTree();

    //FIND//
    CategoryResponse find(String cateId);

    //DELETE//
    Void delete(String cateId);
}
