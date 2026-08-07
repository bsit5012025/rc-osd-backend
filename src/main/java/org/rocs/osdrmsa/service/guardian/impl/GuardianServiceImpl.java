package org.rocs.osdrmsa.service.guardian.impl;

import lombok.RequiredArgsConstructor;
import org.rocs.osdrmsa.domain.person.guardian.Guardian;
import org.rocs.osdrmsa.domain.person.student.Student;
import org.rocs.osdrmsa.repository.student.StudentRepository;
import org.rocs.osdrmsa.service.guardian.GuardianService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GuardianServiceImpl implements GuardianService {

    private final StudentRepository studentRepository;

    @Override
    public List<Guardian> getByStudentId(String studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found: " + studentId));
        return student.getGuardians();
    }
}
