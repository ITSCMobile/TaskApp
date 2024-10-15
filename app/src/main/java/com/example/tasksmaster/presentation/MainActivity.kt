package com.example.tasksmaster.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasksmaster.data.entities.Task
import com.example.tasksmaster.presentation.navigation.Routes
import com.example.tasksmaster.presentation.screens.MainScreen
import com.example.tasksmaster.presentation.screens.TextScreen
import com.example.tasksmaster.presentation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                @Suppress("DEPRECATION")
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

                val owner = LocalViewModelStoreOwner.current
                owner?.let {

                    val navController = rememberNavController()
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                    var item = Task(
                        titleTask = "",
                        nameTask = "",
                        dateTask = ""
                    )

                    var stateHelper by remember {
                        mutableStateOf(false)
                    }

                    NavHost(
                        navController = navController,
                        startDestination = Routes.MAIN_SCREEN
                    ) {
                        composable(Routes.MAIN_SCREEN) {
                            MainScreen(
                                viewModel = viewModel,
                                drawerState = drawerState,
                                scope = scope,
                                stateHelper = stateHelper,
                                onClick = { task ->
                                    item = task
                                    navController.navigate(Routes.TEXT_SCREEN)
                                },
                                onStateHelper = { state ->
                                    stateHelper = state
                                }
                            )
                        }
                        composable(Routes.TEXT_SCREEN) {
                            TextScreen(
                                viewModel = viewModel,
                                task = item,
                                navController = navController,
                                stateHelper = stateHelper,
                            )
                        }
                    }
                }
            }
        }
    }
}
