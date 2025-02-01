package eu.kennytv.maintenance.core.command.subcommand;

import eu.kennytv.maintenance.core.MaintenancePlugin;
import eu.kennytv.maintenance.core.command.CommandInfo;
import eu.kennytv.maintenance.core.util.SenderInfo;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ModifyPingMessageCommand extends CommandInfo {

    public ModifyPingMessageCommand(MaintenancePlugin plugin, @Nullable String permission) {
        super(plugin, permission);
    }

    @Override
    public void execute(SenderInfo sender, String[] args) {
        if (args.length < 2) {
            sender.send(getHelpMessage());
        } else if (args[1].equalsIgnoreCase("clear")) {
            plugin.clearPingMessages();
            sender.send(getMessage("pingMessagesCleared"));
        } else if (args[1].equalsIgnoreCase("add") && Objects.nonNull(args[2])) {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                sb.append(args[i]);
            }
            plugin.addPingMessage(sb.toString().replace("<ws>", " "));
            sender.send(getMessage("pingMessageAppended"));
        } else {
            sender.send(getHelpMessage());
        }

    }
}
