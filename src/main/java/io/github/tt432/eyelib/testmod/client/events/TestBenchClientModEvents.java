package io.github.tt432.eyelib.testmod.client.events;

import io.github.tt432.eyelib.Eyelib;
import io.github.tt432.eyelib.client.render.sections.dynamic.DynamicChunkBuffers;
import io.github.tt432.eyelib.client.render.sections.events.ReloadDynamicChunkBufferEvent;
import io.github.tt432.eyelib.testmod.EyelibTestMod;
import io.github.tt432.eyelib.testmod.client.blocks.TestBenchBlockEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Eyelib.MOD_ID, value = Dist.CLIENT)
public class TestBenchClientModEvents {
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(EyelibTestMod.TEST_BENCH_BLOCK_ENTITY_TYPE.get(), context -> new TestBenchBlockEntityRenderer());
    }

    //这个Event会在客户端启动和资源包重载后触发, 无需担心重复注册, DynamicChunkBuffers内部使用computeIfAbsent
    @SubscribeEvent
    public static void onReloadDynamicChunkBuffers(ReloadDynamicChunkBufferEvent event) {
        DynamicChunkBuffers.markMultiCutoutChunkBuffer(EntityType.BOGGED, ResourceLocation.withDefaultNamespace("textures/entity/skeleton/bogged.png"), ResourceLocation.withDefaultNamespace("textures/entity/skeleton/bogged_overlay.png")); //注册带有多个cutout贴图的实体RenderType (这里是bogged) (常用)
        // DynamicChunkBuffers.markCutoutChunkBuffer 注册只有一张cutout贴图的实体RenderType (常用)
        // DynamicChunkBuffers.markTranslucentChunkBuffer 注册只有一张translucent贴图的实体RenderType
        // DynamicChunkBuffers.markMultiTranslucentChunkBuffer 注册带有多个translucent贴图的实体RenderType
        // DynamicChunkBuffers.markEntityChunkBuffer 直接传入Entity实例进行dummy渲染收集需要的RenderType并录入Chunk Buffer

        // 所有使用EntityType存入ChunkBuffer的key都为对应EntityType所注册EntityRenderer的getTextureLocation,
        // 因此在是用SectionGeometryRenderContext.getUncachedDynamic(Cutout/Translucent/AllSingle/Multi)BufferSource的ResourceLocation参数的重载时,
        // 为获取渲染对应EntityType的实体所需要的BufferSource/VertexConsumer, 应传入EntityType所对应EntityRenderer的getTextureLocation
    }
}
