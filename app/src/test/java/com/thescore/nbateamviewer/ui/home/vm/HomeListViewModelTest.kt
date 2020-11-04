package com.thescore.nbateamviewer.ui.home.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.thescore.data.AppUtils
import com.thescore.data.model.Players
import com.thescore.data.model.TeamModel
import com.thescore.data.networking.RetrofitService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(JUnit4::class)
class HomeListViewModelTest {

    private val viewModel by lazy { HomeListViewModel() }
    private lateinit var teamModel1: TeamModel
    private lateinit var teamModel2: TeamModel
    private lateinit var teamModel3: TeamModel
    private lateinit var teamList: List<TeamModel>

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)


        var players1 = Players(1, "player", "one", "C", 4)
        var players2 = Players(2, "player", "two", "SG", 5)
        var players = listOf(players1, players2)
        teamModel1 = TeamModel(1, 20, 10, "A Team", players)
        teamModel2 = TeamModel(2, 10, 20, "B Team", players)
        teamModel3 = TeamModel(3, 30, 0, "C Team", players)
        teamList = listOf(teamModel1, teamModel2, teamModel3)
    }

    @After
    fun clear() {
        viewModel.repository?.mResponse?.removeObserver { this }
    }

    @Test
    fun sortTeamsByName() {
        viewModel.repository?.mResponse?.postValue(teamList)
        viewModel.data.observeForever {
            Assert.assertEquals(teamModel1, viewModel?.data.value?.get(0))
        }
    }

    @Test
    fun sortTeamsByLosses() {
        viewModel.sortList(3)
        viewModel.data.observeForever {
            Assert.assertEquals(teamModel3, viewModel?.data.value?.get(0))
        }
    }

    @Test
    fun sortTeamsByWins() {
        viewModel.sortList(2)
        viewModel.data.observeForever {
            Assert.assertEquals(teamModel2, viewModel?.data.value?.get(0))
        }
    }

    @Test
    fun checkApiSuccess() {

        var mockWebServer = MockWebServer()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("").toString())
            .build()

        mockWebServer.enqueue(MockResponse().setBody(AppUtils.sampleSuccessResponse))

        var service = retrofit.create(RetrofitService::class.java)
        val call: Call<List<TeamModel>> = service.getMovies()
        assert(call.execute() != null)
    }

    @Test
    fun checkApiFailure() {
        var mockWebServer = MockWebServer()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("").toString())
            .build()

        mockWebServer.enqueue(MockResponse().setBody("[]"))

        var service = retrofit.create(RetrofitService::class.java)
        val call: Call<List<TeamModel>> = service.getMovies()
        val response: Response<List<TeamModel>> = call.execute()
        Assert.assertTrue(response.body()?.isEmpty() == true)

    }

}