package cool.zack1stplayer.RandomStuffs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MusicalBlock extends Block {
    public MusicalBlock(Properties pProperties) {
        super(pProperties);
    }

    private float pPitch = 0f;

    private void RaisePitch() {
        pPitch += 1;
        if(pPitch == 16) {pPitch = 0;}
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos,
                                                        Player pPlayer, BlockHitResult pHitResult) {
        RaisePitch();
        pLevel.playSound(pPlayer, pPos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.RECORDS, 2f, pPitch);

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        pLevel.playSound(pPlayer, pPos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.RECORDS, 2f, pPitch);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    /*@Override
    protected int getSignal(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pDirection) {
        pLevel.playSound(pLevel., pPos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.RECORDS, 2f, pPitch);
        return super.getSignal(pState, pLevel, pPos, pDirection);
    }*/
}
