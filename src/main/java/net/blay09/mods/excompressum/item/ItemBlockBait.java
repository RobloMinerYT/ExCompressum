package net.blay09.mods.excompressum.item;

import net.blay09.mods.excompressum.ExCompressum;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockBait extends ItemBlock {

    private final IIcon[] icons = new IIcon[6];

    public ItemBlockBait(Block block) {
        super(block);
        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        icons[0] = iconRegister.registerIcon(ExCompressum.MOD_ID + ":bait_wolf");
        icons[1] = iconRegister.registerIcon(ExCompressum.MOD_ID + ":bait_ocelot");
        icons[2] = iconRegister.registerIcon(ExCompressum.MOD_ID + ":bait_cow");
        icons[3] = iconRegister.registerIcon(ExCompressum.MOD_ID + ":bait_pig");
        icons[4] = iconRegister.registerIcon(ExCompressum.MOD_ID + ":bait_chicken");
        icons[5] = iconRegister.registerIcon(ExCompressum.MOD_ID + ":bait_sheep");
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return icons[damage];
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "item." + ExCompressum.MOD_ID + ":bait_" + itemStack.getItemDamage();
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

}
