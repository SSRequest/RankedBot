package com.kasp.rankedbot.commands.utilities;

import com.kasp.rankedbot.CommandSubsystem;
import com.kasp.rankedbot.EmbedType;
import com.kasp.rankedbot.commands.Command;
import com.kasp.rankedbot.instance.GameMap;
import com.kasp.rankedbot.instance.cache.MapCache;
import com.kasp.rankedbot.instance.Embed;
import com.kasp.rankedbot.messages.Msg;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class DeleteMapCmd extends Command {
    public DeleteMapCmd(String command, String usage, String[] aliases, String description, CommandSubsystem subsystem) {
        super(command, usage, aliases, description, subsystem);
    }

    @Override
    public void execute(String[] args, Guild guild, Member sender, TextChannel channel, Message msg) {
        if (args.length != 2) {
            Embed reply = new Embed(EmbedType.ERROR, "Invalid Arguments", Msg.getMsg("wrong-usage").replaceAll("%usage%", getUsage()), 1);
            msg.replyEmbeds(reply.build()).queue();
            return;
        }

        String name = args[1];

        if (!MapCache.containsMap(name)) {
            Embed reply = new Embed(EmbedType.ERROR, "Error", Msg.getMsg("map-doesnt-exist"), 1);
            msg.replyEmbeds(reply.build()).queue();
            return;
        }

        GameMap.delete(name);

        Embed success = new Embed(EmbedType.SUCCESS, "", Msg.getMsg("map-deleted"), 1);
        msg.replyEmbeds(success.build()).queue();
    }
}
