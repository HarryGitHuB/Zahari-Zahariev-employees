package api.io;

import java.util.List;

public interface FileReadWrite {
    List<String> read(String file);

    void write(String fileContent, String file);
}
