package teamroots.embers.proxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamroots.embers.golem.GolemRegistry;
import teamroots.embers.golem.ModelGolem;
import teamroots.embers.golem.ParticleRenderer;
import teamroots.embers.golem.RenderAncientGolem;

public class ClientProxy extends CommonProxy {
  public static ParticleRenderer particleRenderer = new ParticleRenderer();
  @Override
  public void preInit(FMLPreInitializationEvent event) {
    super.preInit(event);
    RenderAncientGolem.model = new ModelGolem();
    GolemRegistry.registerEntityRendering();
  }
}
