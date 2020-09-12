package org.nametagkeeper.listener;

import org.nametagkeeper.config.MobEntry;
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

import java.util.ArrayList;
import java.util.List;
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
                && shouldDrop(entity.getType().getId(), customName.get().toPlain())) {
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

    /**
     * Check if the Entity should drop
     * the Name Tag
     * @param entityType Entity Type
     * @param name Entity Name
     * @return True if the Entity should drop the Name Tag, False otherwise
     */
    private boolean shouldDrop(String entityType, String name) {
        MobEntry entry = PluginObjects.ENTRIES.get(entityType);
        MobEntry allEntry = PluginObjects.ENTRIES.get("*");
        if(allEntry == null && entry == null) {
            return true;
        }
        List<String> drop = new ArrayList<>();
        List<String> nodrop = new ArrayList<>();
        if(entry != null) {
            if(entry.DROP.isEmpty() && entry.NODROP.isEmpty()) {
                return false;
            }
            drop.addAll(entry.DROP);
            nodrop.addAll(entry.NODROP);
        }
        if(allEntry != null) {
            drop.addAll(allEntry.DROP);
            nodrop.addAll(allEntry.NODROP);
        }
        if(!nodrop.isEmpty() && nodrop.contains(name)) {
            return false;
        }
        return drop.isEmpty() || drop.contains(name);
    }
}
