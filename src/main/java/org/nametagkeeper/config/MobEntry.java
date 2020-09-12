package org.nametagkeeper.config;

import java.util.List;

/**
 * Mob Entry
 *
 * @author JimiIT92
 */
public final class MobEntry {

    /**
     * If the Entity has a name in this
     * list it will drop the Name Tag
     */
    public List<String> DROP;
    /**
     * If the Entity has a name in this list
     * it will not drop the Name Tag
     */
    public List<String> NODROP;
}
