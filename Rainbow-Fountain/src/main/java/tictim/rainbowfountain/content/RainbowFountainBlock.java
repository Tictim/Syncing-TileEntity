package tictim.rainbowfountain.content;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class RainbowFountainBlock extends Block{
	public RainbowFountainBlock(Properties properties){
		super(properties);
	}

	@Override public boolean hasTileEntity(BlockState state){
		return true;
	}
	@Override public TileEntity createTileEntity(BlockState state, IBlockReader world){
		return new RainbowFountainTileEntity();
	}

	@SuppressWarnings("deprecation")
	@Override public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit){
		TileEntity te = world.getTileEntity(pos);
		if(te instanceof RainbowFountainTileEntity){
			RainbowFountainTileEntity rainbowFountain = ((RainbowFountainTileEntity)te);
			ItemStack stack = player.getHeldItem(hand);
			if(!stack.isEmpty()&&stack.getItem() instanceof IDyeableArmorItem){
				IDyeableArmorItem i = (IDyeableArmorItem)stack.getItem();
				i.setColor(stack, rainbowFountain.getColor());
			}
		}
		return ActionResultType.SUCCESS;
	}
}
