package com.aleksejb.chesstimer

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import com.aleksejb.chesstimer.main.root.node.MainRootNode
import com.aleksejb.chesstimer.ui.theme.ChessTimerTheme
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : NodeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TAAAG", "MainACt")

        setContent {
            Log.d("TAAAG", "SetContent")
            ChessTimerTheme {
                Log.d("TAAAG", "ChessTimerTheme")
                NodeHost(
                    integrationPoint = appyxIntegrationPoint
                ) {
                    Log.d("TAAAG", "NodeHost")
                    MainRootNode(buildContext = it)
                }
            }
        }

    }
}
