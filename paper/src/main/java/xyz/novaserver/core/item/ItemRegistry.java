package xyz.novaserver.core.item;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private static final Map<String, CustomItem> itemMap = new HashMap<>();

    public static void addItem(@NotNull CustomItem item) {
        final String id = item.getId();
        if (itemMap.containsKey(id)) {
            throw new IllegalStateException(id + " cannot be added to the registry, an item with the same id already exists!");
        } else if (itemMap.containsValue(item)) {
            throw new IllegalStateException(item.getClass().getSimpleName()
                    + " cannot be added to the registry, an item with the same class already exists!");
        }
        itemMap.put(id, item);
    }

    @Nullable
    public static CustomItem getItem(String id) {
        return itemMap.getOrDefault(id, null);
    }
}
