package gersgarage2.GersGarage2.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index"; // "index" refers to index.html in src/main/resources/templates
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }
    @GetMapping("/service")
    public String service() {
        return "service";
    }
    @GetMapping("/wash")
    public String wash() {
        return "wash";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    @GetMapping("/staff")
    public String staff() {
        return "staff";
    }
    @GetMapping("/testimonial")
    public String testimonial() {
        return "testimonial";
    }

}
