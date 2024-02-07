package com.mealmentor.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mealmentor.R
import com.mealmentor.logic.database.sign_in.UserData
import com.mealmentor.logic.navigation.getBottomNavigationItems

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    userData: UserData?,
    onSignOut: () -> Unit
) {

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    Surface(modifier = Modifier) {
        Scaffold(
            bottomBar = {
                NavigationBar (
                    containerColor = MaterialTheme.colorScheme.secondary,
                ) {
                    getBottomNavigationItems().forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                //Here can be a navController
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(650),
                                    ),
                                )
                                    },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.inversePrimary,
                                selectedTextColor = MaterialTheme.colorScheme.inversePrimary,
                                unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                                unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                                indicatorColor = MaterialTheme.colorScheme.secondary
                            ),
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex)
                                            item.selectedIcon
                                        else item.unselectedIcon,
                                        contentDescription = item.title,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        )
                    }
                }
            }
        ) {
    //тут по-ідеї має бути вміст сторінки
        }
    }
//    Це пробний код, який можна використати для відображення інформації про користувача

//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        if (userData?.profilePictureUrl != null) {
//            AsyncImage(
//                model = userData.profilePictureUrl,
//                contentDescription = "Profile Picture",
//                modifier = Modifier
//                    .size(200.dp)
//                    .clip(CircleShape),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//        if (userData?.username != null) {
//            Text(
//                text = userData.username,
//                style = MaterialTheme.typography.titleLarge,
//                textAlign = TextAlign.Center
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//        ElevatedButton(
//            onClick = {
//                onSignOut()
//            },
//            shape = ButtonDefaults.elevatedShape,
//            colors = ButtonDefaults.elevatedButtonColors(
//                containerColor = MaterialTheme.colorScheme.tertiary,
//                contentColor = MaterialTheme.colorScheme.onTertiary
//            ),
//            elevation = ButtonDefaults.elevatedButtonElevation(
//                defaultElevation = 4.dp,
//                pressedElevation = 0.dp
//            ),
//            modifier = Modifier
//                .padding(horizontal = 80.dp)
//                .height(65.dp)
//                .fillMaxWidth()
//        ) {
//            Text(
//                text = stringResource(id = R.string.sign_out),
//                style = MaterialTheme.typography.labelLarge
//            )
//        }
//    }
}