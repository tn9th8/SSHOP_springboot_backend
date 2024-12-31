package dmon.SSHOP_springboot_backend._controller.product;

import dmon.SSHOP_springboot_backend._repository.product.ICategoryProjection;
import dmon.SSHOP_springboot_backend._service.product.ICategoryService;
import dmon.SSHOP_springboot_backend.base.PageResponse;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryCreateRequest;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryUpdateRequest;
import dmon.SSHOP_springboot_backend.dto.response.product.CategoryResponse;
import dmon.SSHOP_springboot_backend.utils.AppUtils;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController_Admin {
    ICategoryService cateService;

    //CREATE//
    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> create(
            @RequestBody @Valid CategoryCreateRequest cateDto)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cateService.create(cateDto));
    }

    //UPDATE//
    @PatchMapping("/update/{cateId}")
    public ResponseEntity<CategoryResponse> update(
            @RequestBody @Valid CategoryUpdateRequest cateDto,
            @PathVariable String cateId)
    {
        return  ResponseEntity
                .ok()
                .body(this.cateService.update(cateDto, cateId));
    }

    //LIST//
    @GetMapping("/list")
    public ResponseEntity<PageResponse<ICategoryProjection>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "position") String sort,
            @RequestParam(defaultValue = "asc") String direct
    ) {
        Pageable pageable = AppUtils.pageableOf(page, size, sort, direct); //todo: use 1 RequestParam
        return ResponseEntity
                .ok()
                .body(this.cateService.findAll(pageable));
    }

    //FIND//
    @GetMapping("/find/{cateId}")
    public ResponseEntity<CategoryResponse> find(
            @PathVariable String cateId
    ) {
        return ResponseEntity
                .ok()
                .body(this.cateService.find(cateId));
    }

    //DELETE//
    @DeleteMapping("/delete/{cateId}")
    public ResponseEntity<Void> delete(
            @PathVariable String cateId
    ) {
        return ResponseEntity
                .ok()
                .body(this.cateService.delete(cateId));
    }
}
