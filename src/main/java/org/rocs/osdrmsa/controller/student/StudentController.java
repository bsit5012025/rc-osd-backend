package org.rocs.osdrmsa.controller.student;

import lombok.RequiredArgsConstructor;
import org.rocs.osdrmsa.domain.department.Department;
import org.rocs.osdrmsa.domain.person.student.Student;
import org.rocs.osdrmsa.service.student.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Student>> getAll(
            @RequestParam(required = false) Department department) {

        if (department != null) {
            return ResponseEntity.ok(studentService.getByDepartment(department));
        }

        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/active")
    @PreAuthorize("hasRole('PREFECT')")
    public ResponseEntity<List<Student>> getActive(
            @RequestParam(required = false) Department department) {

        if (department != null) {
            return ResponseEntity.ok(
                    studentService.getByDepartmentActive(department)
            );
        }

        return ResponseEntity.ok(studentService.getActive());
    }

    @GetMapping("/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PREFECT', 'STAFF') "
            + "or (hasRole('USER') and @access.isSelfStudent(#studentId))")
    public ResponseEntity<Student> getById(@PathVariable String studentId) {
        return studentService.getById(studentId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.create(student));
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Student> update(
            @PathVariable String studentId, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.update(studentId, student));
    }

    @PatchMapping("/{studentId}/active")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Student> setActive(
            @PathVariable String studentId,
            @RequestParam boolean active) {

        return ResponseEntity.ok(
                studentService.setActive(studentId, active)
        );
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String studentId) {
        studentService.delete(studentId);
        return ResponseEntity.noContent().build();
    }
}