package com.kudelich.mmfapp.presentation.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.West
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kudelich.mmfapp.R
import com.kudelich.mmfapp.presentation.views.navigation.Screen
import com.kudelich.mmfapp.presentation.views.navigation.top.CustomBarViewModel

@Composable
fun CustomTopBar(
    navController: NavController,
    viewModel: CustomBarViewModel = hiltViewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    TopAppBar(
        title = {
            Text(
                text = viewModel.getTitle(navBackStackEntry?.destination?.route),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            when (viewModel.getNavigationIcon(navBackStackEntry?.destination?.route)) {
                1 -> {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.West,
                            contentDescription = "Back"
                        )
                    }
                }
                2 -> {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_mmf_logo_dark),
                        contentDescription = "Back",
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                    )
                }
            }
        },
        actions = {
            when (viewModel.getActionIcon(navBackStackEntry?.destination?.route)) {
                1 -> {
                    IconButton(onClick = {
                        navController.navigate(
                            Screen.TeacherSearchScr.route
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Back"
                        )
                    }
                }

                2 -> {
                    IconButton(onClick = {
                        viewModel.saveNewGroup()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = "Back"
                        )
                    }
                }

                3 -> {
                    IconButton(onClick = {
                        viewModel.deleteSavedGroup()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Back"
                        )
                    }
                }
                else -> {
                }
            }
        }
    )
}