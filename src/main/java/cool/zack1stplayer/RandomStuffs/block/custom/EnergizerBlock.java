package cool.zack1stplayer.RandomStuffs.block.custom;

import cool.zack1stplayer.RandomStuffs.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class EnergizerBlock extends Block {
    public EnergizerBlock(Properties pProperties) {
        super(pProperties);
    }
    private boolean Energizing = false;

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(pEntity instanceof ItemEntity itemEntity) {
            switch (itemEntity.getItem().getItem().getDescriptionId()) {
                case "item.minecraft.coal": {
                    Energizing = true;
                    if (itemEntity.getAge() >= 320) {
                        itemEntity.setItem(new ItemStack(ModItems.ENERGIZED_COAL.get(), itemEntity.getItem().getCount()));
                    }
                    break;
                }
                case "item.minecraft.charcoal": {
                    Energizing = true;
                    if (itemEntity.getAge() >= 240) {
                        itemEntity.setItem(new ItemStack(ModItems.ENERGIZED_CHARCOAL.get(), itemEntity.getItem().getCount()));
                        System.out.println(itemEntity.getItem().getItem());
                    }
                    break;
                }
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }


    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if(Energizing) {
            Energizing = false;
            if(pRandom.nextInt(1) == 0) {
                double d0 = (double) pPos.getX() + pRandom.nextDouble();
                double d1 = (double) pPos.getY() + 1.0 + pRandom.nextDouble();
                double d2 = (double) pPos.getZ() + pRandom.nextDouble();
                pLevel.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0, 0.0, 0.0);
            }
        }
        super.animateTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.randomstuffs.energizer"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
