package org.rocs.osdrmsa.service.appeal.impl;

import org.rocs.osdrmsa.domain.appeal.Appeal;
import org.rocs.osdrmsa.repository.appeal.AppealRepository;
import org.rocs.osdrmsa.service.appeal.AppealService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppealServiceImpl implements AppealService {

    private final AppealRepository appealRepository;

    public AppealServiceImpl(AppealRepository appealRepository) {
        this.appealRepository = appealRepository;
    }

    @Override
    public List<Appeal> getAppealsByStatus(String status) {
        return appealRepository.findByStatus(status);
    }

    @Override
    public void approveAppeal(Long appealId, String remarks) {
        Appeal appeal = appealRepository.findById(appealId).orElseThrow(() -> new RuntimeException("Appeal not found."));

        appeal.setStatus("APPROVED");
        appeal.setRemarks(remarks);
        appeal.setDateProcessed(LocalDate.now());

        appealRepository.save(appeal);
    }

    @Override
    public void denyAppeal(Long appealId, String remarks) {
        if (remarks == null || remarks.trim().isEmpty()) {
            throw new IllegalArgumentException("Denial remarks are required.");
        }

        Appeal appeal = appealRepository.findById(appealId).orElseThrow(() -> new RuntimeException("Appeal not found."));

        appeal.setStatus("DENIED");
        appeal.setRemarks(remarks);
        appeal.setDateProcessed(LocalDate.now());

        appealRepository.save(appeal);
    }
}
