package com.tguesdon.cobblemon_ability_addon.item;

import com.tguesdon.cobblemon_ability_addon.CobblemonAbilityAddonMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CobblemonAbilityAddonMod.MOD_ID);

    public static final RegistryObject<Item> ABILITY_CAPSULE = ITEMS.register("ability_capsule",
            () -> new AbilityCapsule(new Item.Properties()));

    public static final RegistryObject<Item> ABILITY_PATCH = ITEMS.register("ability_patch",
            () -> new AbilityPatch(new Item.Properties()));
}
