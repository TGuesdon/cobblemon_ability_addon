package com.tguesdon.cobblemon_ability_addon.item;

import com.tguesdon.cobblemon_ability_addon.CobblemonAbilityAddonMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CobblemonAbilityAddonMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> COBBLEMON_ABILITY_TAB = TABS.register("cobblemon_ability_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.cobblemon_ability_tab"))
                    .icon(ModItems.ABILITY_CAPSULE.get()::getDefaultInstance)
                    .displayItems((displayParameters, output) -> {
                        output.accept(ModItems.ABILITY_CAPSULE.get());
                        output.accept(ModItems.ABILITY_PATCH.get());
                    })
                    .build()
    );
}
