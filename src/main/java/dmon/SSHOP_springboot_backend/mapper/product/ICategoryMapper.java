package dmon.SSHOP_springboot_backend.mapper.product;

import dmon.SSHOP_springboot_backend.dto.request.product.CategoryCreateRequest;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryUpdateRequest;
import dmon.SSHOP_springboot_backend.dto.response.product.CategoryResponse;
import dmon.SSHOP_springboot_backend.entity.product.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {
    Category toEntity(CategoryCreateRequest payload);
    Category toEntity(CategoryUpdateRequest payload);
    CategoryResponse toResponse(Category category);
}
