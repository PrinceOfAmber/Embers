package teamroots.embers.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import teamroots.embers.Embers;
import teamroots.embers.RegistryManager;

import teamroots.embers.network.PacketHandler; 
  
import teamroots.embers.util.EmberGenUtil;
 

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
 //       Fields.init();
        PacketHandler.registerMessages();
        RegistryManager.registerAll();
        EmberGenUtil.init();
 
 
    }

    public void init(FMLInitializationEvent event) {
 
    }

    public void postInit(FMLPostInitializationEvent event) {
      
    }
}
