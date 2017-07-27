package teamroots.embers.network;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import teamroots.embers.Embers;
import teamroots.embers.network.message.*;

public class PacketHandler {
  public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Embers.MODID);
  private static int id = 0;
  public static void registerMessages() {
    //INSTANCE.registerMessage(MessageEmberSparkleFX.MessageHolder.class, MessageEmberSparkleFX.class, id++, Side.CLIENT);
    INSTANCE.registerMessage(MessageEmberSizedBurstFX.MessageHolder.class, MessageEmberSizedBurstFX.class, id++, Side.CLIENT);
    INSTANCE.registerMessage(MessageSpawnEmberProj.MessageHolder.class, MessageSpawnEmberProj.class, id++, Side.SERVER);
  }
}
