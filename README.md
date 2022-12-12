# moteloff_api (current version 1.0.1)
 
## ItemBuilder
 A class that allows you to specify the displayName, lore, type, amount, nbt, data, and enchantment when creating an item.
 
  <h4>@text - sets displayName and lore by splitting the string.</h4> 
  
  ```Java
  .text("It's DisplayName\nIt's ItemLore\n And this")
  ```
  
  <h4>@type - sets material for ItemStack</h4> 
  
  ```Java
  .type(Material.GOLD_SWORD)
  ```
  
  ```Java
  ItemStack sword = new ItemBuilder(Material.GOLD_SWORD)
  ```
  
  <h4>@amount - sets the amount of the ItemStack</h4> 
  
  ```Java
  .amount(64)
  ```
  
  <h4>@data - sets the data of the ItemStack</h4> 
  
  ```Java
  .data(0)
  ```
  
  <h4>@nbt - sets the nbt tags for the ItemStack</h4> 
  
  ```Java
  .nbt("key", 5)
  ```
  
  <h4>@enchant - add enchant to the ItemStack</h4> 
  
  ```Java
  .enchant(Enchantment.DAMAGE_ALL, 3)
  ```
  
  <h4>@build - сompletely builds the item according to the specified properties</h4> 
  
  ```Java
  .build()
  ```
  
  <h2>Example of code</h2> 
  
  ```Java
  ItemStack sword = new ItemBuilder(Material.GOLD_SWORD)
                .text("DisplayName\nLore\nOneMore\nLore")
                .amount(1)
                .data(0)
                .nbt("value", "tag")
                .enchant(Enchantment.DAMAGE_ALL, 3)
                .build();
  ```
 
 
## NBTItem
A class that exercises control over NBT tags on items.

<h4>Getters</h4>

 ```Java
  NBTItem nbtItem = new NBTItem(new ItemStack(Material.DIAMOND));

  nbtItem.getBoolean("your key");
  nbtItem.getDouble("your key");
  nbtItem.getInt("your key");
  nbtItem.getString("your key");
  ```
  
  <h4>Setters</h4>

  ```Java
  NBTItem nbtItem = new NBTItem(new ItemStack(Material.DIAMOND));

  nbtItem.setBoolean("your key", false);
  nbtItem.setDouble("your key", 10.0);
  nbtItem.setInt("your key", 999);
  nbtItem.setString("your key", "your value");
   ```
   
  <h4>Contains</h4>

  ```Java
  NBTItem nbtItem = new NBTItem(new ItemStack(Material.DIAMOND));

  nbtItem.contains("your key");
   ```

## NBTEntity


