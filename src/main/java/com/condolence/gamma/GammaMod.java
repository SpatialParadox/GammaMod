package com.condolence.gamma;

import com.condolence.gamma.config.GammaSettings;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = GammaMod.MOD_ID, name = GammaMod.MOD_NAME, version = GammaMod.VERSION)
public class GammaMod {
    static final String MOD_ID = "gammamod";
    static final String VERSION = "1.0.0";
    static final String MOD_NAME = "GammaMod";

    private final GammaSettings settings;

    public GammaMod() {
        this.settings = new GammaSettings(this);
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new GammaCommand(this));
        this.settings.loadConfig();
    }

    public GammaSettings getSettings() { return this.settings; }
}
