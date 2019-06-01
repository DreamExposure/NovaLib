package org.dreamexposure.novalib.api.bukkit.serialization;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.dreamexposure.novalib.api.bukkit.compatibility.NMaterial;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

@SuppressWarnings({"WeakerAccess", "unused", "ConstantConditions"})
public class JsonSerializer {

    /**
     * Serializes the provided itemstack to a JSONObject for writing to disk.
     *
     * @param is The ItemStack to serialize
     * @return a new JSONObject representing the ItemStack.
     */
    @SuppressWarnings("deprecation")
    public static JSONObject serializeItemStack(ItemStack is) {
        JSONObject json = new JSONObject();

        if (is.getData() != null)
            json.put("material", NMaterial.getNMaterial(is.getType().name() + ":" + is.getData().getData()));
        else
            json.put("material", NMaterial.getNMaterial(is.getType().name()));

        json.put("data", is.getData());
        json.put("amount", is.getAmount());

        if (is.hasItemMeta()) {
            JSONObject meta = new JSONObject();
            if (is.getItemMeta().hasDisplayName())
                meta.put("display_name", is.getItemMeta().getDisplayName());
            if (is.getItemMeta().hasLore()) {
                JSONArray lore = new JSONArray();
                lore.put(is.getItemMeta().getLore());

                meta.put("lore", is.getItemMeta().getLore());
            }
            json.put("meta", meta);
        }

        if (!is.getEnchantments().isEmpty()) {
            JSONArray enchants = new JSONArray();
            for (Enchantment e : is.getEnchantments().keySet()) {
                JSONObject s = new JSONObject();
                s.put("enchant", e.getKey());
                s.put("level", is.getEnchantmentLevel(e));
                enchants.put(s);
            }
            json.put("enchantments", enchants);
        }

        //Special item handling
        if (is.getType() == Material.PLAYER_HEAD) {
            json.put("skull", ((SkullMeta) is.getItemMeta()).getOwningPlayer().getUniqueId().toString());
        }

        if (is.getType() == Material.WRITTEN_BOOK || is.getType() == Material.WRITABLE_BOOK) {
            JSONObject book = new JSONObject();
            BookMeta bm = (BookMeta) is.getItemMeta();
            if (bm.hasTitle())
                book.put("title", bm.getTitle());

            if (bm.hasAuthor())
                book.put("author", bm.getAuthor());

            if (bm.hasPages()) {
                JSONArray pages = new JSONArray();

                pages.put(bm.getPages());

                book.put("pages", pages);
            }

            json.put("book", book);
        }


        return json;
    }

    /**
     * Deserializes an ItemStack from the provided JSONObject.
     *
     * @param json The JSONObject representing the ItemStack.
     * @return A new ItemStack from the provided data.
     */
    public static ItemStack deserializeItemStack(JSONObject json) {
        ItemStack is = NMaterial.valueOf(json.getString("material")).getItemStack();
        is.setData((MaterialData) json.get("data"));
        is.setAmount(json.getInt("amount"));
        is.setDurability((Short) json.get("durability"));
        json.put("durability", is.getDurability());

        if (json.has("meta")) {
            JSONObject meta = json.getJSONObject("meta");
            if (meta.has("display_name"))
                is.getItemMeta().setDisplayName(json.getString("display_name"));
            if (meta.has("lore")) {
                JSONArray lore = meta.getJSONArray("lore");
                ArrayList<String> loreToAdd = new ArrayList<>();

                for (int i = 0; i < lore.length(); i++) {
                    loreToAdd.add(lore.getString(i));
                }
                is.getItemMeta().setLore(loreToAdd);
            }
        }

        if (json.has("enchantments")) {
            JSONArray enchants = json.getJSONArray("enchantments");

            for (int i = 0; i < enchants.length(); i++) {
                JSONObject e = enchants.getJSONObject(i);
                is.addEnchantment(Enchantment.getByName(e.getString("enchant")), e.getInt("level"));
            }
        }

        //Special item handling
        if (is.getType() == Material.PLAYER_HEAD) {
            SkullMeta sm = (SkullMeta) is.getItemMeta();
            sm.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString(json.getString("skull"))));
            is.setItemMeta(sm);
        }

        if (is.getType() == Material.WRITTEN_BOOK || is.getType() == Material.WRITABLE_BOOK) {
            JSONObject b = json.getJSONObject("book");
            BookMeta book = (BookMeta) is.getItemMeta();
            if (b.has("title"))
                book.setTitle(b.getString("title"));

            if (b.has("author"))
                book.setAuthor(b.getString("author"));

            if (b.has("pages")) {
                JSONArray p = b.getJSONArray("pages");
                ArrayList<String> pages = new ArrayList<>();

                for (int i = 0; i < p.length(); i++) {
                    pages.add(p.getString(i));
                }
                book.setPages(pages);
            }

            is.setItemMeta(book);
        }

        return is;
    }

    /**
     * Serializes the provided Inventory to a JSONObject for writing to disk.
     *
     * @param inventory The Inventory to serialize.
     * @return a new JSONObject representing the Inventory.
     */
    public static JSONObject serializeInventory(Inventory inventory, String title) {
        JSONObject json = new JSONObject();

        json.put("type", inventory.getType().name());
        json.put("title", title);
        json.put("size", inventory.getSize());
        json.put("holder", inventory.getHolder());

        JSONArray items = new JSONArray();
        for (ItemStack i : inventory.getContents()) {
            items.put(serializeItemStack(i));
        }

        json.put("items", items);

        return json;
    }

    /**
     * Deserializes the Inventory from the provided JSONObject.
     *
     * @param json The JSONObject representing the Inventory.
     * @return A new Inventory from the provided data.
     */
    public static Inventory deserializeInventory(JSONObject json) {
        InventoryType type = InventoryType.valueOf(json.getString("type"));
        InventoryHolder holder;
        try {
            holder = (InventoryHolder) json.get("holder");
        } catch (Exception e) {
            holder = null;
        }
        String title = json.getString("title");

        Inventory inv = Bukkit.createInventory(holder, type, title);

        JSONArray items = json.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            ItemStack item = deserializeItemStack(items.getJSONObject(i));
            inv.setItem(i, item);
        }

        return inv;
    }

    /**
     * Serializes the provided Location to a JSONObject for writing to disk.
     *
     * @param location The Location to serialize.
     * @return a new JSONObject representing the Location.
     */
    public static JSONObject serializeLocation(Location location) {
        JSONObject json = new JSONObject();

        json.put("world", location.getWorld());
        json.put("x", location.getX());
        json.put("y", location.getY());
        json.put("z", location.getZ());
        json.put("yaw", location.getYaw());
        json.put("pitch", location.getPitch());

        return json;
    }

    /**
     * Deserializes the Location from the provided JSONObject.
     *
     * @param json The JSONObject representing the Location
     * @return a new Location from the provided data.
     */
    public static Location deserializeLocation(JSONObject json) {
        World w = Bukkit.getWorld(json.getString("world"));
        double x = json.getDouble("x");
        double y = json.getDouble("y");
        double z = json.getDouble("z");
        int ya = json.getInt("yaw");
        int pi = json.getInt("pitch");

        return new Location(w, x, y, z, ya, pi);
    }
}