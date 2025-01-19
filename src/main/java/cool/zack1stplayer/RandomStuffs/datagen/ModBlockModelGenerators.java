package cool.zack1stplayer.RandomStuffs.datagen;

import com.google.gson.JsonElement;
import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import cool.zack1stplayer.RandomStuffs.block.ModBlocks;
import cool.zack1stplayer.RandomStuffs.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModBlockModelGenerators extends BlockModelGenerators {
    public ModBlockModelGenerators(Consumer<BlockStateGenerator> p_378137_, ItemModelOutput p_378502_, BiConsumer<ResourceLocation, ModelInstance> p_378240_) {
        super(p_378137_, p_378502_, p_378240_);
    }

    @Override
    public void run() {
        this.createTrivialCube(ModBlocks.RADDITE_BLOCK.get());
        this.createTrivialCube(ModBlocks.RADDITE_ORE.get());
        this.createTrivialCube(ModBlocks.DEEPSLATE_RADDITE_ORE.get());

        this.createTrivialBlock(ModBlocks.ENERGIZER.get(), TexturedModel.CUBE_TOP_BOTTOM);

        this.createTrivialCube(ModBlocks.EXAMPLE_BLOCK.get());
        this.createTrivialCube(ModBlocks.CHIME.get());


        if (this.itemModelOutput instanceof ModBlockStateProvider.ItemInfoCollector collector) {
            collector.generateDefaultBlockModels();
        }
    }
}
