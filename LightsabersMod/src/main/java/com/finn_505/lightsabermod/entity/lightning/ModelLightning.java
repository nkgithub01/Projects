package com.finn_505.lightsabermod.entity.lightning;

import java.util.ArrayList;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Lightning - Finn_505
 * Created using Tabula 7.1.0
 */
public class ModelLightning extends ModelBase {
    public ModelRenderer lightningStart;
    public ModelRenderer lightning;
    public ModelRenderer lightning1;
    public ModelRenderer lightning2;
    public ModelRenderer lightning3;
    public ModelRenderer lightning4;
    public ModelRenderer lightning5;
    public ModelRenderer lightning6;
    public ModelRenderer lightning7;
    public ModelRenderer lightning8;
    public ModelRenderer lightning9;
    public ModelRenderer lightning10;
    public ModelRenderer lightning11;
    public ModelRenderer lightning12;
    public ModelRenderer lightning13;
    public ModelRenderer lightning14;
    public ModelRenderer lightning15;
    public ModelRenderer lightning16;
    public ModelRenderer lightning17;
    public ModelRenderer lightning18;
    public ModelRenderer lightning19;
    public ModelRenderer lightning20;
    public ModelRenderer lightning21;
    public ModelRenderer lightning22;
    public ModelRenderer lightningStart_1;
    public ModelRenderer lightning_1;
    public ModelRenderer lightning1_1;
    public ModelRenderer lightning2_1;
    public ModelRenderer lightning3_1;
    public ModelRenderer lightning4_1;
    public ModelRenderer lightning5_1;
    public ModelRenderer lightning6_1;
    public ModelRenderer lightning7_1;
    public ModelRenderer lightning8_1;
    public ModelRenderer lightning9_1;
    public ModelRenderer lightning10_1;
    public ModelRenderer lightning11_1;
    public ModelRenderer lightning12_1;
    public ModelRenderer lightning13_1;
    public ModelRenderer lightning14_1;
    public ModelRenderer lightning15_1;
    public ModelRenderer lightning16_1;
    public ModelRenderer lightning17_1;
    public ModelRenderer lightning18_1;
    public ModelRenderer lightning19_1;
    public ModelRenderer lightning20_1;
    public ModelRenderer lightning21_1;
    public ModelRenderer lightning22_1;
    public ModelRenderer lightningStart_2;
    public ModelRenderer lightning_2;
    public ModelRenderer lightning1_2;
    public ModelRenderer lightning2_2;
    public ModelRenderer lightning3_2;
    public ModelRenderer lightning4_2;
    public ModelRenderer lightning5_2;
    public ModelRenderer lightning6_2;
    public ModelRenderer lightning7_2;
    public ModelRenderer lightning8_2;
    public ModelRenderer lightning9_2;
    public ModelRenderer lightning10_2;
    public ModelRenderer lightning11_2;
    public ModelRenderer lightning12_2;
    public ModelRenderer lightning13_2;
    public ModelRenderer lightning14_2;
    public ModelRenderer lightning15_2;
    public ModelRenderer lightning16_2;
    public ModelRenderer lightning17_2;
    public ModelRenderer lightning18_2;
    public ModelRenderer lightning19_2;
    public ModelRenderer lightning20_2;
    public ModelRenderer lightning21_2;
    public ModelRenderer lightning22_2;
    public ModelRenderer lightningStart_3;
    public ModelRenderer lightning_3;
    public ModelRenderer lightning1_3;
    public ModelRenderer lightning2_3;
    public ModelRenderer lightning3_3;
    public ModelRenderer lightning4_3;
    public ModelRenderer lightning5_3;
    public ModelRenderer lightning6_3;
    public ModelRenderer lightning7_3;
    public ModelRenderer lightning8_3;
    public ModelRenderer lightning9_3;
    public ModelRenderer lightning10_3;
    public ModelRenderer lightning11_3;
    public ModelRenderer lightning12_3;
    public ModelRenderer lightning13_3;
    public ModelRenderer lightning14_3;
    public ModelRenderer lightning15_3;
    public ModelRenderer lightning16_3;
    public ModelRenderer lightning17_3;
    public ModelRenderer lightning18_3;
    public ModelRenderer lightning19_3;
    public ModelRenderer lightning20_3;
    public ModelRenderer lightning21_3;
    public ModelRenderer lightning22_3;
    
    
    public ArrayList<ModelRenderer> shock;
    public ArrayList<ModelRenderer> shock1;
    public ArrayList<ModelRenderer> shock2;
    public ArrayList<ModelRenderer> shock3;
    
