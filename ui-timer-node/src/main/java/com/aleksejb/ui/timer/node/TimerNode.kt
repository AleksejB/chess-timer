package com.aleksejb.ui.timer.node

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aleksejb.ui.timer.TimerScreen
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node

class TimerNode(
    buildContext: BuildContext
): Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) = TimerScreen()
}