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
    @GetMapping("/support")
    public String support() {
        return "support";
    }
    @GetMapping("/staff")
    public String staff() {
        return "staff";
    }
    @GetMapping("/testimonial")
    public String testimonial() {
        return "testimonial";
    }



    @GetMapping("/about-a")
    public String aboutA() {
        return "admin/about-a";
    }
    @GetMapping("/index-a")
    public String indexA() {
        return "admin/index-a";
    }
    @GetMapping("/shop-a")
    public String shopA() {
        return "admin/shop-a";
    }
    @GetMapping("/service-a")
    public String serviceA() {
        return "admin/service-a";
    }
    @GetMapping("/wash-a")
    public String washA() {
        return "admin/wash-a";
    }
    @GetMapping("/contact-a")
    public String contactA() {
        return "admin/contact-a";
    }
    @GetMapping("/staff-a")
    public String staffA() {
        return "admin/staff-a";
    }
    @GetMapping("/testimonial-a")
    public String testimonialA() {
        return "admin/testimonial-a";
    }




    @GetMapping("/about-c")
    public String aboutC() {
        return "client/about-c";
    }
    @GetMapping("/index-c")
    public String indexC() {
        return "client/index-c";
    }
    @GetMapping("/shop-c")
    public String shopC() {
        return "client/shop-c";
    }
    @GetMapping("/service-c")
    public String serviceC() {
        return "client/service-c";
    }
    @GetMapping("/wash-c")
    public String washC() {
        return "client/wash-c";
    }
    @GetMapping("/contact-c")
    public String contactC() {
        return "client/contact-c";
    }
    @GetMapping("/staff-c")
    public String staffC() {
        return "client/staff-c";
    }
    @GetMapping("/testimonial-c")
    public String testimonialC() {
        return "client/testimonial-c";
    }
}

