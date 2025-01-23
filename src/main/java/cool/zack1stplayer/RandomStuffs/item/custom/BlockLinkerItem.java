package cool.zack1stplayer.RandomStuffs.item.custom;

import cool.zack1stplayer.RandomStuffs.component.ModDataComponentTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;

import java.util.List;

public class BlockLinkerItem extends Item {
    public BlockLinkerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
//        Level level = pContext.getLevel();
//        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();
        ItemStack heldItem = pContext.getItemInHand();

        if (pContext.getPlayer().isShiftKeyDown()) {
            heldItem.set(ModDataComponentTypes.COORDINATES.get(), pContext.getClickedPos());
        } else {
            heldItem.set(ModDataComponentTypes.COORDINATES.get(), null);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        BlockPos coords = pStack.get(ModDataComponentTypes.COORDINATES.get());
        if (coords != null) {
            pTooltipComponents.add(Component.translatable("tooltip.randomstuffs.block_linker.saved_coordinate").append(" " + coords));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
