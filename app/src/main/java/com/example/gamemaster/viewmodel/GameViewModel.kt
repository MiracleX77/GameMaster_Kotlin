package com.example.gamemaster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamemaster.model.api.GameApi
import com.example.gamemaster.model.data.Game
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    // LiveData ที่จะเก็บรายการเกม
    private val _viewState = MutableLiveData<GameViewState>(GameViewState.Loading)
    val viewState:LiveData<GameViewState> = _viewState
    private var allGames = listOf<Game>()

    fun processIntent(intent: GameViewIntent):Unit{
        when(intent){
            is GameViewIntent.LoadAllGame -> loadGames()
            is GameViewIntent.SearchGame -> searchGames(intent.query)
            is GameViewIntent.FilterGame -> filterGames(intent.type,intent.query)
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
                    var filteredGames = GameApi.getGamesByTags(query)
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
}