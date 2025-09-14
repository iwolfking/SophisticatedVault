package xyz.iwolfking.sophisticatedvault.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.p3pp3rf1y.sophisticatedstorage.block.ShulkerBoxBlockEntity;
import net.p3pp3rf1y.sophisticatedstorage.block.StorageBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.sophisticatedvault.init.ModBlocks;

@Mixin(value = ShulkerBoxBlockEntity.class, remap = false)
public abstract class MixinShulkerBoxEntity extends StorageBlockEntity {
    protected MixinShulkerBoxEntity(BlockPos pos, BlockState state, BlockEntityType<? extends StorageBlockEntity> blockEntityType) {
        super(pos, state, blockEntityType);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private static void dontUseLidForBarrels(Level level, BlockPos pos, BlockState state, ShulkerBoxBlockEntity blockEntity, CallbackInfo ci) {
        if(ModBlocks.BARREL_BLOCKS.contains(state.getBlock())) {
            ci.cancel();
        }
    }

}
