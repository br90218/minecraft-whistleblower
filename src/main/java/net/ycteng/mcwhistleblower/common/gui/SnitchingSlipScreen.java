package net.ycteng.mcwhistleblower.common.gui;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.item.ItemStack;
import net.ycteng.mcwhistleblower.McWhistleblower;
import net.ycteng.mcwhistleblower.common.network.Networking;
import net.ycteng.mcwhistleblower.common.network.UpdateSnitchingSlipContentPacket;

public class SnitchingSlipScreen extends Screen {
	
	private static final int WIDTH = 179;
	private static final int HEIGHT = 151;
	
	private final ResourceLocation GUI = new ResourceLocation(McWhistleblower.MODID, "textures/gui/snitchingslip_gui.png");
	
	private String initContent;
	private final ItemStack stack;
	private TextFieldWidget textBox;
	
	public SnitchingSlipScreen(String initContent, ItemStack stack) {
		super(new TranslationTextComponent("screen.mcwhistleblower.snitchingslip"));
		this.initContent = initContent;
		this.stack = stack;
	}
	
	@Override
	protected void init() {
		int relX = (this.width - WIDTH) / 2;
		int relY = (this.height - HEIGHT) / 2;
		
		textBox = new TextFieldWidget(font, relX + 10, relY + 64, 160, 20, new TranslationTextComponent("screen.mcwhistleblower.snitchingslip.container"));
		textBox.setTextColor(0xFFFFFF);
		textBox.setTextColorUneditable(0xFFFFFF);
		textBox.setBordered(true);
		textBox.setVisible(true);
		textBox.insertText(initContent);
		
		this.addButton(textBox);
		this.setInitialFocus(textBox);
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

	@Override
	public void onClose() {
		System.out.println("Closing snitching slip input UI");
		String updatedContent = textBox.getValue();
		Networking.sendToServer(new UpdateSnitchingSlipContentPacket(stack, updatedContent));
		super.onClose();
	}

	public static void open(String initContent, ItemStack stack) {
		System.out.println(initContent);
		Minecraft.getInstance().setScreen(new SnitchingSlipScreen(initContent, stack));
	}
}
