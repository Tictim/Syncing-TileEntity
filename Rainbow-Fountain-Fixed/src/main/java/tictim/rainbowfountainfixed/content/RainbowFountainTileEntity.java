package tictim.rainbowfountainfixed.content;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.BlockFlags;

import java.util.Random;

public class RainbowFountainTileEntity extends TileEntity implements ITickableTileEntity{
	private static final Random RNG = new Random();

	private int color = 0xFFFFFF;

	public RainbowFountainTileEntity(){
		super(Contents.RAINBOW_FOUNTAIN_TE.get());
	}

	public int getColor(){
		return color;
	}

	@Override public void tick(){
		if(!world.isRemote&&world.getGameTime()%20==0){
			color = RNG.nextInt(0x1000000);
			world.markAndNotifyBlock(pos, world.getChunkAt(pos), getBlockState(), getBlockState(), BlockFlags.BLOCK_UPDATE, 0);
		}
	}

	@Override public SUpdateTileEntityPacket getUpdatePacket(){
		CompoundNBT nbt = new CompoundNBT();
		nbt.putInt("color", color);
		return new SUpdateTileEntityPacket(pos, 0, nbt);
	}

	@Override public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
		CompoundNBT nbt = pkt.getNbtCompound();
		this.color = nbt.getInt("color");
		//아래 메소드 콜은 블럭 색상을 재설정하기 위하여 필요합니다.
		world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), BlockFlags.RERENDER_MAIN_THREAD);
	}
}
