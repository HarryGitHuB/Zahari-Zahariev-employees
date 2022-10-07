package api.io;

public class ConsoleWriter implements Writer {

    @Override
    public void write(String string) {
        System.out.println(string);
    }

    @Override
    public void writeLine(String string) {
        System.out.println(string);
    }

    @Override
    public void writeLine(String string, Object... param) {
        System.out.println(string);
    }


}
