package com.example.gamemaster.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamemaster.model.api.GameApi
import com.example.gamemaster.model.data.Game
import com.example.gamemaster.model.data.GameDetail
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    // LiveData ที่จะเก็บรายการเกม
    private val _viewState = MutableLiveData<GameViewState>(GameViewState.Loading)
    val viewState:LiveData<GameViewState> = _viewState
    private var allGames = listOf<Game>()
    private var voteGame = listOf<Game>()
    private var selectedGame = arrayListOf<Game>()
    var round = -1
    var of_round = -1
    var n_of_round = -1
    var n_round = -1


    fun processIntent(intent: GameViewIntent):Unit{
        when(intent){
            is GameViewIntent.LoadAllGame -> loadGames()
            is GameViewIntent.SearchGame -> searchGames(intent.query)
            is GameViewIntent.FilterGame -> filterGames(intent.type,intent.query)
            is GameViewIntent.DetailGame -> detailGame(intent.id)
            is GameViewIntent.VoteGame -> voteGame()
            is GameViewIntent.StartVoteGame->startVoteGame(intent.platforms,intent.tags,intent.round)
            is GameViewIntent.SelectedVoteGame->selectVoteGame(intent.game)
        }
    }

    // ฟังก์ชันเริ่มต้นที่จะโหลดเกมทั้งหมด
    private fun loadGames() {
        viewModelScope.launch {
            try {
                _viewState.value = GameViewState.Loading
                allGames = GameApi.getAllGame() // API
                if(allGames.isEmpty()){
                    _viewState.value = GameViewState.Empty
                } else{
                    _viewState.value = GameViewState.Success(allGames)
                }
            } catch (e: Exception) {
                // จัดการข้อผิดพลาดที่นี่
                _viewState.value = GameViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
    private fun searchGames(query: String){
        val filteredGames = allGames.filter { game ->
            game.title.contains(query, ignoreCase = true)
        }
        _viewState.value = if (filteredGames.isEmpty()) GameViewState.Empty else GameViewState.Success(filteredGames)
    }
    private fun filterGames(type:String,query: String){
        viewModelScope.launch {
            try {
                _viewState.value = GameViewState.Loading
                if( type == "platform") {
                    val filteredGames = if (query == "all") {
                        GameApi.getAllGame()
                    } else {
                        GameApi.getGamesByPlatform(query)
                    }
                    if (filteredGames.isEmpty()) {
                        _viewState.value = GameViewState.Empty
                    } else {
                        _viewState.value = GameViewState.FilteredGames(filteredGames)
                    }
                }
                if( type == "tags") {
                    val filteredGames = GameApi.getGamesByTags(query)
                    if (filteredGames.isEmpty()) {
                        _viewState.value = GameViewState.Empty
                    } else {
                        _viewState.value = GameViewState.FilteredGames(filteredGames)
                    }
                }
                if (type == "sort"){
                    val filteredGames = GameApi.getGamesBySorted(query)
                    if (filteredGames.isEmpty()) {
                        _viewState.value = GameViewState.Empty
                    } else {
                        _viewState.value = GameViewState.FilteredGames(filteredGames)
                    }
                }
            }
            catch (e:Exception){
                _viewState.value = GameViewState.Error(e.message ?: "Unknown error")
            }
        }

    }
    private fun detailGame(game_id:String) {
        viewModelScope.launch {
            try {
                _viewState.value = GameViewState.Loading
                val gameDetail = GameApi.getGameById(game_id)
                _viewState.value = GameViewState.DetailGames(gameDetail)
                // อัปเดต state ตามข้อมูลที่ได้
            } catch (e: Exception) {
                _viewState.value = GameViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
    private fun voteGame(){
        _viewState.value = GameViewState.VoteGame
    }
    private fun startVoteGame(platforms:String,tags:String,c_round:String){
        viewModelScope.launch {
            try{
                _viewState.value = GameViewState.Loading
                allGames = if(platforms == "all" && tags == "all"){
                    GameApi.getAllGame()
                } else if (platforms == "all"){
                    GameApi.getGamesByTags(tags)
                } else if (tags == "all"){
                    GameApi.getGamesByPlatform(tags)
                }else{
                    GameApi.getGamesByTagsAndPlatform(tags,platforms)
                }
                println(allGames.count())
                if(allGames.count()<c_round.toInt()){
                    _viewState.value = GameViewState.Error("Count of Game less than Round")
                }else{
                    round = c_round.toInt()
                    of_round = c_round.toInt()/2
                    n_round = 1
                    n_of_round = 1
                    selectedGame.clear()
                    voteGame=allGames.shuffled().take(c_round.toInt())
                    _viewState.value = GameViewState.SelectVoteGame(voteGame[0], voteGame[1],n_round,of_round)
                }
            } catch (e:Exception){
                _viewState.value = GameViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
    private fun selectVoteGame(game:Game){
        if(n_of_round<of_round){
            selectedGame.add(game)
            n_round+=1
            n_of_round+=1
        } else {
            selectedGame.add(game)
            voteGame = selectedGame.toList()
            selectedGame.clear()
            n_of_round = 1
            of_round /= 2
            n_round+=1
        }
        if(of_round == 0){
            _viewState.value = GameViewState.ResultVoteGame(voteGame[0])
        }
        else{
            println(voteGame.count())
            println(selectedGame)
            println(n_of_round)
            println(of_round)
            println(n_of_round*2-2)
            println(n_of_round*2-1)
            _viewState.value = GameViewState.SelectVoteGame(voteGame[n_of_round*2-2], voteGame[n_of_round*2-1],n_of_round,of_round)
        }


    }
}