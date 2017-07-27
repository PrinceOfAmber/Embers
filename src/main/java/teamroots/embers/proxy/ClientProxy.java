package teamroots.embers.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamroots.embers.RegistryManager;
import teamroots.embers.model.ModelManager;
import teamroots.embers.particle.ParticleRenderer;

public class ClientProxy extends CommonProxy {

    public static ParticleRenderer particleRenderer = new ParticleRenderer();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ModelManager.init();
        RegistryManager.registerEntityRendering();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        RegistryManager.registerColorHandlers();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
