package xyz.novaserver.core.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ItemUtils {
    public static boolean startsWith(ItemStack itemStack, String prefix, NamespacedKey key) {
        return hasIdKey(itemStack, key) && getId(itemStack, key).startsWith(prefix);
    }

    public static boolean instanceOf(ItemStack itemStack, String id, NamespacedKey key) {
        return hasIdKey(itemStack, key) && Objects.equals(getId(itemStack, key), id);
    }

    public static NamespacedKey getIdKey(Plugin plugin) {
        return new NamespacedKey(plugin, "id");
    }

    @Nullable
    public static String getId(ItemStack itemStack, NamespacedKey key) {
        if (itemStack == null || itemStack.getItemMeta() == null) {
            return null;
        }
        PersistentDataContainer data = itemStack.getItemMeta().getPersistentDataContainer();
        return data.has(key, PersistentDataType.STRING) ? data.get(key, PersistentDataType.STRING) : null;
    }

    public static boolean hasIdKey(ItemStack itemStack, NamespacedKey key) {
        if (itemStack == null || itemStack.getItemMeta() == null) {
            return false;
        }
        return itemStack.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING);
    }

    public static String toLegacyString(Component component) {
        return LegacyComponentSerializer.legacySection().serialize(component);
    }
}
