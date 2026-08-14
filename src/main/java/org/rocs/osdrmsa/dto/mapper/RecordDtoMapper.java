package org.rocs.osdrmsa.dto.mapper;

import org.rocs.osdrmsa.dto.request.RecordCreateRequest;
import org.rocs.osdrmsa.dto.request.RecordUpdateRequest;
import org.rocs.osdrmsa.domain.disciplinary.action.DisciplinaryAction;
import org.rocs.osdrmsa.domain.enrollment.Enrollment;
import org.rocs.osdrmsa.domain.offense.Offense;
import org.rocs.osdrmsa.domain.person.employee.Employee;
import org.rocs.osdrmsa.domain.record.Record;
import org.rocs.osdrmsa.dto.response.AppealResponse;

import java.time.LocalDate;

public final class RecordDtoMapper {

    private RecordDtoMapper() {
    }

    public static Record toEntity(RecordCreateRequest request) {
        Record record = new Record();
        applyFields(record, request.enrollmentId(), request.employeeId(), request.offenseId(),
                request.dateOfViolation(), request.actionId(), request.remarks());
        return record;
    }

    public static Record toEntity(RecordUpdateRequest request) {
        Record record = new Record();
        record.setRecordId(request.recordId());
        applyFields(record, request.enrollmentId(), request.employeeId(), request.offenseId(),
                request.dateOfViolation(), request.actionId(), request.remarks());
        return record;
    }

    private static void applyFields(
            Record record,
            long enrollmentId,
            String employeeId,
            long offenseId,
            LocalDate dateOfViolation,
            long actionId,
            String remarks) {

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(enrollmentId);
        record.setEnrollment(enrollment);

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        record.setEmployee(employee);

        Offense offense = new Offense();
        offense.setOffenseId(offenseId);
        record.setOffense(offense);

        DisciplinaryAction action = new DisciplinaryAction();
        action.setActionId(actionId);
        record.setAction(action);

        record.setDateOfViolation(dateOfViolation);
        record.setRemarks(remarks);
    }

    public static AppealResponse.RecordResponse toResponse(Record record) {
        if (record == null) {
            return null;
        }
        return new AppealResponse.RecordResponse(
                record.getRecordId(),
                CommonDtoMapper.toEnrollmentSummary(record.getEnrollment()),
                CommonDtoMapper.toEmployeeSummary(record.getEmployee()),
                CommonDtoMapper.toOffenseSummary(record.getOffense()),
                record.getDateOfViolation(),
                CommonDtoMapper.toActionSummary(record.getAction()),
                record.getDateOfResolution(),
                record.getRemarks(),
                record.getStatus());
    }
}