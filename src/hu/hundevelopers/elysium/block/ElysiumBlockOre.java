package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ElysiumBlockOre extends ElysiumBlockHeatable
{

	public ElysiumBlockOre()
	{
		super(Material.rock);
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_)
	{
		return this == Elysium.oreSulphure ? Elysium.itemSulphur : (this == Elysium.oreTourmaline ? Elysium.itemTourmaline : (this == Elysium.oreJade ? Elysium.itemJade
				: (this == Elysium.oreBeryl ? Elysium.itemBeryl : Item.getItemFromBlock(this))));
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random) {
		return this == Elysium.oreBeryl ? 4 + par1Random.nextInt(5) : 1;
	}

	@Override
	/**
	 * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
	 */
	public int quantityDroppedWithBonus(int par1, Random par2Random) {
		if (par1 > 0 && Item.getItemFromBlock(this) != getItemDropped(0, par2Random, par1)) {
			int j = par2Random.nextInt(par1 + 2) - 1;

			if (j < 0) {
				j = 0;
			}

			return this.quantityDropped(par2Random) * (j + 1);
		} else {
			return this.quantityDropped(par2Random);
		}
	}

	@Override
	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(world, x, y, z, par5,
				par6, par7);

		if (getItemDropped(par5, world.rand, par7) != Item.getItemFromBlock(this)) {
			int j1 = 0;

			if (this == Elysium.oreSulphure) {
				j1 = MathHelper.getRandomIntegerInRange(world.rand, 0, 2);
			} else if (this == Elysium.oreTourmaline) {
				j1 = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
			} else if (this == Elysium.oreBeryl) {
				j1 = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
			} else if (this == Elysium.oreJade) {
				j1 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
			}

			this.dropXpOnBlockBreak(world, x, y, z, j1);
		}
	}
	
	/**
     * Called when fire is updating, checks if a block face can catch fire.
     *
     *
     * @param world The current world
     * @param x The blocks X position
     * @param y The blocks Y position
     * @param z The blocks Z position
     * @param face The face that the fire is coming from
     * @return True if the face can be on fire, false otherwise.
     */
	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return this == Elysium.oreSulphure;
    }
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		super.onNeighborBlockChange(world, x, y, z, block);
		if (block.isFlammable(world, x, y, z, ForgeDirection.UNKNOWN))
		{
			world.newExplosion(null, x, y, z, 4F, true, true);
			world.setBlockToAir(x, y, z);
		}
	}
}