package api.repository;

import api.model.EmployeeRecord;

import java.util.Collection;
import java.util.List;

public interface EmployeeRepository {

    void saveAll(Collection<EmployeeRecord> employeeRecordCollections);

    List<EmployeeRecord> getAllRecords();
}