    public int visible;

    public ModelLightning() {
    	
    	this.shock = new ArrayList<ModelRenderer>();
    	this.shock1 = new ArrayList<ModelRenderer>();
    	this.shock2 = new ArrayList<ModelRenderer>();
    	this.shock3 = new ArrayList<ModelRenderer>();
    	
    	this.textureWidth = 32;
        this.textureHeight = 32;
        this.lightning21_1 = new ModelRenderer(this, 0, 0);
        this.lightning21_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning21_1.addBox(9.4F, 0.0F, -33.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning21_1, 0.0F, 0.3029891581462156F, 0.0F);
        this.lightning5_1 = new ModelRenderer(this, 0, 0);
        this.lightning5_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning5_1.addBox(-6.1F, 0.0F, -15.2F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning5_1, 0.0F, -0.5235987755982988F, 0.0F);
        this.lightning15 = new ModelRenderer(this, 0, 0);
        this.lightning15.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning15.addBox(-6.7F, 0.0F, -29.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning15, 0.0F, -0.3415609346152903F, 0.0F);
        this.lightning17_2 = new ModelRenderer(this, 0, 0);
        this.lightning17_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning17_2.addBox(6.0F, 0.0F, -36.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning17_2, 0.0F, 0.268082573106329F, 0.0F);
        this.lightning18_1 = new ModelRenderer(this, 0, 0);
        this.lightning18_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning18_1.addBox(-8.0F, 0.0F, -35.8F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning18_1, 0.0F, -0.26703537555513246F, 0.0F);
        this.lightning9 = new ModelRenderer(this, 0, 0);
        this.lightning9.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning9.addBox(-8.0F, 0.0F, -15.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning9, 0.0F, -0.47123889803846897F, 0.0F);
        this.lightning_1 = new ModelRenderer(this, 0, 0);
        this.lightning_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning_1.addBox(1.5F, 0.0F, -7.4F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning_1, 0.0F, 0.7853981633974483F, 0.0F);
        this.lightning13_3 = new ModelRenderer(this, 0, 0);
        this.lightning13_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning13_3.addBox(-17.9F, 0.0F, -26.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning13_3, 0.0F, -0.5270894341022875F, 0.0F);
        this.lightning2 = new ModelRenderer(this, 0, 0);
        this.lightning2.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning2.addBox(5.0F, 0.0F, -10.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning2, 0.0F, 0.5235987755982988F, 0.0F);
        this.lightning5_3 = new ModelRenderer(this, 0, 0);
        this.lightning5_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning5_3.addBox(-6.1F, 0.0F, -15.2F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning5_3, 0.0F, -0.5235987755982988F, 0.0F);
        this.lightning10_3 = new ModelRenderer(this, 0, 0);
        this.lightning10_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning10_3.addBox(2.7F, 0.0F, -20.1F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning10_3, 0.0F, 0.5235987755982988F, 0.0F);
        this.lightning2_2 = new ModelRenderer(this, 0, 0);
        this.lightning2_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning2_2.addBox(5.0F, 0.0F, -10.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning2_2, 0.0F, 0.5235987755982988F, 0.0F);
        this.lightning22_1 = new ModelRenderer(this, 0, 0);
        this.lightning22_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning22_1.addBox(1.1F, 0.0F, -41.8F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning22_1, 0.0F, 0.136659280431156F, 0.0F);
        this.lightning1_3 = new ModelRenderer(this, 0, 0);
        this.lightning1_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning1_3.addBox(-0.9000000000000004F, 0.0F, -7.4F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning1_3, 0.0F, -0.5235987755982988F, 0.0F);
        this.lightning9_1 = new ModelRenderer(this, 0, 0);
        this.lightning9_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning9_1.addBox(-8.0F, 0.0F, -15.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning9_1, 0.0F, -0.47123889803846897F, 0.0F);
        this.lightning4_1 = new ModelRenderer(this, 0, 0);
        this.lightning4_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning4_1.addBox(-5.2F, 0.0F, -11.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning4_1, 0.0F, -0.18203784098300857F, 0.0F);
        this.lightning20_2 = new ModelRenderer(this, 0, 0);
        this.lightning20_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning20_2.addBox(18.3F, 0.0F, -36.9F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning20_2, 0.0F, 0.47647488579445196F, 0.0F);
        this.lightning8_3 = new ModelRenderer(this, 0, 0);
        this.lightning8_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning8_3.addBox(9.0F, 0.0F, -19.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning8_3, 0.0F, 0.4363323129985824F, 0.0F);
        this.lightning3_1 = new ModelRenderer(this, 14, 7);
        this.lightning3_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning3_1.addBox(-1.9F, 0.0F, 6.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning3_1, 0.0F, 2.86844862565268F, 0.0F);
        this.lightning7_3 = new ModelRenderer(this, 0, 0);
        this.lightning7_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning7_3.addBox(-12.5F, 0.0F, -17.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning7_3, 0.0F, -0.4553564018453205F, 0.0F);
        this.lightning = new ModelRenderer(this, 0, 0);
        this.lightning.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning.addBox(1.5F, 0.0F, -7.4F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning, 0.0F, 0.7853981633974483F, 0.0F);
        this.lightning7_1 = new ModelRenderer(this, 0, 0);
        this.lightning7_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning7_1.addBox(-12.5F, 0.0F, -17.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning7_1, 0.0F, -0.4553564018453205F, 0.0F);
        this.lightning11 = new ModelRenderer(this, 0, 0);
        this.lightning11.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning11.addBox(1.0F, 0.0F, -27.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning11, 0.0F, 0.22759093446006054F, 0.0F);
        this.lightning19 = new ModelRenderer(this, 0, 0);
        this.lightning19.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning19.addBox(-15.3F, 0.0F, -39.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning19, 0.0F, -0.3309144261781249F, 0.0F);
        this.lightning5_2 = new ModelRenderer(this, 0, 0);
        this.lightning5_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning5_2.addBox(-6.1F, 0.0F, -15.2F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning5_2, 0.0F, -0.5235987755982988F, 0.0F);
        this.lightning6_2 = new ModelRenderer(this, 0, 0);
        this.lightning6_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning6_2.addBox(5.0F, 0.0F, -16.6F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning6_2, 0.0F, 0.6632251157578453F, 0.0F);
        this.lightning17 = new ModelRenderer(this, 0, 0);
        this.lightning17.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning17.addBox(6.0F, 0.0F, -36.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning17, 0.0F, 0.268082573106329F, 0.0F);
        this.lightning17_1 = new ModelRenderer(this, 0, 0);
        this.lightning17_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning17_1.addBox(6.0F, 0.0F, -36.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning17_1, 0.0F, 0.268082573106329F, 0.0F);
        this.lightning22_3 = new ModelRenderer(this, 0, 0);
        this.lightning22_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning22_3.addBox(1.0999999999999996F, 0.0F, -41.8F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning22_3, 0.0F, 0.136659280431156F, 0.0F);
        this.lightning8_1 = new ModelRenderer(this, 0, 0);
        this.lightning8_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning8_1.addBox(9.0F, 0.0F, -19.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning8_1, 0.0F, 0.4363323129985824F, 0.0F);
        this.lightning21_2 = new ModelRenderer(this, 0, 0);
        this.lightning21_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning21_2.addBox(9.4F, 0.0F, -33.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning21_2, 0.0F, 0.3029891581462156F, 0.0F);
        this.lightning8 = new ModelRenderer(this, 0, 0);
        this.lightning8.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning8.addBox(9.0F, 0.0F, -19.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning8, 0.0F, 0.4363323129985824F, 0.0F);
        this.lightning11_3 = new ModelRenderer(this, 0, 0);
        this.lightning11_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning11_3.addBox(1.0F, 0.0F, -27.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning11_3, 0.0F, 0.22759093446006054F, 0.0F);
        this.lightning16 = new ModelRenderer(this, 0, 0);
        this.lightning16.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning16.addBox(6.6F, 0.0F, -24.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning16, 0.0F, 0.3077015471266003F, 0.0F);
        this.lightning1_2 = new ModelRenderer(this, 0, 0);
        this.lightning1_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning1_2.addBox(-0.9F, 0.0F, -7.4F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning1_2, 0.0F, -0.5235987755982988F, 0.0F);
        this.lightning6 = new ModelRenderer(this, 0, 0);
        this.lightning6.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning6.addBox(5.0F, 0.0F, -16.6F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning6, 0.0F, 0.6632251157578453F, 0.0F);
        this.lightning1 = new ModelRenderer(this, 0, 0);
        this.lightning1.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning1.addBox(-0.9F, 0.0F, -7.4F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning1, 0.0F, -0.5235987755982988F, 0.0F);
        this.lightning8_2 = new ModelRenderer(this, 0, 0);
        this.lightning8_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning8_2.addBox(9.0F, 0.0F, -19.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning8_2, 0.0F, 0.4363323129985824F, 0.0F);
        this.lightning3 = new ModelRenderer(this, 14, 7);
        this.lightning3.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning3.addBox(-1.9F, 0.0F, 6.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning3, 0.0F, 2.86844862565268F, 0.0F);
        this.lightning16_2 = new ModelRenderer(this, 0, 0);
        this.lightning16_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning16_2.addBox(6.6F, 0.0F, -24.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning16_2, 0.0F, 0.3077015471266003F, 0.0F);
        this.lightning20 = new ModelRenderer(this, 0, 0);
        this.lightning20.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning20.addBox(18.3F, 0.0F, -36.9F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning20, 0.0F, 0.47647488579445196F, 0.0F);
        this.lightning10_1 = new ModelRenderer(this, 0, 0);
        this.lightning10_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning10_1.addBox(2.7F, 0.0F, -20.1F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning10_1, 0.0F, 0.5235987755982988F, 0.0F);
        this.lightning13 = new ModelRenderer(this, 0, 0);
        this.lightning13.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning13.addBox(-17.9F, 0.0F, -26.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning13, 0.0F, -0.5270894341022875F, 0.0F);
        this.lightning6_1 = new ModelRenderer(this, 0, 0);
        this.lightning6_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning6_1.addBox(5.0F, 0.0F, -16.6F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning6_1, 0.0F, 0.6632251157578453F, 0.0F);
        this.lightning4 = new ModelRenderer(this, 0, 0);
        this.lightning4.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning4.addBox(-5.2F, 0.0F, -11.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning4, 0.0F, -0.18203784098300857F, 0.0F);
        this.lightning15_1 = new ModelRenderer(this, 0, 0);
        this.lightning15_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning15_1.addBox(-6.7F, 0.0F, -29.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning15_1, 0.0F, -0.3415609346152903F, 0.0F);
        this.lightning20_1 = new ModelRenderer(this, 0, 0);
        this.lightning20_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning20_1.addBox(18.3F, 0.0F, -36.9F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning20_1, 0.0F, 0.47647488579445196F, 0.0F);
        this.lightning12_1 = new ModelRenderer(this, 0, 0);
        this.lightning12_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning12_1.addBox(-7.3F, 0.0F, -25.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning12_1, 0.0F, -0.36425021489121656F, 0.0F);
        this.lightningStart_1 = new ModelRenderer(this, 0, 0);
        this.lightningStart_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightningStart_1.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        this.lightning1_1 = new ModelRenderer(this, 0, 0);
        this.lightning1_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning1_1.addBox(-0.9F, 0.0F, -7.4F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning1_1, 0.0F, -0.5235987755982988F, 0.0F);
        this.lightning9_2 = new ModelRenderer(this, 0, 0);
        this.lightning9_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning9_2.addBox(-8.0F, 0.0F, -15.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning9_2, 0.0F, -0.47123889803846897F, 0.0F);
        this.lightning19_2 = new ModelRenderer(this, 0, 0);
        this.lightning19_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning19_2.addBox(-15.3F, 0.0F, -39.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning19_2, 0.0F, -0.3309144261781249F, 0.0F);
        this.lightning3_3 = new ModelRenderer(this, 14, 7);
        this.lightning3_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning3_3.addBox(-1.9000000000000004F, 0.0F, 6.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning3_3, 0.0F, 2.86844862565268F, 0.0F);
        this.lightning17_3 = new ModelRenderer(this, 0, 0);
        this.lightning17_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning17_3.addBox(6.0F, 0.0F, -36.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning17_3, 0.0F, 0.268082573106329F, 0.0F);
        this.lightning15_3 = new ModelRenderer(this, 0, 0);
        this.lightning15_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning15_3.addBox(-6.699999999999999F, 0.0F, -29.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning15_3, 0.0F, -0.3415609346152903F, 0.0F);
        this.lightning10 = new ModelRenderer(this, 0, 0);
        this.lightning10.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning10.addBox(2.7F, 0.0F, -20.1F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning10, 0.0F, 0.5235987755982988F, 0.0F);
        this.lightning14_1 = new ModelRenderer(this, 0, 0);
        this.lightning14_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning14_1.addBox(10.3F, 0.0F, -29.7F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning14_1, 0.0F, 0.33213615665452095F, 0.0F);
        this.lightning14_2 = new ModelRenderer(this, 0, 0);
        this.lightning14_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning14_2.addBox(10.3F, 0.0F, -29.7F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning14_2, 0.0F, 0.33213615665452095F, 0.0F);
        this.lightning14_3 = new ModelRenderer(this, 0, 0);
        this.lightning14_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning14_3.addBox(10.3F, 0.0F, -29.7F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning14_3, 0.0F, 0.33213615665452095F, 0.0F);
        this.lightningStart_2 = new ModelRenderer(this, 0, 0);
        this.lightningStart_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightningStart_2.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        this.lightning2_3 = new ModelRenderer(this, 0, 0);
        this.lightning2_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning2_3.addBox(5.0F, 0.0F, -10.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning2_3, 0.0F, 0.5235987755982988F, 0.0F);
        this.lightning12_2 = new ModelRenderer(this, 0, 0);
        this.lightning12_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning12_2.addBox(-7.3F, 0.0F, -25.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning12_2, 0.0F, -0.36425021489121656F, 0.0F);
        this.lightningStart = new ModelRenderer(this, 0, 0);
        this.lightningStart.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightningStart.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        this.lightning7_2 = new ModelRenderer(this, 0, 0);
        this.lightning7_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning7_2.addBox(-12.5F, 0.0F, -17.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning7_2, 0.0F, -0.4553564018453205F, 0.0F);
        this.lightning22_2 = new ModelRenderer(this, 0, 0);
        this.lightning22_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning22_2.addBox(1.1F, 0.0F, -41.8F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning22_2, 0.0F, 0.136659280431156F, 0.0F);
        this.lightning16_1 = new ModelRenderer(this, 0, 0);
        this.lightning16_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning16_1.addBox(6.6F, 0.0F, -24.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning16_1, 0.0F, 0.3077015471266003F, 0.0F);
        this.lightningStart_3 = new ModelRenderer(this, 0, 0);
        this.lightningStart_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightningStart_3.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        this.lightning9_3 = new ModelRenderer(this, 0, 0);
        this.lightning9_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning9_3.addBox(-8.0F, 0.0F, -15.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning9_3, 0.0F, -0.47123889803846897F, 0.0F);
        this.lightning13_2 = new ModelRenderer(this, 0, 0);
        this.lightning13_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning13_2.addBox(-17.9F, 0.0F, -26.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning13_2, 0.0F, -0.5270894341022875F, 0.0F);
        this.lightning3_2 = new ModelRenderer(this, 14, 7);
        this.lightning3_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning3_2.addBox(-1.9F, 0.0F, 6.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning3_2, 0.0F, 2.86844862565268F, 0.0F);
        this.lightning15_2 = new ModelRenderer(this, 0, 0);
        this.lightning15_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning15_2.addBox(-6.7F, 0.0F, -29.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning15_2, 0.0F, -0.3415609346152903F, 0.0F);
        this.lightning13_1 = new ModelRenderer(this, 0, 0);
        this.lightning13_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning13_1.addBox(-17.9F, 0.0F, -26.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning13_1, 0.0F, -0.5270894341022875F, 0.0F);
        this.lightning4_2 = new ModelRenderer(this, 0, 0);
        this.lightning4_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning4_2.addBox(-5.2F, 0.0F, -11.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning4_2, 0.0F, -0.18203784098300857F, 0.0F);
        this.lightning19_3 = new ModelRenderer(this, 0, 0);
        this.lightning19_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning19_3.addBox(-15.3F, 0.0F, -39.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning19_3, 0.0F, -0.3309144261781249F, 0.0F);
        this.lightning2_1 = new ModelRenderer(this, 0, 0);
        this.lightning2_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning2_1.addBox(5.0F, 0.0F, -10.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning2_1, 0.0F, 0.5235987755982988F, 0.0F);
        this.lightning11_2 = new ModelRenderer(this, 0, 0);
        this.lightning11_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning11_2.addBox(1.0F, 0.0F, -27.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning11_2, 0.0F, 0.22759093446006054F, 0.0F);
        this.lightning12_3 = new ModelRenderer(this, 0, 0);
        this.lightning12_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning12_3.addBox(-7.300000000000001F, 0.0F, -25.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning12_3, 0.0F, -0.36425021489121656F, 0.0F);
        this.lightning7 = new ModelRenderer(this, 0, 0);
        this.lightning7.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning7.addBox(-12.5F, 0.0F, -17.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning7, 0.0F, -0.4553564018453205F, 0.0F);
        this.lightning11_1 = new ModelRenderer(this, 0, 0);
        this.lightning11_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning11_1.addBox(1.0F, 0.0F, -27.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning11_1, 0.0F, 0.22759093446006054F, 0.0F);
        this.lightning_3 = new ModelRenderer(this, 0, 0);
        this.lightning_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning_3.addBox(1.5F, 0.0F, -7.4F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning_3, 0.0F, 0.7853981633974483F, 0.0F);
        this.lightning18 = new ModelRenderer(this, 0, 0);
        this.lightning18.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning18.addBox(-8.0F, 0.0F, -35.8F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning18, 0.0F, -0.26703537555513246F, 0.0F);
        this.lightning22 = new ModelRenderer(this, 0, 0);
        this.lightning22.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning22.addBox(1.1F, 0.0F, -41.8F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning22, 0.0F, 0.136659280431156F, 0.0F);
        this.lightning14 = new ModelRenderer(this, 0, 0);
        this.lightning14.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning14.addBox(10.3F, 0.0F, -29.7F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning14, 0.0F, 0.33213615665452095F, 0.0F);
        this.lightning10_2 = new ModelRenderer(this, 0, 0);
        this.lightning10_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning10_2.addBox(2.7F, 0.0F, -20.1F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning10_2, 0.0F, 0.5235987755982988F, 0.0F);
        this.lightning16_3 = new ModelRenderer(this, 0, 0);
        this.lightning16_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning16_3.addBox(6.6F, 0.0F, -24.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning16_3, 0.0F, 0.3077015471266003F, 0.0F);
        this.lightning21 = new ModelRenderer(this, 0, 0);
        this.lightning21.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning21.addBox(9.4F, 0.0F, -33.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning21, 0.0F, 0.3029891581462156F, 0.0F);
        this.lightning18_2 = new ModelRenderer(this, 0, 0);
        this.lightning18_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning18_2.addBox(-8.0F, 0.0F, -35.8F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning18_2, 0.0F, -0.26703537555513246F, 0.0F);
        this.lightning21_3 = new ModelRenderer(this, 0, 0);
        this.lightning21_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning21_3.addBox(9.4F, 0.0F, -33.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning21_3, 0.0F, 0.3029891581462156F, 0.0F);
        this.lightning18_3 = new ModelRenderer(this, 0, 0);
        this.lightning18_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning18_3.addBox(-8.0F, 0.0F, -35.8F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning18_3, 0.0F, -0.26703537555513246F, 0.0F);
        this.lightning20_3 = new ModelRenderer(this, 0, 0);
        this.lightning20_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning20_3.addBox(18.3F, 0.0F, -36.9F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning20_3, 0.0F, 0.47647488579445196F, 0.0F);
        this.lightning19_1 = new ModelRenderer(this, 0, 0);
        this.lightning19_1.setRotationPoint(-2.0F, 9.8F, -0.7999999999999999F);
        this.lightning19_1.addBox(-15.3F, 0.0F, -39.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning19_1, 0.0F, -0.3309144261781249F, 0.0F);
        this.lightning5 = new ModelRenderer(this, 0, 0);
        this.lightning5.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning5.addBox(-6.1F, 0.0F, -15.2F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning5, 0.0F, -0.5235987755982988F, 0.0F);
        this.lightning_2 = new ModelRenderer(this, 0, 0);
        this.lightning_2.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lightning_2.addBox(1.5F, 0.0F, -7.4F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning_2, 0.0F, 0.7853981633974483F, 0.0F);
        this.lightning4_3 = new ModelRenderer(this, 0, 0);
        this.lightning4_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning4_3.addBox(-5.199999999999999F, 0.0F, -11.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning4_3, 0.0F, -0.18203784098300857F, 0.0F);
        this.lightning12 = new ModelRenderer(this, 0, 0);
        this.lightning12.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lightning12.addBox(-7.3F, 0.0F, -25.5F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning12, 0.0F, -0.36425021489121656F, 0.0F);
        this.lightning6_3 = new ModelRenderer(this, 0, 0);
        this.lightning6_3.setRotationPoint(-0.9999999999999999F, 11.3F, -2.7755575615628914E-17F);
        this.lightning6_3.addBox(5.0F, 0.0F, -16.6F, 1, 1, 6, 0.0F);
        this.setRotateAngle(lightning6_3, 0.0F, 0.6632251157578453F, 0.0F);
        
        shock.add(lightning);
        shock.add(lightning1);
        shock.add(lightning2);
        shock.add(lightning3);
        shock.add(lightning4);
        shock.add(lightning5);
        shock.add(lightning6);
        shock.add(lightning7);
        shock.add(lightning8);
        shock.add(lightning9);
        shock.add(lightning10);
        shock.add(lightning11);
        shock.add(lightning12);
        shock.add(lightning13);
        shock.add(lightning14);
        shock.add(lightning15);
        shock.add(lightning16);
        shock.add(lightning17);
        shock.add(lightning18);
        shock.add(lightning19);
        shock.add(lightning20);
        shock.add(lightning21);
        shock.add(lightning22);
        shock.add(lightningStart);
        
        shock1.add(lightning_1);
        shock1.add(lightning1_1);
        shock1.add(lightning2_1);
        shock1.add(lightning3_1);
        shock1.add(lightning4_1);
        shock1.add(lightning5_1);
        shock1.add(lightning6_1);
        shock1.add(lightning7_1);
        shock1.add(lightning8_1);
        shock1.add(lightning9_1);
        shock1.add(lightning10_1);
        shock1.add(lightning11_1);
        shock1.add(lightning12_1);
        shock1.add(lightning13_1);
        shock1.add(lightning14_1);
        shock1.add(lightning15_1);
        shock1.add(lightning16_1);
        shock1.add(lightning17_1);
        shock1.add(lightning18_1);
        shock1.add(lightning19_1);
        shock1.add(lightning20_1);
        shock1.add(lightning21_1);
        shock1.add(lightning22_1);
        shock1.add(lightningStart_1);
        
        shock2.add(lightning_2);
        shock2.add(lightning1_2);
        shock2.add(lightning2_2);
        shock2.add(lightning3_2);
        shock2.add(lightning4_2);
        shock2.add(lightning5_2);
        shock2.add(lightning6_2);
        shock2.add(lightning7_2);
        shock2.add(lightning8_2);
        shock2.add(lightning9_2);
        shock2.add(lightning10_2);
        shock2.add(lightning11_2);
        shock2.add(lightning12_2);
        shock2.add(lightning13_2);
        shock2.add(lightning14_2);
        shock2.add(lightning15_2);
        shock2.add(lightning16_2);
        shock2.add(lightning17_2);
        shock2.add(lightning18_2);
        shock2.add(lightning19_2);
        shock2.add(lightning20_2);
        shock2.add(lightning21_2);
        shock2.add(lightning22_2);
        shock2.add(lightningStart_2);
        
        shock3.add(lightning_3);
        shock3.add(lightning1_3);
        shock3.add(lightning2_3);
        shock3.add(lightning3_3);
        shock3.add(lightning4_3);
        shock3.add(lightning5_3);
        shock3.add(lightning6_3);
        shock3.add(lightning7_3);
        shock3.add(lightning8_3);
        shock3.add(lightning9_3);
        shock3.add(lightning10_3);
        shock3.add(lightning11_3);
        shock3.add(lightning12_3);
        shock3.add(lightning13_3);
        shock3.add(lightning14_3);
        shock3.add(lightning15_3);
        shock3.add(lightning16_3);
        shock3.add(lightning17_3);
        shock3.add(lightning18_3);
        shock3.add(lightning19_3);
        shock3.add(lightning20_3);
        shock3.add(lightning21_3);
        shock3.add(lightning22_3);
        shock3.add(lightningStart_3);
        
        visible = 0;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.lightning15_3.render(f5);
        this.lightning13_1.render(f5);
        this.lightning18_2.render(f5);
        this.lightning6_1.render(f5);
        this.lightning10_1.render(f5);
        this.lightning17_2.render(f5);
        this.lightningStart.render(f5);
        this.lightning14_1.render(f5);
        this.lightning16_3.render(f5);
        this.lightning3_2.render(f5);
        this.lightning11_1.render(f5);
        this.lightning15.render(f5);
        this.lightning7_2.render(f5);
        this.lightning5.render(f5);
        this.lightning12_1.render(f5);
        this.lightning5_1.render(f5);
        this.lightning20_3.render(f5);
        this.lightning2_3.render(f5);
        this.lightning21_3.render(f5);
        this.lightningStart_3.render(f5);
        this.lightning3_3.render(f5);
        this.lightning19.render(f5);
        this.lightning20.render(f5);
        this.lightning21.render(f5);
        this.lightning4.render(f5);
        this.lightning14_2.render(f5);
        this.lightning17_1.render(f5);
        this.lightning9_3.render(f5);
        this.lightning14.render(f5);
        this.lightning20_1.render(f5);
        this.lightning3.render(f5);
        this.lightning9.render(f5);
        this.lightningStart_1.render(f5);
        this.lightning2_2.render(f5);
        this.lightning13_2.render(f5);
        this.lightning18_3.render(f5);
        this.lightning8_2.render(f5);
        this.lightning15_2.render(f5);
        this.lightning21_1.render(f5);
        this.lightning5_2.render(f5);
        this.lightning1_1.render(f5);
        this.lightning_1.render(f5);
        this.lightning16_2.render(f5);
        this.lightning12_2.render(f5);
        this.lightning18_1.render(f5);
        this.lightning17_3.render(f5);
        this.lightning_2.render(f5);
        this.lightning12.render(f5);
        this.lightning22.render(f5);
        this.lightning4_3.render(f5);
        this.lightning4_1.render(f5);
        this.lightning9_1.render(f5);
        this.lightning1_2.render(f5);
        this.lightning10_2.render(f5);
        this.lightning12_3.render(f5);
        this.lightning8_3.render(f5);
        this.lightning22_3.render(f5);
        this.lightning22_1.render(f5);
        this.lightning4_2.render(f5);
        this.lightning10_3.render(f5);
        this.lightningStart_2.render(f5);
        this.lightning2.render(f5);
        this.lightning1_3.render(f5);
        this.lightning19_2.render(f5);
        this.lightning_3.render(f5);
        this.lightning6_3.render(f5);
        this.lightning16.render(f5);
        this.lightning15_1.render(f5);
        this.lightning.render(f5);
        this.lightning6_2.render(f5);
        this.lightning11_2.render(f5);
        this.lightning10.render(f5);
        this.lightning7_1.render(f5);
        this.lightning13.render(f5);
        this.lightning5_3.render(f5);
        this.lightning11.render(f5);
        this.lightning14_3.render(f5);
        this.lightning3_1.render(f5);
        this.lightning9_2.render(f5);
        this.lightning2_1.render(f5);
        this.lightning6.render(f5);
        this.lightning13_3.render(f5);
        this.lightning22_2.render(f5);
        this.lightning8.render(f5);
        this.lightning17.render(f5);
        this.lightning7_3.render(f5);
        this.lightning8_1.render(f5);
        this.lightning19_1.render(f5);
        this.lightning11_3.render(f5);
        this.lightning1.render(f5);
        this.lightning19_3.render(f5);
        this.lightning21_2.render(f5);
        this.lightning18.render(f5);
        this.lightning7.render(f5);
        this.lightning16_1.render(f5);
        this.lightning20_2.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
    		float headPitch, float scaleFactor, Entity entityIn) {
    	super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    	
    	EntityLightning lightning = (EntityLightning)entityIn;
    	
    	if((int)(ageInTicks) % 5 == 0)
    	{
	        visible = (int) (Math.random() * 4);
	        for(int i = 0; i < 4; i++)
	        {
	        	if(i != visible)
	        	{
	        		hideAll(getListById(i));
	        	}
	        	else
	        	{
	        		showAll(getListById(i));
	        	}
	        }
    	}
    	if((int)(ageInTicks) % 2 == 0)
    	{
    		ArrayList<ModelRenderer> list = getListById(visible);
    		randomHide(list.get(3));
    		randomHide(list.get(9));
    		randomHide(list.get(10));
    		randomHide(list.get(15));
    		randomHide(list.get(16));
    		randomHide(list.get(21));
    		randomHide(list.get(22));
    	}
    }
    
    public void randomHide(ModelRenderer model)
    {
    	if(Math.random() > 0.5)
    	{
    		model.isHidden = true;
    	}
    	else
    	{
    		model.isHidden = false;
    	}
    }
    
    public void hideAll(ArrayList<ModelRenderer> list)
    {
    	for(ModelRenderer model: list)
    	{
    		model.isHidden = true;
    	}
    }
    
    public void showAll(ArrayList<ModelRenderer> list)
    {
    	for(ModelRenderer model: list)
    	{
    		model.isHidden = false;
    	}
    }
    
    public ArrayList<ModelRenderer> getListById(int id)
    {
    	switch (id) {
		case 0:
			return shock;
		case 1:
			return shock1;
		case 2:
			return shock2;
		case 3:
			return shock3;
		default:
			return shock;
		}
    }
}