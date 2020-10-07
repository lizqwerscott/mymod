package com.lizqwerscott.mymod.gui.recpiedictionary;

import com.lizqwerscott.mymod.Main;
import com.lizqwerscott.mymod.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MyGuiContainer extends GuiContainer {

    private static final String TEXTURE_PATH = Reference.MOD_ID + ":" + "textures/gui/recpie_dictionary/recipe_dictionary.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

    public MyGuiContainer(MyContainer mycontainer) {
        super(mycontainer);
        this.xSize = 175;
        this.ySize = 195;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        /*
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

         */
        mc.getTextureManager().bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString("RecpieDictionary", 10, 0, 4210752);
    }
}
