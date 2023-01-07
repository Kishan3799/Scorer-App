package com.kishan.courtcounter.ui.theme


/*
*  this class is present ui state of Score counter
* */
data class CourtCounterUiState(
//    initial current team score is 0 and name is by default
    val currentScoreOfTeams1:Int = 0,
    val currentScoreOfTeams2:Int = 0,
    val currentTeamName:String = "Enter Name"
)
