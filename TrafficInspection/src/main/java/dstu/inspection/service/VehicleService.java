package dstu.inspection.service;

import dstu.inspection.entity.info.*;
import dstu.inspection.repository.info.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehiclesInfoRepository vehiclesInfoRepository;
    public List<VehiclesInfo> findAllVehiclesInfo() {
        return vehiclesInfoRepository.findAll();
    }
    public List<VehiclesInfo> findAllVehiclesInfoByUsername(String username) {
        return vehiclesInfoRepository.findByUsername(username);
    }
    public VehiclesInfo findVehicleInfoByRegistrationCode(String registrationCode) {
        return vehiclesInfoRepository.findByRegistrationCode(registrationCode);
    }
}
