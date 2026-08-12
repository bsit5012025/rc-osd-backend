package org.rocs.osdrmsa.controller.guardian;

import lombok.RequiredArgsConstructor;
import org.rocs.osdrmsa.domain.person.guardian.Guardian;
import org.rocs.osdrmsa.service.guardian.GuardianService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/guardians")
@RequiredArgsConstructor
public class GuardianController {

    private final GuardianService guardianService;

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PREFECT', 'STAFF') "
            + "or (hasRole('USER') and @access.isSelfStudent(#studentId))")
    public ResponseEntity<List<Guardian>> getByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(guardianService.getByStudentId(studentId));
    }
}
