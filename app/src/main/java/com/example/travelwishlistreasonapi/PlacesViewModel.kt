package com.example.travelwishlistreasonapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

const val TAG = "PLACES_VIEW_MODEL"

class PlacesViewModel: ViewModel() {

//    private val places = mutableListOf<Place>(Place("Auckland, NZ","To Count Sheep", starred = true),
//        Place("Rome","To Eat Pizza", starred = true))

    private val placeRepository = PlaceRepository() // initializes placeRepository variable which holds a PlaceRepository()

    val allPlaces = MutableLiveData<List<Place>>(listOf<Place>())  // initializes allPlaces variable which is a MutableLiveData type that is a list of place objects
    val userMessage = MutableLiveData<String>()

    init { // initializer
        getPlaces()
    }

    fun getPlaces() {  // suspend function for placeRepository.getAllPlaces() has to be called in background using a coroutine
        viewModelScope.launch {  // calls launch function on coroutine scope
            val apiResult = placeRepository.getAllPlaces()  // returns list of places
            if (apiResult.status == ApiStatus.SUCCESS) {
                allPlaces.postValue(apiResult.data)  // sets mutable live data allPlaces as list of places
            }
            userMessage.postValue(apiResult.message)
        }
    }

    fun addNewPlace(place: Place) {
        viewModelScope.launch {
            val apiResult = placeRepository.addPlace(place)
            updateUI(apiResult)
        }
    }

    fun updatePlace(place: Place) {
        viewModelScope.launch {
            val apiResult = placeRepository.updatePlace(place)
            updateUI(apiResult)
        }
    }

    fun deletePlace(place: Place) { // removes a place at the given position and returns that place
        viewModelScope.launch {
            val apiResult = placeRepository.deletePlace(place)
            updateUI(apiResult)
        }
    }

    private fun updateUI(result: ApiResult<Any>) {
        if (result.status == ApiStatus.SUCCESS) {
            getPlaces()
        }
        userMessage.postValue(result.message)
    }
}

