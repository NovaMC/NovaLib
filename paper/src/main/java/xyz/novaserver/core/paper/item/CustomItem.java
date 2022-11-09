package xyz.novaserver.core.paper.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public abstract class CustomItem extends ItemStack {
    private final Plugin plugin;
    private final String id;
    private final Component name;

    public CustomItem(String id, Material material, Component name, Plugin plugin) {
        super(material, 1);
        this.plugin = plugin;
        this.id = id;
        this.name = name;

        setName(this.name);
        setKeyData(this.id);
    }

    public Component getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    private void setKeyData(String id) {
        ItemMeta meta = this.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(ItemUtils.getIdKey(plugin), PersistentDataType.STRING, id);
        this.setItemMeta(meta);
    }

    private void setName(Component name) {
        ItemMeta meta = this.getItemMeta();
        meta.displayName(name);
        this.setItemMeta(meta);
    }
}
