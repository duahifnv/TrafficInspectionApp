package dstu.inspection.controller.inspect;

import dstu.inspection.entity.Department;
import dstu.inspection.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/department")
@RequiredArgsConstructor
public class InspectDepartmentController {
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
