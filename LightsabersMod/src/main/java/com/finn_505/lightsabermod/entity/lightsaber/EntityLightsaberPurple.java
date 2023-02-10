package com.finn_505.lightsabermod.entity.lightsaber;

import java.util.List;
import java.util.UUID;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.entity.grip.EntityGrip;
import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.init.ModItems;
import com.finn_505.lightsabermod.init.ModSoundEvents;
import com.finn_505.lightsabermod.init.ModTriggers;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class EntityLightsaberPurple extends EntityFlying implements IEntityOwnable{

	public BlockPos target;
	public boolean reachedDestination;
	public UUID ownerId;
	public boolean givenPlayer;
	public int ticksNotMoved;
	public BlockPos prevPos;
	public boolean startedInOffHand;
	
	public EntityLightsaberPurple(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		setDestinationReached(false);
		givenPlayer = false;
		this.setEntityInvulnerable(true);
		this.isImmuneToFire = true;
		ticksNotMoved = 0;
		prevPos = null;
		startedInOffHand = false;
	}
	
	public EntityLightsaberPurple(World worldIn, EntityPlayer Owner, boolean returnToOffHand)
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		this.setOwnerId(Owner.getUniqueID());
		setDestinationReached(false);
		givenPlayer = false;
		this.setEntityInvulnerable(true);
		this.isImmuneToFire = true;
		ticksNotMoved = 0;
		prevPos = null;
		startedInOffHand = returnToOffHand;
	}
	
	public void setDestinationReached(boolean reached)
	{
		reachedDestination = reached;
	}
	
	@Override
	public boolean getLeashed() {
		return false;
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		return;
	}
	
	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		return;
	}
	
	@Override
	public SoundEvent getAmbientSound() {
		return super.getAmbientSound();
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return super.getHurtSound(damageSourceIn);
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return super.getDeathSound();
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.5D);
    }
	
	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		if(this.getOwner() != null)
		{
			this.target = this.getOwner().rayTrace(20.0D, 10).getBlockPos();
		}
	}
	
	public void onLivingUpdate()
    {
		
		//AI
		if((target == null && world.getClosestPlayer(this.getPosition().getX(), this.getPosition().getY(), 
				this.getPosition().getZ(), 10.0D, false) != null) || this.getOwner() == null)
		{
			if(world.getClosestPlayer(this.getPosition().getX(), this.getPosition().getY(), 
				this.getPosition().getZ(), 10.0D, false) != null)
			{
				this.setOwnerId(world.getClosestPlayer(this.getPosition().getX(), this.getPosition().getY(), 
					this.getPosition().getZ(), 10.0D, false).getUniqueID());
				target = this.getOwner().rayTrace(20.0D, 10).getBlockPos();
			}
			else
			{
				this.setDead();
				return;
			}
		}
		if(reachedDestination == false && target != null)
		{
			dragonTravelTo(target);
		}
		else if(reachedDestination == true && this.getOwner() != null)
		{
			dragonTravelTo(this.getOwner().getPosition());
		}
		
		if(this.getOwner() == null) 
		{
			this.setDead();
			return;
		}
		
		if(ticksExisted % 2 == 0)
		this.playSound(ModSoundEvents.LIGHTSABER_ROTATE, 1.0F, 1.0F);
		
        super.onLivingUpdate();

        if (!this.world.isRemote)
        {
            int i = MathHelper.floor(this.posX);
            int j = MathHelper.floor(this.posY);
            int k = MathHelper.floor(this.posZ);

            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this))
            {
                return;
            }
            

            boolean placed = false;
            for (int l = 0; l < 4; ++l)
            {
            	i = (int)(this.posX + (double)((float)(l % 2 * 2 - 1) * 0.25F));
                j = (int)(this.posY);
                k = (int)(this.posZ + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockpos = new BlockPos(i, j, k);

                if (this.world.getBlockState(blockpos).getMaterial() == Material.AIR && ModBlocks.LIGHT_SOURCE.canPlaceBlockAt(this.world, blockpos))
                {
                    this.world.setBlockState(blockpos, ModBlocks.LIGHT_SOURCE.getDefaultState());
                    placed = true;
                }
	        }
            
            if(placed == false)
            {
            	for (int l = 0; l < 4; ++l)
                {
                	i = (int)(this.posX + (double)((float)(l % 2 * 2 - 1) * 0.25F));
                    j = (int)(this.posY + 1);
                    k = (int)(this.posZ + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
                    BlockPos blockpos = new BlockPos(i, j, k);

                    if (this.world.getBlockState(blockpos).getMaterial() == Material.AIR && ModBlocks.LIGHT_SOURCE.canPlaceBlockAt(this.world, blockpos))
                    {
                        this.world.setBlockState(blockpos, ModBlocks.LIGHT_SOURCE.getDefaultState());
                        placed = true;
                    }
    	        }
            }
        }
        
        //attacking close entites or returning to owner
        List<Entity> closeEntities = world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(
        		this.posX - 1.25, this.posY - 1.25, this.posZ - 1.25,
        		this.posX + 1.25, this.posY + 1.25, this.posZ + 1.25));
        for(Entity e: closeEntities)
        {
        	if(!world.isRemote)
        	{
	        	if(!e.equals(this.getOwner()) 
	        			&& !(e instanceof EntityLightsaberPurple) && !(e instanceof EntityLightsaberPurple) 
	        			&& !(e instanceof EntityLightsaberPurple) && !(e instanceof EntityLightsaberRed) 
	        			&& e instanceof EntityLiving && !(e instanceof EntityGrip))
	        	{
	        		e.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getOwner()), 20.0F);
	        	}
	        	else if(e.equals(this.getOwner()) && reachedDestination == true)
	        	{
	        		this.setDead();
	        		EntityPlayer owner = (EntityPlayer) this.getOwner();
	        		if(owner == null) return;
	        		if(owner instanceof EntityPlayerMP)
	        		ModTriggers.SABER_CATCH.trigger((EntityPlayerMP)owner);
	        		if(startedInOffHand && owner.getHeldItemOffhand().getItem() == Items.AIR)
	        		{
	        			owner.setHeldItem(EnumHand.OFF_HAND, new ItemStack(ModItems.ITEM_IGNITEDPURPLELS, 1));
	        		}
	        		else
	        		{
	        			ItemHandlerHelper.giveItemToPlayer((EntityPlayer) this.getOwner(), new ItemStack(ModItems.ITEM_IGNITEDPURPLELS));
	        		}
	        	}
        	}
        }
        
        
        double range = 1.5D;
        if(target != null)
        {
        	if(Math.abs(this.getPosition().getX() - target.getX()) < range && Math.abs(this.getPosition().getY() - target.getY()) < range 
        			&& Math.abs(this.getPosition().getZ() - target.getZ()) < range && !reachedDestination)
        	{
        		setDestinationReached(true);
        	}
        }
        
        
        boolean inLiquid = false;
		//check if the saber is in liquid
		for(int i = -1; i < 2; i++)
		{
			for(int j = -1; j < 2; j++)
			{
				BlockPos p = new BlockPos(this.getPosition().getX() + i, this.getPosition().getY(), this.getPosition().getZ() + j);
				if((world.getBlockState(p).getBlock().getDefaultState() == Blocks.WATER.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.LAVA.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.FLOWING_WATER.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.FLOWING_LAVA.getDefaultState()))
				{
					inLiquid = true;
				}
			}
		}
        
		if(!world.isRemote && inLiquid)
    	{
    		this.setDead();
    		dropItem(ModItems.ITEM_PURPLELS, 1);
    	}
        
		
		if(prevPos != null)
		{
			if(prevPos.equals(this.getPosition()))
			{
				ticksNotMoved++;
			}
			else
			{
				ticksNotMoved = 0;
			}
		}
		
		if(ticksNotMoved >= 20)
		{
			System.out.println("moving up");
			dragonTravelTo(new BlockPos(this.getPosition().getX(), this.getPosition().getY() + 1.0D, this.getPosition().getZ()));
		}
		if(!world.isRemote && ticksNotMoved >= 60)
		{
			this.setDead();
			dropItem(ModItems.ITEM_IGNITEDPURPLELS, 1);
		}
		
		if(ticksExisted >= 200)
		{
			reachedDestination = true;
		}
		
		prevPos = this.getPosition();
    }
	
	@Override
	protected void doBlockCollisions() {
		super.doBlockCollisions();
		int x = getPosition().getX();
		int y = getPosition().getY();
		int z = getPosition().getZ();
		boolean inLiquid = false;
		//check if the saber is in liquid
		for(int i = -1; i < 2; i++)
		{
			for(int j = -1; j < 2; j++)
			{
				BlockPos p = new BlockPos(this.getPosition().getX() + i, this.getPosition().getY(), this.getPosition().getZ() + j);
				if((world.getBlockState(p).getBlock().getDefaultState() == Blocks.WATER.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.LAVA.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.FLOWING_WATER.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.FLOWING_LAVA.getDefaultState()))
				{
					inLiquid = true;
				}
			}
		}
		
		if(!inLiquid)
		{
			for(int i = -1; i < 2; i++)
			{
				for(int j = -1; j < 2; j++)
				{
					BlockPos p = new BlockPos(x + i, y, z + j);
					if(!world.isAirBlock(p) && !(world.getBlockState(p) == Blocks.BEDROCK.getDefaultState()) 
							&& !(world.getBlockState(p).getBlock().getDefaultState() == Blocks.FLOWING_WATER.getDefaultState())
							&& !(world.getBlockState(p).getBlock().getDefaultState() == Blocks.FIRE.getDefaultState())
							&& !(world.getBlockState(p).getBlock().getDefaultState() == Blocks.END_PORTAL.getDefaultState())
							&& !(world.getBlockState(p).getBlock().getDefaultState() == Blocks.FIRE.getDefaultState()))
					{
						if(!world.isRemote)
						world.destroyBlock(p, true);
						//uncomment the line below to make lightsaber return to owner after breaking a block
						//setDestinationReached(true);
					}
				}
			}
		}
	}
	
	public void dragonTravelTo(BlockPos p)
	{
		//from dragon code
		Vec3d vec3d = new Vec3d(p.getX(), p.getY(), p.getZ());

        if (vec3d != null)
        {
            double d6 = vec3d.x - this.posX;
            double d7 = vec3d.y - this.posY;
            double d8 = vec3d.z - this.posZ;
            double d3 = d6 * d6 + d7 * d7 + d8 * d8;
            float f5 = 0.8F;
            d7 = MathHelper.clamp(d7 / (double)MathHelper.sqrt(d6 * d6 + d8 * d8), (double)(-f5), (double)f5);
            this.motionY += d7 * 0.10000000149011612D;
            this.rotationYaw = MathHelper.wrapDegrees(this.rotationYaw);
            double d4 = MathHelper.clamp(MathHelper.wrapDegrees(180.0D - MathHelper.atan2(d6, d8) * (180D / Math.PI) - (double)this.rotationYaw), -50.0D, 50.0D);
            Vec3d vec3d1 = (new Vec3d(vec3d.x - this.posX, vec3d.y - this.posY, vec3d.z - this.posZ)).normalize();
            Vec3d vec3d2 = (new Vec3d((double)MathHelper.sin(this.rotationYaw * 0.017453292F), this.motionY, (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)))).normalize();
            float f7 = Math.max(((float)vec3d2.dotProduct(vec3d1) + 0.5F) / 1.5F, 0.0F);
            this.randomYawVelocity *= 0.8F;
            this.randomYawVelocity = (float)((double)this.randomYawVelocity + d4 * (double)5);
            this.rotationYaw += this.randomYawVelocity * 0.1F;
            float f8 = (float)(2.0D / (d3 + 1.0D));
            float f9 = 0.06F;
            this.moveRelative(0.0F, 0.0F, -1.0F, 0.06F * (f7 * f8 + (1.0F - f8)));

            this.move(MoverType.SELF, this.motionX * 3.0F, this.motionY * 3.0F, this.motionZ * 3.0F);
            
            Vec3d vec3d3 = (new Vec3d(this.motionX, this.motionY, this.motionZ)).normalize();
            float f10 = ((float)vec3d3.dotProduct(vec3d2) + 1.0F) / 2.0F;
            f10 = 0.8F + 0.15F * f10;
            this.motionX *= ((double)f10);
            this.motionZ *= ((double)f10);
            this.motionY *= (0.9100000262260437D);
        }
	}
	
	
	public void setOwnerId(UUID ID)
	{
		this.ownerId = ID;
	}

	@Override
	public UUID getOwnerId() {
		return ownerId;
	}

	@Override
	public Entity getOwner() {
		if(ownerId != null)
		return world.getPlayerEntityByUUID(ownerId);
		return Minecraft.getMinecraft().player;
	}
}
