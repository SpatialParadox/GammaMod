package com.condolence.gamma.gui;

import com.condolence.gamma.GammaMod;
import com.condolence.gamma.config.GammaSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.client.config.GuiSlider;

import java.io.IOException;

public class GammaGui extends GuiScreen {
    private final Minecraft mc;
    private final GammaSettings settings;

    private GuiSlider gammaSlider;

    public GammaGui(final GammaMod mod) {
        this.mc = Minecraft.getMinecraft();
        this.settings = mod.getSettings();
    }

    /**
     * Returns the horizontal center by simply dividing the screen width by 2.
     */
    private int getHorizontalCenter() { return this.width / 2; }

    /**
     * Returns the horizontal center for a specific element, this should be used if the point at
     * which a element is positioned around is not in the center of the element (in which case, it is usually in the top left).
     */
    private int getHorizontalCenter(final int guiWidth) { return (this.width / 2) - (guiWidth / 2); }

    private int getVerticalCenter() { return this.height / 2; }

    private int getVerticalCenter(final int guiHeight) { return (this.height / 2) - (guiHeight / 2); }

    public void initGui() {
        this.gammaSlider = new GuiSlider(1, this.getHorizontalCenter(100), this.getVerticalCenter(20), 100, 20, "Gamma: ", "", 1.0f, 15.0f, this.settings.getGammaAmount(), false, true);
        final GuiButton saveButton = new GuiButton(2, this.getHorizontalCenter(50), this.getVerticalCenter(20) + 25, 50, 20, "Save");

        this.buttonList.add(this.gammaSlider);
        this.buttonList.add(saveButton);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();

        this.drawCenteredString(this.mc.fontRendererObj, "GammaMod", this.getHorizontalCenter(), 20, 2201331);
        this.drawCenteredString(this.mc.fontRendererObj, "1.0", this.getHorizontalCenter(), 30, 4149685);

        this.settings.setGammaAmount(this.gammaSlider.getValue());

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 2) {
            if (this.mc.thePlayer == null) { return; }

            this.mc.thePlayer.closeScreen();
        }
    }

    public void onGuiClosed()
    {
        this.settings.saveConfig();
    }
}
