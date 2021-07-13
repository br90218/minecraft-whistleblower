package net.ycteng.mcwhistleblower.common.gui;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.ycteng.mcwhistleblower.McWhistleblower;

public class SnitchingSlipScreen extends Screen {
	
	private static final int WIDTH = 179;
	private static final int HEIGHT = 151;
	private ResourceLocation GUI = new ResourceLocation(McWhistleblower.MODID, "textures/gui/snitchingslip_gui.png");
	
	public SnitchingSlipScreen() {
		super(new TranslationTextComponent("screen.mcwhistleblower.snitchingslip"));
	}
	
	@Override
	protected void init() {
		int relX = (this.width - WIDTH) / 2;
		int relY = (this.height - HEIGHT) / 2;
		
		TextFieldWidget textBox = new TextFieldWidget(font, 
													  relX + 45, 
													  relY + 45, 
													  120,
													  12,
													  new TranslationTextComponent("screen.mcwhistleblower.snitchingslip.confirm"));
		
		this.addWidget(textBox);
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		minecraft.getTextureManager().bind(GUI);
		int relX = (this.width - WIDTH) / 2;
		int relY = (this.height - HEIGHT) / 2;
		this.blit(matrixStack, relX, relY, 0, 0, WIDTH, HEIGHT);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}
	
	public static void open() {
		Minecraft.getInstance().setScreen(new SnitchingSlipScreen());
	}
}
