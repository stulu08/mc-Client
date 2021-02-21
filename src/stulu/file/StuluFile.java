package stulu.file;

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class StuluFile {
    Wini ClientIni;
    public WriteStuluFile ClientINIWrite;
    public ReadStuluFile ClientINIRead;
    public String name;
    public StuluFile(String name) throws IOException {
        this.name = name;
        init();
    }
    public void init() throws IOException {
        new File("stulu").mkdir();
        File file = createFile("stulu/"+ name + ".ini");
        ClientIni = new Wini(file);
        ClientINIWrite = new WriteStuluFile(ClientIni);
        ClientINIRead = new ReadStuluFile(ClientIni);
        ClientINIWrite.save("Stulu","Version","Stulu 1.0.1 | Minecraft 1.8.8");
    }
    public File createFile(String path) throws IOException {
        File file = new File(path);
        if (file.createNewFile()) {

            System.out.println("File created: " + file.getName());
        } else {
            System.out.println("File already exists.");
        }

        return file;
    }

}
