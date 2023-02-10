package com.finn_505.lightsabermod.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.init.ModItems;
import com.finn_505.lightsabermod.init.ModSoundEvents;
import com.finn_505.lightsabermod.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Lightsaber extends CustomSword
{
	private final float attackDamage;
	public int color;
	
    public Lightsaber(String name)
    {
    	super(name);
        this.maxStackSize = 1;
        this.setMaxDamage(1560);
        this.attackDamage = 3.0F + 13.0F;
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.color = 0;
        ModItems.ITEMS.add(this);
    }
    
    public Lightsaber(String name, int color)
    {
    	super(name);
        this.maxStackSize = 1;
        this.setMaxDamage(1560);
        this.attackDamage = 3.0F + 13.0F;
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.color = color;
        ModItems.ITEMS.add(this);
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		if(playerIn.isSneaking() && !worldIn.isRemote)
		{	
			worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundEvents.LIGHTSABER_OFF, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			playerIn.setHeldItem(handIn, new ItemStack(ModItems.HILTS[color], 1));
			SoundManager mng = ReflectionHelper.getPrivateValue(SoundHandler.class, 
				      Minecraft.getMinecraft().getSoundHandler(), "sndManager");
				Map playingSounds = ReflectionHelper.getPrivateValue(SoundManager.class, 
				      mng, "playingSounds");
				Iterator it = playingSounds.keySet().iterator();
				while(it.hasNext())
				{
				   PositionedSound ps = (PositionedSound)playingSounds.get(it.next());
				   ResourceLocation reloc = ReflectionHelper.getPrivateValue(PositionedSound.class, 
				         ps, "positionedSoundLocation");
				   if(("lightsaber_idle").equals(reloc.getResourcePath()))
				   {
				      Minecraft.getMinecraft().getSoundHandler().stopSound(ps);
				      break;
				   }
				}
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		}
		else
		{
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		}
    }
	
	public String getItemStackDisplayName(ItemStack stack)
    {
		if(color == 0)
		{
			return "Green Lightsaber";
		}
		else if(color == 1)
		{
			return "Blue Lightsaber";
		}
		else if(color == 2)
		{
			return "Purple Lightsaber";
		}
		else if(color == 3)
		{
			return "Red Lightsaber";
		}
		else 
		{
			return "Green Lightsaber";
		}
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		ArrayList<KeyBinding> binds = Main.keyBinds;
		tooltip.add("Sneak + " + Minecraft.getMinecraft().gameSettings.keyBindUseItem.getDisplayName() + " to turn off");
		tooltip.add(binds.get(1).getDisplayName() + " to throw");
		tooltip.add("Hold " + binds.get(0).getDisplayName() + " to deflect projectiles");
		tooltip.add("Does not fare well in water or lava");
		super.addInformation(stack, worldIn, tooltip, flagIn);
    }
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        return true;
    }
}
