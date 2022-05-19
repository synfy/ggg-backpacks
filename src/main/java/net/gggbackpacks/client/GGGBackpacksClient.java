package net.gggbackpacks.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.gggbackpacks.GGGBackpacks;
import net.gggbackpacks.client.HandledScreens.LeatherScreen;

@Environment(EnvType.CLIENT)
public class GGGBackpacksClient implements ClientModInitializer {
    public void onInitializeClient(){
        ScreenRegistry.register(GGGBackpacks.LEATHER_SCREEN_HANDLER, LeatherScreen::new);
    }
}
