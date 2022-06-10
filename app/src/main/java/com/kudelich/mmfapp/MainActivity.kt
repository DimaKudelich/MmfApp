package com.kudelich.mmfapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.kudelich.mmfapp.presentation.course_list.CourseViewModel
import com.kudelich.mmfapp.presentation.navigation.BottomNavigationBar
import com.kudelich.mmfapp.presentation.navigation.CustomTopBar
import com.kudelich.mmfapp.presentation.views.navigation.Navigation
import com.kudelich.mmfapp.ui.theme.MMFAppTheme
import com.kudelich.mmfapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CourseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepVisibleCondition {
                viewModel.state.isLoading
            }
        }

        setContent {
            MMFAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()

                    Scaffold(
                        topBar = {
                            CustomTopBar(navController = navController)
                        },
                        bottomBar = {
                            BottomNavigationBar(
                                items = Constants.BOTTOM_ITEMS,
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)

                                }
                            )
                        }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            Navigation(navController = navController)
                        }
                    }
                }
            }
        }
    }
}