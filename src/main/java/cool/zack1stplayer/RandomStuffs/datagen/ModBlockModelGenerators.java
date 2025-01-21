package cool.zack1stplayer.RandomStuffs.datagen;

import cool.zack1stplayer.RandomStuffs.block.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModBlockModelGenerators extends BlockModelGenerators {
    public ModBlockModelGenerators(Consumer<BlockStateGenerator> blockStateGeneratorConsumer, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> p_378240_) {
        super(blockStateGeneratorConsumer, itemModelOutput, p_378240_);
    }

    @Override
    public void run() {
        this.createTrivialCube(ModBlocks.RADDITE_BLOCK.get());
        this.createTrivialCube(ModBlocks.RADDITE_ORE.get());
        this.createTrivialCube(ModBlocks.DEEPSLATE_RADDITE_ORE.get());

        this.createTrivialBlock(ModBlocks.ENERGIZER.get(), TexturedModel.CUBE_TOP_BOTTOM);


        // VANILLA ALT BLOCKS
        this.family(Blocks.HONEYCOMB_BLOCK)
                .slab(ModBlocks.HONEYCOMB_SLAB.get())
                .stairs(ModBlocks.HONEYCOMB_STAIRS.get());
        this.createTrapdoor(ModBlocks.HONEYCOMB_TRAPDOOR.get(), BuiltInRegistries.BLOCK.getKey(Blocks.HONEYCOMB_BLOCK));

        // WIP BLOCKS
        this.createTrivialCube(ModBlocks.EXAMPLE_BLOCK.get());
        this.createTrivialCube(ModBlocks.CHIME.get());


        if (this.itemModelOutput instanceof ModBlockStateProvider.ItemInfoCollector collector) {
            collector.generateDefaultBlockModels();
        }
    }


    protected static BlockStateGenerator createTrapdoor(Block block, ResourceLocation locationTop, ResourceLocation locationBottom, ResourceLocation locationOpen) {
        return MultiVariantGenerator.multiVariant(block)
                .with(
                        PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.OPEN)
                                .select(Direction.NORTH, Half.BOTTOM, false, Variant.variant().with(VariantProperties.MODEL, locationBottom))
                                .select(Direction.SOUTH, Half.BOTTOM, false, Variant.variant().with(VariantProperties.MODEL, locationBottom))
                                .select(Direction.EAST, Half.BOTTOM, false, Variant.variant().with(VariantProperties.MODEL, locationBottom))
                                .select(Direction.WEST, Half.BOTTOM, false, Variant.variant().with(VariantProperties.MODEL, locationBottom))
                                .select(Direction.NORTH, Half.TOP, false, Variant.variant().with(VariantProperties.MODEL, locationTop))
                                .select(Direction.SOUTH, Half.TOP, false, Variant.variant().with(VariantProperties.MODEL, locationTop))
                                .select(Direction.EAST, Half.TOP, false, Variant.variant().with(VariantProperties.MODEL, locationTop))
                                .select(Direction.WEST, Half.TOP, false, Variant.variant().with(VariantProperties.MODEL, locationTop))
                                .select(Direction.NORTH, Half.BOTTOM, true, Variant.variant().with(VariantProperties.MODEL, locationOpen))
                                .select(Direction.SOUTH, Half.BOTTOM, true, Variant.variant().with(VariantProperties.MODEL, locationOpen)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                                .select(Direction.EAST, Half.BOTTOM, true, Variant.variant().with(VariantProperties.MODEL, locationOpen)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                                .select(Direction.WEST, Half.BOTTOM, true, Variant.variant().with(VariantProperties.MODEL, locationOpen)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                                .select(Direction.NORTH, Half.TOP, true, Variant.variant().with(VariantProperties.MODEL, locationOpen))
                                .select(Direction.SOUTH, Half.TOP,
                                        true, Variant.variant().with(VariantProperties.MODEL, locationOpen)
                                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                                .select(Direction.EAST, Half.TOP, true, Variant.variant().with(VariantProperties.MODEL, locationOpen)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                                .select(Direction.WEST, Half.TOP, true, Variant.variant().with(VariantProperties.MODEL, locationOpen)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                );
    }

    protected void createTrapdoor(Block block, ResourceLocation source) {
        TextureMapping texturemapping = TextureMapping.defaultTexture(source.withPrefix("block/"));
        ResourceLocation resourcelocation = ModelTemplates.TRAPDOOR_TOP.create(block, texturemapping, this.modelOutput);
        ResourceLocation resourcelocation1 = ModelTemplates.TRAPDOOR_BOTTOM.create(block, texturemapping, this.modelOutput);
        ResourceLocation resourcelocation2 = ModelTemplates.TRAPDOOR_OPEN.create(block, texturemapping, this.modelOutput);
        this.blockStateOutput.accept(createTrapdoor(block, resourcelocation, resourcelocation1, resourcelocation2));
        this.registerSimpleItemModel(block, resourcelocation1);
    }

    protected ModBlockModelGenerators.ModBlockFamilyProvider family(Block block) {
        return new ModBlockModelGenerators.ModBlockFamilyProvider(block);
    }

    @OnlyIn(Dist.CLIENT)
    public class ModBlockFamilyProvider extends BlockFamilyProvider {
        private final ResourceLocation fullBlock;

        public ModBlockFamilyProvider(Block block) {
            super(TexturedModel.CUBE.get(block).getMapping());
            fullBlock = BuiltInRegistries.BLOCK.getKey(block);
        }

        @Override
        public ModBlockModelGenerators.BlockFamilyProvider slab(Block p_377334_) {
            if (this.fullBlock == null) {
                throw new IllegalStateException("Full block not generated yet");
            } else {
                ResourceLocation resourcelocation = this.getOrCreateModel(ModelTemplates.SLAB_BOTTOM, p_377334_);
                ResourceLocation resourcelocation1 = this.getOrCreateModel(ModelTemplates.SLAB_TOP, p_377334_);
                ModBlockModelGenerators.this.blockStateOutput.accept(ModBlockModelGenerators.createSlab(p_377334_, resourcelocation, resourcelocation1, this.fullBlock));
                ModBlockModelGenerators.this.registerSimpleItemModel(p_377334_, resourcelocation);
                return this;
            }
        }
    }
}
