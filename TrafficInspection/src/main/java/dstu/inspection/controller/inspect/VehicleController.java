package dstu.inspection.controller.inspect;

import dstu.inspection.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inspect/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final InfoService infoService;
    @GetMapping()
    public String vehiclesPage(Model model) {
        model.addAttribute("vehicles", infoService.findAllVehicles());
        return "pages/employee/all_vehicles";
    }
}
