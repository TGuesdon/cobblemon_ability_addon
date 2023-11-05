package com.tguesdon.cobblemon_ability_addon.item;

import com.cobblemon.mod.common.api.interaction.PokemonEntityInteraction;
import com.cobblemon.mod.common.api.storage.StoreCoordinates;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

abstract class PokemonItem extends Item {
    public PokemonItem(Properties arg) {
        super(arg);
    }

    @Override
    @NotNull
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity target, InteractionHand hand) {
        if(player.level().isClientSide()) {
            return use(player.level(), player, hand).getResult();
        }

        if(!(target instanceof PokemonEntity)){
            return InteractionResult.FAIL;
        }

        Pokemon pokemon = ((PokemonEntity) target).getPokemon();
        UUID ownerUUID = pokemon.getOwnerUUID();
        PokemonEntityInteraction.Ownership ownership;

        if(ownerUUID == null) {
            ownership = PokemonEntityInteraction.Ownership.WILD;
        }else if(ownerUUID.toString().equals(player.getUUID().toString())){
            ownership = PokemonEntityInteraction.Ownership.OWNER;
        }else{
            ownership = PokemonEntityInteraction.Ownership.OWNED_ANOTHER;
        }

        if(ownership != PokemonEntityInteraction.Ownership.OWNER) {
            return InteractionResult.FAIL;
        }

        return this.processInteraction(itemStack, player, pokemon, hand);
    }

    abstract InteractionResult processInteraction(ItemStack itemStack, Player player, Pokemon target, InteractionHand hand);
}


