package tictim.rainbowfountain;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tictim.rainbowfountain.content.Contents;
import tictim.rainbowfountain.content.RainbowFountainTileEntity;

@Mod(RainbowFountain.MODID)
public class RainbowFountain{
	public static final String MODID = "rainbowfountain";

	public RainbowFountain(){
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		Contents.registerEventHandlers(modEventBus);
	}

	@Mod.EventBusSubscriber(modid = MODID, bus = Bus.MOD, value = Dist.CLIENT)
	public static final class ClientHandler{
		private ClientHandler(){}

		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event){
			RenderTypeLookup.setRenderLayer(Contents.RAINBOW_FOUNTAIN.get(), RenderType.getTranslucent());
		}

		@SubscribeEvent
		public static void registerBlockColor(ColorHandlerEvent.Block event){
			event.getBlockColors().register((state, reader, pos, index) -> {
				if(reader==null||pos==null) return -1;
				TileEntity te = reader.getTileEntity(pos);
				return te instanceof RainbowFountainTileEntity ?
						((RainbowFountainTileEntity)te).getColor() :
						-1;
			}, Contents.RAINBOW_FOUNTAIN.get());
		}
	}
}
