package cool.zack1stplayer.RandomStuffs.block;

import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import cool.zack1stplayer.RandomStuffs.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RandomStuffsMain.MODID);

    public static final RegistryObject<Block> REDDITE_BLOCK = registerBlock("reddite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("reddite_block"))
                    .strength(3f,4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            )
    );

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Block> EXAMPLE_BLOCK = registerBlock("example_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("example_block"))
                    .strength(4f,4f)
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.STONE)
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
