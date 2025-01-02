package dmon.SSHOP_springboot_backend._controller.system;

import dmon.SSHOP_springboot_backend._service.system.IMediaService;
import dmon.SSHOP_springboot_backend.dto.response.system.UploadRes;
import dmon.SSHOP_springboot_backend.utils.annotation.ApiMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;

public class MediaController {
    @RestController
    @RequestMapping("/admin/api/v1/media")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Admin {
        IMediaService mediaService;

        //UPLOAD//
        @PostMapping("/upload")
        public ResponseEntity<UploadRes> upload(
                @RequestParam(name = "file", required = false) MultipartFile file,
                @RequestParam("folder") String folder
        ) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.mediaService.upload(file, folder));
        }

    }

    @RestController
    @RequestMapping("/seller/api/v1/media")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Seller {
        IMediaService mediaService;

        //UPLOAD//
        @PostMapping("/upload")
        public ResponseEntity<UploadRes> upload(
                @RequestParam(name = "file", required = false) MultipartFile file,
                @RequestParam("folder") String folder
        ) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.mediaService.upload(file, folder));
        }

    }

    @RestController
    @RequestMapping("/api/v1/media")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Buyer {
        IMediaService mediaService;

        //UPLOAD//
        @PostMapping("/upload")
        public ResponseEntity<UploadRes> upload(
                @RequestParam(name = "file", required = false) MultipartFile file,
                @RequestParam("folder") String folder
        ) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.mediaService.upload(file, folder));
        }
    }
}
