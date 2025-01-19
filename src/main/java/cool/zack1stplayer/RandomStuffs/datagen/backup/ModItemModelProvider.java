package cool.zack1stplayer.RandomStuffs.datagen.backup;

import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import cool.zack1stplayer.RandomStuffs.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RandomStuffsMain.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RADDITE.get());
        basicItem(ModItems.RAW_RADDITE.get());
        basicItem(ModItems.ENERGIZED_COAL.get());
        basicItem(ModItems.ENERGIZED_CHARCOAL.get());

        basicItem(ModItems.RADDITE_LONGSWORD.get());
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.GROWTH_WAND.get());

        basicItem(ModItems.EXAMPLE_ITEM.get());
    }
}
