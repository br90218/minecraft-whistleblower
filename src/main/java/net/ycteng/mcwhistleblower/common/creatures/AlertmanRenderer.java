package net.ycteng.mcwhistleblower.common.creatures;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.ycteng.mcwhistleblower.McWhistleblower;

public class AlertmanRenderer extends BipedRenderer<AlertmanEntity, AlertmanModel>{

	public AlertmanRenderer(EntityRendererManager erm) {
		super(erm, new AlertmanModel(0, false), 0.5f);
		// TODO Auto-generated constructor stub
	}


	public static final ResourceLocation ALERTMAN_SKIN = new ResourceLocation(McWhistleblower.MODID, "textures/entity/alertman.png");
	

	@Override
	public ResourceLocation getTextureLocation(AlertmanEntity p_110775_1_) {
		return ALERTMAN_SKIN;
	}


	@Override
	protected void renderNameTag(AlertmanEntity p_225629_1_, ITextComponent p_225629_2_, MatrixStack p_225629_3_,
			IRenderTypeBuffer p_225629_4_, int p_225629_5_) {
		// TODO Auto-generated method stub
		super.renderNameTag(p_225629_1_, p_225629_2_, p_225629_3_, p_225629_4_, p_225629_5_);
	}
}
