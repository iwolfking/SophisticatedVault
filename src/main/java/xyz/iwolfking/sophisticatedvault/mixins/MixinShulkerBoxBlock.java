package xyz.iwolfking.sophisticatedvault.mixins;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.p3pp3rf1y.sophisticatedcore.util.ColorHelper;
import net.p3pp3rf1y.sophisticatedstorage.Config;
import net.p3pp3rf1y.sophisticatedstorage.block.IAdditionalDropDataBlock;
import net.p3pp3rf1y.sophisticatedstorage.block.ITintableBlockItem;
import net.p3pp3rf1y.sophisticatedstorage.block.ShulkerBoxBlock;
import net.p3pp3rf1y.sophisticatedstorage.block.StorageBlockBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.sophisticatedvault.init.ModBlocks;

import java.util.function.Supplier;

@Mixin(value = ShulkerBoxBlock.class)
public abstract class MixinShulkerBoxBlock  extends StorageBlockBase implements IAdditionalDropDataBlock {

    protected MixinShulkerBoxBlock(Properties properties, Supplier<Integer> numberOfInventorySlotsSupplier, Supplier<Integer> numberOfUpgradeSlotsSupplier) {
        super(properties, numberOfInventorySlotsSupplier, numberOfUpgradeSlotsSupplier);
    }

    @Shadow
    public abstract ItemStack getTintedStack(DyeColor color);

    /**
     * @author iwolfking
     * @reason Remove color variations
     */
    @Inject(method = "fillItemCategory", at = @At("HEAD"), cancellable = true)
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items, CallbackInfo ci) {
        if(ModBlocks.BARREL_BLOCKS.contains(this)) {
            items.add(new ItemStack(this));
            ci.cancel();
        }
    }
}
