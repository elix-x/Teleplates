package code.elix_x.mods.teleplates.net;

import io.netty.buffer.ByteBuf;

import java.util.UUID;

import code.elix_x.mods.teleplates.teleplates.TeleportationManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class TeleportToTeleplateMessage implements IMessage{

	private int teleplate;
	
	public TeleportToTeleplateMessage() {
		
	}
	
	public TeleportToTeleplateMessage(int teleplateId) {
		this.teleplate = teleplateId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		teleplate = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(teleplate);
	}
	
	public static class TeleportToTeleplateMessageHandler implements IMessageHandler<TeleportToTeleplateMessage, IMessage>{

		@Override
		public IMessage onMessage(TeleportToTeleplateMessage message, MessageContext ctx) {
			TeleportationManager.teleport(ctx.getServerHandler().playerEntity, message.teleplate);
			return null;
		}
		
	}

}
