package dstu.inspection.controller;

import dstu.inspection.service.StaticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final StaticService staticService;
    @GetMapping
    public String landingPage() {
        return "landing";
    }
    @GetMapping("/categories")
    public String categoryList(Model model) {
        model.addAttribute("categories",
                staticService.findAllCategories());
        return "categories";
    }
    @GetMapping("/fines")
    public String fineList(Model model) {
        model.addAttribute("fines",
                staticService.findAllFines());
        return "fines";
    }
}
