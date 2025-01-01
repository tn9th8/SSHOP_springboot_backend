package dmon.SSHOP_springboot_backend.utils;

import dmon.SSHOP_springboot_backend.base.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.Arrays;

public class SystemUtils {
    /**
     * Map PageResponse from Page of Jpa
     *
     * @param pageJpa: Page of Jpa
     * @return PageResponse
     */
    public static <T> PageResponse<T> toPageResponse(Page<T> pageJpa) {
        return PageResponse.<T>builder()
                .totalPages(pageJpa.getTotalPages())
                .totalElements(pageJpa.getTotalElements())
                .page(pageJpa.getNumber() + 1) //Page of client starts 1. But PageNumber of Jpa starts from 0
                .size(pageJpa.getSize())
                .content(pageJpa.getContent())
                .build();
    }

    /**
     * Build Pageable from page, sort and direct
     *
     * @param page: int
     * @param size: int
     * @param sort: sort on attribute
     * @param direct: direct on asc or desc
     * @return Pageable
     */
    public static Pageable pageableOf(int page, int size, String sort, String direct) {
        Sort.Direction direction = direct.equalsIgnoreCase("ASC")
                ? Sort.Direction.ASC : Sort.Direction.DESC;

        return PageRequest.of(--page, size, direction, sort); //Page of client starts 1. But PageNumber of Jpa starts from 0
    }

    /**
     * Map source to target with ignoring null value.
     *
     * @param target   Entity will be updated
     * @param source   Entity is requested
     * @throws IllegalAccessException when can not access any fields
     */
    public static <T> void ignoreNull(T target, T source) {
        //Get fields (attributes) of source or target
        Field[] fields = source.getClass().getDeclaredFields();

        //Iterate through fields
        Arrays.stream(fields)
                .peek(field -> field.setAccessible(true))  //Allow to access the private fields
                .filter(field -> {
                    try {
                        return field.get(source) != null; //Select non-null fields
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(field -> {
                    try {
                        field.set(target, field.get(source)); //Map source to target
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
