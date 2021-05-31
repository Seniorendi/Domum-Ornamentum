package com.ldtteam.domumornamentum.block;

import com.ldtteam.domumornamentum.util.Suppression;
import com.ldtteam.domumornamentum.block.interfaces.IDOBlock;
import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * The abstract class for structurize-added walls.
 * Currently only used by brown brick, beige brick, and cream brick walls.
 */
public abstract class AbstractBlockWall<B extends AbstractBlockWall<B>> extends WallBlock implements IDOBlock<B>
{
    /**
     * Create a new instance of a wall block.
     *
     * @param properties the properties of the wall block.
     */
    public AbstractBlockWall(final Block.Properties properties)
    {
        super(properties);
    }

    @Override
    @SuppressWarnings(Suppression.UNCHECKED)
    public B registerBlock(final IForgeRegistry<Block> registry)
    {
        registry.register(this);
        return (B) this;
    }

    @Override
    public void registerItemBlock(final IForgeRegistry<Item> registry, final Item.Properties properties)
    {
        registry.register((new BlockItem(this, properties)).setRegistryName(this.getRegistryName()));
    }
}
