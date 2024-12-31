package dmon.SSHOP_springboot_backend._controller.product;

import dmon.SSHOP_springboot_backend._repository.product.ICategoryProjection;
import dmon.SSHOP_springboot_backend._service.product.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController_Buyer {
    ICategoryService cateService;

    //LIST//
    @GetMapping("/tree")
    public ResponseEntity<List<ICategoryProjection>> findTree() {
        return ResponseEntity
                .ok()
                .body(this.cateService.findTree());
    }

}
