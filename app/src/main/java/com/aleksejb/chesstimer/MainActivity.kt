package com.aleksejb.chesstimer

import android.os.Bundle
import androidx.activity.compose.setContent
import com.aleksejb.chesstimer.ui.theme.ChessTimerTheme
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity

class MainActivity : NodeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChessTimerTheme {
                NodeHost(
                    integrationPoint = appyxIntegrationPoint
                ) { MainRootNode(it) }
            }
        }

    }
}
