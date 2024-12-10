package dstu.inspection.service;

import dstu.inspection.entity.Driver;
import dstu.inspection.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }
    public Optional<Driver> findById(Long id) {
        return driverRepository.findById(id);
    }
}
