package dmon.SSHOP_springboot_backend._service.system.impl;

import dmon.SSHOP_springboot_backend._service.system.IMediaService;
import dmon.SSHOP_springboot_backend.base.AppException;
import dmon.SSHOP_springboot_backend.base.ExceptionCode;
import dmon.SSHOP_springboot_backend.dto.response.system.UploadRes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class MediaServiceImpl implements IMediaService {
    @Value("${sshop.media.uri}")
    String mediaUri;

    final List<String> allowedExtensions = Arrays.asList("pdf", "jpg", "jpeg", "png", "doc", "docx");

    @Override
    public UploadRes upload(MultipartFile file, String folder) {
        //validate
        this.validateFile(file);
        //create a directory if not exist
        this.createDirectory(folder);
        //store file
        String fileName = this.storeFile(file, folder);

        return new UploadRes(fileName, Instant.now());
    }


    private void validateFile(MultipartFile file) {
        //check not empty
        if (file == null || file.isEmpty())
            throw new AppException(ExceptionCode.SYSTEM__FILE_EMPTY);

        //check in extensions
        boolean isInExt = allowedExtensions
                .stream()
                .anyMatch(e ->  file.getOriginalFilename().toLowerCase().endsWith("." + e));
        if (!isInExt)
            throw new AppException(ExceptionCode.SYSTEM__FILE_OUT_EXTENSIONS);
        //todo: check other cases more
    }


    private void createDirectory(String folder) {
        //create a folder Uri (a link to folder which has the protocol "file:///"
        URI uri;
        try {
            uri = new URI(mediaUri + "/" + folder);
        } catch (URISyntaxException e) {
            throw new AppException(ExceptionCode.SYSTEM__FILE_CREATE_FOLDER);
        }
        //create a folder Path (use uri)
        Path path = Paths.get(uri);
        //create Directory
        File tempDir = new File(path.toString());
        if (!tempDir.isDirectory()) {
            try {
                Files.createDirectory(tempDir.toPath());
                log.info("SSHOP MEDIA: CREATE NEW DIRECTORY SUCCESSFULLY, PATH = " + tempDir.toPath());
            } catch (IOException e) {
                throw new AppException(ExceptionCode.SYSTEM__FILE_CREATE_DIRECTORY);
            }
        } else
            log.info("SSHOP MEDIA: THE DIRECTORY ALREADY EXISTS");
    }

    private String storeFile(MultipartFile file, String folder) {
        //create a filename
        String fileName = System.currentTimeMillis()
                + "-"
                + file.getOriginalFilename()
                    .toLowerCase()
                    .replace(" ", "-")
                    .replace("_", "-")
                    .replaceAll("[^a-zA-Z0-9-.]", "x") //not az AZ 09 -
                    .replaceAll("[^\\p{ASCII}]", "x") //not ASCII

                ;
        //create a file uri/path
        URI uri;
        try {
            uri = new URI(mediaUri + "/" + folder + "/" + fileName);
        } catch (URISyntaxException e) {
            throw new AppException(ExceptionCode.SYSTEM__FILE_CREATE_PATH);
        }
        final Path path = Paths.get(uri);
        //stream the file
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new AppException(ExceptionCode.SYSTEM__FILE_STREAM);
        }
        return fileName;

    }
}
