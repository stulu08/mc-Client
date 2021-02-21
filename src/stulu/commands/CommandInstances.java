package stulu.commands;

import stulu.commands.impl.StuluFullbrightCommand;
import stulu.commands.impl.StuluHelpCommand;
import stulu.commands.impl.StuluSayCommand;

public class CommandInstances {
    static StuluHelpCommand helpCommand;
    static StuluFullbrightCommand fullbrightCommand;
    static StuluSayCommand sayCommand;
    public static void register(StuluCommandApi api){
        helpCommand = new StuluHelpCommand();
        fullbrightCommand = new StuluFullbrightCommand();
        sayCommand = new StuluSayCommand();

        api.registerCommand(fullbrightCommand);
        api.registerCommand(sayCommand);
        api.registerCommand(helpCommand);
        api.registerCommand(fullbrightCommand, "f");
    }
}
