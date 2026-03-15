package com.kerlos.mobhud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.Objects;

public class EntityRenderer {

    public static void render(DrawContext context) {

        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.player == null || mc.world == null) return;

        MobHudRenderer.render(context);
    }
}