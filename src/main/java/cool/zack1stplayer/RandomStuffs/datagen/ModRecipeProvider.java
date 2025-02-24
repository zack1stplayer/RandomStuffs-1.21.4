package cool.zack1stplayer.RandomStuffs.datagen;

import com.google.gson.JsonElement;
import cool.zack1stplayer.RandomStuffs.RandomStuffsMain;
import cool.zack1stplayer.RandomStuffs.block.ModBlocks;
import cool.zack1stplayer.RandomStuffs.item.ModItems;
import cool.zack1stplayer.RandomStuffs.util.ModTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private final HolderGetter<Item> items;

    protected ModRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
        super(pRegistries, new Wrapped(pOutput));
        ((Wrapped)this.output).setSelf(this);
        this.items = pRegistries.lookupOrThrow(Registries.ITEM);
    }

    @Override
    protected void buildRecipes() {
        /*
        *   When there is a conflicting name use the following line:
        * .save(this.output, RandomStuffsMain.MODID + ":itemname_extension")
        *
        */

//  SHAPED RECIPES
        // Growth Wand
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, ModItems.GROWTH_WAND.get(),1)
                .pattern(" bm")
                .pattern(" rb")
                .pattern("s  ")
                .define('r', ModItems.RADDITE.get())
                .define('b', Items.BONE_MEAL)
                .define('m', Items.MOSS_BLOCK)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(ModItems.RADDITE.get().toString(), has(ModItems.RADDITE.get()))
                .save(this.output);

        // Chisel
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, ModItems.CHISEL.get(),1)
                .pattern("  i")
                .pattern(" r ")
                .pattern("s  ")
                .define('i', Tags.Items.INGOTS_IRON)
                .define('r', ModItems.RADDITE.get())
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(ModItems.RADDITE.get().toString(), has(ModItems.RADDITE.get()))
                .save(this.output);

        // Energizer
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, ModBlocks.ENERGIZER.get(),1)
                .pattern("rrr")
                .pattern("#R#")
                .pattern("ccc")
                .define('r', ModItems.RADDITE.get())
                .define('R', Items.REDSTONE_BLOCK)
                .define('#', Tags.Items.COBBLESTONE_NORMAL)
                .define('c', Tags.Items.INGOTS_COPPER)
                .unlockedBy(ModItems.RADDITE.get().toString(), has(ModItems.RADDITE.get()))
                .save(this.output);

        // Torches x8
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, Items.TORCH,8)
                .pattern("#")
                .pattern("c")
                .define('#', ModTags.Items.ENERGIZED_FUEL)
                .define('c', Tags.Items.RODS_WOODEN)
                .unlockedBy(ModItems.RADDITE.get().toString(), has(ModItems.RADDITE.get()))
                .save(this.output, RandomStuffsMain.MODID + ":torch");

        // Raddite Longsword
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, ModItems.RADDITE_LONGSWORD.get(),1)
                .pattern("r")
                .pattern("r")
                .pattern("c")
                .define('r', ModItems.RADDITE.get())
                .define('c', Tags.Items.RODS_WOODEN)
                .unlockedBy(ModItems.RADDITE.get().toString(), has(ModItems.RADDITE.get()))
                .save(this.output);

        // Raddite Block
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, ModBlocks.RADDITE_BLOCK.get(),1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .define('r', ModItems.RADDITE.get())
                .unlockedBy(ModItems.RADDITE.get().toString(), has(ModItems.RADDITE.get()))
                .save(this.output);


        // VANILLA ALT RECIPES
        // Honeycomb Slab
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, ModBlocks.HONEYCOMB_SLAB.get(),1)
                .pattern("rrr")
                .define('r', Blocks.HONEYCOMB_BLOCK)
                .unlockedBy(Blocks.HONEYCOMB_BLOCK.toString(), has(Blocks.HONEYCOMB_BLOCK))
                .save(this.output);

        // Honeycomb Stairs
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, ModBlocks.HONEYCOMB_STAIRS.get(),1)
                .pattern("r  ")
                .pattern("rr ")
                .pattern("rrr")
                .define('r', Blocks.HONEYCOMB_BLOCK)
                .unlockedBy(Blocks.HONEYCOMB_BLOCK.toString(), has(Blocks.HONEYCOMB_BLOCK))
                .save(this.output);

        // Honeycomb Trapdoor
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, ModBlocks.HONEYCOMB_TRAPDOOR.get(),1)
                .pattern("rrr")
                .pattern("rrr")
                .define('r', Blocks.HONEYCOMB_BLOCK)
                .unlockedBy(Blocks.HONEYCOMB_BLOCK.toString(), has(Blocks.HONEYCOMB_BLOCK))
                .save(this.output);


