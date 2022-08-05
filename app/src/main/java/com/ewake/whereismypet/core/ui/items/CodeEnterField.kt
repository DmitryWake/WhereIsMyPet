package com.ewake.whereismypet.core.ui.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly

/**
 * @author Nikolaevskiy Dmitriy
 */
@Composable
fun CodeEnterField(
    onValueChanged: (String) -> Unit,
    size: Int,
    modifier: Modifier = Modifier,
    onAfterAllCodeEntered: ((String) -> Unit)? = null,
) {

    var codeList: List<String> by remember {
        mutableStateOf(List(size) {
            ""
        })
    }
    val focusRequesters: List<FocusRequester> = remember {
        List(size) {
            FocusRequester()
        }
    }

    Row(
        modifier = modifier
    ) {
        for (i in codeList.indices) {
            OutlinedTextField(
                value = codeList[i],
                onValueChange = {
                    if (it.length <= 1 && it.isDigitsOnly()) {
                        val tempCodeList = codeList.toMutableList()
                        tempCodeList[i] = it
                        codeList = tempCodeList

                        val code = codeList.joinToString("")

                        onValueChanged.invoke(code)

                        if (it.length == 1) {
                            focusRequesters.getOrNull(i + 1)?.requestFocus() ?: onAfterAllCodeEntered?.invoke(code)
                        }
                    }
                },
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp)
                    .width(40.dp)
                    .focusRequester(focusRequesters[i]),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (i == codeList.lastIndex) ImeAction.Go else ImeAction.Next
                ),
                textStyle = TextStyle(textAlign = TextAlign.Center)
            )
        }

    }
}