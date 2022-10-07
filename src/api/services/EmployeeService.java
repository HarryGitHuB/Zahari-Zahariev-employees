package api.services;

import api.model.Pair;
import api.model.EmployeeRecord;

import java.util.List;

public interface EmployeeService {

    void addEmployeeRecords(List<EmployeeRecord> records);

    List<Pair> findAllEmployee();
}
