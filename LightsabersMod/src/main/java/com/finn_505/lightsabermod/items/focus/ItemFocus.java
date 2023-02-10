package com.finn_505.lightsabermod.items.focus;

import java.util.ArrayList;
import java.util.List;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.entity.grip.EntityGrip;
import com.finn_505.lightsabermod.entity.lightning.EntityLightning;
import com.finn_505.lightsabermod.init.ModItems;
import com.finn_505.lightsabermod.init.ModSoundEvents;
import com.finn_505.lightsabermod.util.force.ForceProvider;
import com.finn_505.lightsabermod.util.force.IForce;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFocus extends Item{
	
	int maxForce;
	int maxPassiveForce;
	boolean jediFocus;
	int level;
	
	public ItemFocus(String name, int maxForce, int maxPassiveForce, boolean jediFocus, int level)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(Main.STARWARS);
		this.setMaxStackSize(1);
		this.maxForce = maxForce;
		this.jediFocus = jediFocus;
		this.maxPassiveForce = maxPassiveForce;
		this.level = level;
		
		ModItems.ITEMS.add(this);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		ArrayList<KeyBinding> binds = Main.keyBinds;
		if(binds == null) return;
		tooltip.add("Hold in main or off hand in order to use the force");
		tooltip.add("Press " + binds.get(2).getDisplayName() + " to open force abilities gui");
		tooltip.add(Minecraft.getMinecraft().gameSettings.keyBindUseItem.getDisplayName() + " to use the force");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		IForce force = playerIn.getCapability(ForceProvider.FORCE, null);
		if(force.isUsingForce() || playerIn.isSneaking()) return super.onItemRightClick(worldIn, playerIn, handIn);
		double dist = 10;
		double x = playerIn.posX + playerIn.getLookVec().x * dist;
		double y = playerIn.posY + playerIn.getLookVec().y * dist + 1;
		double z = playerIn.posZ + playerIn.getLookVec().z * dist;
		double x0 = playerIn.posX;
		double y0 = playerIn.posY + 2;
		double z0 = playerIn.posZ;
		while(Math.abs(x0 - x) < 1)
		{
			x0 += 0.5;
			x -= 0.5;
		}
		while(Math.abs(y0 - y) < 1)
		{
			y0 += 0.5;
			y -= 0.5;
		}
		while(Math.abs(z0 - z) < 1)
		{
			z0 += 0.5;
			z -= 0.5;
		}
		AxisAlignedBB box = new AxisAlignedBB(new BlockPos(x0, y0, z0), new BlockPos(x,y,z));
		List<Entity> entities = playerIn.world.getEntitiesWithinAABBExcludingEntity(playerIn, box);
		Entity entityIn = null;
		if(entities.size() > 0) entityIn = entities.get(entities.size() - 1);
		if(entityIn == null) 
		{
			force.setUsingLightning(false);
			force.setUsingForce(false);
			if(worldIn.getEntityByID(force.getGripId()) != null)
			{
				worldIn.getEntityByID(force.getGripId()).setDead();
			}
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		force.setUsingForce(true);
		int amount1 = 50;
		int amount2 = 2;
		int amount3 = 50;
		int amount4 = 20;
		int amount5 = 50;
		int amount6 = 5;
		if(force.getEquippedAbility() == 1 && force.canUseForce(amount1))
		{
			//mind trick
			force.setMindTrickTicks(100);
			if(entityIn instanceof EntityMob)
			{
				if(((EntityMob)entityIn).getAttackTarget() == playerIn)
				{
					((EntityMob)entityIn).setAttackTarget(null);
					force.setMindTrickId(entityIn.getEntityId());
					force.useForce(amount1);
					force.setTicksSinceLastUsedForce(0);
				}
			}
			else if(entityIn instanceof EntityHorse)
			{
				((EntityHorse) entityIn).setTamedBy(playerIn);
				force.setTicksSinceLastUsedForce(0);
			}
			else if(entityIn instanceof EntityTameable)
			{
				EntityTameable entity = (EntityTameable)entityIn;
				entity.setTamedBy(playerIn);
				force.useForce(amount1);
				force.setTicksSinceLastUsedForce(0);
			}
			else if(entityIn instanceof EntityAnimal)
			{
				((EntityAnimal) entityIn).setInLove(playerIn);
				force.useForce(amount1);
				force.setTicksSinceLastUsedForce(0);
			}
			else if(entityIn instanceof EntityPlayer && force.canUseForce(4 * amount1))
			{
				force.setMindTrickId(entityIn.getEntityId());
				force.setTrickedTime(100);
				force.useForce(4 * amount1);
				force.setTicksSinceLastUsedForce(0);
			}
		}
		else if(force.getEquippedAbility() == 2 && force.canUseForce(amount2) && !(entityIn instanceof EntityLightning))
		{
			//grip
			EntityGrip grip = new EntityGrip(worldIn, playerIn, entityIn, false);
			grip.setLocationAndAngles(entityIn.getPosition().getX(), entityIn.getPosition().getY(), 
					entityIn.getPosition().getZ(), playerIn.cameraYaw, playerIn.cameraPitch);
			worldIn.spawnEntity(grip);
			force.setGripId(grip.getEntityId());
			entityIn.startRiding(grip, true);
			force.setTicksSinceLastUsedForce(0);
		}
		else if(force.getEquippedAbility() == 3 && force.canUseForce(amount3))
		{
			//push
			double speedMultiplier = 4.0;
			entityIn.addVelocity(speedMultiplier*playerIn.getLookVec().x, 
					speedMultiplier*playerIn.getLookVec().y + 1.0, speedMultiplier*playerIn.getLookVec().z);
			force.useForce(amount3);
			force.setTicksSinceLastUsedForce(0);
		}
		else if(force.getEquippedAbility() == 4 && force.canUseForce(amount4))
		{
			//pull
			double speedMultiplier = -0.2 * (entityIn.getDistance(playerIn));
			entityIn.addVelocity(speedMultiplier*playerIn.getLookVec().x, 
					speedMultiplier*playerIn.getLookVec().y + 0.1, speedMultiplier*playerIn.getLookVec().z);
			force.useForce(amount4);
			force.setTicksSinceLastUsedForce(0);
		}
		else if(force.getEquippedAbility() == 5 && force.canUseForce(amount5) && !(entityIn instanceof EntityLightning))
		{
			//choke
			EntityGrip grip = new EntityGrip(worldIn, playerIn, entityIn, true);
			grip.setLocationAndAngles(entityIn.getPosition().getX(), entityIn.getPosition().getY(), 
					entityIn.getPosition().getZ(), playerIn.cameraYaw, playerIn.cameraPitch);
			worldIn.spawnEntity(grip);
			force.setGripId(grip.getEntityId());
			entityIn.startRiding(grip, true);
			force.setTicksSinceLastUsedForce(0);
			//force.useForce(amount4);
		}
		else if(force.getEquippedAbility() == 6 && force.canUseForce(amount6))
		{
			//sith lightning
			if(entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityLightning))
			{
				worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundEvents.SITH_LIGHTNING, 
						SoundCategory.NEUTRAL, 0.5F, 1.0F);
				EntityLightning lightning = new EntityLightning(worldIn, playerIn);
				lightning.setLocationAndAngles(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.cameraYaw, playerIn.cameraPitch);
				if(!worldIn.isRemote && !force.isUsingLightning())
				worldIn.spawnEntity(lightning);
				entityIn.attackEntityFrom(DamageSource.causeIndirectMagicDamage(lightning, playerIn), 2.0F);
				((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, 3, false, false));
				force.setGripId(lightning.getEntityId());
				force.setUsingLightning(true);
				force.setTicksSinceLastUsedForce(0);
				force.useForce(amount6);
			}
			//force.setUsingLightning(false);
			force.setUsingForce(false);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	public int getMaxForce(){
		return maxForce;
	}
	
	public int getMaxPassiveForce() {
		return maxPassiveForce;
	}
	
	public boolean isJediFocus() {
		return jediFocus;
	}
	
	public int getLevel() {
		return level;
	}
}
