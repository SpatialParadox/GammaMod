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

    private final ChatComponentText invalidNumberGiven = new ChatComponentText(EnumChatFormatting.RED + "Invalid number given!");
    private final ChatComponentText gammaAmountTooLow = new ChatComponentText(EnumChatFormatting.RED + "The gamma cannot be set to a number less than 0!");
    private final ChatComponentText gammaAmountTooHigh = new ChatComponentText(EnumChatFormatting.RED + "The gamma canot be to set a number greater than 15!");

    public GammaCommand(final GammaMod mod) {
        this.mod = mod;
        this.settings = this.mod.getSettings();
    }

    private boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public String getCommandName() { return "gamma"; }
    public String getCommandUsage(final ICommandSender sender) { return "/gamma <amount>"; }
    public void processCommand(final ICommandSender sender, final String[] args) {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Current gamma amount: " + this.settings.getGammaAmount()));
            return;
        }

        if (!this.isNumeric(args[0])) {
            sender.addChatMessage(invalidNumberGiven);
            return;
        }

        final float gammaAmount = Float.parseFloat(args[0]);

        if (gammaAmount < 0.0) {
            sender.addChatMessage(gammaAmountTooLow);
            return;
        }
        if (gammaAmount > 15.0) {
            sender.addChatMessage(gammaAmountTooHigh);
            return;
        }

        final GameSettings settings = Minecraft.getMinecraft().gameSettings;
        settings.gammaSetting = gammaAmount;

        this.settings.setGammaAmount(gammaAmount);
        this.settings.saveConfig();

        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Successfully set gamma to " + gammaAmount));
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) { return true; }
}
