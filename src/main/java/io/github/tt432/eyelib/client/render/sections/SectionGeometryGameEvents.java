package io.github.tt432.eyelib.client.render.sections;

import io.github.tt432.eyelib.Eyelib;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.AddSectionGeometryEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = Eyelib.MOD_ID, value = Dist.CLIENT)
public class SectionGeometryGameEvents {
    @SubscribeEvent
    public static void addSectionGeometry(AddSectionGeometryEvent event) {
        event.addRenderer(new SectionGeometryBlockEntityRenderDispatcher(event.getSectionOrigin().immutable()));
    }
}
