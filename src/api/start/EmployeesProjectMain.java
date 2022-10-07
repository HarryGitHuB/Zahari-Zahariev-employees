package api.start;

import api.base.Runner;
import api.ext_filter.ExtensionFileFilter;
import api.io.ConsoleWriter;
import api.io.FileReadWriteImpl;
import api.io.Writer;
import api.repository.EmployeeRepository;
import api.repository.EmployeeRepositoryImpl;
import api.services.EmployeeService;
import api.services.EmployeeServiceImpl;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class EmployeesProjectMain {
    // start
    public static void main(String[] arg) {

        JFileChooser fileChooser = new JFileChooser(".");

        FileFilter txtFilter = new ExtensionFileFilter("txt", new String[]{"TXT"});
        FileFilter csvFilter = new ExtensionFileFilter("csv", new String[]{"CSV"});
        FileFilter docFilter = new ExtensionFileFilter("doc", new String[]{"DOC"});

        fileChooser.addChoosableFileFilter(txtFilter);
        fileChooser.addChoosableFileFilter(csvFilter);
        fileChooser.addChoosableFileFilter(docFilter);

        fileChooser.showOpenDialog(null);
        int ret = fileChooser.showDialog(null, "Open file");

        if (ret == JFileChooser.FILES_ONLY) {
            File file = fileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            FileReadWriteImpl fileReadWriteImpl = new FileReadWriteImpl();
            Writer writer = new ConsoleWriter();
            EmployeeRepository emplRepository = new EmployeeRepositoryImpl();
            EmployeeService emplService = new EmployeeServiceImpl(emplRepository);
            Runner runner = new Runner(path, fileReadWriteImpl, writer, emplService);
            runner.run();
        }
    }
}
