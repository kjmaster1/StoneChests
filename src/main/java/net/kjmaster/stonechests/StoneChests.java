package net.kjmaster.stonechests;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Logger;

public class StoneChests implements ModInitializer {

	public static final String MODID = "stonechests";
    public static Logger logger;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModBlocks.init();
	}
}
