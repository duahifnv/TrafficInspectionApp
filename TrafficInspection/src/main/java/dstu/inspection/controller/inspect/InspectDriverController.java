package dstu.inspection.controller.inspect;

import dstu.inspection.entity.Driver;
import dstu.inspection.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/inspect/driver")
@RequiredArgsConstructor
public class InspectDriverController {
    private final DriverService driverService;
    @GetMapping()
    public ResponseEntity<?> getAllDrivers() {
        return ResponseEntity.ok(driverService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable Long id) {
        Optional<Driver> optionalDriver = driverService.findById(id);
        if (optionalDriver.isEmpty()) {
            return new ResponseEntity<>("Пользователь с id " + id + " не найден",
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(optionalDriver.get());
    }
    @GetMapping("/{id}/profile")
    public String getDriverProfile(@PathVariable Long id, Model model) {
        Optional<Driver> optionalDriver = driverService.findById(id);
        optionalDriver.ifPresent(driver -> model.addAttribute("driver", driver));
        return "profile_old";
    }
}
