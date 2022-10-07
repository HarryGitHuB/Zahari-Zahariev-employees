package api.services;

import api.factories.EmployeeFactory;
import api.model.Pair;
import api.model.EmployeeRecord;
import api.repository.EmployeeRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository emplRepo;

    public EmployeeServiceImpl(EmployeeRepository emplRepo) {
        this.emplRepo = emplRepo;
    }

    @Override
    public void addEmployeeRecords(List<EmployeeRecord> employeeRecordList) {
        this.emplRepo.saveAll(employeeRecordList);
        ;
    }

    @Override
    public List<Pair> findAllEmployee() {

        List<EmployeeRecord> allRecords = this.emplRepo.getAllRecords();
        List<Pair> pair = new ArrayList<>();

        for (int i = 0; i < allRecords.size() - 1; i++) {
            for (int j = i + 1; j < allRecords.size(); j++) {
                EmployeeRecord firstEmpl = allRecords.get(i);
                EmployeeRecord secondEmpl = allRecords.get(j);

                if (firstEmpl.getProjectID() == secondEmpl.getProjectID()
                        && hasWorkedSimultaneously(firstEmpl, secondEmpl)) {
                    long overlappDays = calculateOverlapDays(firstEmpl, secondEmpl);

                    if (overlappDays > 0) {
                        updateEmployee(pair, firstEmpl, secondEmpl, overlappDays);
                    }
                }
            }
        }
        return pair;
    }

    private void updateEmployee(List<Pair> pair, EmployeeRecord firstEmpl, EmployeeRecord secondEmpl, long overlappDays) {
        AtomicBoolean isPresent = new AtomicBoolean(false);

        pair.forEach(empl -> {
            if (isEmployeePresent(empl, firstEmpl.getEmployeeID(), secondEmpl.getEmployeeID())) {
                empl.addOverlapDuration(overlappDays);
                isPresent.set(true);
            }
        });

        if (!isPresent.get()) {
            Pair newPair = EmployeeFactory.execute(
                    firstEmpl.getEmployeeID(),
                    secondEmpl.getEmployeeID(),
                    overlappDays);
            pair.add(newPair);
        }
    }

    private boolean isEmployeePresent(Pair pair, Long firstEmployeeID, Long secondEmployeeID) {
        return (pair.getFirstEmployeeId() == firstEmployeeID
                && pair.getSecondEmployeeId() == secondEmployeeID)
                || (pair.getFirstEmployeeId() == secondEmployeeID
                && pair.getSecondEmployeeId() == firstEmployeeID);
    }


    private long calculateOverlapDays(EmployeeRecord firstEmpl, EmployeeRecord secondEmpl) {

        LocalDate startDate = firstEmpl.getDateFrom().isBefore(secondEmpl.getDateFrom()) ?
                secondEmpl.getDateFrom() : firstEmpl.getDateFrom();

        LocalDate endDate = firstEmpl.getDateTo().isBefore(secondEmpl.getDateTo()) ?
                firstEmpl.getDateTo() : secondEmpl.getDateTo();
        long period = Math.abs(ChronoUnit.DAYS.between(startDate, endDate));

        return period;
    }

    private boolean hasWorkedSimultaneously(EmployeeRecord firstEmpl, EmployeeRecord secondEmpl) {
        return (firstEmpl.getDateFrom().isBefore(secondEmpl.getDateTo())
                || firstEmpl.getDateFrom().equals(secondEmpl.getDateTo())
                && firstEmpl.getDateTo().isAfter(secondEmpl.getDateFrom())
                || firstEmpl.getDateTo().equals(secondEmpl.getDateFrom()));
    }
}
