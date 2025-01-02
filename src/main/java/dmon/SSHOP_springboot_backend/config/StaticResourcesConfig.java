package dmon.SSHOP_springboot_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourcesConfig implements WebMvcConfigurer {
    @Value("${sshop.media.uri}")
    private String mediaUri;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                // thêm 1 endpoint
                .addResourceHandler("/media/**")
                .addResourceLocations(mediaUri);
    }
}
