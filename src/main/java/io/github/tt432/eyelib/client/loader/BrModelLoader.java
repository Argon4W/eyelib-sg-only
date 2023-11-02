package io.github.tt432.eyelib.client.loader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.github.tt432.eyelib.client.model.bedrock.BrModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TT432
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BrModelLoader extends SimpleJsonResourceReloadListener {
    private static final BrModelLoader INSTANCE = new BrModelLoader(new Gson(), "models/bedrock");

    @SubscribeEvent
    public static void onEvent(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(INSTANCE);
    }

    private final Map<ResourceLocation, BrModel> models = new HashMap<>();

    public static BrModel getModel(ResourceLocation location) {
        return INSTANCE.models.get(location);
    }

    private BrModelLoader(Gson pGson, String pDirectory) {
        super(pGson, pDirectory);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        models.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : pObject.entrySet()) {
            models.put(entry.getKey(), BrModel.parse(entry.getKey().toString(), entry.getValue().getAsJsonObject()));
        }
    }
}
