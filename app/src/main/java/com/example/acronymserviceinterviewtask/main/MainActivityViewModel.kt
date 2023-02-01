package com.example.acronymserviceinterviewtask.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networking.domain.Repo
import com.willor.lib_data.domain.dataobjects.AcromineResp
import com.willor.lib_data.domain.dataobjects.LongFormWithVariations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    val tag: String = MainActivityViewModel::class.java.simpleName

    // Variables to hold the search response + current displayed 'long name variations'
    var searchResults: AcromineResp? = null
    var longNamesWithVariations: List<LongFormWithVariations>? = null

    // States to determine which RecyclerView to display
    private val _showAcronymResultsRv = MutableStateFlow(false)
    val showAcronymResultsRv get() = _showAcronymResultsRv.asStateFlow()

    private val _showLongNamesRv = MutableStateFlow(false)
    val showLongNamesRv get() = _showLongNamesRv.asStateFlow()


    /**
     * Display Secondary Recycler View with Long Name Variations for selected Acronym.
     *
     * Searchs the searchResults to find which item was clicked. Set it as longNamesWithVariations.
     * Then change state to show Recycler view.
     */
    fun showSecondaryRvWithLongNameResults(shortFormAcro: String) {

        // Search the search results list for the AcromineRespItem that matches the one clicked
        val longNames = searchResults!!.find {
            it.shortForm == shortFormAcro
        }

        // Change state values to show the Long Name recycler view
        longNamesWithVariations = longNames!!.longFormWithVariations
        _showAcronymResultsRv.value = false
        _showLongNamesRv.value = true
    }

    /**
     * Change the state values to show Primary Search Results.
     */
    fun showPrimaryRvWithSearchResults() {
        _showLongNamesRv.value = false
        _showAcronymResultsRv.value = true
        longNamesWithVariations = null
    }

    /**
     * Hides both recycler views, and removes current search results + long name variations
     */
    fun hideBothRvAndClearSearch() {
        _showAcronymResultsRv.value = false
        _showLongNamesRv.value = false
        longNamesWithVariations = null
        searchResults = null
    }

    /**
     * Calls the repo to search for Acronym. Also clears the current data from Recycler Views
     */
    fun searchByAcronym(shortFormAcro: String, onFailure: () -> Unit) {
        hideBothRvAndClearSearch()

        viewModelScope.launch(Dispatchers.IO) {

            repo.searchByAcronymShortForm(shortFormAcro).catch { e ->

                Log.d(tag, "searchByAcronym Flow collection caught an Exception: $e")
                onFailure()

            }.collectLatest {
                Log.d(tag, "searchbyAcronym collection triggered. $it")
                if (!it.isNullOrEmpty()) {
                    searchResults = it
                    showPrimaryRvWithSearchResults()
                } else {
                    onFailure()
                }
            }
        }
    }

    /**
     * Calls the repo to search for Acronym by it's Long Name and clears current data from
     * RecyclerViews.
     */
    fun searchByLongName(longName: String, onFailure: () -> Unit) {
        hideBothRvAndClearSearch()
        viewModelScope.launch(Dispatchers.IO) {
            repo.searchByAcronymLongForm(longName).catch { e ->

                Log.d(tag, "searchByLongName Flow collection caught an Exception: $e")
                onFailure()

            }.collectLatest {
                Log.d(tag, "searchbyLongName collection triggered. $it")

                if (!it.isNullOrEmpty()) {
                    searchResults = it
                    showPrimaryRvWithSearchResults()
                } else {
                    onFailure()
                }
            }
        }
    }
}