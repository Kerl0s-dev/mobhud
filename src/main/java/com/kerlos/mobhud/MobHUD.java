package com.kerlos.mobhud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.util.Identifier;

import static net.fabricmc.fabric.impl.client.indigo.Indigo.LOGGER;

public class MobHUD implements ClientModInitializer {
	public static final String MOD_ID = "mobhud";

	@Override
	public void onInitializeClient() {
		LOGGER.debug("Mob Hud successfully loaded");

		HudElementRegistry.addLast(
				Identifier.of(MOD_ID, "entity_hud"),
				(drawContext, tickCounter) -> {
					EntityRenderer.render(drawContext);
				}
		);
	}
}