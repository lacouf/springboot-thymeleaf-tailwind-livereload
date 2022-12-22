package jyad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
public class TailwindSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TailwindSpringBootApplication.class, args);
    }

    @Bean
    public ITemplateResolver svgTemplateResolver() {
        SpringResourceTemplateResolver resolver = new
                SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/svg/");
        resolver.setSuffix(".svg");
        resolver.setTemplateMode("XML");
        return resolver;
    }
}




@Controller
class MainController {

    @GetMapping
    public String index() {
        return "index";
    }
}