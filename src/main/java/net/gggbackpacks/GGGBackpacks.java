package net.gggbackpacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.gggbackpacks.common.ScreenHandlers.LeatherScreenHandler;
import net.gggbackpacks.core.registry.BlockEntities;
import net.gggbackpacks.core.registry.Blocks;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.gggbackpacks.core.registry.Blocks.LEATHER_BACKPACK_BLOCK;

public class GGGBackpacks implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("gggbackpacks");

	public static final ScreenHandlerType<LeatherScreenHandler> LEATHER_SCREEN_HANDLER;
	
	static {
		LEATHER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier("gggbackpacks","leather_backpack"), LeatherScreenHandler::new);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		
		
		Blocks.register();
		BlockEntities.register();
		LOGGER.info("Hello Fabric world!");
	}
}
