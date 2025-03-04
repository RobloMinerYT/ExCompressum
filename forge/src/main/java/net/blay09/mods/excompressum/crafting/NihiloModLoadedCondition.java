package net.blay09.mods.excompressum.crafting;

import com.google.gson.JsonObject;
import net.blay09.mods.excompressum.ExCompressum;
import net.blay09.mods.excompressum.registry.ExNihilo;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class NihiloModLoadedCondition implements ICondition {

    private static final ResourceLocation NAME = new ResourceLocation(ExCompressum.MOD_ID, "nihilo_mod_loaded");

    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test() {
        return ExNihilo.hasNihiloMod();
    }

    public static class Serializer implements IConditionSerializer<NihiloModLoadedCondition> {

        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, NihiloModLoadedCondition value) {
        }

        @Override
        public NihiloModLoadedCondition read(JsonObject json) {
            return new NihiloModLoadedCondition();
        }

        @Override
        public ResourceLocation getID() {
            return NihiloModLoadedCondition.NAME;
        }

    }

}
