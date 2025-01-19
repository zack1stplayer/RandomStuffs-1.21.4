package cool.zack1stplayer.RandomStuffs.datagen;

import cool.zack1stplayer.RandomStuffs.block.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModBlockModelGenerators extends BlockModelGenerators {
    public ModBlockModelGenerators(Consumer<BlockStateGenerator> p_378137_, ItemModelOutput p_378502_, BiConsumer<ResourceLocation, ModelInstance> p_378240_) {
        super(p_378137_, p_378502_, p_378240_);
    }

    @Override
    public void run() {
        this.createTrivialCube(ModBlocks.RADDITE_BLOCK.get());
        this.createTrivialCube(ModBlocks.RADDITE_ORE.get());
        this.createTrivialCube(ModBlocks.DEEPSLATE_RADDITE_ORE.get());

        this.createTrivialBlock(ModBlocks.ENERGIZER.get(), TexturedModel.CUBE_TOP_BOTTOM);

        this.createTrivialCube(ModBlocks.EXAMPLE_BLOCK.get());
        this.createTrivialCube(ModBlocks.CHIME.get());


        if (this.itemModelOutput instanceof ModBlockStateProvider.ItemInfoCollector collector) {
            collector.generateDefaultBlockModels();
        }
    }
}
