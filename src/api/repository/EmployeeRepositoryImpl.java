package api.repository;

import api.model.EmployeeRecord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EmployeeRepositoryImpl  implements  EmployeeRepository{

   private List<EmployeeRecord> repo;

    public EmployeeRepositoryImpl(){
         this.repo = new ArrayList<>();
    }

    @Override
    public void saveAll(Collection<EmployeeRecord> employeeRecordCollections) {
        this.repo.addAll(employeeRecordCollections);
    }

    @Override
    public List<EmployeeRecord> getAllRecords() {
        return Collections.unmodifiableList(this.repo);
    }
}
