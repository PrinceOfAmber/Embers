package teamroots.embers.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamroots.embers.RegistryManager;
import teamroots.embers.entity.ModelGolem;
import teamroots.embers.entity.RenderAncientGolem;
import teamroots.embers.particle.ParticleRenderer;

public class ClientProxy extends CommonProxy {

    public static ParticleRenderer particleRenderer = new ParticleRenderer();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
      RenderAncientGolem.model =  new ModelGolem();
        RegistryManager.registerEntityRendering();
    }

 
 
}
