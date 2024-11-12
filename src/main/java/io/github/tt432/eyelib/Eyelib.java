package io.github.tt432.eyelib;

import io.github.tt432.eyelib.testmod.EyelibTestMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

/**
 * @author TT432
 */
@Mod(Eyelib.MOD_ID)
public class Eyelib {
    public static final String MOD_ID = "eyelib";

    public Eyelib(IEventBus bus) {
        EyelibTestMod.register(bus);
    }
}
