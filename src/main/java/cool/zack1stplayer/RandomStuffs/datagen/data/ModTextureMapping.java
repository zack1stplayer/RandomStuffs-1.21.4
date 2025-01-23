package cool.zack1stplayer.RandomStuffs.datagen.data;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class ModTextureMapping extends TextureMapping {
    public static TextureMapping cubeBottomTop(ResourceLocation resourceLocation) {
        return new TextureMapping()
                .put(TextureSlot.SIDE, resourceLocation.withSuffix("_side"))
                .put(TextureSlot.TOP, resourceLocation.withSuffix("_top"))
                .put(TextureSlot.BOTTOM, resourceLocation.withSuffix("_bottom"));
    }
}
