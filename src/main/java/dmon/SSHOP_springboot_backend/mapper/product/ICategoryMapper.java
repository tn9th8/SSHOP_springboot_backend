package dmon.SSHOP_springboot_backend.mapper.product;

import dmon.SSHOP_springboot_backend.dto.request.product.CategoryCreateReq;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryUpdateReq;
import dmon.SSHOP_springboot_backend.dto.response.product.CategoryRes;
import dmon.SSHOP_springboot_backend.entity.product.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {
    Category toEntity(CategoryCreateReq payload);
    Category toEntity(CategoryUpdateReq payload);
    CategoryRes toResponse(Category category);
}
