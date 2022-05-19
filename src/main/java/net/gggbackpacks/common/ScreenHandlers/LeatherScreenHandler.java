package net.gggbackpacks.common.ScreenHandlers;

import net.gggbackpacks.GGGBackpacks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class LeatherScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public LeatherScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(9));
    }

    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public LeatherScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(GGGBackpacks.LEATHER_SCREEN_HANDLER, syncId);
        checkSize(inventory, 9);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        int y;
        int x;
        //Backpack
        //y needs to be changed when we move to bigger inventories
        //A lot of this needs to be adjusted when we move to bigger inventories, actually
        for (y = 0; y < 1; ++y) {
            for (x = 0; x < 9; ++x) {
                this.addSlot(new Slot(inventory, x + y * (9 + y), 8 + x * 18, 19 + y * 18));
            }
        }
        //The player inventory
        for (y = 0; y < 3; ++y) {
            for (x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 49 + y * 18));
            }
        }
        //The player Hotbar
        for (x = 0; x < 9; ++x) {
            this.addSlot(new Slot(playerInventory, x, 8 + x * 18, 107));
        }

    }

    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}
