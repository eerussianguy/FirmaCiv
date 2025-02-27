package com.alekiponi.firmaciv.mixins.minecraft;

import com.alekiponi.firmaciv.events.config.FirmacivConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Boat.class)
public class BoatMixin extends Entity implements net.minecraftforge.common.extensions.IForgeBoat {

    public BoatMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Inject(method = "canAddPassenger", at = @At("HEAD"))
    public void injectStopAddingPassengers(Entity pPassenger, CallbackInfoReturnable<Boolean> cir){
        if (FirmacivConfig.SERVER.disableVanillaBoatFunctionality.get()) {
            cir.setReturnValue(false);
        }
    }


    @Shadow
    protected void defineSynchedData() {

    }

    @Shadow
    protected int getMaxPassengers() {
        return 2;
    }

    @Shadow
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Shadow
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }
}
