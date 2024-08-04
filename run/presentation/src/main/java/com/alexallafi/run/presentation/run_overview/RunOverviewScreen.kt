package com.alexallafi.run.presentation.run_overview

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexallafi.presentation.designsystem.AnalyticsIcon
import com.alexallafi.presentation.designsystem.LogoIcon
import com.alexallafi.presentation.designsystem.LogoutIcon
import com.alexallafi.presentation.designsystem.MyRuniqueTheme
import com.alexallafi.presentation.designsystem.RunIcon
import com.alexallafi.presentation.designsystem.components.RuniqueFloatingActionButton
import com.alexallafi.presentation.designsystem.components.RuniqueScaffold
import com.alexallafi.presentation.designsystem.components.RuniqueToolbar
import com.alexallafi.presentation.designsystem.components.util.DropDownItem
import com.plcoding.run.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun RunOverviewScreenRoot(
    viewModel: RunOverviewViewModel = koinViewModel()
) {
    RunOverviewScreen(
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RunOverviewScreen(
    onAction: (RunOverviewAction) -> Unit

) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )

    RuniqueScaffold(
        topAppBar = {
            RuniqueToolbar(
                showBackButton = false,
                title = stringResource(id = R.string.runique),
                scrollBehavior = scrollBehavior,
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                },
                menuItems = listOf(
                    DropDownItem(
                        icon = AnalyticsIcon,
                        title = stringResource(id = R.string.analytics)
                    ),
                    DropDownItem(
                        icon = LogoutIcon,
                        title = stringResource(id = R.string.logout)
                    )
                ),
                modifier = Modifier,
                onMenuItemClick = { index ->
                    when(index) {
                        0 -> onAction(RunOverviewAction.OnAnalyticsClick)
                        1 -> onAction(RunOverviewAction.OnLogoutClick)
                    }
                }
            )
        },
        floatingActionButton = {
            RuniqueFloatingActionButton(
                icon = RunIcon,
                onClick = {
                    onAction(RunOverviewAction.OnStartRun)
                }
            )
        }
    ) { padding ->

    }
}

@Preview
@Composable
private fun RunOverviewScreenRootScreenPreview() {
    MyRuniqueTheme {
        RunOverviewScreen(
            onAction = {}
        )
    }
}