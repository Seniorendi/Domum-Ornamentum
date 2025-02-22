package com.ldtteam.domumornamentum.block;

import com.ldtteam.domumornamentum.container.ArchitectsCutterContainer;
import com.ldtteam.domumornamentum.util.BlockUtils;
import com.ldtteam.domumornamentum.util.Constants;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.inventory.ContainerLevelAccess;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public final class ArchitectsCutterBlock extends AbstractBlock<ArchitectsCutterBlock>
{
    private static final   Component    CONTAINER_NAME = new TranslatableComponent(Constants.MOD_ID + ".architectscutter");
    public static final    DirectionProperty FACING         = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE = Block.box(0.01D, 0.01D, 0.01D, 15.99D, 9.9D, 15.99D);

    public ArchitectsCutterBlock(AbstractBlock.Properties propertiesIn) {
        super(propertiesIn);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        this.setRegistryName(new ResourceLocation(Constants.MOD_ID, "architectscutter"));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @NotNull
    public InteractionResult use(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (worldIn.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(worldIn, pos));
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    public MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level worldIn, @NotNull BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new ArchitectsCutterContainer(id, inventory, ContainerLevelAccess.create(worldIn, pos)), CONTAINER_NAME);
    }

    @NotNull
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    public boolean useShapeForLightOcclusion(@NotNull BlockState state) {
        return true;
    }

    @NotNull
    public RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @NotNull
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @NotNull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull PathComputationType type) {
        return false;
    }

    @Override
    public @NotNull List<ItemStack> getDrops(final @NotNull BlockState state, final @NotNull LootContext.Builder builder)
    {
        final List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(this));
        return list;
    }
}
