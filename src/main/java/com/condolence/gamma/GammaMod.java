package com.condolence.gamma;

import com.condolence.gamma.config.GammaSettings;
import com.condolence.gamma.gui.GammaGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = GammaMod.MOD_ID, name = GammaMod.MOD_NAME, version = GammaMod.VERSION)
public class GammaMod {
    static final String MOD_ID = "gammamod";
    static final String VERSION = "1.0.0";
    static final String MOD_NAME = "GammaMod";

    private final GammaSettings settings;
    private Minecraft mc;

    private boolean menu = false;

    public GammaMod() {
        this.settings = new GammaSettings(this);
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        this.mc = Minecraft.getMinecraft();

        ClientCommandHandler.instance.registerCommand(new GammaCommand(this));
        MinecraftForge.EVENT_BUS.register(this);
        this.settings.loadConfig();
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (this.menu) {
            this.menu = false;
            this.mc.displayGuiScreen(new GammaGui(this));
        }

        this.mc.gameSettings.gammaSetting = (float)this.settings.getGammaAmount();
    }

    public void openMenu() { this.menu = true; }
    public GammaSettings getSettings() { return this.settings; }
}
