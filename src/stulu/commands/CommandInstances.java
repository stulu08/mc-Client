package stulu.commands;

import stulu.commands.impl.StuluFullbrightCommand;
import stulu.commands.impl.StuluHelpCommand;

public class CommandInstances {
    static StuluHelpCommand helpCommand;
    static StuluFullbrightCommand fullbrightCommand;
    public static void register(StuluCommandApi api){
        helpCommand = new StuluHelpCommand();
        fullbrightCommand = new StuluFullbrightCommand();

        api.registerCommand(helpCommand, helpCommand.Prefix());
        api.registerCommand(fullbrightCommand, fullbrightCommand.Prefix());
        api.registerCommand(fullbrightCommand, "f");
    }
}
