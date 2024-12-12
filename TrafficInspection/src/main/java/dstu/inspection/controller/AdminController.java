package dstu.inspection.controller;

import dstu.inspection.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "admin";
    }
    @PostMapping("/admin/{phone}/{action}")
    public String deleteUser(@PathVariable String phone,
                             @PathVariable String action) {
        if (action.equals("delete")){
            userService.deleteUser(phone);
        }
        return "redirect:/admin";
    }
}
