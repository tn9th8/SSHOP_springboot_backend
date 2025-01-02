package dmon.SSHOP_springboot_backend._service.system;

import dmon.SSHOP_springboot_backend.dto.response.system.UploadRes;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IMediaService {
    //UPLOAD//
    UploadRes upload(MultipartFile file, String folder);
}
