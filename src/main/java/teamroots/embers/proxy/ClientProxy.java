package teamroots.embers.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamroots.embers.RegistryManager;
import teamroots.embers.golem.ModelGolem;
import teamroots.embers.golem.RenderAncientGolem;
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
