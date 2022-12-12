package me.moteloff.api.itembuilder;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagByte;
import net.minecraft.server.v1_12_R1.NBTTagByteArray;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagDouble;
import net.minecraft.server.v1_12_R1.NBTTagFloat;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagLong;
import net.minecraft.server.v1_12_R1.NBTTagShort;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;

public class Platform  {

    public ItemStack createItemStack(Material material, int amount, int data, Map<String, Object> nbt) {
        net.minecraft.server.v1_12_R1.ItemStack item = new net.minecraft.server.v1_12_R1.ItemStack(CraftMagicNumbers.getItem(material), amount, data, false);
        if (!nbt.isEmpty()) {
            item.setTag((NBTTagCompound)toNbt(nbt));
        }

        return CraftItemStack.asBukkitCopy(item);
    }



    public static NBTBase toNbt(Object object) {
        Iterator var3;
        NBTBase value;
        if (object instanceof Map) {
            Map<?, ?> map = (Map)object;
            NBTTagCompound nbtMap = new NBTTagCompound();
            var3 = map.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<?, ?> entry = (Map.Entry)var3.next();
                value = toNbt(entry.getValue());
                if (value != null) {
                    nbtMap.set(String.valueOf(entry.getKey()), value);
                }
            }

            return nbtMap;
        } else if (object instanceof Collection) {
            Collection<?> collection = (Collection)object;
            NBTTagList nbtList = new NBTTagList();
            var3 = collection.iterator();

            while(var3.hasNext()) {
                Object o = var3.next();
                value = toNbt(o);
                if (value != null) {
                    nbtList.add(value);
                }
            }

            return nbtList;
        } else if (object.getClass() == Integer.class) {
            return new NBTTagInt((Integer)object);
        } else if (object.getClass() == Double.class) {
            return new NBTTagDouble((Double)object);
        } else if (object.getClass() == Float.class) {
            return new NBTTagFloat((Float)object);
        } else if (object.getClass() == Long.class) {
            return new NBTTagLong((Long)object);
        } else if (object.getClass() == Short.class) {
            return new NBTTagShort((Short)object);
        } else if (object.getClass() == Byte.class) {
            return new NBTTagByte((Byte)object);
        } else if (object.getClass() == byte[].class) {
            return new NBTTagByteArray((byte[])((byte[])object));
        } else {
            return object instanceof CharSequence ? new NBTTagString(String.valueOf(object)) : null;
        }
    }
}
