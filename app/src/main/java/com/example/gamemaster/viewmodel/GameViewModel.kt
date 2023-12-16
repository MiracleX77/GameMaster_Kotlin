package com.example.gamemaster.viewmodel

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
    private var selectedGame = listOf<Game>()

    fun processIntent(intent: GameViewIntent):Unit{
        when(intent){
            is GameViewIntent.LoadAllGame -> loadGames()
            is GameViewIntent.SearchGame -> searchGames(intent.query)
            is GameViewIntent.FilterGame -> filterGames(intent.type,intent.query)
            is GameViewIntent.DetailGame -> detailGame(intent.id)
            is GameViewIntent.VoteGame -> voteGame()
            is GameViewIntent.StartVoteGame->startVoteGame(intent.platforms,intent.tags,intent.round)
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
    private fun startVoteGame(platforms:String,tags:String,round:String){
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
                if(allGames.count()<round.toInt()){
                    _viewState.value = GameViewState.Error("Count of Game less than Round")
                }else{
                    voteGame=allGames.shuffled().take(round.toInt())
                    _viewState.value = GameViewState.SelectVoteGame(voteGame[0], voteGame[1],2,round.toInt())
                }
            } catch (e:Exception){
                _viewState.value = GameViewState.Error(e.message ?: "Unknown error")
            }
        }

    }
}