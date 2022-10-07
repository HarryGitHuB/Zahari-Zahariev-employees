package api.model;

import java.time.LocalDate;

public class EmployeeRecord {

    private Long employeeID;
    private Long projectID;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public EmployeeRecord(Long employeeID, Long projectID, LocalDate dateFrom, LocalDate dateTo) {
        this.setEmployeeID(employeeID);
        this.setProjectID(projectID);
        this.setDateFrom(dateFrom);
        this.setDateTo(dateTo);
    }


    public Long getEmployeeID() {
        return this.employeeID;
    }

    public void setEmployeeID(Long employeeID) {

        this.employeeID = employeeID;
    }

    public Long getProjectID() {

        return this.projectID;
    }

    public void setProjectID(Long projectID) {

        this.projectID = projectID;
    }

    public LocalDate getDateFrom() {

        return this.dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {

        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {

        return this.dateTo;
    }

    public void setDateTo(LocalDate dateTo) {

        this.dateTo = dateTo;
    }


}
