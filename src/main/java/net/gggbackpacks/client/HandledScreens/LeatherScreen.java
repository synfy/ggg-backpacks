package net.gggbackpacks.client.HandledScreens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.gggbackpacks.common.ScreenHandlers.LeatherScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class LeatherScreen extends HandledScreen<LeatherScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("gggbackpacks", "textures/screens/leather_backpack_inv.png");

    public LeatherScreen(LeatherScreenHandler handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title);
    }


    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        this.textRenderer.draw(matrices, "Leather Backpack", 8.0F, 7.0F, 4210752);
        this.textRenderer.draw(matrices, "Inventory", 8.0F, (float)(this.backgroundHeight - 130 + 2), 4210752);
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    protected void init() {
        super.init();
    }
}
