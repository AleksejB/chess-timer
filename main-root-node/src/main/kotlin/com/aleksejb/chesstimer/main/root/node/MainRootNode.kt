package com.aleksejb.chesstimer.main.root.node

import android.os.Parcelable
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aleksejb.chesstimer.ui.timer.node.TimerNode
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.core.node.node
import com.bumble.appyx.navmodel.spotlight.Spotlight
import kotlinx.parcelize.Parcelize

class MainRootNode(
    buildContext: BuildContext,
    private val backStack: Spotlight<Routing> = Spotlight(
        items = listOf(Routing.TimerNode),
        initialActiveIndex = 0,
        savedStateMap = buildContext.savedStateMap
    )
): ParentNode<MainRootNode.Routing>(
    buildContext = buildContext,
    navModel = backStack
) {

    sealed class Routing: Parcelable {
        @Parcelize
        object TimerNode: Routing()
    }

    override fun resolve(navTarget: Routing, buildContext: BuildContext) =
        when (navTarget) {
            Routing.TimerNode -> TimerNode(buildContext)
        }

    @Composable
    override fun View(modifier: Modifier) {
        Children(navModel = backStack)
    }
}