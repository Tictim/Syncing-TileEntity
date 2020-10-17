package tictim.rainbowfountainfixed.content;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static tictim.rainbowfountainfixed.RainbowFountainFixed.MODID;

public final class Contents{
	private Contents(){}

	public static final ItemGroup GROUP = new ItemGroup(MODID){
		@Override public ItemStack createIcon(){
			return new ItemStack(RAINBOW_FOUNTAIN_ITEM.get());
		}
	};

	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);

	public static final RegistryObject<Block> RAINBOW_FOUNTAIN = BLOCKS.register("rainbow_fountain",
			() -> new RainbowFountainBlock(Block.Properties.create(Material.ROCK).setLightLevel(s -> 15)));
	public static final RegistryObject<Item> RAINBOW_FOUNTAIN_ITEM = ITEMS.register("rainbow_fountain", () -> new BlockItem(RAINBOW_FOUNTAIN.get(), new Item.Properties().group(GROUP)));
	public static final RegistryObject<TileEntityType<RainbowFountainTileEntity>> RAINBOW_FOUNTAIN_TE = TILE_ENTITIES.register("rainbow_fountain",
			() -> TileEntityType.Builder.create(RainbowFountainTileEntity::new, RAINBOW_FOUNTAIN.get()).build(null));

	public static void registerEventHandlers(IEventBus modEventBus){
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
		TILE_ENTITIES.register(modEventBus);
	}
}
