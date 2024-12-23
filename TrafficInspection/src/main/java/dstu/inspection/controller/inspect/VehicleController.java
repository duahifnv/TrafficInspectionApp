package dstu.inspection.controller.inspect;

import dstu.inspection.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inspect/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;
    @GetMapping()
    public String vehiclesPage(Model model) {
        model.addAttribute("vehicles", vehicleService.findAllVehiclesInfo());
        return "pages/employee/all_vehicles";
    }
}
