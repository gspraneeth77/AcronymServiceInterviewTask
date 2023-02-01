package com.example.acronymserviceinterviewtask.mainactivityviewmodel_test

import android.util.Log
import com.example.acronymserviceinterviewtask.main.MainActivityViewModel
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MainActivityViewModelTest {

    private val viewmodel = MainActivityViewModel(FakeRepo())

    /**
     * Set up the Log class
     */
    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
    }

    @Test
    fun `test searchByLongName() valid params`() {

        runBlocking {
            viewmodel.searchByLongName("Hello How Are You", {})

            delay(1000)

            assert(viewmodel.searchResults != null)
        }
    }

    @Test
    fun `test searchByLongName invalid params`() {
        runBlocking {
            viewmodel.searchByLongName("", {})
            delay(1000)
            assert(viewmodel.searchResults == null)
        }
    }

    @Test
    fun `test searchByAcronym valid params`() {
        runBlocking {
            viewmodel.searchByAcronym("ADD", {})
            delay(1000)
            assert(viewmodel.searchResults != null)
        }
    }

    @Test
    fun `test searchByAcronym invalid params`() {
        runBlocking {
            viewmodel.searchByAcronym("", {})
            delay(1000)
            assert(viewmodel.searchResults == null)
        }
    }

    @Test
    fun `test showSecondaryRvWithLongNameResults`() {

        runBlocking {
            // Feed the viewmodel dummy data
            viewmodel.searchByAcronym("ADD", {})
            delay(1000)
        }


        viewmodel.showSecondaryRvWithLongNameResults("ADD")


        assert(
            viewmodel.longNamesWithVariations != null
                    && !viewmodel.showAcronymResultsRv.value
                    && viewmodel.showLongNamesRv.value
        )

    }

}