//  SHAPELESS RECIPES
        // Raddite
        ShapelessRecipeBuilder.shapeless(this.items, RecipeCategory.MISC, ModItems.RADDITE.get(), 9)
                .requires(ModBlocks.RADDITE_BLOCK.get())
                .unlockedBy(ModBlocks.RADDITE_BLOCK.get().toString(), has(ModBlocks.RADDITE_BLOCK.get()))
                .save(this.output);


//  STONECUTTER RECIPES
        // Honeycomb Slab
        easyStonecutting(Blocks.HONEYCOMB_BLOCK, ModBlocks.HONEYCOMB_SLAB.get(), "honeycomb_slab_from_stonecutting_honeycomb_block");

        // Honeycomb Stairs
        easyStonecutting(Blocks.HONEYCOMB_BLOCK, ModBlocks.HONEYCOMB_STAIRS.get(), "honeycomb_stairs_from_stonecutting_honeycomb_block");

        // Honeycomb Trapdoor
        easyStonecutting(Blocks.HONEYCOMB_BLOCK, ModBlocks.HONEYCOMB_TRAPDOOR.get(), "honeycomb_trapdoor_from_stonecutting_honeycomb_block");


//  SMELTING RECIPES
        // Raddite from smelting Raw Raddite
        easySmelting(ModItems.RAW_RADDITE.get(), ModItems.RADDITE.get(), "raddite_from_smelting_raw_raddite");

        // Raddite from smelting Raddite Ore
        easySmelting(ModBlocks.RADDITE_ORE.get(), ModItems.RADDITE.get(), "raddite_from_smelting_raddite_ore");

        // Raddite from smelting Deepslate Raddite Ore
        easySmelting(ModBlocks.DEEPSLATE_RADDITE_ORE.get(), ModItems.RADDITE.get(), "raddite_from_smelting_deepslate_raddite_ore");


//  BLASTING RECIPES
        // Raddite from blasting Raw Raddite
        easyBlasting(ModItems.RAW_RADDITE.get(), ModItems.RADDITE.get(), "raddite_from_blasting_raw_raddite");

        // Raddite from blasting Raddite Ore
        easyBlasting(ModBlocks.RADDITE_ORE.get(), ModItems.RADDITE.get(), "raddite_from_blasting_raddite_ore");

        // Raddite from blasting Deepslate Raddite Ore
        easyBlasting(ModBlocks.DEEPSLATE_RADDITE_ORE.get(), ModItems.RADDITE.get(), "raddite_from_blasting_deepslate_raddite_ore");
    }


    protected void easyStonecutting(ItemLike pIngredient, ItemLike pOutput, String recipeName) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(pIngredient), RecipeCategory.BUILDING_BLOCKS, pOutput)
                .unlockedBy(pIngredient.toString(), has(pIngredient))
                .save(this.output, RandomStuffsMain.MODID + ":" + recipeName);
    }

    protected void easySmelting(ItemLike pIngredient, ItemLike pOutput, String recipeName) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(pIngredient), RecipeCategory.MISC, pOutput, 0.7f, 200)
                .unlockedBy(pIngredient.toString(), has(pIngredient))
                .save(this.output, RandomStuffsMain.MODID + ":" + recipeName);
    }

    protected void easyBlasting(ItemLike pIngredient, ItemLike pOutput, String recipeName) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(pIngredient), RecipeCategory.MISC, pOutput, 0.7f, 100)
                .unlockedBy(pIngredient.toString(), has(pIngredient))
                .save(this.output, RandomStuffsMain.MODID + ":" + recipeName);
    }


    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        public String getName() {
            return ModRecipeProvider.class.getSimpleName();
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }
    }

    private static class Wrapped implements RecipeOutput {
        private final RecipeOutput wrapped;
        private ModRecipeProvider self;

        private Wrapped(RecipeOutput wrapped) {
            this.wrapped = wrapped;
        }

        private void setSelf(ModRecipeProvider self) {
            this.self = self;
        }

        @Override
        public void accept(ResourceKey<Recipe<?>> id, Recipe<?> recipe, AdvancementHolder advancement) {
            wrapped.accept(id, recipe, null);
        }

        @Override
        public Advancement.Builder advancement() {
            return wrapped.advancement();
        }

        @Override
        public void accept(ResourceKey<Recipe<?>> id, Recipe<?> recipe, ResourceLocation advancementId, JsonElement advancement) {
            wrapped.accept(id, recipe, null);
        }

        @Override
        public HolderLookup.Provider registry() {
            return wrapped.registry();
        }

        @Override
        public void includeRootAdvancement() {}
    }
}
