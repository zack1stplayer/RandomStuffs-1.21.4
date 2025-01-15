package cool.zack1stplayer.RandomStuffs.item;

import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.print.attribute.Attribute;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RandomStuffsMain.MODID);


    public static final RegistryObject<Item> MY_ITEM = ITEMS.register("my_item",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("my_item"))
//                    .attributes(ItemAttributeModifiers.builder().build())
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
