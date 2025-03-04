package net.blay09.mods.excompressum.block;

import net.blay09.mods.balm.api.block.BalmBlocks;
import net.blay09.mods.excompressum.ExCompressum;
import net.blay09.mods.excompressum.compat.Compat;
import net.blay09.mods.excompressum.compat.botania.BotaniaCompat;
import net.blay09.mods.excompressum.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Function;

public class ModBlocks {

    public static Block[] compressedBlocks;
    public static Block[] heavySieves;
    public static Block[] woodenCrucibles;
    public static Block[] baits;
    public static Block autoHammer;
    public static Block autoCompressedHammer;
    public static Block autoHeavySieve;
    public static Block autoSieve;
    public static Block manaSieve;
    public static Block evolvedOrechid;
    public static Block autoCompressor;
    public static Block rationingAutoCompressor;

    public static void initialize(BalmBlocks blocks) {
        compressedBlocks = registerEnumBlock(blocks, CompressedBlockType.values(), it -> CompressedBlock.namePrefix + it, CompressedBlock::new);
        heavySieves = registerEnumBlock(blocks, HeavySieveType.values(), it -> it + HeavySieveBlock.nameSuffix, HeavySieveBlock::new);
        woodenCrucibles = registerEnumBlock(blocks, WoodenCrucibleType.values(), it -> it + WoodenCrucibleBlock.nameSuffix, WoodenCrucibleBlock::new);
        baits = registerEnumBlock(blocks, BaitType.values(), it -> it + BaitBlock.nameSuffix, BaitBlock::new);

        blocks.register(() -> autoHammer = new AutoHammerBlock(), () -> new BlockItem(autoHammer, itemProperties()), id("auto_hammer"));
        blocks.register(() -> autoSieve = new AutoSieveBlock(), () -> new BlockItem(autoSieve, itemProperties()), id("auto_sieve"));
        blocks.register(() -> manaSieve = BotaniaCompat.createManaSieveBlock(), () -> new BlockItem(manaSieve, optionalItemProperties(Compat.BOTANIA)), id("mana_sieve"));
        blocks.register(() -> autoCompressedHammer = new AutoCompressedHammerBlock(), () -> new BlockItem(autoCompressedHammer, itemProperties()), id("auto_compressed_hammer"));
        blocks.register(() -> autoHeavySieve = new AutoHeavySieveBlock(), () -> new BlockItem(autoHeavySieve, itemProperties()), id("auto_heavy_sieve"));
        blocks.register(() -> autoCompressor = new AutoCompressorBlock(), () -> new BlockItem(autoCompressor, itemProperties()), id("auto_compressor"));
        blocks.register(() -> rationingAutoCompressor = new RationingAutoCompressorBlock(), () -> new BlockItem(rationingAutoCompressor, itemProperties()), id("rationing_auto_compressor"));
        blocks.register(() -> evolvedOrechid = BotaniaCompat.createOrechidBlock(), () -> new BlockItem(evolvedOrechid, optionalItemProperties(Compat.BOTANIA)), id("evolved_orechid"));
    }

    private static <T extends Enum<T> & StringRepresentable> Block[] registerEnumBlock(BalmBlocks blocks, T[] types, Function<String, String> nameFactory, Function<T, Block> factory) {
        Block[] blockArray = new Block[types.length];
        for (T type : types) {
            blocks.register(() -> blockArray[type.ordinal()] = factory.apply(type), () -> new BlockItem(blockArray[type.ordinal()], itemProperties()), id(nameFactory.apply(type.getSerializedName())));
        }

        return blockArray;
    }

    private static Item.Properties itemProperties() {
        return new Item.Properties().tab(ModItems.creativeModeTab);
    }

    private static Item.Properties optionalItemProperties(String modId) {
        Item.Properties properties = new Item.Properties();
        if (ModList.get().isLoaded(modId)) {
            return properties.tab(ModItems.creativeModeTab);
        }

        return properties;
    }

    private static ResourceLocation id(String path) {
        return new ResourceLocation(ExCompressum.MOD_ID, path);
    }

}
