package xyz.iwolfking.sophisticatedvault.mixins;

import net.minecraft.world.level.block.Block;
import net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(value = ModBlocks.class, remap = false)
public class MixinModBlocks {
    @ModifyArg(method = "lambda$static$75", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntityType$Builder;of(Lnet/minecraft/world/level/block/entity/BlockEntityType$BlockEntitySupplier;[Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/world/level/block/entity/BlockEntityType$Builder;", ordinal = 0), index = 1, remap = true)
    private static Block[] addNewShulkerBoxEntities(Block[] validBlocks) {
        ArrayList<Block> entityList = new java.util.ArrayList<>(Arrays.stream(validBlocks).toList());
        entityList.add(xyz.iwolfking.sophisticatedvault.init.ModBlocks.SOPHISTICATED_VAULT_ORNATE_BARREL);
        entityList.add(xyz.iwolfking.sophisticatedvault.init.ModBlocks.SOPHISTICATED_VAULT_GILDED_BARREL);
        entityList.add(xyz.iwolfking.sophisticatedvault.init.ModBlocks.SOPHISTICATED_VAULT_LIVING_BARREL);
        entityList.add(xyz.iwolfking.sophisticatedvault.init.ModBlocks.SOPHISTICATED_VAULT_WOODEN_BARREL);
        return entityList.toArray(new Block[]{});
    }
}
