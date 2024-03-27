package com.example.datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.datastore.ui.theme.DataStoreTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun SelectCity(navController: NavHostController) {

    var cityName by remember {
        mutableStateOf("")
    }

    Text(text = "Select City", fontSize = 20.sp)
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")
        Image(painter = painterResource(id = R.drawable.map), contentDescription = "",
            Modifier
                .height(200.dp).fillMaxWidth().padding(0.dp,0.dp,0.dp,10.dp))
        OutlinedTextField(value = cityName, onValueChange = {cityName = it},Modifier.padding(0.dp,0.dp,0.dp,10.dp))
        Button(onClick = { navController.navigate("weather/$cityName") }) {
            Text(text = "Show Weather")
        }
    }
}

@Composable
fun Weather(cityName: String?) {



    Text(text = "Weather Detail")
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "City Name : $cityName", fontSize = 30.sp)
        Row(
            Modifier
                .padding(10.dp)
                .shadow(elevation = 2.dp)
                .fillMaxWidth()) {
            Image(painter = painterResource(id = R.drawable.baseline_device_thermostat_24), contentDescription = "",Modifier.size(80.dp).padding(20.dp))
            Column(Modifier.padding(10.dp,15.dp,0.dp,0.dp)) {
                Text(text = "Temperature", fontSize = 30.sp)
                Text(text = "30%", fontSize = 15.sp)

            }
        }
        Row(
            Modifier
                .padding(10.dp)
                .shadow(elevation = 2.dp)
                .fillMaxWidth()) {
            Icon(painter = painterResource(R.drawable.baseline_cloud_24), contentDescription = "",Modifier.size(80.dp).padding(20.dp))
            Column(Modifier.padding(10.dp,15.dp,0.dp,0.dp)) {
                Text(text = "Humidity", fontSize = 30.sp)
                Text(text = "Sunny", fontSize = 15.sp)

            }
        }
        Row(
            Modifier
                .padding(10.dp)
                .shadow(elevation = 2.dp)
                .fillMaxWidth()) {
            Icon(painter = painterResource(R.drawable.baseline_wb_sunny_24), contentDescription = "",Modifier.size(80.dp).padding(20.dp))
            Column(Modifier.padding(10.dp,15.dp,0.dp,0.dp)) {
                Text(text = "Condition", fontSize = 30.sp)
                Text(text = "32c", fontSize = 15.sp)

            }
        }
    }
}

@Composable
fun Navigation(){
    val args = listOf(navArgument("cityName"){type = NavType.StringType})

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home" ){
        composable(route = "home"){ Splash(navController)}
        composable(route = "landing"){ SelectCity(navController)}
        composable(route = "weather/{cityName}", arguments = args){navBackStackEntry ->
            val cityName = navBackStackEntry.arguments?.getString("cityName")
            Weather(cityName)}
    }
}


@Composable
fun Splash(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")
        Text(text = "Sky City", fontSize = 30.sp)
    }

    LaunchedEffect(Unit){
        delay(3000)
        navController.navigate("landing")

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GreetingPreview() {
    DataStoreTheme {
        Greeting("Android")
    }
}

//LaunchedEffect(Unit) {
//
//    delay(3000) // Wait for 3 seconds
//
//    // code to be called after 3 seconds
//
//}
//
//
//
//Get Dynamic Data from Strings
//
//val weatherInfo: List<String> = run {
//
//    val resourceId = resources.getIdentifier("weather_info_${cityName.lowercase()}", "array", context.packageName)
//
//    if (resourceId != 0) resources.getStringArray(resourceId).toList() else listOf("Info not available")