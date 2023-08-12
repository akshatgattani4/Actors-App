package com.example.app_32_actors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.app_32_actors.repository.ActorRepository
import com.example.app_32_actors.retrofit.Actor
import com.example.app_32_actors.retrofit.RetrofitInstance
import com.example.app_32_actors.ui.theme.APP32_ActorsTheme
import com.example.app_32_actors.viewModel.ActorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APP32_ActorsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = ActorViewModel(ActorRepository(RetrofitInstance.provideAPI(RetrofitInstance.provideRetrofit())))
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: ActorViewModel) {
    val actors by viewModel.state.collectAsState()

    val filtered = mutableListOf<Actor>()

    actors.forEach{
        if(it.image != ""){
            filtered.add(it)
        }
    }

    ActorsList(list = filtered)

}

@Composable
fun ActorsList(list: List<Actor>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {
        items(list) { item ->
            ActorCard(item)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ActorCard(actor: Actor) {
    Column {
        GlideImage(
            model = actor.image,
            contentDescription = "Actor Image",
            modifier = Modifier
                .padding(4.dp)
                .size(width = 140.dp, height = 180.dp)
        )

        Text(text = actor.actor, fontSize = 20.sp)
    }
}