package com.ldtteam.domumornamentum.block.types;

import com.ldtteam.domumornamentum.block.decorative.PaperWallBlock;
import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;

/**
 * Types that the {@link PaperWallBlock} supports
 */
public enum PaperwallType implements IStringSerializable
{
    OAK("oak"),
    SPRUCE("spruce"),
    BIRCH("birch"),
    JUNGLE("jungle"),
    ACACIA("acacia"),
    DARK_OAK("dark_oak"),
    CRIMSON("crimson"),
    WARPED("warped"),
    CACTUS("cactus");

    private final String name;

    PaperwallType(final String nameIn)
    {
        this.name = nameIn;
    }

    @Override
    public String getString()
    {
        return this.name;
    }

    @NotNull
    public String getName()
    {
        return this.name;
    }
}
