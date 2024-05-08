package com.cook_and_share.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.cook_and_share.R
import com.cook_and_share.domain.model.Ingredient

@Composable
fun IngredientItem(
    modifier: Modifier = Modifier,
    name: String,
    unitList: List<String>,
    isEdit: Boolean = true,
    selectedIngredientItems: MutableState<List<Ingredient>>,
    onButtonClick: () -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    val isExpanded = remember { mutableStateOf(false) }
    val height = if (expanded) 121 else 75
    val selectedIndex = remember { mutableIntStateOf(0) }
    val quantity = remember { mutableStateOf("0") }

    Box(
        modifier = modifier
            .shadow(1.dp, shape = RoundedCornerShape(16.dp), clip = true)
            .height(height.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = colorScheme.secondary),
    ) {
        Row(
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .height(75.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (isEdit) {
                    IconButton(
                        onClick = { onButtonClick() },
                        content = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "delete ingredient"
                            )
                        }
                    )
                }
                Text(
                    buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontStyle = typography.titleMedium.fontStyle,
                                color = colorScheme.tertiary,
                                fontWeight = typography.titleMedium.fontWeight
                            )
                        ) {
                            append(name + "\n")
                        }
                        withStyle(
                            SpanStyle(
                                fontStyle = typography.bodySmall.fontStyle,
                                color = colorScheme.tertiary,
                                fontWeight = typography.bodySmall.fontWeight
                            )
                        ) {
                            append(quantity.value + " " + unitList[selectedIndex.intValue])
                        }
                    },
                )
            }
            if (isEdit) {
                SecondaryButton(
                    modifier = Modifier
                        .padding(12.dp)
                        .height(51.dp),
                    shape = RoundedCornerShape(8.dp),
                    label = if (!expanded) R.string.edit else R.string.save,
                    onClick = {
                        if (!expanded) {
                            expanded = true
                        } else {
                            selectedIngredientItems.value +=
                                Ingredient(
                                    name,
                                    quantity.value.toInt(),
                                    unitList[selectedIndex.intValue]
                                )
                            expanded = false
                        }
                    }
                )
            }
        }
        if (expanded && isEdit) {
            Row(
                modifier = Modifier
                    .padding(top = 75.dp, start = 12.dp, end = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DecrementIncrementButtons(
                    modifier = Modifier,
                    quantity = quantity.value,
                    onDecrement = {
                        if (quantity.value.toInt() > 0) {
                            quantity.value = (quantity.value.toInt() - 1).toString()

                        }
                    },
                    onIncrement = { quantity.value = (quantity.value.toInt() + 1).toString() }
                )
                CustomDropdownMenu(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .wrapContentSize(),
                    expanded = isExpanded,
                    items = unitList,
                    selectedIndex = selectedIndex
                )
            }
        }
    }
}

@Composable
private fun DecrementIncrementButtons(
    modifier: Modifier,
    quantity: String,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .size(35.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { onDecrement.invoke() }
                .background(colorScheme.tertiary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "-",
                style = typography.labelLarge,
                color = colorScheme.onTertiary,
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = quantity,
            style = typography.labelLarge,
            textAlign = TextAlign.Center,
            color = colorScheme.onSecondary
        )
        Box(
            modifier = Modifier
                .size(35.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(colorScheme.tertiary)
                .clickable { onIncrement.invoke() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+",
                style = typography.labelLarge,
                color = colorScheme.onTertiary
            )
        }
    }
}

@Composable
private fun CustomDropdownMenu(
    modifier: Modifier,
    expanded: MutableState<Boolean>,
    items: List<String>,
    selectedIndex: MutableState<Int>,
) {
    Box(
        modifier = modifier
            .height(35.dp)
            .width(90.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(colorScheme.tertiary)
            .clickable { expanded.value = true },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = items[selectedIndex.value],
            style = typography.titleMedium,
            color = colorScheme.onTertiary
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
        ) {
            items.forEachIndexed { index, label ->
                DropdownMenuItem(
                    colors = MenuItemColors(
                        textColor = colorScheme.onSecondary,
                        leadingIconColor = colorScheme.onSecondary,
                        trailingIconColor = colorScheme.onSecondary,
                        disabledTextColor = colorScheme.onSecondary,
                        disabledLeadingIconColor = colorScheme.onSecondary,
                        disabledTrailingIconColor = colorScheme.onSecondary,
                    ),
                    onClick = {
                        selectedIndex.value = index
                        expanded.value = false
                    },
                    text = { Text(text = label) }
                )
            }
        }
    }
}