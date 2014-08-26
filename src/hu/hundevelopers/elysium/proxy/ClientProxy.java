package hu.hundevelopers.elysium.proxy;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.entity.EntityCaterPillar;
import hu.hundevelopers.elysium.entity.EntityDeer;
import hu.hundevelopers.elysium.entity.EntityPinkUnicorn;
import hu.hundevelopers.elysium.entity.EntitySwan;
import hu.hundevelopers.elysium.entity.EntityVoidSpecter;
import hu.hundevelopers.elysium.model.ModelPinkUnicorn;
import hu.hundevelopers.elysium.render.ElysiumTileEntityPortalRenderer;
import hu.hundevelopers.elysium.render.RenderCaterPillar;
import hu.hundevelopers.elysium.render.RenderDeer;
import hu.hundevelopers.elysium.render.RenderPinkUnicorn;
import hu.hundevelopers.elysium.render.RenderSwan;
import hu.hundevelopers.elysium.render.RenderVoidSpecter;
import hu.hundevelopers.elysium.render.StaffRenderer;
import hu.hundevelopers.elysium.tile.ElysianTileEntityPortal;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	/* INSTANCES */
	public Object getClient() {
		return FMLClientHandler.instance().getClient();
	}

	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void registerRenderers()
	{
//	FIXME	MainMenuAPI.registerMenu("Elysian Menu", ElysianMenu.class);
//		MainMenuAPI.registerMenu("Misc Elysian Menu", MiscElysianMenu.class);

		Elysium.pipeStoneReinderingID = RenderingRegistry.getNextAvailableRenderId();

		ClientRegistry.bindTileEntitySpecialRenderer(ElysianTileEntityPortal.class, new ElysiumTileEntityPortalRenderer());

		MinecraftForgeClient.registerItemRenderer(Elysium.itemStaff, new StaffRenderer());

		
//		RenderingRegistry.registerBlockHandler(new CrystalBlockRendererOBJ());

		RenderingRegistry.registerEntityRenderingHandler(EntityCaterPillar.class, new RenderCaterPillar());
		RenderingRegistry.registerEntityRenderingHandler(EntitySwan.class, new RenderSwan());
		RenderingRegistry.registerEntityRenderingHandler(EntityDeer.class, new RenderDeer());
		RenderingRegistry.registerEntityRenderingHandler(EntityPinkUnicorn.class, new RenderPinkUnicorn(new ModelPinkUnicorn()));
		RenderingRegistry.registerEntityRenderingHandler(EntityVoidSpecter.class, new RenderVoidSpecter());

//		TickRegistry.registerTickHandler(new ElysianClientTickHandler(), Side.CLIENT);
	

	}

	/* NETWORKING */
	@Override
	public void sendToServer(Packet packet) {
		FMLClientHandler.instance().getClient().getNetHandler().addToSendQueue(packet);
	}

	/*@Override
	public void sendToPlayer(EntityPlayer entityplayer, ElysiumPacket packet){}
	
	@Override
	public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance){}*/

	@Override
	public String playerName() {
		return FMLClientHandler.instance().getClient().thePlayer.getCommandSenderName();
	}

}
