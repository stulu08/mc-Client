package stulu.file;

import org.ini4j.Wini;

import java.io.IOException;

public class ReadStuluFile {
    Wini ini;
    public ReadStuluFile(Wini ini) throws IOException {
        this.ini = ini;
    }
    public String get(String Header,String Key){
        String s = ini.get(Header,Key);
        if (s == null)
            s = "0";
        return s;
    }
    public boolean getBool(String Header,String Key){
        String s = ini.get(Header,Key);
        if (s == null)
            s = "false";
        return (s.equals("true"));
    }
}
