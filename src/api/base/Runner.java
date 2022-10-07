package api.base;

import api.factories.EmployeeRecordFactory;
import api.io.FileReadWrite;
import api.io.Writer;
import api.model.Pair;
import api.model.EmployeeRecord;
import api.services.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

public class Runner implements Runnable {
    private FileReadWrite fileReadWrite;
    private String path;
    private Writer writer;

    private EmployeeService emplService;


    public Runner(String path,
                  FileReadWrite fileReadWrite,
                  Writer writer,
                  EmployeeService emplService) {
        this.path = path;
        this.fileReadWrite = fileReadWrite;
        this.writer = writer;
        this.emplService = emplService;
    }


    @Override
    public void run() {

        List<EmployeeRecord> records = this.fileReadWrite.read(path)
                .stream()
                .map(EmployeeRecordFactory::execute)
                .collect(Collectors.toList());

        this.emplService.addEmployeeRecords(records);

        List<Pair> employ = this.emplService.findAllEmployee();

        printResult(employ);
    }

    private void printResult(List<Pair> pair) {
        if (pair.size() != 0) {
            pair.sort((empl1, empl2) ->
                    (int) (empl2.getDuration() - empl1.getDuration()));
            Pair employPerformed = pair.get(0);

            this.writer.write(
                    String.format("The pair of employees who have worked\n" +
                                    "together on common projects for the longest period of time:" +
                                    "%n employeeID: %d & employeeID: %d with duration of %d days",
                            employPerformed.getFirstEmployeeId(),
                            employPerformed.getSecondEmployeeId(),
                            employPerformed.getDuration()));
        } else {
            this.writer.write("No pair found");
        }
    }
}

