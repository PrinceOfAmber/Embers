package teamroots.embers.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import teamroots.embers.EventManager;
import teamroots.embers.block.BlockEmberInjector;
import teamroots.embers.particle.ParticleUtil;
import teamroots.embers.power.DefaultEmberCapability;
import teamroots.embers.power.EmberCapabilityProvider;
import teamroots.embers.power.IEmberCapability;

import javax.annotation.Nullable;
import java.util.Random;

public class TileEntityEmberInjector extends TileEntity implements ITileEntityBase, ITickable {
    public IEmberCapability capability = new DefaultEmberCapability();
    public boolean dirty = false;
    int ticksExisted = 0;
    int progress = -1;
    Random random = new Random();

    public TileEntityEmberInjector() {
        super();
        capability.setEmberCapacity(24000);
        capability.setEmber(0);
    }

    @Override
    public void markForUpdate() {
        EventManager.markTEForUpdate(getPos(), this);
    }

    @Override
    public void markDirty() {
        markForUpdate();
        super.markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        capability.writeToNBT(tag);
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        capability.readFromNBT(tag);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == EmberCapabilityProvider.emberCapability) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == EmberCapabilityProvider.emberCapability) {
            return (T) this.capability;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean activate(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
                            EnumFacing side, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        this.invalidate();
        world.setTileEntity(pos, null);
    }

    @Override
    public void update() {
        IBlockState state = world.getBlockState(getPos());
        TileEntity tile = world.getTileEntity(pos.offset(state.getValue(BlockEmberInjector.facing)));
        if (tile instanceof TileEntitySeed && capability.getEmber() > 1.0) {
            ((TileEntitySeed) tile).size++;
            tile.markDirty();
            this.capability.removeAmount(1.0, true);
            markDirty();
            if (world.isRemote) {
                for (int i = 0; i < 2; i++) {
                    ParticleUtil.spawnParticleLineGlow(world, pos.getX() + 0.5f + 0.25f * (random.nextFloat() - 0.5f), pos.getY() + 0.625f, pos.getZ() + 0.5f + 0.25f * (random.nextFloat() - 0.5f), tile.getPos().getX() + 0.5f + state.getValue(BlockEmberInjector.facing).getDirectionVec().getX() + 0.5f * (random.nextFloat() - 0.5f), tile.getPos().getY() + 0.5f + state.getValue(BlockEmberInjector.facing).getDirectionVec().getY() + 0.5f * (random.nextFloat() - 0.5f), tile.getPos().getZ() + 0.5f + state.getValue(BlockEmberInjector.facing).getDirectionVec().getZ() + 0.5f * (random.nextFloat() - 0.5f), 255, 64, 16, 4.0f + random.nextFloat() * 2.0f, 40);
                }
            }
        }
    }
}
