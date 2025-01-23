package cool.zack1stplayer.RandomStuffs.item;

import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import cool.zack1stplayer.RandomStuffs.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RandomStuffsMain.MODID);


    public static final RegistryObject<CreativeModeTab> RANDOMSTUFFS_ITEMS_TAB = CREATIVE_MODE_TABS.register("randomstuffs_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.RADDITE_BLOCK.get()))
                    .title(Component.translatable("creativetab.randomstuffs.randdomstuffs_items"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RADDITE.get());
                        output.accept(ModItems.RAW_RADDITE.get());

                        output.accept(ModBlocks.RADDITE_BLOCK.get());
                        output.accept(ModBlocks.RADDITE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_RADDITE_ORE.get());

                        output.accept(ModItems.ENERGIZED_COAL.get());
                        output.accept(ModItems.ENERGIZED_CHARCOAL.get());

                        output.accept(ModBlocks.ENERGIZER.get());

                        output.accept(ModItems.RADDITE_LONGSWORD.get());
                        output.accept(ModItems.CHISEL.get());
                        output.accept(ModItems.GROWTH_WAND.get());
                        output.accept(ModItems.BLOCK_LINKER.get());


                        // VANILLA ALT ITEMS
                        output.accept(ModBlocks.HONEYCOMB_STAIRS.get());
                        output.accept(ModBlocks.HONEYCOMB_SLAB.get());
                        output.accept(ModBlocks.HONEYCOMB_TRAPDOOR.get());


                        // WIP ITEMS
                        output.accept(ModBlocks.CHIME.get());

                        output.accept(ModItems.EXAMPLE_ITEM.get());
                        output.accept(ModBlocks.EXAMPLE_BLOCK.get());
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
