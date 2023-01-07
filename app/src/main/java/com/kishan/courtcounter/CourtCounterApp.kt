package com.kishan.courtcounter



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kishan.courtcounter.ui.theme.CourtCounterViewModel

@Composable
fun CourtCounterScreen(
    modifier: Modifier = Modifier,
    courtViewModel: CourtCounterViewModel = viewModel()
) {
    //Initialize court Ui State by using courtViewModel uiState
    val courtUiState by courtViewModel.uiState.collectAsState()

    Column(modifier= modifier
        .fillMaxSize()
        .padding(vertical = 20.dp)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.score_counter), fontSize = 40.sp)
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround) {
            TeamName(teamName = courtUiState.currentTeamName)
            TeamName(teamName = courtUiState.currentTeamName)
        }

        Row(modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround) {
            ScoreBoard(
                scoreValue =  courtUiState.currentScoreOfTeams1,
                onClickPointThreeBtn = { courtViewModel.updateByTenTeam1() },
                onClickUndo =  {
                               if(courtUiState.currentScoreOfTeams1 != 0 ){
                                   courtViewModel.undoTeam1()
                               }
                },
                modifier = modifier)
             ScoreBoard(
                scoreValue = courtUiState.currentScoreOfTeams2,
                onClickPointThreeBtn = {  courtViewModel.updateByTenTeam2() },
                onClickUndo =  {
                    if(courtUiState.currentScoreOfTeams2 != 0 ){
                        courtViewModel.undoTeam2()
                    }
                },
                modifier = modifier)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { courtViewModel.reset() }) {
            Text(text = stringResource(R.string.reset))
        }
    }
}

@Composable
fun TeamName(
    teamName:String,
    modifier: Modifier=Modifier,
) {
    Column {
        //current value of openDialog is false because currently not showing alertdialog
        val openDialog = rememberSaveable { mutableStateOf(false)}
        // this variable is used to enter team name
        var enterText by rememberSaveable{ mutableStateOf(teamName) }


        Text(text = enterText, fontSize = 18.sp,  modifier = modifier.clickable {
            openDialog.value = true
        })
        // At this time alert dialog is not showing by using if condition value is by default false
        if (openDialog.value) {

            //using AlertDialog Composable to show alert dialog box
            AlertDialog(
                onDismissRequest = { },
                text = {
                    Column {
                        val focusManager = LocalFocusManager.current
                        OutlinedTextField(
                            value = enterText,
                            onValueChange = {enterText = it},
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {focusManager.clearFocus()}
                            )
                        )
                    }
                },
                buttons = {
                    Row(modifier = modifier.padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center) {
                        Button(modifier = modifier.fillMaxWidth(),
                            onClick = {
                                openDialog.value = false
                            }) {
                            Text(text = stringResource(R.string.submit))
                        }
                    }
                },
                modifier = modifier,
            )
        }
    }

}

@Composable
fun ScoreBoard(
    scoreValue: Int,
    onClickPointThreeBtn: ()-> Unit,
    onClickUndo: ()-> Unit,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = scoreValue.toString(), fontSize = 40.sp)
        Button(onClick = onClickPointThreeBtn) {
            Text(text = stringResource(R.string.ten_points))
        }
        Button(onClick = onClickUndo) {
            Text(text = stringResource(R.string.undo))
        }
    }
}


@Preview
@Composable
fun BoardPreview() {
    CourtCounterScreen()
}