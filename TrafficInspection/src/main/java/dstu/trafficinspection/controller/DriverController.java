package dstu.trafficinspection.controller;

import dstu.trafficinspection.entity.Driver;
import dstu.trafficinspection.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {
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
}
