package com.kerlos.mobhud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.util.Identifier;

public class MobHUD implements ClientModInitializer {
	public static final String MOD_ID = "mobhud";

	@Override
	public void onInitializeClient() {
		HudElementRegistry.addLast(
				Identifier.of(MOD_ID, "entity_hud"),
				(drawContext, tickCounter) -> {
					EntityRenderer.render(drawContext);
				}
		);
	}
}