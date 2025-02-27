package com.alekiponi.firmaciv.common.blockentity;

import com.alekiponi.firmaciv.Firmaciv;
import com.alekiponi.firmaciv.common.entity.BoatVariant;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.alekiponi.firmaciv.common.block.FirmacivBlocks.CANOE_COMPONENT_BLOCKS;
import static com.alekiponi.firmaciv.common.block.FirmacivBlocks.BOAT_FRAME_ANGLED;

public class FirmacivBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES;

    public static final RegistryObject<BlockEntityType<CanoeComponentBlockEntity>> CANOE_COMPONENT_BLOCK_ENTITY;
    public static final RegistryObject<BlockEntityType<BoatFrameBlockEntity>> BOAT_FRAME_BLOCK_ENTITY;

    static {
        BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Firmaciv.MOD_ID);

        CANOE_COMPONENT_BLOCK_ENTITY = register("canoe_component_block_entity", CanoeComponentBlockEntity::new,
                Stream.of(BoatVariant.values()).map(CANOE_COMPONENT_BLOCKS::get));

        BOAT_FRAME_BLOCK_ENTITY = register("boat_frame_block_entity", BoatFrameBlockEntity::new,
                BOAT_FRAME_ANGLED);

    }

    public FirmacivBlockEntities() {
    }

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name,
                                                                                       BlockEntityType.BlockEntitySupplier<T> factory,
                                                                                       Supplier<? extends Block> block) {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, block);
    }

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name,
                                                                                       BlockEntityType.BlockEntitySupplier<T> factory,
                                                                                       Stream<? extends Supplier<? extends Block>> blocks) {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, blocks);
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}