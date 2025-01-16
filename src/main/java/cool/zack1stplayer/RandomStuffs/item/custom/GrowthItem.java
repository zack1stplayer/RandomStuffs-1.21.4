package cool.zack1stplayer.RandomStuffs.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class GrowthItem extends Item {
    private static final Map<Block, Block> GROWTH_MAP =
            Map.ofEntries(
                    Map.entry(Blocks.DIRT, Blocks.GRASS_BLOCK),
                    Map.entry(Blocks.GRASS_BLOCK, Blocks.PODZOL),
                    Map.entry(Blocks.PODZOL, Blocks.DIRT),
                    Map.entry(Blocks.COARSE_DIRT, Blocks.DIRT),

                    Map.entry(Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS),
                    Map.entry(Blocks.STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB),
                    Map.entry(Blocks.STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS),
                    Map.entry(Blocks.STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICK_WALL),

                    Map.entry(Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE),
                    Map.entry(Blocks.COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB),
                    Map.entry(Blocks.COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS),
                    Map.entry(Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL),

                    Map.entry(Blocks.STRIPPED_OAK_LOG, Blocks.OAK_LOG),
                    Map.entry(Blocks.STRIPPED_OAK_WOOD, Blocks.OAK_WOOD),
                    Map.entry(Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_LOG),
                    Map.entry(Blocks.STRIPPED_BIRCH_WOOD, Blocks.BIRCH_WOOD),
                    Map.entry(Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_LOG),
                    Map.entry(Blocks.STRIPPED_JUNGLE_WOOD, Blocks.JUNGLE_WOOD),
                    Map.entry(Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_LOG),
                    Map.entry(Blocks.STRIPPED_SPRUCE_WOOD, Blocks.SPRUCE_WOOD),
                    Map.entry(Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_LOG),
                    Map.entry(Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.DARK_OAK_WOOD),
                    Map.entry(Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_LOG),
                    Map.entry(Blocks.STRIPPED_ACACIA_WOOD, Blocks.ACACIA_WOOD),
                    Map.entry(Blocks.STRIPPED_MANGROVE_LOG, Blocks.MANGROVE_LOG),
                    Map.entry(Blocks.STRIPPED_MANGROVE_WOOD, Blocks.MANGROVE_WOOD),
                    Map.entry(Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_LOG),
                    Map.entry(Blocks.STRIPPED_CHERRY_WOOD, Blocks.CHERRY_WOOD),
                    Map.entry(Blocks.STRIPPED_PALE_OAK_LOG, Blocks.PALE_OAK_LOG),
                    Map.entry(Blocks.STRIPPED_PALE_OAK_WOOD, Blocks.PALE_OAK_WOOD),

                    Map.entry(Blocks.STRIPPED_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK),

                    Map.entry(Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_STEM),
                    Map.entry(Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.CRIMSON_HYPHAE),
                    Map.entry(Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_STEM),
                    Map.entry(Blocks.STRIPPED_WARPED_HYPHAE, Blocks.WARPED_HYPHAE)
            );


    public GrowthItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(@NotNull UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(GROWTH_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(pContext.getClickedPos(),
                        GROWTH_MAP.get(clickedBlock).withPropertiesOf(level.getBlockState(pContext.getClickedPos())));

                pContext.getItemInHand().hurtAndBreak(1,
                        ((ServerLevel) level),
                        ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
                );
                level.playSound(null, pContext.getClickedPos(), SoundEvents.ROOTED_DIRT_PLACE, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
