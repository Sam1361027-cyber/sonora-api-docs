package example.gallery;

import java.util.List;
import jp.suzuk.sonora.api.SonoraAPI;
import jp.suzuk.sonora.api.SonoraEvents;
import jp.suzuk.vhaudio.AudioLibrary;
import jp.suzuk.vhaudio.ServerAudioPlayback;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public final class GalleryAudio {
    private static final ResourceLocation THEME =
            ResourceLocation.fromNamespaceAndPath("gallery", "theme");

    public static SonoraAPI.Playback openRoom(ServerLevel level, BlockPos speaker) {
        return SonoraAPI.playAt(level, speaker, THEME, 4f, 32f, 0.8f);
    }

    public static ServerAudioPlayback startTour(ServerLevel level, ServerPlayer guide) {
        return AudioLibrary.get(level.getServer()).play(THEME, List.of(guide));
    }

    public static void joinTour(ServerAudioPlayback tour, ServerPlayer visitor) {
        tour.addListener(visitor, ServerAudioPlayback.JoinMode.CURRENT_POSITION);
    }

    public static void lowerVolume(SonoraAPI.Playback audio) {
        audio.fadeTo(0.25f, 1500);
    }

    public static void stop(SonoraAPI.Playback audio) {
        audio.close();
    }

    public static SonoraEvents.Subscription eveningBell(ServerLevel level, BlockPos bell) {
        return SonoraEvents.of(level.getServer()).onTime(level, 13000, cue ->
                SonoraAPI.playAt(cue.level(), bell, "gallery:bell", 3f, 20f, 0.6f));
    }
}
