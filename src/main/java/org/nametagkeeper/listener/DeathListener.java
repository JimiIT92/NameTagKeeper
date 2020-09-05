package org.nametagkeeper.listener;

import org.nametagkeeper.config.PluginObjects;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.filter.IsCancelled;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.Tristate;
import org.spongepowered.api.world.World;

import java.util.Optional;

/**
 * Listener for the Living Entity Death Event
 *
 * @author JimiIT92
 */
public final class DeathListener {

    /**
     * Check when a Living Entity dies
     *
     * @param event Death Event
     */
    @Listener
    @IsCancelled(Tristate.UNDEFINED)
    public final void onLivingEntityDeath(final DestructEntityEvent.Death event) {
        if(!event.isCancelled()) {
            Living entity = event.getTargetEntity();
            Optional<Text> customName = entity.get(Keys.DISPLAY_NAME);
            if(!(entity instanceof Player) && customName.isPresent()
                    && !PluginObjects.EXCLUDED_ENTITIES.contains(entity.getType().getId())
                    && !PluginObjects.EXCLUDED_NAMES.contains(customName.get().toPlain())) {
                World world = entity.getWorld();
                Entity itemEntity = world.createEntity(EntityTypes.ITEM, entity.getLocation().getBlockPosition());
                ItemStack itemStack = ItemStack.builder()
                        .itemType(ItemTypes.NAME_TAG)
                        .quantity(1)
                        .add(Keys.DISPLAY_NAME, customName.get())
                        .build();
                itemEntity.offer(Keys.REPRESENTED_ITEM, itemStack.createSnapshot());
                world.spawnEntity(itemEntity);
            }
        }
    }
}
