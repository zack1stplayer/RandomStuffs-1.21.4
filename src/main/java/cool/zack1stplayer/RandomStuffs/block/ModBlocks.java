package cool.zack1stplayer.RandomStuffs.block;

import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import cool.zack1stplayer.RandomStuffs.block.custom.CottonCropBlock;
import cool.zack1stplayer.RandomStuffs.block.custom.EnergizerBlock;
import cool.zack1stplayer.RandomStuffs.block.custom.MusicalBlock;
import cool.zack1stplayer.RandomStuffs.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RandomStuffsMain.MODID);

// MODBLOCKS
    public static final RegistryObject<Block> ENERGIZER = registerBlock("energizer",
            () -> new EnergizerBlock(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("energizer"))
                    .strength(5f,6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.COPPER)
                    .mapColor(MapColor.COLOR_RED)
            )
    );

    public static final RegistryObject<Block> RADDITE_BLOCK = registerBlock("raddite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("raddite_block"))
                    .strength(5f,6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
                    .mapColor(MapColor.COLOR_RED)
            )
    );

    public static final RegistryObject<Block> RADDITE_ORE = registerBlock("raddite_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("raddite_ore"))
                    .strength(3f, 3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .mapColor(MapColor.STONE)
                    .lightLevel(p_220871_ -> 3)
            )
    );

    public static final RegistryObject<Block> DEEPSLATE_RADDITE_ORE = registerBlock("deepslate_raddite_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("deepslate_raddite_ore"))
                    .strength(4.5f, 3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
                    .mapColor(MapColor.DEEPSLATE)
                    .lightLevel(p_220871_ -> 3)
            )
    );

    public static final RegistryObject<Block> COTTON_CROP = BLOCKS.register("cotton_crop",
            () -> new CottonCropBlock(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("cotton_crop"))
                    .mapColor(blockState -> blockState.getValue(CottonCropBlock.AGE) >= 6 ? MapColor.QUARTZ : MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
            )
    );

// VANILLA ALT BLOCKS
    public static final RegistryObject<StairBlock> HONEYCOMB_STAIRS = registerBlock("honeycomb_stairs",
        () -> new StairBlock(Blocks.HONEYCOMB_BLOCK.defaultBlockState(),
                BlockBehaviour.Properties.of()
                        .setId(BLOCKS.key("honeycomb_stairs")).mapColor(MapColor.COLOR_ORANGE)
                        .strength(0.6F).sound(SoundType.CORAL_BLOCK))
    );

    public static final RegistryObject<SlabBlock> HONEYCOMB_SLAB = registerBlock("honeycomb_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("honeycomb_slab")).mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.6F).sound(SoundType.CORAL_BLOCK))
    );

    public static final RegistryObject<TrapDoorBlock> HONEYCOMB_TRAPDOOR = registerBlock("honeycomb_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.ACACIA, BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("honeycomb_trapdoor")).mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.6F).sound(SoundType.CORAL_BLOCK).noOcclusion())
    );

// WIP BLOCKS
    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Block> EXAMPLE_BLOCK = registerBlock("example_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("example_block"))
                    .strength(4f,4f)
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.STONE)
            )
    );

    // Musical Block, WIP
    public static final RegistryObject<Block> CHIME = registerBlock("chime",
            () -> new MusicalBlock(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("chime"))
                    .strength(0.8f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)
                    .mapColor(MapColor.WOOD)
            )
    );


    private static <T extends Block> RegistryObject<T> registerBlock (String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().setId(ModItems.ITEMS.key(name))));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
