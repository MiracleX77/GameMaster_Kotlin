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

    fun processIntent(intent: GameViewIntent){
        when(intent){
            is GameViewIntent.LoadAllGame -> loadGames()
        }
    }

    // ฟังก์ชันเริ่มต้นที่จะโหลดเกมทั้งหมด
    private fun loadGames() {
        viewModelScope.launch {
            try {
                _viewState.value = GameViewState.Loading
                val gameList = GameApi.getAllGame() // API
                if(gameList.isEmpty()){
                    _viewState.value = GameViewState.Empty
                } else{
                    _viewState.value = GameViewState.Success(gameList)
                }
            } catch (e: Exception) {
                // จัดการข้อผิดพลาดที่นี่
                _viewState.value = GameViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
}