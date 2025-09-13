package xyz.iwolfking.sophisticatedvault.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import iskallia.vault.block.VaultBarrelBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.p3pp3rf1y.sophisticatedstorage.block.ShulkerBoxBlock;
import net.p3pp3rf1y.sophisticatedstorage.block.ShulkerBoxBlockEntity;
import net.p3pp3rf1y.sophisticatedstorage.client.render.DisplayItemRenderer;
import net.p3pp3rf1y.sophisticatedstorage.client.render.LockRenderer;
import net.p3pp3rf1y.sophisticatedstorage.client.render.ShulkerBoxRenderer;
import net.p3pp3rf1y.sophisticatedstorage.client.render.StorageRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.sophisticatedvault.init.ModBlocks;

@Mixin(value = ShulkerBoxRenderer.class, remap = false)
public abstract class MixinShulkerBoxRenderer extends StorageRenderer<ShulkerBoxBlockEntity> {
    @Shadow
    @Final
    private DisplayItemRenderer displayItemRenderer;

    @Inject(method = "render(Lnet/p3pp3rf1y/sophisticatedstorage/block/ShulkerBoxBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At("HEAD"), cancellable = true)
    private void renderBarrels(ShulkerBoxBlockEntity shulkerBoxEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, CallbackInfo ci) {
        if(shulkerBoxEntity.getBlockState().getBlock().equals(ModBlocks.SOPHISTICATED_VAULT_ORNATE_BARREL)) {
            sophisticatedvault$renderFallbackBlockModel(shulkerBoxEntity, poseStack, bufferSource, packedLight, packedOverlay);
            ci.cancel();
        }
    }

    @Unique
    private void sophisticatedvault$renderFallbackBlockModel(ShulkerBoxBlockEntity shulkerBoxEntity, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Minecraft mc = Minecraft.getInstance();

        BlockState fallbackState = shulkerBoxEntity.getBlockState();

        BakedModel bakedModel = mc.getBlockRenderer().getBlockModel(fallbackState);

        Direction direction = Direction.UP;

        if (shulkerBoxEntity.hasLevel()) {
            BlockState blockstate = shulkerBoxEntity.getLevel().getBlockState(shulkerBoxEntity.getBlockPos());
            if (blockstate.getBlock() instanceof ShulkerBoxBlock) {
                direction = (Direction)blockstate.getValue(ShulkerBoxBlock.FACING);
            }
        }

        poseStack.pushPose();

        // Translate to the center of the block
        poseStack.translate(0.5D, 0.5D, 0.5D);

        // Scale to make the model slightly smaller (if needed)
        poseStack.scale(0.9995F, 0.9995F, 0.9995F);

        // Rotate based on the direction
        poseStack.mulPose(direction.getRotation());

        // Translate back to position before rotation
        poseStack.translate(-0.5D, -0.5D, -0.5D);

        mc.getBlockRenderer().getModelRenderer().renderModel(
                poseStack.last(),
                bufferSource.getBuffer(RenderType.cutout()),
                fallbackState,
                bakedModel,
                1.0F, 1.0F, 1.0F,
                packedLight,
                packedOverlay
        );

        poseStack.popPose();

        poseStack.pushPose();

        poseStack.translate(0.5D, 0.5D, 0.5D);
        if (shulkerBoxEntity.shouldShowUpgrades() || holdsItemThatShowsUpgrades()) {
            displayItemRenderer.renderUpgradeItems(shulkerBoxEntity, poseStack, bufferSource, packedLight, packedOverlay, holdsItemThatShowsUpgrades(), shouldShowDisabledUpgradesDisplay(shulkerBoxEntity));
        }

        LockRenderer.renderLock(shulkerBoxEntity, poseStack, bufferSource, packedLight, packedOverlay, 0.5125F, () -> false);
        this.displayItemRenderer.renderDisplayItem(shulkerBoxEntity, poseStack, bufferSource, packedLight, packedOverlay);

        poseStack.popPose();
    }

}
