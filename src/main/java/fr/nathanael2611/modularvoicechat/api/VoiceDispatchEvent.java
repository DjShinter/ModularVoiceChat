package fr.nathanael2611.modularvoicechat.api;

import com.google.common.collect.Maps;
import fr.nathanael2611.modularvoicechat.network.objects.VoiceToClient;
import fr.nathanael2611.modularvoicechat.server.VoiceServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.HashMap;

/**
 * Event used to able the modders to create their own VoiceDispatcher.
 */
public class VoiceDispatchEvent extends PlayerEvent
{

    private HashMap<EntityPlayerMP, VoiceToClient> hearList = Maps.newHashMap();

    /* The speaker (sender of the audio-data) */
    private EntityPlayerMP speaker;
    /* The VoiceServer that received the audio-data */
    private VoiceServer voiceServer;
    /* encoded audio-data */
    private byte[] voiceData;

    /**
     * Constructor
     * @param server VoiceServer that received audio-data
     * @param speaker Sender that the audio-data come from
     * @param voiceData Encoded voice-data
     */
    public VoiceDispatchEvent(VoiceServer server, EntityPlayerMP speaker, byte[] voiceData)
    {
        super(speaker);
        this.speaker = speaker;
        this.voiceServer = server;
        this.voiceData = voiceData;
    }

    /**
     * Simply the speaker getter
     * @return the speaker
     */
    public EntityPlayerMP getSpeaker()
    {
        return speaker;
    }

    /**
     * This event is cancelable.
     * @return true
     */
    @Override
    public boolean isCancelable()
    {
        return true;
    }

    /**
     * Simply the VoiceServer getter
     * @return the voice-server
     */
    public VoiceServer getVoiceServer()
    {
        return voiceServer;
    }

    private VoiceToClient getPacket(int volume, VoiceProperties properties)
    {
        return new VoiceToClient(this.getEntityPlayer().getEntityId(), this.voiceData, volume, properties);
    }

    /**
     * Simply return a new voice-packet for the given volume
     * @param volume the volume that we want the audio-data to be played
     * @return the created packet
     */
    private VoiceToClient getPacket(int volume)
    {
        return getPacket(volume, VoiceProperties.empty());
    }

    /**
     * Send voice-data to a specific player, with default volume
     * @param playerMP the player that we want to send the audio-data
     */
    public void dispatchTo(EntityPlayerMP playerMP)
    {
        this.dispatchTo(playerMP, 100);
    }

    /**
     * Send voice-data to a specific player, with custom volume
     * @param playerMP the player that we want to send the audio-data
     * @param voiceVolume the custom volume that we want the audio-data to be played
     */
    public void dispatchTo(EntityPlayerMP playerMP, int voiceVolume)
    {
        this.dispatchTo(playerMP, voiceVolume, VoiceProperties.empty());
    }

    public void dispatchTo(EntityPlayerMP playerMP, int voiceVolume, VoiceProperties properties)
    {
        this.dispatchTo(playerMP, voiceVolume, properties, false);
    }

    public void dispatchTo(EntityPlayerMP playerMP, int voiceVolume, VoiceProperties properties, boolean forceOverride)
    {
        VoiceToClient packet = getPacket(voiceVolume, properties);
        if(!forceOverride && this.hearList.containsKey(playerMP))
        {
            if(voiceVolume < this.hearList.get(playerMP).volumePercent)
            {
                return;
            }
        }
        this.hearList.put(playerMP, packet);
    }

    /**
     * Send audio-data to all players, except the speaker, with the default volume
     */
    public void dispatchToAllExceptSpeaker()
    {
        dispatchToAllExceptSpeaker(100);
    }

    /**
     * Send audio-data to all player, except a specific one
     * @param voiceVolume the custom volume that we want the audio-data to be played
     */
    public void dispatchToAllExceptSpeaker(int voiceVolume)
    {
        this.dispatchToAllExceptSpeaker(voiceVolume, VoiceProperties.empty());
    }

    public void dispatchToAllExceptSpeaker(int voiceVolume, VoiceProperties properties)
    {
        this.dispatchToAllExceptSpeaker(voiceVolume, properties, false);
    }

    /**
     * Send audio-data to all player, except a specific one
     * @param voiceVolume the custom volume that we want the audio-data to be played
     */
    public void dispatchToAllExceptSpeaker(int voiceVolume, VoiceProperties properties, boolean forceOverride)
    {
        for (EntityPlayerMP connectedPlayer : this.voiceServer.getConnectedPlayers())
        {
            this.dispatchTo(connectedPlayer, voiceVolume, properties, forceOverride);
        }
    }

    /**
     * THIS METHOD DO NOT HAVE TO BE USED BY API.
     */
    public void finalizeDispatch()
    {
        this.hearList.forEach((player, packet)-> this.voiceServer.send(player, packet));
    }


}
