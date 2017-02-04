package ruby.bamboo.block;

import java.util.List;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ruby.bamboo.api.Constants;
import ruby.bamboo.core.init.BambooData.BambooBlock;
import ruby.bamboo.core.init.BambooData.BambooBlock.StateIgnore;
import ruby.bamboo.core.init.EnumCreateTab;
import ruby.bamboo.core.init.EnumMaterial;
import ruby.bamboo.item.itemblock.ItemBambooPane;
import ruby.bamboo.texture.IMultiTextuerBlock;

@BambooBlock(itemBlock = ItemBambooPane.class, createiveTabs = EnumCreateTab.TAB_BAMBOO, material = EnumMaterial.GROUND)
public class BambooPane extends BlockPane implements IMultiTextuerBlock {

    public static final PropertyInteger META = PropertyInteger.create(Constants.META, 0, 3);

    public BambooPane(Material materialIn) {
        super(materialIn, true);
        this.setHardness(0.3F);
        this.setDefaultState(this.getDefaultState().withProperty(META, 0));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(META, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(META).intValue();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 0; i < 4; i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, WEST, SOUTH, META });
    }

    @Override
    public String getTexName(IBlockState state, EnumFacing side) {
        int meta = state.getValue(META).intValue();
        switch (meta) {
            case 0:
                return "bamboomod:blocks/bamboopane";
            case 1:
                return "bamboomod:blocks/bamboopane2";
            case 2:
                return "bamboomod:blocks/bamboopane3";
            case 3:
                return "bamboomod:blocks/ranma";
        }
        return "bamboomod:blocks/bamboopane";
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(this, 1, state.getValue(META));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(META);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, state.getValue(META));
    }

    @StateIgnore
    public IProperty[] getIgnoreState() {
        return new IProperty[] { NORTH, EAST, WEST, SOUTH };
    }
}
