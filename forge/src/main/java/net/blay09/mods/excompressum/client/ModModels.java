package net.blay09.mods.excompressum.client;

import net.blay09.mods.balm.api.DeferredObject;
import net.blay09.mods.balm.api.client.rendering.BalmModels;
import net.blay09.mods.excompressum.ExCompressum;
import net.blay09.mods.excompressum.block.HeavySieveType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = ExCompressum.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModModels {
    public static final Map<String, DeferredObject<BakedModel>> meshes = new HashMap<>();

    public static final List<DeferredObject<BakedModel>> sieves = new ArrayList<>();
    public static DeferredObject<BakedModel> woodenCrucibleLiquid;

    public static void initialize(BalmModels models) {
        woodenCrucibleLiquid = models.loadModel(location("block/wooden_crucible_liquid"));

        meshes.put("string", models.loadModel(location("block/string_mesh")));
        meshes.put("flint", models.loadModel(location("block/flint_mesh")));
        meshes.put("iron", models.loadModel(location("block/iron_mesh")));
        meshes.put("diamond", models.loadModel(location("block/diamond_mesh")));
        meshes.put("emerald", models.loadModel(location("block/emerald_mesh")));
        meshes.put("netherite", models.loadModel(location("block/netherite_mesh")));

        HeavySieveType[] sieveTypes = HeavySieveType.values;
        for (HeavySieveType sieveType : sieveTypes) {
            sieves.add(sieveType.ordinal(), models.loadModel(location("block/" + sieveType.getSerializedName() + "_sieve")));
        }
    }

    private static ResourceLocation location(String path) {
        return new ResourceLocation(ExCompressum.MOD_ID, path);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBake(ModelBakeEvent event) {
        HeavySieveType[] sieveTypes = HeavySieveType.values;
        for (HeavySieveType sieveType : sieveTypes) {
            UnbakedModel model = event.getModelLoader().getModel(location("block/" + sieveType.getSerializedName() + "_sieve"));
            model.getMaterials(event.getModelLoader()::getModel, Collections.emptySet());
        }
    }
}
