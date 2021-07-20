package net.ycteng.mcwhistleblower.common.creatures;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.ycteng.mcwhistleblower.McWhistleblower;
import net.ycteng.mcwhistleblower.common.data.CapabilityPlayerState;

public class AlertmanEntity extends MonsterEntity {

	private static final boolean shouldDespawnInPeaceful = false;
	private static final boolean shouldDropExperience = false;
	private static final boolean shouldDropLoot = false;
	private static final float lookAtPlayerDistance = 8.0f;
	private static final int cryTick = 60;
	private static final Style chatStyle = Style.EMPTY.withColor(Color.fromRgb(0xFFAA00));
	private static final float clearMessageDistance = 15f;
	private static final float vagueMessageDistance = 30f;

	public AlertmanEntity(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
		super(p_i48553_1_, p_i48553_2_);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, lookAtPlayerDistance));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(2, new ChaseTargetGoal(0.8D));
		this.goalSelector.addGoal(2, new CryAlertGoal());
		this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 0.5D));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.FOLLOW_RANGE, 35)
				.add(Attributes.MOVEMENT_SPEED, (double) 0.4f);
	}

	@Override
	public boolean hurt(DamageSource damageSource, float value) {
		if (!super.hurt(damageSource, value)) {
			return false;
		} else if (!(this.level instanceof ServerWorld)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return shouldDespawnInPeaceful;
	}

	@Override
	protected boolean shouldDropExperience() {
		return shouldDropExperience;
	}

	@Override
	protected boolean shouldDropLoot() {
		return shouldDropLoot;
	}

	@Override
	public boolean isAggressive() {
		// TODO Auto-generated method stub
		return super.isAggressive();
	}

	class CryAlertGoal extends Goal {

		private int nextCryTick = 0;

		@Override
		public boolean canUse() {
			if (AlertmanEntity.this.getTarget() == null) {
				return false;
			}
			if (this.nextCryTick > 0) {
				nextCryTick--;
				return false;
			} else {
				this.nextCryTick = cryTick;
				PlayerList playerList = AlertmanEntity.this.getServer().getPlayerList();
				for (ServerPlayerEntity player : playerList.getPlayers()) {
					float distance = AlertmanEntity.this.distanceTo(player);
					if (distance < clearMessageDistance) {
						player.sendMessage(putTogetherMessage(AlertmanEntity.this.getTarget()),
								AlertmanEntity.this.getUUID());

						// maybe I should separate this into two different goals
					} else if (distance < vagueMessageDistance) {
						player.sendMessage(
								new TranslationTextComponent("***You can hear an alertman shouting somewhere...")
										.withStyle(chatStyle.withColor(Color.fromRgb(0xBB7D00))),
								AlertmanEntity.this.getUUID());
					}
				}
				return true;
			}
		}
		
		

		@Override
		public void start() {
			// TODO Auto-generated method stub
			super.start();
		}



		@Override
		public void stop() {
			// TODO Auto-generated method stub
			super.stop();
		}



		@Override
		public void tick() {
			// TODO Auto-generated method stub
			super.tick();
		}



		private ITextComponent putTogetherMessage(LivingEntity target) {
			String color = "Yellow"; // TODO: this is just here for now, some day team colors will be fetched from
										// target.
			Integer number = target.getCapability(CapabilityPlayerState.PLAYERSTATE).resolve().get().getBackNumber();

			return new TranslationTextComponent(
					"ALERT! " + target.getDisplayName().getString() + "'s number is " + number.toString())
							.withStyle(chatStyle);
		}

	}

	class ChaseTargetGoal extends Goal {

		private final boolean followingTargetEvenIfNotSeen = false;
		private final double speedModifier;
		
		private Path path;
		private long lastCanUseCheck;
		private int ticksUntilNextPathRecalculation;
		private double pathedTargetX;
		private double pathedTargetY;
		private double pathedTargetZ;
		private boolean canPenalize = false;
		private int failedPathFindingPenalty = 0;
		
		
		public ChaseTargetGoal(double speedModifier) {
			this.speedModifier = speedModifier;
		}
		
		@Override
		public boolean canUse() {
			long i = AlertmanEntity.this.level.getGameTime();
			if(i - this.lastCanUseCheck < 20L) {
				return false;
			} else {
				this.lastCanUseCheck = i;
				LivingEntity targetedPlayer = AlertmanEntity.this.getTarget();
				if(targetedPlayer == null) {
					return false;
				} else if (!targetedPlayer.isAlive()) {
					return false;
				} else {
					this.path = AlertmanEntity.this.getNavigation().createPath(targetedPlayer, 0);
					return true;
				}
			}
		}
		
		@Override
		public boolean canContinueToUse() {
			LivingEntity targetedPlayer = AlertmanEntity.this.getTarget();
			if(targetedPlayer == null) {
				return false;
			} else if (!targetedPlayer.isAlive()) {
				return false;
			} else if (!this.followingTargetEvenIfNotSeen) {
				return !AlertmanEntity.this.getNavigation().isDone();
			} else if (!AlertmanEntity.this.isWithinRestriction(targetedPlayer.blockPosition())) {
				return false;
			} else {
				return !(targetedPlayer instanceof PlayerEntity) || !targetedPlayer.isSpectator() && !((PlayerEntity) targetedPlayer).isCreative();
			}
		}

		@Override
		public void start() {
			AlertmanEntity.this.getNavigation().moveTo(path, speedModifier);
			this.ticksUntilNextPathRecalculation = 0;
			McWhistleblower.LOGGER.debug("alertman started chasing goal");
		}

		@Override
		public void stop() {
			LivingEntity playerTarget = AlertmanEntity.this.getTarget();
			if(!EntityPredicates.NO_CREATIVE_OR_SPECTATOR.test(playerTarget)) {
				AlertmanEntity.this.setTarget((LivingEntity)null);
			}
			AlertmanEntity.this.getNavigation().stop();
			McWhistleblower.LOGGER.debug("alertman stopped chasing goal");
		}

		@Override
		public void tick() {
			LivingEntity targetedPlayer = AlertmanEntity.this.getTarget();
			AlertmanEntity.this.getLookControl().setLookAt(targetedPlayer, 30f, 30f);
	        double d0 = AlertmanEntity.this.distanceToSqr(targetedPlayer.getX(), targetedPlayer.getY(), targetedPlayer.getZ());
	        this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
			if ((this.followingTargetEvenIfNotSeen || AlertmanEntity.this.getSensing().canSee(targetedPlayer)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || targetedPlayer.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || AlertmanEntity.this.getRandom().nextFloat() < 0.05F)) {
				this.pathedTargetX = targetedPlayer.getX();
			    this.pathedTargetY = targetedPlayer.getY();
			    this.pathedTargetZ = targetedPlayer.getZ();
			    this.ticksUntilNextPathRecalculation = 4 + AlertmanEntity.this.getRandom().nextInt(7);
			    if (this.canPenalize) {
			       this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
			       if (AlertmanEntity.this.getNavigation().getPath() != null) {
			          net.minecraft.pathfinding.PathPoint finalPathPoint = AlertmanEntity.this.getNavigation().getPath().getEndNode();
			          if (finalPathPoint != null && targetedPlayer.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
			             failedPathFindingPenalty = 0;
			          else
			             failedPathFindingPenalty += 10;
			       } else {
		           failedPathFindingPenalty += 10;
			       }			     
			    }
			    if (d0 > 1024.0D) {
			       this.ticksUntilNextPathRecalculation += 10;
			    } else if (d0 > 256.0D) {
			       this.ticksUntilNextPathRecalculation += 5;
			    }
			    
			    if (!AlertmanEntity.this.getNavigation().moveTo(targetedPlayer, this.speedModifier)) {
			    	this.ticksUntilNextPathRecalculation += 15;			     
		        }
			}
		}
	}
}
