package fr.minecraftforgefrance.ffmtlibs;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FFMTRegistry
{
    public static final int HELMET_TYPE = 0;
    public static final int CHESTPLATE_TYPE = 1;
    public static final int LEGGINS_TYPE = 2;
    public static final int BOOTS_TYPE = 3;

    public static final int AXE_TYPE = 0;
    public static final int SHOVEL_TYPE = 1;
    public static final int HOE_TYPE = 2;
    public static final int PICKAXE_TYPE = 3;
    public static final int SWORD_TYPE = 4;

    /**
     * Helper for crafting armors
     * @param material the item or block used for the craft
     * @param type Helmet:0 Chestplate:1 Leggings:2 Boots:3
     * @param output your helmet or your chest plate, etc ...
     */
    public static void addArmorCrafting(ItemStack material, int type, ItemStack output)
    {
        if(type == 0)
        {
            GameRegistry.addRecipe(output, new Object[] {"XXX", "X X", 'X', material});
        }
        else if(type == 1)
        {
            GameRegistry.addRecipe(output, new Object[] {"X X", "XXX", "XXX", 'X', material});
        }
        else if(type == 2)
        {
            GameRegistry.addRecipe(output, new Object[] {"XXX", "X X", "X X", 'X', material});
        }
        else if(type == 3)
        {
            GameRegistry.addRecipe(output, new Object[] {"X X", "X X", 'X', material});
        }
        else
        {
            FFMTLibs.ffmtLog.error("Failed to register armor crafting, couldn't handle type " + type);
        }
    }

    /**
     * Helper for crafting all armors
     * @param material the item used for the craft
     * @param outputHelmet your helmet
     * @param outputChestPlate your chest plate
     * @param outputLeggings your leggings
     * @param outputBoots your boots
     */
    public static void addAllArmorCrafting(Item material, Item outputHelmet, Item outputChestPlate, Item outputLeggings, Item outputBoots)
    {
        addAllArmorCrafting(new ItemStack(material), new ItemStack(outputHelmet), new ItemStack(outputChestPlate), new ItemStack(outputLeggings), new ItemStack(outputBoots));
    }

    /**
     * Helper for crafting all armors
     * @param material the item or block used for the craft
     * @param outputHelmet an itemstack with your helmet
     * @param outputChestPlate an itemstack with your chest plate
     * @param outputLeggings an itemstack with your leggings
     * @param outputBoots an itemstack with your boots
     */
    public static void addAllArmorCrafting(ItemStack material, ItemStack outputHelmet, ItemStack outputChestPlate, ItemStack outputLeggings, ItemStack outputBoots)
    {
        GameRegistry.addRecipe(outputHelmet, new Object[] {"XXX", "X X", 'X', material});
        GameRegistry.addRecipe(outputChestPlate, new Object[] {"X X", "XXX", "XXX", 'X', material});
        GameRegistry.addRecipe(outputLeggings, new Object[] {"XXX", "X X", "X X", 'X', material});
        GameRegistry.addRecipe(outputBoots, new Object[] {"X X", "X X", 'X', material});
    }

    /**
     * Helper for crafting tools
     * @param material the item or block used for the craft
     * @param type Axe:0 Shovel:1 Hoe:2 Pickaxe:3 Sword:4
     * @param output the instance of your tool
     * @param stick the stick of the tool
     */
    public static void addToolsCrafting(ItemStack material, int type, ItemStack output, ItemStack stick)
    {
        if(type == 0)
        {
            GameRegistry.addRecipe(output, new Object[] {"XX", "XS", " S", 'X', material, 'S', stick});
            GameRegistry.addRecipe(output, new Object[] {"XX", "SX", "S ", 'X', material, 'S', stick});
        }
        else if(type == 1)
        {
            GameRegistry.addRecipe(output, new Object[] {"X", "S", "S", 'X', material, 'S', stick});
        }
        else if(type == 2)
        {
            GameRegistry.addRecipe(output, new Object[] {"XX", " S", " S", 'X', material, 'S', stick});
            GameRegistry.addRecipe(output, new Object[] {"XX", "S ", "S ", 'X', material, 'S', stick});
        }
        else if(type == 3)
        {
            GameRegistry.addRecipe(output, new Object[] {"XXX", " S ", " S ", 'X', material, 'S', stick});
        }
        else if(type == 4)
        {
            GameRegistry.addRecipe(output, new Object[] {"X", "X", "S", 'X', material, 'S', stick});
        }
        else
        {
            FFMTLibs.ffmtLog.error("Failed to register tools crafting, couldn't handle type " + type);
        }
    }

    public static void addAllToolsCrafting(Item material, Item outputAxe, Item outputShovel, Item outputHoe, Item outputPickaxe, Item outputSword, Item stick)
    {
        addAllToolsCrafting(new ItemStack(material), new ItemStack(outputAxe), new ItemStack(outputShovel), new ItemStack(outputHoe), new ItemStack(outputPickaxe), new ItemStack(outputSword), new ItemStack(stick));
    }

    public static void addAllToolsCrafting(ItemStack material, ItemStack outputAxe, ItemStack outputShovel, ItemStack outputHoe, ItemStack outputPickaxe, ItemStack outputSword, ItemStack stick)
    {
        GameRegistry.addRecipe(outputAxe, new Object[] {"XX", "XS", " S", 'X', material, 'S', stick});
        GameRegistry.addRecipe(outputAxe, new Object[] {"XX", "SX", "S ", 'X', material, 'S', stick});
        GameRegistry.addRecipe(outputShovel, new Object[] {"X", "S", "S", 'X', material, 'S', stick});
        GameRegistry.addRecipe(outputHoe, new Object[] {"XX", " S", " S", 'X', material, 'S', stick});
        GameRegistry.addRecipe(outputHoe, new Object[] {"XX", "S ", "S ", 'X', material, 'S', stick});
        GameRegistry.addRecipe(outputPickaxe, new Object[] {"XXX", " S ", " S ", 'X', material, 'S', stick});
        GameRegistry.addRecipe(outputSword, new Object[] {"X", "X", "S", 'X', material, 'S', stick});
    }

    /**
     * Remove a recipe
     * @param stack The removed ItemStack
     */
    public static void removeRecipe(ItemStack stack)
    {
        List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();
        for(int i = 0; i < recipeList.size(); i++)
        {
            ItemStack output = recipeList.get(i).getRecipeOutput();
            if(output != null && stack.getItem() == output.getItem() && stack.getItemDamage() == output.getItemDamage())
            {
                recipeList.remove(i);
                FFMTLibs.ffmtLog.info("Removed recipe for : " + output.getItem().getUnlocalizedName(output));
            }
        }
    }
}