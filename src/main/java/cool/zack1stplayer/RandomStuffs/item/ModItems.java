package cool.zack1stplayer.RandomStuffs.item;

import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import cool.zack1stplayer.RandomStuffs.item.custom.ChiselItem;
import cool.zack1stplayer.RandomStuffs.item.custom.FuelItem;
import cool.zack1stplayer.RandomStuffs.item.custom.GrowthItem;
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


    public static final RegistryObject<Item> RAW_RADDITE = ITEMS.register("raw_raddite",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("raw_raddite"))
            )
    );

    public static final RegistryObject<Item> RADDITE = ITEMS.register("raddite",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("raddite"))
            )
    );

    public static final RegistryObject<Item> ENERGIZED_COAL = ITEMS.register("energized_coal",
            () -> new FuelItem(new Item.Properties()
                    .setId(ITEMS.key("energized_coal"))
                    , 3200
            )   // Coal's burnTime is 1600
    );

    public static final RegistryObject<Item> RADDITE_LONGSWORD = ITEMS.register("raddite_longsword",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("raddite_longsword"))
                    .equippable(EquipmentSlot.byName("mainhand"))
            )
    );

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () ->new ChiselItem(new Item.Properties()
                    .setId(ITEMS.key("chisel"))
                    .durability(250)
            )
    );

    public static final RegistryObject<Item> GROWTH_WAND = ITEMS.register("growth_wand",
            () ->new GrowthItem(new Item.Properties()
                    .setId(ITEMS.key("growth_wand"))
                    .durability(250)
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
