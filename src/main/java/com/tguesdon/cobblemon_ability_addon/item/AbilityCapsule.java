package com.tguesdon.cobblemon_ability_addon.item;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.abilities.Ability;
import com.cobblemon.mod.common.api.abilities.AbilityPool;
import com.cobblemon.mod.common.api.abilities.PotentialAbility;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class AbilityCapsule extends PokemonItem {

    public AbilityCapsule(Properties arg) {
        super(arg);
    }

    @Override
    InteractionResult processInteraction(ItemStack itemStack, Player player, Pokemon target, InteractionHand hand) {
        PotentialAbility hiddenAbility = null;
        PotentialAbility otherAbility = null;

        Ability currentAbility = target.getAbility();

        AbilityPool pool = target.getSpecies().getAbilities();

        for (PotentialAbility potentialAbility : pool) {
            if (potentialAbility.getPriority() == Priority.LOWEST && !potentialAbility.getTemplate().getName().equals(currentAbility.getName())) {
                otherAbility = potentialAbility;
            }else if(potentialAbility.getPriority() == Priority.LOW){
                hiddenAbility = potentialAbility;
            }
        }

        if(hiddenAbility != null && currentAbility.getName().equals(hiddenAbility.getTemplate().getName())){
            player.sendSystemMessage(Component.translatable("cobblemon_ability_addon.errors.has_hidden_ability"));
            return InteractionResult.FAIL;
        }

        if(otherAbility == null){
            player.sendSystemMessage(Component.translatable("cobblemon_ability_addon.errors.only_one_ability"));
            return InteractionResult.FAIL;
        }

        Ability newAbility = otherAbility.getTemplate().getBuilder().invoke(otherAbility.getTemplate(), false);
        target.setAbility(newAbility);

        if(!player.isCreative()){
            itemStack.setCount(itemStack.getCount() - 1);
        }

        return InteractionResult.SUCCESS;
    }
}
