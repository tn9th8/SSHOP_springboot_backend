package dmon.SSHOP_springboot_backend._controller.product;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class ProductController {
    @RestController
    @RequestMapping("/admin/api/v1/product")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Admin {
        //LIST//
        @GetMapping("/list")
        public ResponseEntity<String> findAll() {
            return ResponseEntity
                    .ok()
                    .body("The product for admin");
        }
    }

    @RestController
    @RequestMapping("/seller/api/v1/product")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Seller {
        //LIST//
        @GetMapping("/list")
        public ResponseEntity<String> findAll() {
            return ResponseEntity
                    .ok()
                    .body("The product for seller");
        }
    }

    @RestController
    @RequestMapping("/api/v1/product")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Buyer {
        //LIST//
        @GetMapping("/list")
        public ResponseEntity<String> findAll() {
            return ResponseEntity
                    .ok()
                    .body("The product on SSHOP");
        }
    }
}
