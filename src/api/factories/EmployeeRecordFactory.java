package api.factories;

import api.model.EmployeeRecord;

import java.time.LocalDate;

public class EmployeeRecordFactory {
    private static final String CSV_REGEX_PATTERN = ", ";
    private static final String NULL = "NULL";
    private static final int RECORD_INDEX_ZERO = 0;
    private static final int RECORD_INDEX_ONE = 1;
    private static final int RECORD_INDEX_TWO = 2;
    private static final int RECORD_INDEX_THREE = 3;

    public EmployeeRecordFactory() {
    }


    public static EmployeeRecord execute(String line) {

        String[] recordIndex = line.split(CSV_REGEX_PATTERN);

        long emplID = Long.parseLong(recordIndex[RECORD_INDEX_ZERO].trim());
        long projID = Long.parseLong(recordIndex[RECORD_INDEX_ONE].trim());

        LocalDate dateFrom = LocalDate.parse(recordIndex[RECORD_INDEX_TWO]);
        LocalDate dateTo;

        if (recordIndex[RECORD_INDEX_THREE] == null | NULL.equals(recordIndex[RECORD_INDEX_THREE])) {
            dateTo = LocalDate.now();
        } else {
            dateTo = LocalDate.parse(recordIndex[RECORD_INDEX_THREE]);
        }
        return new EmployeeRecord(
                emplID,
                projID,
                dateFrom,
                dateTo
        );

    }
}
