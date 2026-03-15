package com.kerlos.mobhud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.EntityHitResult;

public class MobHudRenderer {

    private static final int BAR_WIDTH = 120;
    private static final int BAR_HEIGHT = 12;
    private static final int Y = 20;

    public static void render(DrawContext ctx) {

        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.player == null || !(mc.crosshairTarget instanceof EntityHitResult hit)) return;
        if (!(hit.getEntity() instanceof LivingEntity living)) return;

        float health = living.getHealth();
        float maxHealth = living.getMaxHealth();
        float percent = health / maxHealth;

        int centerX = mc.getWindow().getScaledWidth() / 2;
        int barX = centerX - BAR_WIDTH / 2;

        int filled = (int) (BAR_WIDTH * percent);

        String name = living.getName().getString();
        String hp = (int)health + " / " + (int) maxHealth;

        var text = mc.textRenderer;

        // Nom du mob
        ctx.drawText(
                text,
                name,
                centerX - text.getWidth(name) / 2,
                Y - 12,
                0xFFFFFFFF,
                true
        );

        // Fond de la barre
        ctx.fill(
                barX,
                Y,
                barX + BAR_WIDTH,
                Y + BAR_HEIGHT,
                0x90000000
        );

        // Barre de vie
        ctx.fill(
                barX,
                Y,
                barX + filled,
                Y + BAR_HEIGHT,
                getHealthColor(percent)
        );

        // Texte HP
        ctx.drawText(
                text,
                hp,
                centerX - text.getWidth(hp) / 2,
                Y + 2,
                0xFFFFFFFF,
                true
        );
    }

    private static int getHealthColor(float percent) {
        if (percent > 0.6f) return 0xFF00FF00;
        if (percent > 0.3f) return 0xFFFFAA00;
        return 0xFFFF0000;
    }
}