package cool.zack1stplayer.RandomStuffs.datagen;

import com.google.gson.JsonElement;
import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import cool.zack1stplayer.RandomStuffs.block.ModBlocks;
import cool.zack1stplayer.RandomStuffs.item.ModItems;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
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

public class ModBlockStateProvider implements DataProvider {
    private final PackOutput.PathProvider blockStatePathProvider;
    private final PackOutput.PathProvider itemInfoPathProvider;
    private final PackOutput.PathProvider modelPathProvider;

    public ModBlockStateProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        this.blockStatePathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.itemInfoPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
        this.modelPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
    }


    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        ModBlockStateProvider.ItemInfoCollector modelprovider$iteminfocollector = new ModBlockStateProvider.ItemInfoCollector(this::getKnownItems);
        ModBlockStateProvider.BlockStateGeneratorCollector modelprovider$blockstategeneratorcollector = new ModBlockStateProvider.BlockStateGeneratorCollector(this::getKnownBlocks);
        ModBlockStateProvider.SimpleModelCollector modelprovider$simplemodelcollector = new ModBlockStateProvider.SimpleModelCollector();

        getBlockModelGenerators(modelprovider$blockstategeneratorcollector, modelprovider$iteminfocollector, modelprovider$simplemodelcollector).run();
        getItemModelGenerators(modelprovider$iteminfocollector, modelprovider$simplemodelcollector).run();

        modelprovider$blockstategeneratorcollector.validate();
        modelprovider$iteminfocollector.finalizeAndValidate();

        return CompletableFuture.allOf(
                modelprovider$blockstategeneratorcollector.save(cachedOutput, this.blockStatePathProvider),
                modelprovider$simplemodelcollector.save(cachedOutput, this.modelPathProvider),
                modelprovider$iteminfocollector.save(cachedOutput, this.itemInfoPathProvider)
        );
    }

    protected Stream<RegistryObject<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream();
    }

    protected Stream<RegistryObject<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream();
    }

    protected ModBlockModelGenerators getBlockModelGenerators(ModBlockStateProvider.BlockStateGeneratorCollector blocks, ModBlockStateProvider.ItemInfoCollector items, ModBlockStateProvider.SimpleModelCollector models) {
        return new ModBlockModelGenerators(blocks, items, models);
    }

    protected ModItemModelGenerators getItemModelGenerators(ModBlockStateProvider.ItemInfoCollector items, ModBlockStateProvider.SimpleModelCollector models) {
        return new ModItemModelGenerators(items, models);
    }

    static <T> CompletableFuture<?> saveAll(CachedOutput cachedOutput, Function<T, Path> pathFunction, Map<T, ? extends Supplier<JsonElement>> tgen) {
        return DataProvider.saveAll(cachedOutput, Supplier::get, pathFunction, tgen);
    }

    @Override
    public final String getName() {
        return "Model Definitions";
    }



    @OnlyIn(Dist.CLIENT)
    public static class BlockStateGeneratorCollector implements Consumer<BlockStateGenerator> {
        private final Map<Block, BlockStateGenerator> generators = new HashMap<>();
        private final Supplier<Stream<RegistryObject<Block>>> known;

        public BlockStateGeneratorCollector() {
            this(() -> ModBlocks.BLOCKS.getEntries().stream());
        }

        public BlockStateGeneratorCollector(Supplier<Stream<RegistryObject<Block>>> known) {
            this.known = known;
        }

        public void accept(BlockStateGenerator p_378147_) {
            Block block = p_378147_.getBlock();
            BlockStateGenerator blockstategenerator = this.generators.put(block, p_378147_);
            if (blockstategenerator != null) {
                throw new IllegalStateException("Duplicate blockstate definition for " + block);
            }
        }

        public void validate() {
            Stream<Block> stream = known.get().map(RegistryObject::get);
            List<String> list = stream.filter(p_378423_ -> !this.generators.containsKey(p_378423_))
                    .map(Block::getDescriptionId)
                    .toList();
            if (!list.isEmpty()) {
                throw new IllegalStateException("Missing blockstate definitions for: " + list);
            }
        }

        public CompletableFuture<?> save(CachedOutput p_377986_, PackOutput.PathProvider p_377969_) {
            return ModBlockStateProvider.saveAll(p_377986_, p_378541_ -> p_377969_.json(ResourceLocation.fromNamespaceAndPath(RandomStuffsMain.MODID, p_378541_.asItem().getDescriptionId())), this.generators);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class ItemInfoCollector implements ItemModelOutput {
        private final Map<Item, ClientItem> itemInfos = new HashMap<>();
        private final Map<Item, Item> copies = new HashMap<>();
        private final Supplier<Stream<RegistryObject<Item>>> known;

        public ItemInfoCollector() {
            this(() -> ModItems.ITEMS.getEntries().stream());
        }

        public ItemInfoCollector(Supplier<Stream<RegistryObject<Item>>> known) {
            this.known = known;
        }

        @Override
        public void accept(Item p_376450_, ItemModel.Unbaked p_378513_) {
            this.register(p_376450_, new ClientItem(p_378513_, ClientItem.Properties.DEFAULT));
        }

        public void accept(RegistryObject<Item> p_376450_, ItemModel.Unbaked p_378513_) {
            this.register(p_376450_.get(), new ClientItem(p_378513_, ClientItem.Properties.DEFAULT));
        }

        private void register(Item p_378050_, ClientItem p_376323_) {
            ClientItem clientitem = this.itemInfos.put(p_378050_, p_376323_);
            if (clientitem != null) {
                throw new IllegalStateException("Duplicate item model definition for " + p_378050_);
            }
        }

        @Override
        public void copy(Item p_377438_, Item p_376965_) {
            this.copies.put(p_376965_, p_377438_);
        }

        public void copy(RegistryObject<Item> p_377438_, RegistryObject<Item> p_376965_) {
            this.copies.put(p_376965_.get(), p_377438_.get());
        }

        public void generateDefaultBlockModels() {
            System.out.println("Default Models Called");

            Stream<Block> tStream = ModBlocks.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get);

            tStream.forEach(p_378629_ -> {
                System.out.println(this.copies);
                if (!this.copies.containsKey(p_378629_.asItem())) {
                    System.out.println("!this.copies.containsKey(p_378629_.get().asItem())");
                    if (p_378629_.asItem() instanceof BlockItem blockitem && !this.itemInfos.containsKey(blockitem)) {
                        System.out.println("p_378629_.get().asItem() instanceof BlockItem blockitem && !this.itemInfos.containsKey(blockitem)");
                        ResourceLocation resourcelocation = ResourceLocation.fromNamespaceAndPath(RandomStuffsMain.MODID, blockitem.getDescriptionId());
                        this.accept(blockitem, ItemModelUtils.plainModel(resourcelocation));
                    }
                }
            });
            System.out.println("Test");
        }

        public void finalizeAndValidate() {
            this.copies.forEach((p_376289_, p_375718_) -> {
                ClientItem clientitem = this.itemInfos.get(p_375718_);
                if (clientitem == null) {
                    throw new IllegalStateException("Missing donor: " + p_375718_ + " -> " + p_376289_);
                } else {
                    this.register(p_376289_, clientitem);
                }
            });

            List<String> list = known.get()
                    .map(RegistryObject::get)
                    .filter(p_377225_ -> !this.itemInfos.containsKey(p_377225_))
                    .map(Item::getDescriptionId)
                    .toList();
            if (!list.isEmpty()) {
                throw new IllegalStateException("Missing item model definitions for: " + list);
            }
        }

        public CompletableFuture<?> save(CachedOutput p_378568_, PackOutput.PathProvider p_377933_) {
            return DataProvider.saveAll(
                    p_378568_, ClientItem.CODEC, p_377091_ -> p_377933_.json(ResourceLocation.fromNamespaceAndPath(RandomStuffsMain.MODID, p_377091_.getDescriptionId())), this.itemInfos
            );
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class SimpleModelCollector implements BiConsumer<ResourceLocation, ModelInstance> {
        private final Map<ResourceLocation, ModelInstance> models = new HashMap<>();

        public void accept(ResourceLocation p_376394_, ModelInstance p_376914_) {
            Supplier<JsonElement> supplier = this.models.put(p_376394_, p_376914_);
            if (supplier != null) {
                throw new IllegalStateException("Duplicate model definition for " + p_376394_);
            }
        }

        public CompletableFuture<?> save(CachedOutput p_377109_, PackOutput.PathProvider p_378055_) {
            return ModBlockStateProvider.saveAll(p_377109_, p_378055_::json, this.models);
        }
    }
}
