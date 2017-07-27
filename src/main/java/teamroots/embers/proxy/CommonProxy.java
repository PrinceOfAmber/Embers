package teamroots.embers.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import teamroots.embers.Embers;
import teamroots.embers.RegistryManager;
import teamroots.embers.entity.MessageEmberSizedBurstFX; 
 

public class CommonProxy {

  public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Embers.MODID);
    public void preInit(FMLPreInitializationEvent event) {
 //       Fields.init();

     int id = 0;
      INSTANCE.registerMessage(MessageEmberSizedBurstFX.MessageHolder.class, MessageEmberSizedBurstFX.class, id++, Side.CLIENT);
      //INSTANCE.registerMessage(MessageSpawnEmberProj.MessageHolder.class, MessageSpawnEmberProj.class, id++, Side.SERVER);
        RegistryManager.registerAll();
   
 
    }

    public void init(FMLInitializationEvent event) {
 
    }

    public void postInit(FMLPostInitializationEvent event) {
      
    }
}
