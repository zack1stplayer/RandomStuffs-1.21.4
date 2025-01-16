package cool.zack1stplayer.RandomStuffs.item;

import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import cool.zack1stplayer.RandomStuffs.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RandomStuffsMain.MODID);


    public static final RegistryObject<CreativeModeTab> RANDOMSTUFFS_ITEMS_TAB = CREATIVE_MODE_TABS.register("randomstuffs_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.REDDITE_BLOCK.get()))
                    .title(Component.translatable("creativetab.randomstuffs.randdomstuffs_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.REDDITE_ITEM.get());
                        output.accept(ModItems.RAW_REDDITE.get());

                        output.accept(ModBlocks.REDDITE_BLOCK.get());

                        output.accept(ModItems.REDDITE_LONGSWORD.get());

                        output.accept(ModItems.EXAMPLE_ITEM.get());
                        output.accept(ModBlocks.EXAMPLE_BLOCK.get());
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
