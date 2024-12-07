package dstu.trafficinspection.controller;

import dstu.trafficinspection.entity.Department;
import dstu.trafficinspection.entity.Driver;
import dstu.trafficinspection.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        return ResponseEntity.ok(departmentService.findAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id) {
        Optional<Department> optionalDriver = departmentService.findById(id);
        if (optionalDriver.isEmpty()) {
            return new ResponseEntity<>("Подразделение с id " + id + " не найдено",
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(optionalDriver.get());
    }
}
