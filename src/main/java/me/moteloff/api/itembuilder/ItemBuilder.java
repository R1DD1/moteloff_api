package me.moteloff.api.itembuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder {

    private final Platform platform = new Platform();
    public Material type;
    public int amount;
    public int data;
    public final Map<String, Object> nbt;
    public final List<String> text;

    public ItemBuilder(Material type) {
        this.type = Material.STONE;
        this.amount = 1;
        this.nbt = new HashMap();
        this.text = new ArrayList();
        this.type = type;
    }

    public ItemBuilder nbt(String key, Object value) {
        this.nbt0(this.nbt, Collections.singletonMap(key, value));
        return this;
    }

    public ItemBuilder nbt(Map<String, Object> map) {
        this.nbt0(this.nbt, map);
        return this;
    }

    private void nbt0(Map receiver, Map source) {
        Iterator var3 = source.entrySet().iterator();

        while (var3.hasNext()) {
            Object entry = var3.next();
            String key = String.valueOf(((Map.Entry) entry).getKey());
            Object value = ((Map.Entry) entry).getValue();
            if (value instanceof Map) {
                Object existing = receiver.get(key);
                Map<?, ?> branch = existing instanceof Map ? (Map) existing : new HashMap();
                this.nbt0((Map) branch, (Map) value);
                receiver.put(key, branch);
            } else {
                receiver.put(key, value);
            }
        }
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        HashMap<Object, Object> enchantData = new HashMap();
        enchantData.put("id", (short)enchantment.getId());
        enchantData.put("lvl", (short)level);
        ArrayList<Object> enchantList = new ArrayList();
        enchantList.add(enchantData);
        return this.nbt("ench", enchantList);
    }

    public ItemBuilder text(String text) {
        List<String> list = Arrays.asList(text.split("\n"));
        if (list.isEmpty()) {
            return this;
        } else {
            int emptyLines = 0;
            boolean content = false;
            Iterator var5 = list.iterator();

            while(true) {
                while(var5.hasNext()) {
                    String line = (String)var5.next();
                    line = line.trim();
                    if (!line.isEmpty()) {
                        for(int i = 0; i < emptyLines; ++i) {
                            this.text.add("§f");
                        }

                        this.text.add("§f" + line.replace('&', '§'));
                        content = true;
                        emptyLines = 0;
                    } else if (content) {
                        ++emptyLines;
                    }
                }

                return this;
            }
        }
    }

    public ItemStack build() {
        if (!this.text.isEmpty()) {
            Map<String, Object> displayMap = new HashMap();
            Iterator<String> iterator = this.text.iterator();
            displayMap.put("Name", iterator.next());
            if (iterator.hasNext()) {
                List<String> lore = new ArrayList();

                while(iterator.hasNext()) {
                    lore.add(iterator.next());
                }

                displayMap.put("Lore", lore);
            }

            this.nbt("display", displayMap);
        }

        return platform.createItemStack(type, amount, data, nbt);
    }

    public ItemBuilder() {
        this.type = Material.STONE;
        this.amount = 1;
        this.nbt = new HashMap();
        this.text = new ArrayList();
    }

    public ItemBuilder type(Material type) {
        this.type = type;
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder data(int data) {
        this.data = data;
        return this;
    }

    public Material type() {
        return this.type;
    }

    public int amount() {
        return this.amount;
    }

    public int data() {
        return this.data;
    }

    public Map<String, Object> nbt() {
        return this.nbt;
    }

    public List<String> text() {
        return this.text;
    }
}
