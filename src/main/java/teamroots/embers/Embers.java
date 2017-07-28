package teamroots.embers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.embers.golem.GolemRegistry;
import teamroots.embers.proxy.CommonProxy;

@Mod(modid = Embers.MODID)
public class Embers {
  public static final String MODID = "embers";
  public static final String MODNAME = "Embers";
  @SidedProxy(clientSide = "teamroots.embers.proxy.ClientProxy", serverSide = "teamroots.embers.proxy.ServerProxy")
  public static CommonProxy proxy;
  public static CreativeTabs tab = new CreativeTabs(MODID) {
    @Override
    public String getTabLabel() {
      return MODID;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
      return new ItemStack(Blocks.MOB_SPAWNER);
    }
  };
  @Instance(MODID)
  public static Embers instance;
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    MinecraftForge.EVENT_BUS.register(new GolemRegistry());
    proxy.preInit(event);
  }
}
