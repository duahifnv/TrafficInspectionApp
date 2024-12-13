package dstu.inspection.controller.admin;

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
    @PostMapping("/admin/{username}/{action}")
    public String deleteUser(@PathVariable String username,
                             @PathVariable String action) {
        if (action.equals("delete")){
            userService.deleteUser(username);
        }
        return "redirect:/admin";
    }
}
