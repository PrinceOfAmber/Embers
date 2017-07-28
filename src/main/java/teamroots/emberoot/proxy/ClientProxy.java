package teamroots.emberoot.proxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamroots.emberoot.golem.GolemRegistry;
import teamroots.emberoot.golem.ModelGolem;
import teamroots.emberoot.golem.ParticleRenderer;
import teamroots.emberoot.golem.RenderAncientGolem;

public class ClientProxy extends CommonProxy {
  public static ParticleRenderer particleRenderer = new ParticleRenderer();
  @Override
  public void preInit(FMLPreInitializationEvent event) {
    super.preInit(event);
    RenderAncientGolem.model = new ModelGolem();
    GolemRegistry.registerEntityRendering();
  }
}
