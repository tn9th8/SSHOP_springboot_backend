package dmon.SSHOP_springboot_backend._controller.product;

import dmon.SSHOP_springboot_backend._repository.product.ICategoryProjection;
import dmon.SSHOP_springboot_backend._service.product.ICategoryService;
import dmon.SSHOP_springboot_backend.base.PageRes;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryCreateReq;
import dmon.SSHOP_springboot_backend.dto.request.product.CategoryUpdateReq;
import dmon.SSHOP_springboot_backend.dto.response.product.CategoryRes;
import dmon.SSHOP_springboot_backend.utils.SystemUtils;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CategoryController {
    @RestController
    @RequestMapping("/admin/api/v1/category")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Admin {
        ICategoryService cateService;

        //CREATE//
        @PostMapping("/create")
        public ResponseEntity<CategoryRes> create(
                @RequestBody @Valid CategoryCreateReq cateDto)
        {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(cateService.create(cateDto));
        }

        //UPDATE//
        @PatchMapping("/update/{cateId}")
        public ResponseEntity<CategoryRes> update(
                @RequestBody @Valid CategoryUpdateReq cateDto,
                @PathVariable String cateId)
        {
            return  ResponseEntity
                    .ok()
                    .body(this.cateService.update(cateDto, cateId));
        }

        //LIST//
        @GetMapping("/list")
        public ResponseEntity<PageRes<ICategoryProjection>> findAll(
                @RequestParam(defaultValue = "1") int page,
                @RequestParam(defaultValue = "4") int size,
                @RequestParam(defaultValue = "position") String sort,
                @RequestParam(defaultValue = "asc") String direct
        ) {
            Pageable pageable = SystemUtils.pageableOf(page, size, sort, direct); //todo: use 1 RequestParam
            return ResponseEntity
                    .ok()
                    .body(this.cateService.findAll(pageable));
        }

        //FIND//
        @GetMapping("/find/{cateId}")
        public ResponseEntity<CategoryRes> find(
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

    @RestController
    @RequestMapping("/seller/api/v1/category")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Seller {
        ICategoryService cateService;

        //LIST//
        @GetMapping("/tree")
        public ResponseEntity<List<ICategoryProjection>> findTree() {
            return ResponseEntity
                    .ok()
                    .body(this.cateService.findTree());
        }
    }

    @RestController
    @RequestMapping("/api/v1/category")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Buyer {
        ICategoryService cateService;

        //LIST//
        @GetMapping("/tree")
        public ResponseEntity<List<ICategoryProjection>> findTree() {
            return ResponseEntity
                    .ok()
                    .body(this.cateService.findTree());
        }
    }
}
