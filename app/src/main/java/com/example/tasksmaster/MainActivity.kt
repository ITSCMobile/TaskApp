package com.example.tasksmaster

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasksmaster.objects.Routes
import com.example.tasksmaster.objects.Task
import com.example.tasksmaster.screens.MainScreenSetup
import com.example.tasksmaster.screens.TextScreen
import com.example.tasksmaster.ui.theme.TasksMasterTheme
import com.example.tasksmaster.view.MainViewModel


class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksMasterTheme {
                Surface {
                    val owner = LocalViewModelStoreOwner.current

                    owner?.let {
                        val viewModel: MainViewModel = viewModel(
                            it,
                            "MainViewModel",
                            MainViewModelFactory(
                                LocalContext.current.applicationContext
                                        as Application)
                        )

                        val navController = rememberNavController()
                        val drawerState = rememberDrawerState(DrawerValue.Closed)
                        val scope = rememberCoroutineScope()



                        var item = Task(
                            taskName = "",
                            taskDate = ""
                        )

                        NavHost(
                            navController = navController,
                            startDestination = Routes.MAIN_SCREEN
                        ){
                            composable(Routes.MAIN_SCREEN) {
                                MainScreenSetup(
                                    viewModel = viewModel,
                                    drawerState = drawerState,
                                    scope = scope,
                                ) { task ->
                                    item = task
                                    navController.navigate(Routes.TEXT_SCREEN)
                                }
                            }

                            composable(Routes.TEXT_SCREEN) {
                                TextScreen(
                                    viewModel = viewModel,
                                    task = item
                                ){
                                    navController.navigate(Routes.MAIN_SCREEN)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application) as T
    }
}
