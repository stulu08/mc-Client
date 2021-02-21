package stulu.file;

import org.ini4j.Wini;

import java.io.IOException;

public class WriteStuluFile {
    Wini ini;
    public WriteStuluFile(Wini ini) throws IOException {
        this.ini = ini;
    }
    public void save(String Header, String Key, String Value){
        ini.put(Header,Key,Value);
        try{
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
