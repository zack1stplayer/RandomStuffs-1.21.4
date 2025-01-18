package cool.zack1stplayer.RandomStuffs.datagen;

import cool.zack1stplayer.RandomStuffs.block.ModBlocks;
import cool.zack1stplayer.RandomStuffs.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.EXAMPLE_BLOCK.get());
        dropSelf(ModBlocks.RADDITE_BLOCK.get());
        dropSelf(ModBlocks.ENERGIZER.get());
        dropSelf(ModBlocks.CHIME.get());

        this.add(ModBlocks.RADDITE_ORE.get(),
                block -> createMultiOreDrops(ModBlocks.RADDITE_ORE.get(), ModItems.RAW_RADDITE.get(),
                        1.0f, 3.0f)
        );
        this.add(ModBlocks.DEEPSLATE_RADDITE_ORE.get(),
                block -> createMultiOreDrops(ModBlocks.DEEPSLATE_RADDITE_ORE.get(), ModItems.RAW_RADDITE.get(),
                        2.0f, 5.0f)
        );
    }

    protected LootTable.Builder createMultiOreDrops(Block pBlock, Item pItem, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        pBlock,
                        LootItem.lootTableItem(pItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
