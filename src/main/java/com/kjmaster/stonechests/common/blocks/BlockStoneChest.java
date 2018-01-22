package com.kjmaster.stonechests.common.blocks;

import com.kjmaster.stonechests.StoneChests;
import com.kjmaster.stonechests.common.ModGuiHandler;
import com.kjmaster.stonechests.common.blocks.tile.TileStoneChest;
import com.kjmaster.stonechests.common.util.BlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockStoneChest extends Block {

    public static final PropertyEnum<StoneChestType> VARIANT_PROP = PropertyEnum.create("variant", StoneChestType.class);

    public BlockStoneChest() {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, StoneChestType.COBBLE));

        this.setHardness(3.0F);
        this.setRegistryName(new ResourceLocation(BlockNames.STONE_CHEST));
        this.setUnlocalizedName("StoneChest");
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return state.getValue(VARIANT_PROP).makeEntity();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.0625F, 0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Nullable
    public ILockableContainer getLockableContainer(World world, BlockPos pos) {
        return this.getContainer(world, pos, false);
    }

    @Nullable
    public ILockableContainer getContainer(World world, BlockPos pos, boolean allowBlocking) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return true;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        TileEntity te = worldIn.getTileEntity(pos);

        if (te == null || !(te instanceof TileStoneChest)) {
            return true;
        }

        if (worldIn.isSideSolid(pos.add(0, 1, 0), EnumFacing.DOWN)) {
            return true;
        }

        if (worldIn.isRemote) {
            return true;
        }

        playerIn.openGui(StoneChests.instance, ModGuiHandler.chestID, worldIn,
                pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        if (itemIn.equals(CreativeTabs.DECORATIONS)) {
            for (StoneChestType type : StoneChestType.values()) {
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT_PROP, StoneChestType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT_PROP).ordinal();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT_PROP);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ItemStack stack = new ItemStack(this, 1, getMetaFromState(state));
        drops.add(stack);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
        worldIn.notifyBlockUpdate(pos, state, state, 3);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity te = worldIn.getTileEntity(pos);

        if (te != null && te instanceof TileStoneChest)
        {
            TileStoneChest teic = (TileStoneChest) te;

            teic.wasPlaced(placer, stack);
            teic.setFacing(placer.getHorizontalFacing().getOpposite());

            worldIn.notifyBlockUpdate(pos, state, state, 3);
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT_PROP).ordinal();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileStoneChest tileStoneChest = (TileStoneChest) worldIn.getTileEntity(pos);
        if (tileStoneChest != null) {
            InventoryHelper.dropInventoryItems(worldIn, pos, tileStoneChest);
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    private static final EnumFacing[] validRotationAxes = new EnumFacing[] {EnumFacing.UP, EnumFacing.DOWN};

    @Nullable
    @Override
    public EnumFacing[] getValidRotations(World world, BlockPos pos) {
        return validRotationAxes;
    }

    @Override
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
        if (world.isRemote) {
            return false;
        }
        if (axis == EnumFacing.UP || axis == EnumFacing.DOWN) {
            TileEntity te = world.getTileEntity(pos);
            if (te instanceof TileStoneChest) {
                TileStoneChest tesc = (TileStoneChest) te;
                tesc.rotateAround();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        super.eventReceived(state, worldIn, pos, id, param);

        TileEntity te = worldIn.getTileEntity(pos);

        return te != null && te.receiveClientEvent(id, param);
    }
}
