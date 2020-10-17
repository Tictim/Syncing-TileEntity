package tictim.rainbowfountain.content;

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
		if(world.getGameTime()%20==0){
			color = RNG.nextInt(0x1000000);
			//아래 메소드 콜은 블럭 색상을 재설정하기 위하여 필요합니다.
			world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), BlockFlags.RERENDER_MAIN_THREAD);
		}
	}
}
