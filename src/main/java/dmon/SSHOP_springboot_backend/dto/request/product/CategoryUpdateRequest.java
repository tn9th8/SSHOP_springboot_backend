package dmon.SSHOP_springboot_backend.dto.request.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdateRequest {
    @Size(message = "CATEGORY__NAME_OUT_SIZE", min = 2, max = 40)
    String name;

    @Size(message = "CATEGORY__DESCRIPTION_OUT_SIZE", max = 255)
    String description;

    @Size(message = "CATEGORY__PHOTO_OUT_SIZE", max = 255)
    String photo;

    @Min(message = "CATEGORY__POSITION_MIN", value = 1)
    Integer position;
}
