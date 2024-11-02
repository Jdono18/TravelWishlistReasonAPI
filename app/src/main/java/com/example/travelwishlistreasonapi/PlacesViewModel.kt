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

    init { // initializer
        getPlaces()
    }

    fun getPlaces() {  // suspend function for placeRepository.getAllPlaces() has to be called in background using a coroutine
        viewModelScope.launch {  // calls launch function on coroutinescope
            val places = placeRepository.getAllPlaces()  // returns list of places
            allPlaces.postValue(places)  // sets mutablelivedata allPlaces as list of places
        }
    }

    fun addNewPlace(place: Place) {
    // todo

    }

    fun deletePlace(place: Place) { // removes a place at the given position and returns that place
        // todo
    }

    fun updatePlace(place: Place) {
      //  Todo
    }
}