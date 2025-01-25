package cool.zack1stplayer.RandomStuffs.item;

import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import cool.zack1stplayer.RandomStuffs.block.ModBlocks;
import cool.zack1stplayer.RandomStuffs.item.custom.BlockLinkerItem;
import cool.zack1stplayer.RandomStuffs.item.custom.ChiselItem;
import cool.zack1stplayer.RandomStuffs.item.custom.FuelItem;
import cool.zack1stplayer.RandomStuffs.item.custom.GrowthItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

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

    public static final RegistryObject<Item> ENERGIZED_CHARCOAL = ITEMS.register("energized_charcoal",
            () -> new FuelItem(new Item.Properties()
                    .setId(ITEMS.key("energized_charcoal"))
                    , 2400
            )   // Coal's burnTime is 1600
    );

    public static final RegistryObject<Item> RADDITE_LONGSWORD = ITEMS.register("raddite_longsword",
            () -> new SwordItem(ToolMaterial.IRON, 3.5f, -2.4f, new Item.Properties()
                    .setId(ITEMS.key("raddite_longsword"))
                    .attributes(ItemAttributeModifiers.builder()
                            .add(Attributes.BLOCK_INTERACTION_RANGE,
                                    new AttributeModifier(ResourceLocation.withDefaultNamespace("block_interaction_range"),
                                            0.5f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.ENTITY_INTERACTION_RANGE,
                                    new AttributeModifier(ResourceLocation.withDefaultNamespace("entity_interaction_range"),
                                            0.5f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND).build())
            )
    );

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties()
                    .setId(ITEMS.key("chisel"))
                    .durability(250)
            )
    );

    public static final RegistryObject<Item> GROWTH_WAND = ITEMS.register("growth_wand",
            () -> new GrowthItem(new Item.Properties()
                    .setId(ITEMS.key("growth_wand"))
                    .durability(250)
            )
    );

    public static final RegistryObject<Item> BLOCK_LINKER = ITEMS.register("block_linker",
            () -> new BlockLinkerItem(new Item.Properties()
                    .setId(ITEMS.key("block_linker"))
                    .stacksTo(1)
            )
    );

    public static final RegistryObject<Item> COTTON_SEEDS = ITEMS.register("cotton_crop",
            () -> new BlockItem(ModBlocks.COTTON_CROP.get(), new Item.Properties()
                    .setId(ITEMS.key("cotton_crop"))
                    .useItemDescriptionPrefix()
            )
    );

    public static final RegistryObject<Item> COTTON_BOLL = ITEMS.register("cotton_boll",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("cotton_boll"))
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
