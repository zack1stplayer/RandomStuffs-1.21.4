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
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

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


        // VANILLA ALT BLOCKS
        this.autoGenSimpleStairs(ModBlocks.HONEYCOMB_STAIRS.get(),
                fromNamespaceAndPath("minecraft", BuiltInRegistries.BLOCK.getKey(Blocks.HONEYCOMB_BLOCK).getPath()));
        this.autoGenSimpleSlab(ModBlocks.HONEYCOMB_SLAB.get(),
                fromNamespaceAndPath("minecraft", BuiltInRegistries.BLOCK.getKey(Blocks.HONEYCOMB_BLOCK).getPath()));
        this.autoGenSimpleTrapdoor(ModBlocks.HONEYCOMB_TRAPDOOR.get(),
                fromNamespaceAndPath("minecraft", BuiltInRegistries.BLOCK.getKey(Blocks.HONEYCOMB_BLOCK).getPath()));


        // WIP BLOCKS
        this.createTrivialCube(ModBlocks.EXAMPLE_BLOCK.get());
        this.createTrivialCube(ModBlocks.CHIME.get());


        if (this.itemModelOutput instanceof ModBlockStateProvider.ItemInfoCollector collector) {
            collector.generateDefaultBlockModels();
        }
        System.out.println();
    }


    protected void autoGenSimpleSlab(Block block, ResourceLocation resourceLocation) {
        createSimpleSlab(block, resourceLocation);
    }

    protected void autoGenSimpleStairs(Block block, ResourceLocation resourceLocation) {
        createStairs(block, resourceLocation);
    }

    protected void autoGenSimpleTrapdoor(Block block, ResourceLocation resourceLocation) {
        createTrapdoor(block, resourceLocation);
    }


    protected static BlockStateGenerator createSlab(Block block, ResourceLocation locationBottom, ResourceLocation locationTop, ResourceLocation locationDouble) {
        return MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.property(BlockStateProperties.SLAB_TYPE)
                        .select(SlabType.BOTTOM, Variant.variant().with(VariantProperties.MODEL, locationBottom))
                        .select(SlabType.TOP, Variant.variant().with(VariantProperties.MODEL, locationTop))
                        .select(SlabType.DOUBLE, Variant.variant().with(VariantProperties.MODEL, locationDouble))
                );
    }

    protected void createSimpleSlab(Block block, ResourceLocation source) {
        TextureMapping texturemapping = TextureMapping.cube(source.withPrefix("block/"));
        ResourceLocation resourcelocation = ModelTemplates.SLAB_BOTTOM.create(block, texturemapping, this.modelOutput);
        ResourceLocation resourcelocation1 = ModelTemplates.SLAB_TOP.create(block, texturemapping, this.modelOutput);
        this.blockStateOutput.accept(createSlab(block, resourcelocation, resourcelocation1, source.withPrefix("block/")));
        this.registerSimpleItemModel(block, resourcelocation);
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

    protected static BlockStateGenerator createStairs(Block block, ResourceLocation location_inner, ResourceLocation location_straight, ResourceLocation location_outer) {
        return MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.STAIRS_SHAPE)
                        .select(Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, location_straight))

                        .select(Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, location_straight)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, location_straight)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, location_straight)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_outer))

                        .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_outer))

                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_inner))

                        .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_inner))

                        .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.EAST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, location_straight)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.WEST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, location_straight)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.SOUTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, location_straight)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, location_straight)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_outer)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true))

                        .select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, location_inner)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true))
                );
    }

    public void createStairs(Block block, ResourceLocation source) {
        TextureMapping texturemapping = TextureMapping.cube(source.withPrefix("block/"));
        ResourceLocation resourcelocation = ModelTemplates.STAIRS_INNER.create(block, texturemapping, this.modelOutput);
        ResourceLocation resourcelocation1 = ModelTemplates.STAIRS_STRAIGHT.create(block, texturemapping, this.modelOutput);
        ResourceLocation resourcelocation2 = ModelTemplates.STAIRS_OUTER.create(block, texturemapping, this.modelOutput);
        this.blockStateOutput.accept(createStairs(block, resourcelocation, resourcelocation1, resourcelocation2));
        this.registerSimpleItemModel(block, resourcelocation1);
    }
}
