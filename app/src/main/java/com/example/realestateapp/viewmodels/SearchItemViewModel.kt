package com.example.realestateapp.viewmodels

import androidx.lifecycle.*
import com.example.realestateapp.model.search.data.SearchDao
import com.example.realestateapp.model.search.data.SearchItems
import kotlinx.coroutines.launch

class SearchItemViewModel(private val searchDao : SearchDao) : ViewModel() {

    val allSearchItems : LiveData<List<SearchItems>> = searchDao.getSearchItems().asLiveData()

    private fun createNewSearchItem(searchItem : SearchItems){
        viewModelScope.launch {
            searchDao.create(searchItem)
        }
    }

    fun addNewSearchItem(searchText : String) {
        val newItem = SearchItems(searchText= searchText)
        createNewSearchItem(newItem)
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */

class SearchViewModelFactory(private val searchDao: SearchDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchItemViewModel(searchDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}