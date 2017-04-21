package softproject.components;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by pontu on 2017-04-21.
 */
@Component
class WebConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ext/**").addResourceLocations("C:/Users/pontu/Documents/Programming/Web/pontek/index.html");
    }

}