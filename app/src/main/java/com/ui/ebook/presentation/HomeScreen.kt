package com.ui.ebook.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Report
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController) {
    var drawerState = rememberDrawerState(DrawerValue.Closed)
    var scope = rememberCoroutineScope()
    var context = LocalContext.current
    var urlHandler = LocalUriHandler.current

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet() {
                Column(
                    Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 8.dp)
                        .fillMaxHeight()
                        .width(200.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isOpen) {
                                        drawerState.close()
                                    } else {
                                        drawerState.open()
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, "menu")
                        }

                            Text("DRAWER",Modifier.padding(top = 13.dp), fontWeight = FontWeight.SemiBold, fontSize = 15.sp)

                    }
                    NavigationDrawerItem(
                        selected = true ,
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color.DarkGray,
                            selectedTextColor = Color.White,
                            selectedIconColor = Color.White
                        ),
                        label = {Text("Home")},
                        icon = {Icon(Icons.Default.Home,null)},
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        },

                    )
                    Divider(color = Color.Black)
                    NavigationDrawerItem(
                        selected = false,
                        label = {Text("Version 1.0")},
                        icon = {Icon(Icons.Default.Info,null)},
                        onClick = {
                            scope.launch {
                            drawerState.close()}
                           Toast.makeText(context,"Version 1.0", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Divider(color = Color.Black)
                    NavigationDrawerItem(
                        selected = false,
                        label = {Text("Contact Me")},
                        icon = {Icon(Icons.Default.Call,null)},
                        onClick = {
                          urlHandler.openUri("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrWbBMggm_54omSU2u2n4FjLtvcuixSDZ8Sw&s")
                        }
                    )
                    Divider(color=Color.Black)
                    NavigationDrawerItem(
                        selected = false,
                        label = { Text("</>" , fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                            Text("        Source Code")},
                        onClick = {
                            urlHandler.openUri("https://youtu.be/vH3e3fU1LnM?list=TLGGYXFNJmgA1isxODA5MjAyNQ")
                        }
                    )
                    Divider(color = Color.Black)
                    NavigationDrawerItem(
                        selected = false,
                        label = {
                            Text("Report!")},
                        icon = { Icon(Icons.Default.Report,null)},
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            Toast.makeText(context,"Report Accepted👍", Toast.LENGTH_LONG).show()
                        }
                    )
                }


            }
        }
    ) {
        Scaffold(
            modifier = Modifier.background(Color.Black),
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(Color.Black),
                    title = {
                        Text("Book Library")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu,null)
                        }
                    }
                )
            }
        ){innerpadding->

            Column (Modifier.padding(innerpadding).fillMaxSize()){
                TabScreen(navHostController = navHostController)
            }
        }
    }
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerCreation() {
    var drawerstate = rememberDrawerState(DrawerValue.Closed)
    var scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        gesturesEnabled = false,
        drawerState = drawerstate,
        drawerContent = {
            ModalDrawerSheet() {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())

                ) {
                    IconButton(onClick = { scope.launch {
                        if (drawerstate.isOpen){
                            drawerstate.close()
                        }
                        else {
                            drawerstate.open()
                        }}}) {
                        Icon(Icons.Default.Menu,"Meni")
                    }
                    Text("DRAWER TITLE:")
                    HorizontalDivider()
                    Text("CONTENT->")
                }

                NavigationDrawerItem(
                    label = {Text("ITEM 1")},
                    selected = false,
                    onClick = {
                    }
                )
                NavigationDrawerItem(
                    label = {Text("ITEM 1")},
                    selected = false,
                    onClick = {
                    }
                )

            }
        }) {

        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text("NAVIGATION DRAWER")
                },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerstate.isClosed){
                                    drawerstate.open()
                                }
                                else{
                                    drawerstate.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Bar")
                        }
                    })

            }
        ) { innerPaddinng ->
            Box(Modifier
                .fillMaxSize()
                .padding(innerPaddinng)) {
                Text("Main Content")
            }
        }

    }
}
 */

@Preview(showSystemUi = true)
@Composable
fun pre(){
    val navController = rememberNavController()
    HomeScreen(navHostController = navController)
}