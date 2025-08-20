package com.manoj.dogz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manoj.dogz.data.Dog
import com.manoj.dogz.data.dogs
import com.manoj.dogz.ui.theme.DogzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogzTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DogzApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogzTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp),
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        }
    }, modifier = modifier)
}

@Composable
fun DogzApp() {
    Scaffold(topBar = {
        DogzTopBar()
    }) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(dogs) { dog ->
                DogzItem(dog, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun DogzItem(dog: Dog, modifier: Modifier = Modifier) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ).background(color = color)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DogzItemImage(dog)
                DogzItemInfo(dog)
                Spacer(modifier = Modifier.weight(1f))
                DogzItemIcon(expanded = expanded, onClick = {
                    expanded = !expanded
                })
            }
            if (expanded) {
                DogzItemHobby(dog.hobbies, Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            }
        }
    }
}

@Composable
private fun DogzItemInfo(dog: Dog) {
    Column(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Text(
            text = stringResource(dog.name),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = stringResource(R.string.years_old, dog.age),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
private fun DogzItemImage(dog: Dog) {
    Image(
        painter = painterResource(dog.imageResId),
        contentDescription = stringResource(dog.name),
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.surface),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun DogzItemHobby(@StringRes hobbyId: Int, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(text = stringResource(R.string.about), style = MaterialTheme.typography.labelSmall)
        Text(text = stringResource(hobbyId), style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun DogzItemIcon(expanded: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DogZItemPreview() {
    DogzItem(dogs[0])
}

@Preview()
@Composable
fun DogZAppPreview() {
    DogzTheme(true) {
        DogzApp()
    }
}

@Preview
@Composable
fun WoofDarkThemePreview() {
    DogzTheme() {
        DogzApp()
    }
}