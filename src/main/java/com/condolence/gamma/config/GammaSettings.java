package com.condolence.gamma.config;

import com.condolence.gamma.GammaMod;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class GammaSettings {
    private GammaMod mod;
    private Minecraft minecraft;
    private File configFile;

    private double gammaAmount;

    public GammaSettings(final GammaMod mod) {
        this.mod = mod;
        this.minecraft = Minecraft.getMinecraft();
        this.configFile = new File(this.minecraft.mcDataDir, "config/GammaMod.cfg");
    }

    public void saveConfig() {
        final Configuration config = new Configuration(this.configFile);
        this.updateConfig(config, true);
        config.save();
    }

    public void loadConfig() {
        final Configuration config = new Configuration(this.configFile);
        config.load();
        this.updateConfig(config, false);
        config.save();
    }

    public void updateConfig(final Configuration config, final boolean save) {
        Property gammaAmount = config.get("global", "amount", 1.0);

        if (save) {
            gammaAmount.set(this.gammaAmount);
        } else {
            this.gammaAmount = gammaAmount.getDouble();
        }
    }

    public double getGammaAmount() { return this.gammaAmount; }
    public void setGammaAmount(final double gammaAmount) { this.gammaAmount = gammaAmount; }
}
