package fr.minecraftforgefrance.ffmtlibs.block;

import java.util.List;

import fr.minecraftforgefrance.ffmtlibs.entity.EntityBlockSittable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * To use extends or call the static function
 **/
public class BlockSittable extends Block
{
    public BlockSittable(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        return sitPlayer(worldIn, pos, playerIn, 1.0F);
    }

    /**
     * Call this in onBlockActivated
     *
     * @param world
     * @param pos
     * @param player
     * @param entityY
     * @return
     */
    public static boolean sitPlayer(World world, BlockPos pos, EntityPlayer player, float entityY)
    {
        return sitPlayer(world, pos, player, 0.5F, entityY, 0.5F);
    }

    /**
     * Call this in onBlockActivated
     *
     * @param world
     * @param pos
     * @param player
     * @param entityX
     * @param entityY
     * @param entityZ
     * @return
     */
    public static boolean sitPlayer(World world, BlockPos pos, EntityPlayer player, float entityX, float entityY, float entityZ)
    {
        if(!world.isRemote)
        {
            List<EntityBlockSittable> listEMB = world.getEntitiesWithinAABB(EntityBlockSittable.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0D, pos.getY() + 1.0D, pos.getZ() + 1.0D).expand(1.0D, 1.0D, 1.0D));
            for(EntityBlockSittable entitytocheck : listEMB)
            {
                if((entitytocheck.blockPosX == pos.getX()) && (entitytocheck.blockPosY == pos.getY()) && (entitytocheck.blockPosZ == pos.getZ()))
                {
                    entitytocheck.interact(player);
                    return true;
                }
            }

            EntityBlockSittable entity = new EntityBlockSittable(world, pos, pos.getX() + entityX, pos.getY() + entityY, pos.getZ() + entityZ);
            world.spawnEntity(entity);
            entity.interact(player);
        }
        return true;
    }
}