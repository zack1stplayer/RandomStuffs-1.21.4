package cool.zack1stplayer.RandomStuffs.datagen;

import cool.zack1stplayer.RandomStuffs.item.ModItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class ModItemModelGenerators extends ItemModelGenerators {
    public ModItemModelGenerators(ItemModelOutput p_375677_, BiConsumer<ResourceLocation, ModelInstance> p_377569_) {
        super(p_375677_, p_377569_);
    }

    @Override
    public void run() {
        this.generateFlatItem(ModItems.RADDITE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.RAW_RADDITE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.ENERGIZED_COAL.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.ENERGIZED_CHARCOAL.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.RADDITE_LONGSWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        this.generateFlatItem(ModItems.CHISEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GROWTH_WAND.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.BLOCK_LINKER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

//        this.generateFlatItem(ModItems.COTTON_SEEDS.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.COTTON_BOLL.get(), ModelTemplates.FLAT_ITEM);


        this.generateFlatItem(ModItems.EXAMPLE_ITEM.get(), ModelTemplates.FLAT_ITEM);
    }
}
