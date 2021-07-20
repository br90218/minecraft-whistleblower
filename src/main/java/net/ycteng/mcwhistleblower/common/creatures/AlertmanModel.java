package net.ycteng.mcwhistleblower.common.creatures;

import net.minecraft.client.renderer.entity.model.AbstractZombieModel;

public class AlertmanModel extends AbstractZombieModel<AlertmanEntity> {

	public AlertmanModel(float p_i1168_1_, boolean p_i1168_2_) {
	      this(p_i1168_1_, 0.0F, 64, p_i1168_2_ ? 32 : 64);
	   }

	protected AlertmanModel(float p_i51070_1_, float p_i51070_2_, int p_i51070_3_, int p_i51070_4_) {
		super(p_i51070_1_, p_i51070_2_, p_i51070_3_, p_i51070_4_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAggressive(AlertmanEntity p_212850_1_) {
		// TODO Auto-generated method stub
		return false;
	}

}
