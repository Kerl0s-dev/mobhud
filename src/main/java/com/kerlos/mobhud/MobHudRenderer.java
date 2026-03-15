package com.kerlos.mobhud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class MobHudRenderer {

    public static void render(DrawContext drawContext) {

        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.player == null || mc.crosshairTarget == null) return;

        HitResult hit = mc.crosshairTarget;

        if (!(hit instanceof EntityHitResult entityHit)) return;
        if (!(entityHit.getEntity() instanceof LivingEntity living)) return;

        float health = living.getHealth();
        float maxHealth = living.getMaxHealth();
        float percent = health / maxHealth;

        int screenWidth = mc.getWindow().getScaledWidth();

        int centerX = screenWidth / 2;
        int y = 20;

        int barWidth = 120;
        int barHeight = 12;

        int filled = (int)(barWidth * percent);

        // nom du mob
        String name = living.getName().getString();

        drawContext.drawText(
                mc.textRenderer,
                name,
                centerX - mc.textRenderer.getWidth(name) / 2,
                y - 12,
                0xFFFFFFFF,
                true
        );

        // fond
        drawContext.fill(
                centerX - barWidth / 2,
                y,
                centerX + barWidth / 2,
                y + barHeight,
                0x90000000
        );

        // changement de couleur de la barre de vie dynamique
        int color;

        if (percent > 0.6f)
            color = 0xFF00FF00;
        else if (percent > 0.3f)
            color = 0xFFFFAA00;
        else
            color = 0xFFFF0000;

        // barre de vie
        drawContext.fill(
                centerX - barWidth / 2,
                y,
                centerX - barWidth / 2 + filled,
                y + barHeight,
                color
        );

        String hp = (int)health + " / " + (int)maxHealth;

        //points de vie
        drawContext.drawText(
                mc.textRenderer,
                hp,
                centerX - mc.textRenderer.getWidth(hp) / 2,
                y + 2,
                0xFFFFFFFF,
                true
        );
    }
}