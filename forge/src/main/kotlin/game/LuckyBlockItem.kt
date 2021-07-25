package mod.lucky.forge.game

import mod.lucky.forge.*
import mod.lucky.java.game.LuckyItemValues
import net.minecraft.core.NonNullList
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.TooltipFlag

class LuckyBlockItem(block: MCBlock) : BlockItem(
    block,
    Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
) {
    override fun fillItemCategory(group: CreativeModeTab, stacks: NonNullList<MCItemStack>) {
        if (allowdedIn(group)) {
            stacks.add(MCItemStack(this, 1))
            if (this == ForgeLuckyRegistry.luckyBlockItem) {
                stacks.addAll(createLuckySubItems(this, LuckyItemValues.veryLuckyBlock, LuckyItemValues.veryUnluckyBlock))
            }
        }
    }

    @OnlyInClient
    override fun appendHoverText(stack: MCItemStack, world: MCWorld?, tooltip: MutableList<MCText>, context: TooltipFlag) {
        tooltip.addAll(createLuckyTooltip(stack))
    }
}
