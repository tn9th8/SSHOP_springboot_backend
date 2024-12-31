package dmon.SSHOP_springboot_backend._controller;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class NestedController {

    @RestController
    @RequestMapping("/test/admin")
    public static class Admin {
        @GetMapping("/hello")
        public ResponseEntity<String> hello() {
            return ResponseEntity.ok().body("Hello The nested tester for Admin subsystem");
        }

    }

    @RestController
    @RequestMapping("/test/seller")
    public static class Seller {
        @GetMapping("/hello")
        public ResponseEntity<String> hello() {
            return ResponseEntity.ok().body("Hello The nested tester for Seller subsystem");
        }

    }

    @RestController
    @RequestMapping("/test")
    public static class Buyer {
        @GetMapping("/hello")
        public ResponseEntity<String> hello() {
            return ResponseEntity.ok().body("Hello The nested tester for SSHOP subsystem");
        }

    }


}
