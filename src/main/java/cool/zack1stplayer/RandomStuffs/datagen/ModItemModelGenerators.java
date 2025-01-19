package cool.zack1stplayer.RandomStuffs.datagen;

import cool.zack1stplayer.RandomStuffs.block.ModBlocks;
import cool.zack1stplayer.RandomStuffs.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModItemModelGenerators extends ItemModelGenerators {
    public ModItemModelGenerators(ItemModelOutput p_375677_, BiConsumer<ResourceLocation, ModelInstance> p_377569_) {
        super(p_375677_, p_377569_);
    }

    @Override
    public void run() {
        this.generateFlatItem(ModItems.CHISEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GROWTH_WAND.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.RADDITE_LONGSWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        this.generateFlatItem(ModItems.RADDITE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.RAW_RADDITE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.ENERGIZED_COAL.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.ENERGIZED_CHARCOAL.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.EXAMPLE_ITEM.get(), ModelTemplates.FLAT_ITEM);
    }
}
