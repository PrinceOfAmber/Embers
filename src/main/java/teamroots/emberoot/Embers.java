package teamroots.emberoot;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.emberoot.golem.GolemRegistry;
import teamroots.emberoot.proxy.CommonProxy;

@Mod(modid = Const.MODID)
public class Embers {
  public static final String MODNAME = "Ember Root";
  @SidedProxy(clientSide = "teamroots." + Const.MODID + ".proxy.ClientProxy", serverSide = "teamroots." + Const.MODID + ".proxy.ServerProxy")
  public static CommonProxy proxy;
  public static CreativeTabs tab = new CreativeTabs(Const.MODID) {
    @Override
    public String getTabLabel() {
      return Const.MODID;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
      return new ItemStack(Blocks.MOB_SPAWNER);
    }
  };
  @Instance(Const.MODID)
  public static Embers instance;
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    MinecraftForge.EVENT_BUS.register(new GolemRegistry());
    proxy.preInit(event);
  }
}
