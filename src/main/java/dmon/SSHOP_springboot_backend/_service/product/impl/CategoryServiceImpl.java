package dmon.SSHOP_springboot_backend._service.product.impl;

import dmon.SSHOP_springboot_backend._repository.product.ICategoryProjection;
import dmon.SSHOP_springboot_backend._repository.product.ICategoryRepository;
import dmon.SSHOP_springboot_backend._service.product.ICategoryService;
import dmon.SSHOP_springboot_backend.base.AppException;
import dmon.SSHOP_springboot_backend.base.ExceptionCode;
import dmon.SSHOP_springboot_backend.base.PageResponse;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryCreateRequest;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryUpdateRequest;
import dmon.SSHOP_springboot_backend.dto.response.product.CategoryResponse;
import dmon.SSHOP_springboot_backend.entity.product.Category;
import dmon.SSHOP_springboot_backend.mapper.product.ICategoryMapper;
import dmon.SSHOP_springboot_backend.utils.SystemUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryServiceImpl implements ICategoryService {
    ICategoryRepository cateRepo;

    ICategoryMapper cateMapper;

    //CREATE//
    @Override
    public CategoryResponse create(CategoryCreateRequest payload) {
        if (this.cateRepo.findFirstByName(payload.getName()).isPresent())
            throw new AppException(ExceptionCode.CATEGORY__NAME_UNIQUE);

        Category category = this.cateRepo.save(this.cateMapper.toEntity(payload));
        return this.cateMapper.toResponse(category);
    }

    //UPDATE//
    @Override
    public CategoryResponse update(CategoryUpdateRequest cateDto, String cateId) {
        if (this.cateRepo.findFirstByNameAndIdNot(cateDto.getName(), cateId).isPresent())
            throw new AppException(ExceptionCode.CATEGORY__NAME_UNIQUE);

        Category cateRequested = this.cateMapper.toEntity(cateDto);
        cateRequested.setId(cateId);

        Category cateUpdated = this.cateRepo.findById(cateId)
                .orElseThrow(()-> new AppException(ExceptionCode.CATEGORY__NOT_FOUND));

        SystemUtils.ignoreNull(cateUpdated, cateRequested);

        Category category = this.cateRepo.save(cateUpdated);
        return this.cateMapper.toResponse(category);
    }

    //LIST//
    @Override
    public PageResponse<ICategoryProjection> findAll(Pageable pageable) {
        Page<ICategoryProjection> catePage = this.cateRepo.findAllProjectedBy(pageable);
        return SystemUtils.toPageResponse(catePage);
    }

    @Override
    public List<ICategoryProjection> findTree() {
        Sort sort = Sort.by(Sort.Direction.ASC, "position");
        List<ICategoryProjection> cateList = this.cateRepo.findAllProjectedBy(sort);
        return cateList;
    }

    //FIND//
    @Override
    public CategoryResponse find(String cateId) {
        Category cateFound = this.cateRepo.findById(cateId)
                .orElseThrow(()-> new AppException(ExceptionCode.CATEGORY__NOT_FOUND));

        return this.cateMapper.toResponse(cateFound);
    }

    //DELETE//
    @Override
    public Void delete(String cateId) {
        this.cateRepo.deleteById(cateId);
        return null;
    }

}
