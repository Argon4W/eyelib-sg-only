package io.github.tt432.eyelib.testmod.client.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.tt432.eyelib.client.render.sections.BlockEntitySectionGeometryRenderer;
import io.github.tt432.eyelib.client.render.sections.SectionGeometryRenderContext;
import io.github.tt432.eyelib.testmod.blocks.TestBenchBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.client.event.AddSectionGeometryEvent;

public class TestBenchBlockEntityRenderer implements BlockEntityRenderer<TestBenchBlockEntity>, BlockEntitySectionGeometryRenderer<TestBenchBlockEntity> {
    @Override
    public void renderSectionGeometry(TestBenchBlockEntity blockEntity, AddSectionGeometryEvent.SectionRenderingContext context, PoseStack poseStack, BlockPos pos, BlockPos regionOrigin, SectionGeometryRenderContext renderAndCacheContext) {
        poseStack.pushPose();
        poseStack.translate(0.5, 0, 0.5);
        renderAndCacheContext.renderCachedEntity(EntityType.BOGGED.create(blockEntity.getLevel()), ResourceLocation.withDefaultNamespace("bogged_cache"), poseStack); //通过Entity实例渲染缓存模型, 参数cacheLocation是缓存模型存放的key
        // renderAndCacheContext.renderUncachedEntity 使用markEntityChunkBuffer注册过的实体渲染时使用, 或当你不知道你渲染的实体是通过什么方式注册, 但你确定他注册过, 那就用这个 (其实这个是最通用的方法)
        // renderAndCacheContext.renderUncachedCutoutEntity 渲染只有单张cutout贴图且RenderType注册过的实体
        // renderAndCacheContext.renderUncachedTranslucentEntity 渲染只有单张translucent贴图且RenderType注册过的实体
        // renderAndCacheContext.renderUncachedMultiEntity 渲染注册了多张贴图的实体时使用
        //如果你确定能获得ModelPart或者其他什么能够renderToBuffer的东西
        //那就是用renderAndCacheContext.getUncachedDynamic(Cutout/Translucent/AllSingle/Multi)BufferSource获取BufferSource
        //AllSingle是对于所有只注册过单张贴图的实体使用的, 用于你确定这个实体注册过, 且只有一张帖图
        //对于只有单张贴图的BufferSource, 返回的VertexConsumer与传入的RenderType无关, 可以为null
        poseStack.popPose();
    }

    @Override
    public void render(TestBenchBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

    }
}
