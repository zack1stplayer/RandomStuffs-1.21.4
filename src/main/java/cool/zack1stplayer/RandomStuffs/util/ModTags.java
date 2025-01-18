package cool.zack1stplayer.RandomStuffs.util;

import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(RandomStuffsMain.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ENERGIZED_FUEL = createTag("energized_fuel");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(RandomStuffsMain.MODID, name));
        }
    }
}
