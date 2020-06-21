package com.condolence.gamma;

import com.condolence.gamma.config.GammaSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class GammaCommand extends CommandBase {
    private final GammaMod mod;
    private final GammaSettings settings;

    public GammaCommand(final GammaMod mod) {
        this.mod = mod;
        this.settings = this.mod.getSettings();
    }

    public String getCommandName() { return "gamma"; }
    public String getCommandUsage(final ICommandSender sender) { return "/gamma"; }
    public void processCommand(final ICommandSender sender, final String[] args) {
        this.mod.openMenu();
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) { return true; }
}
