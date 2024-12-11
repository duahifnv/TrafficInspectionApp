package dstu.inspection.service;

import dstu.inspection.entity.info.LicensesInfo;
import dstu.inspection.repository.info.LicensesInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicensesInfoService {
    private final LicensesInfoRepository licensesInfoRepository;
    public List<LicensesInfo> findAll() {
        return licensesInfoRepository.findAll();
    }
    public LicensesInfo findByPhone(String phone) {
        return licensesInfoRepository.findByPhone(phone);
    }
}
