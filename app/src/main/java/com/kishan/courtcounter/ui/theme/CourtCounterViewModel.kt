package com.kishan.courtcounter.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

class CourtCounterViewModel : ViewModel() {

    //court ui state
    private val _uiState = MutableStateFlow(CourtCounterUiState())
    val uiState: StateFlow<CourtCounterUiState> = _uiState.asStateFlow()

    // this function is reset the state to initial state
    fun reset(){
        _uiState.update { currentState ->
            currentState.copy(
                currentScoreOfTeams1 = 0,
                currentScoreOfTeams2 = 0,
                currentTeamName = "Enter Name"
            )
        }
    }

    //this function is used to undo the current the state by 10 of team 1
    fun undoTeam1(){
        _uiState.update { currentState ->
            currentState.copy(
                currentScoreOfTeams1 = currentState.currentScoreOfTeams1 - 10,
            )
        }
    }

    //this function is used to undo the current the state by 10 of team 2
    fun undoTeam2(){
        _uiState.update { currentState ->
            currentState.copy(
                currentScoreOfTeams2 = currentState.currentScoreOfTeams2 - 10,
            )
        }
    }
    //this is function is used to update initial states by 10 of team1
    fun updateByTenTeam1(){
        _uiState.update { currentValue ->
            currentValue.copy(
                currentScoreOfTeams1 = currentValue.currentScoreOfTeams1 + 10,
            )
        }
    }
    //this is function is used to update initial states by 10 of team2
    fun updateByTenTeam2(){
        _uiState.update { currentValue ->
            currentValue.copy(
                currentScoreOfTeams2 = currentValue.currentScoreOfTeams2 + 10,
            )
        }
    }



}
