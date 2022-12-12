package me.moteloff.api.nbt;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public class NBTItem {
    public NBTItem(ItemStack itemStack) {
        item = CraftItemStack.asNMSCopy(itemStack);
    }

    private net.minecraft.server.v1_12_R1.ItemStack item;
    private final NBTTagCompound tag = (item.hasTag()) ? item.getTag() : new NBTTagCompound();

    public String getString(String string) { return tag.getString(string); }

    public int getInt(String string) { return tag.getInt(string); }

    public Double getDouble(String string) { return tag.getDouble(string); }

    public Boolean getBoolean(String string) { return tag.getBoolean(string); }

    public void setString(String key, String value) {
        tag.setString(key, value);
        item.setTag(tag);
    }

    public void setInt(String key, int value) {
        tag.setInt(key, value);
        item.setTag(tag);
    }

    public void setDouble(String key, Double value) {
        tag.setDouble(key, value);
        item.setTag(tag);
    }

    public void setBoolean(String key, Boolean value) {
        tag.setBoolean(key, value);
        item.setTag(tag);
    }

    public Boolean contains(String key) {return tag.hasKey(key);}
}
