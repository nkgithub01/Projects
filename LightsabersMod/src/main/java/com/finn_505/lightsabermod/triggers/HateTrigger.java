package com.finn_505.lightsabermod.triggers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.advancements.critereon.NBTPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class HateTrigger implements ICriterionTrigger<HateTrigger.Instance>{
	
	public ResourceLocation location;
	private final Map<PlayerAdvancements, HateTrigger.Listeners> listeners = Maps.<PlayerAdvancements, HateTrigger.Listeners>newHashMap();
			
	public HateTrigger(ResourceLocation location) {
		super();
		this.location = location;
	}
	
	public HateTrigger(String location) {
		super();
		this.location = new ResourceLocation(location);
	}
	
	@Override
	public ResourceLocation getId() {
		return location;
	}

	@Override
	public void addListener(PlayerAdvancements playerAdvancementsIn, Listener<Instance> listener) {
		HateTrigger.Listeners hatetrigger$listeners = listeners.get(playerAdvancementsIn);
		
		if (hatetrigger$listeners == null)
        {
            hatetrigger$listeners = new HateTrigger.Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, hatetrigger$listeners);
        }

        hatetrigger$listeners.add(listener);
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancementsIn, Listener<Instance> listener) {
		HateTrigger.Listeners hatetrigger$listeners = this.listeners.get(playerAdvancementsIn);

        if (hatetrigger$listeners != null)
        {
            hatetrigger$listeners.remove(listener);

            if (hatetrigger$listeners.isEmpty())
            {
                this.listeners.remove(playerAdvancementsIn);
            }
        }
	}

	@Override
	public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) 
	{
		this.listeners.remove(playerAdvancementsIn);
	}

	@Override
	public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		return new HateTrigger.Instance(getId());
	}

	public void trigger(EntityPlayerMP player)
    {
        HateTrigger.Listeners hatetrigger$listeners = this.listeners.get(player.getAdvancements());

        if (hatetrigger$listeners != null)
        {
            hatetrigger$listeners.trigger(player);
        }
    }
	
	public class Instance extends AbstractCriterionInstance{

		
		public Instance(ResourceLocation rl)
		{
			super(rl);
		}
		
		public boolean test(EntityPlayer player)
		{
			return true;
		}
	}
	
	static class Listeners
	{
		private final PlayerAdvancements playerAdvancements;
        private final Set<ICriterionTrigger.Listener<HateTrigger.Instance>> listeners = Sets.<ICriterionTrigger.Listener<HateTrigger.Instance>>newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn)
        {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty()
        {
            return this.listeners.isEmpty();
        }

        public void add(ICriterionTrigger.Listener<HateTrigger.Instance> listener)
        {
            this.listeners.add(listener);
        }

        public void remove(ICriterionTrigger.Listener<HateTrigger.Instance> listener)
        {
            this.listeners.remove(listener);
        }
        
        public void trigger(EntityPlayer player)
        {
        	List<ICriterionTrigger.Listener<HateTrigger.Instance>> list = null;

            for (ICriterionTrigger.Listener<HateTrigger.Instance> listener : this.listeners)
            {
                if (((HateTrigger.Instance)listener.getCriterionInstance()).test(player))
                {
                    if (list == null)
                    {
                        list = Lists.<ICriterionTrigger.Listener<HateTrigger.Instance>>newArrayList();
                    }

                    list.add(listener);
                }
            }
            
            if (list != null)
            {
                for (ICriterionTrigger.Listener<HateTrigger.Instance> listener1 : list)
                {
                    listener1.grantCriterion(this.playerAdvancements);
                }
            }
        }
	}
}
