package cool.zack1stplayer.RandomStuffs.datagen.backup;

import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import cool.zack1stplayer.RandomStuffs.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RandomStuffsMain.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.RADDITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_RADDITE_ORE);
        blockWithItem(ModBlocks.RADDITE_BLOCK);

        blockWithItem(ModBlocks.ENERGIZER);

        blockWithItem(ModBlocks.EXAMPLE_BLOCK);
        blockWithItem(ModBlocks.CHIME);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
