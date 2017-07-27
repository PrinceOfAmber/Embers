package teamroots.embers.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext; 

import java.util.Random;
import java.util.UUID;

public class MessageRemovePlayerEmber implements IMessage {
    public static Random random = new Random();
    UUID id = null;
    double amount = 0;

    public MessageRemovePlayerEmber() {
        super();
    }

    public MessageRemovePlayerEmber(UUID id, double amount) {
        super();
        this.id = id;
        this.amount = amount;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        id = new UUID(buf.readLong(), buf.readLong());
        amount = buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(id.getMostSignificantBits());
        buf.writeLong(id.getLeastSignificantBits());
        buf.writeDouble(amount);
    }

    public static class MessageHolder implements IMessageHandler<MessageRemovePlayerEmber, IMessage> {
        @Override
        public IMessage onMessage(final MessageRemovePlayerEmber message, final MessageContext ctx) {
            World world = ctx.getServerHandler().player.world;
            EntityPlayer p = world.getPlayerEntityByUUID(message.id);
            if (p != null) {
               // EmberInventoryUtil.removeEmber(p, message.amount);
            }//
            return null;
        }
    }

}
