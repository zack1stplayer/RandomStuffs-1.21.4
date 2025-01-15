package cool.zack1stplayer.RandomStuffs.item;

import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RandomStuffsMain.MODID);


    public static final RegistryObject<Item> REDDITE_LONGSWORD = ITEMS.register("reddite_longsword",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("reddite_longsword"))
                    .equippable(EquipmentSlot.byName("mainhand"))
            )
    );

    public static final RegistryObject<Item> REDDITE_ITEM = ITEMS.register("reddite_item",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("reddite_item"))
            )
    );

    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("example_item"))
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(1)
                            .saturationModifier(2f)
                            .build()
                    )
            )
    );



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
