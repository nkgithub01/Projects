package com.finn_505.lightsabermod.entity.lightsaber;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * saber3 - Finn_505
 * Created using Tabula 7.1.0
 */
public class ModelLightsaber extends ModelBase {
    public ModelRenderer blade;
    public ModelRenderer hiltbase;
    public ModelRenderer bladeGuard;
    public ModelRenderer Jutout1;
    public ModelRenderer Jutout2;
    public ModelRenderer topCircle;
    public ModelRenderer bottomCircle;
    public ModelRenderer trigger;
    public ModelRenderer grip1;
    public ModelRenderer grip2;
    public ModelRenderer grip3;
    public ModelRenderer grip4;
    public ModelRenderer grip5;
    public ModelRenderer grip6;
    public ModelRenderer grip7;
    public ModelRenderer grip8;
    public ModelRenderer bladeInner;
    public ArrayList<ModelRenderer> Lightsaber;
    public ArrayList<ModelRenderer> Blade;


    public ModelLightsaber() {
    	
    	Lightsaber = new ArrayList<ModelRenderer>();
    	Blade = new ArrayList<ModelRenderer>();

    	
        this.textureWidth = 100;
        this.textureHeight = 100;
        this.grip1 = new ModelRenderer(this, 21, 0);
        this.grip1.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.grip1.addBox(0.8F, 0.25F, 11.99F, 1, 1, 6, 0.0F);
        this.trigger = new ModelRenderer(this, 19, 0);
        this.trigger.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.trigger.addBox(1.2F, -0.5F, 8.0F, 1, 1, 3, 0.0F);
        this.grip8 = new ModelRenderer(this, 44, 7);
        this.grip8.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.grip8.addBox(-1.1F, -1.8F, 11.99F, 1, 1, 6, 0.0F);
        this.grip4 = new ModelRenderer(this, 82, 0);
        this.grip4.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.grip4.addBox(-1.15F, 0.8F, 11.99F, 1, 1, 6, 0.0F);
        this.Jutout1 = new ModelRenderer(this, 10, 0);
        this.Jutout1.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.Jutout1.addBox(-1.3F, -1.9F, -0.9F, 1, 1, 2, 0.0F);
        this.grip6 = new ModelRenderer(this, 8, 4);
        this.grip6.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.grip6.addBox(-1.8F, -1.25F, 11.99F, 1, 1, 6, 0.0F);
        this.bladeGuard = new ModelRenderer(this, 0, 0);
        this.bladeGuard.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.bladeGuard.addBox(-1.5F, -1.5F, -2.0F, 3, 1, 2, 0.0F);
        this.bottomCircle = new ModelRenderer(this, 14, 0);
        this.bottomCircle.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.bottomCircle.addBox(-0.5F, 0.8F, 2.0F, 1, 1, 1, 0.0F);
        this.grip5 = new ModelRenderer(this, 0, 3);
        this.grip5.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.grip5.addBox(-1.8F, 0.25F, 11.99F, 1, 1, 6, 0.0F);
        this.grip3 = new ModelRenderer(this, 68, 0);
        this.grip3.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.grip3.addBox(0.25F, 0.8F, 11.99F, 1, 1, 6, 0.0F);
        this.grip2 = new ModelRenderer(this, 44, 0);
        this.grip2.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.grip2.addBox(0.8F, -1.25F, 11.99F, 1, 1, 6, 0.0F);
        this.grip7 = new ModelRenderer(this, 16, 7);
        this.grip7.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.grip7.addBox(0.25F, -1.8F, 11.99F, 1, 1, 6, 0.0F);
        this.hiltbase = new ModelRenderer(this, 44, 0);
        this.hiltbase.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.hiltbase.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 18, 0.0F);
        this.bladeInner = new ModelRenderer(this, 0, 42);
        this.bladeInner.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.bladeInner.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 39, 0.0F);
        this.setRotateAngle(bladeInner, 0.0F, 3.141592653589793F, 0.0F);
        this.Jutout2 = new ModelRenderer(this, 16, 0);
        this.Jutout2.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.Jutout2.addBox(0.3F, -1.9F, -0.9F, 1, 1, 2, 0.0F);
        this.topCircle = new ModelRenderer(this, 8, 0);
        this.topCircle.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.topCircle.addBox(-0.5F, -1.7F, 1.5F, 1, 1, 1, 0.0F);
        this.blade = new ModelRenderer(this, 0, 0);
        this.blade.setRotationPoint(-0.3F, 0.0F, 0.0F);
        this.blade.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 40, 0.0F);
        this.setRotateAngle(blade, 0.0F, 3.141592653589793F, 0.0F);
        
        Lightsaber.add(blade);
        Lightsaber.add(bladeGuard);
        Lightsaber.add(bladeInner);
        Lightsaber.add(topCircle);
        Lightsaber.add(Jutout1);
        Lightsaber.add(Jutout2);
        Lightsaber.add(bottomCircle);
        Lightsaber.add(hiltbase);
        Lightsaber.add(trigger);
        Lightsaber.add(grip1);
        Lightsaber.add(grip2);
        Lightsaber.add(grip3);
        Lightsaber.add(grip4);
        Lightsaber.add(grip5);
        Lightsaber.add(grip6);
        Lightsaber.add(grip7);
        Lightsaber.add(grip8);
        
        Blade.add(blade);
        Blade.add(bladeInner);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.grip1.render(f5);
        this.trigger.render(f5);
        this.grip8.render(f5);
        this.grip4.render(f5);
        this.Jutout1.render(f5);
        this.grip6.render(f5);
        this.bladeGuard.render(f5);
        this.bottomCircle.render(f5);
        this.grip5.render(f5);
        this.grip3.render(f5);
        this.grip2.render(f5);
        this.grip7.render(f5);
        this.hiltbase.render(f5);
        this.bladeInner.render(f5);
        this.Jutout2.render(f5);
        this.topCircle.render(f5);
        this.blade.render(f5);
		//GL11.glScalef(0.5F, 0.5F, 0.5F);
    }

    @Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
    	for(ModelRenderer model: Lightsaber)
    	{
    		model.rotateAngleY = (float) (ageInTicks/2*(Math.PI));
    	}
    	for(ModelRenderer model: Blade)
    	{
    		model.rotateAngleY += Math.PI;
    	}
    }
    
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public static void negateRotationAngles(ModelRenderer modelRenderer)
    {
    	modelRenderer.rotateAngleX = -modelRenderer.rotateAngleX;
        modelRenderer.rotateAngleY = -modelRenderer.rotateAngleY;
        modelRenderer.rotateAngleZ = -modelRenderer.rotateAngleZ;
    }
}
