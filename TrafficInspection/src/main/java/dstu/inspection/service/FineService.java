package dstu.inspection.service;

import dstu.inspection.entity.info.FinesInfo;
import dstu.inspection.repository.info.FinesInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FineService {
    private final FinesInfoRepository finesInfoRepository;
    public List<FinesInfo> findAllFinesInfo() {
        return finesInfoRepository.findAll();
    }
}
