package com.example.superheroes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.HeroesRepository

@Composable
fun HeroCard(
    @StringRes nameRes: Int,
    @StringRes descriptionRes: Int,
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier
            .fillMaxWidth()
//            .height(72.dp)
            .padding(10.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HeroInformation(nameRes = nameRes, descriptionRes = descriptionRes, modifier = modifier.weight(2f))
//            Spacer(modifier = modifier.weight(1f))
            HeroImage(imageRes = imageRes, nameRes = nameRes)
        }
    }

}

@Composable
fun HeroInformation(
    @StringRes nameRes: Int,
    @StringRes descriptionRes: Int,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier
            .padding(16.dp)

    ) {
        Text(
            text = stringResource(id = nameRes),
            style = MaterialTheme.typography.displayLarge,

        )

        Text(
            text = stringResource(id = descriptionRes),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun HeroImage(
    @DrawableRes imageRes: Int,
    @StringRes nameRes: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(16.dp)
//            .weight()

    ) {
        Image(
            painterResource(id = imageRes),
            contentDescription = "Image of ${stringResource(id = nameRes)}",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(72.dp)
                .width(72.dp)
                .clip(shape = MaterialTheme.shapes.small)
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesAppBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Superheroes",
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}

@Composable
fun HeroesApp(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            HeroesAppBar()
        }
    ) {
        LazyColumn(contentPadding = it) {
            items(HeroesRepository.heroes) {hero ->
                HeroCard(nameRes = hero.nameRes, descriptionRes = hero.descriptionRes, imageRes = hero.imageRes)
            }
        }

    }
}