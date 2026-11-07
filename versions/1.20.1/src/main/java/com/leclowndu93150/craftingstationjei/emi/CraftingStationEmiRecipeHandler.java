package com.leclowndu93150.craftingstationjei.emi;

import com.leclowndu93150.craftingstationjei.menu.CraftingStationMenu;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler;
import net.minecraft.world.inventory.Slot;

import java.util.ArrayList;
import java.util.List;

public class CraftingStationEmiRecipeHandler implements StandardRecipeHandler<CraftingStationMenu> {

    @Override
    public List<Slot> getInputSources(CraftingStationMenu menu) {
        List<Slot> slots = new ArrayList<>(getCraftingSlots(menu));
        slots.addAll(menu.getSideSlots());
        for (int i = menu.getPlayerInventoryStartIndex(); i < menu.slots.size(); i++) {
            slots.add(menu.getSlot(i));
        }
        return slots;
    }

    @Override
    public List<Slot> getCraftingSlots(CraftingStationMenu menu) {
        List<Slot> slots = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            slots.add(menu.getSlot(i));
        }
        return slots;
    }

    @Override
    public Slot getOutputSlot(CraftingStationMenu menu) {
        return menu.getSlot(0);
    }

    @Override
    public boolean supportsRecipe(EmiRecipe recipe) {
        return recipe.getCategory() == VanillaEmiRecipeCategories.CRAFTING && recipe.supportsRecipeTree();
    }

    // No craft() override: EMI's default sends one server-side fill packet instead of one click packet per item.
}
