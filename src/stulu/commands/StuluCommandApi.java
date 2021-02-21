package stulu.commands;

import net.minecraft.client.gui.GuiScreen;
import stulu.events.EventManager;

import java.util.HashMap;

public class StuluCommandApi {
    StuluCommandApi(){

    }
    private static StuluCommandApi instance = null;
    public static StuluCommandApi getInstance(){
        if(instance != null) {
            return instance;
        }
        instance = new StuluCommandApi();
        EventManager.register(instance);
        return instance;
    }
    public HashMap<String, IStuluCommand> commands = new HashMap<String, IStuluCommand>();
    public void registerCommand(IStuluCommand command, String Prefix){
        commands.put(Prefix,command);
    }
    public void registerCommand(IStuluCommand command){
        registerCommand(command, command.Prefix());
    }
    public void execute(String msg, GuiScreen returner){
       
        for(String Prefix : commands.keySet()){
            IStuluCommand command = commands.get(Prefix);
            if(msg.startsWith("." + Prefix)) {
                String args[] = msg.substring(("." + Prefix).length()).split(" ");
                command.onCommand(args, returner);
                System.out.println("[Stulu][Client][Command] \"" + command.Name() + "\" was executed");
                return;
            }
        }
        returner.sendClientMessage("Use .help");


    }
}
