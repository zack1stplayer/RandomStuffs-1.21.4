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

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.ofEntries(
                    Map.entry(Blocks.STONE, Blocks.STONE_BRICKS),
                    Map.entry(Blocks.STONE_SLAB, Blocks.STONE_BRICK_SLAB),
                    Map.entry(Blocks.STONE_STAIRS, Blocks.STONE_BRICK_STAIRS),

                    Map.entry(Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS),
                    Map.entry(Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE_BRICKS),
                    Map.entry(Blocks.COBBLED_DEEPSLATE_SLAB, Blocks.DEEPSLATE_BRICK_SLAB),
                    Map.entry(Blocks.COBBLED_DEEPSLATE_STAIRS, Blocks.DEEPSLATE_BRICK_STAIRS),
                    Map.entry(Blocks.COBBLED_DEEPSLATE_WALL, Blocks.DEEPSLATE_BRICK_WALL),

                    Map.entry(Blocks.DEEPSLATE_BRICKS, Blocks.POLISHED_DEEPSLATE),
                    Map.entry(Blocks.DEEPSLATE_BRICK_SLAB, Blocks.POLISHED_DEEPSLATE_SLAB),
                    Map.entry(Blocks.DEEPSLATE_BRICK_STAIRS, Blocks.POLISHED_DEEPSLATE_STAIRS),
                    Map.entry(Blocks.DEEPSLATE_BRICK_WALL, Blocks.POLISHED_DEEPSLATE_WALL),

                    Map.entry(Blocks.POLISHED_DEEPSLATE, Blocks.DEEPSLATE_BRICKS),
                    Map.entry(Blocks.POLISHED_DEEPSLATE_SLAB, Blocks.DEEPSLATE_BRICK_SLAB),
                    Map.entry(Blocks.POLISHED_DEEPSLATE_STAIRS, Blocks.DEEPSLATE_BRICK_STAIRS),
                    Map.entry(Blocks.POLISHED_DEEPSLATE_WALL, Blocks.DEEPSLATE_BRICK_WALL),

                    Map.entry(Blocks.END_STONE, Blocks.END_STONE_BRICKS)
            );


    public ChiselItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(@NotNull UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(pContext.getClickedPos(),
                        CHISEL_MAP.get(clickedBlock).withPropertiesOf(level.getBlockState(pContext.getClickedPos())));

                pContext.getItemInHand().hurtAndBreak(1,
                        ((ServerLevel) level),
                        ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
                );
                level.playSound(null, pContext.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
