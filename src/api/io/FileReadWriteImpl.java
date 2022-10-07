package api.io;

import java.io.*;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

public class FileReadWriteImpl implements FileReadWrite {

    @Override
    public List<String> read(String filePath) {
        List<String> fileContent = new ArrayList<>();

        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    fileContent.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return fileContent;
    }

    @Override
    public void write(String fileContent, String file) {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(fileContent);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
