package com.lobo.score
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class AllMatchesFragment : Fragment() {

    private lateinit var matchesAdapter: MatchesAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_matches, container, false)
        recyclerView = view.findViewById(R.id.matches_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        matchesAdapter = MatchesAdapter()
        recyclerView.adapter = matchesAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchMatchData()
    }

    private fun fetchMatchData() {
        val url = "https://apistoreapp.ru/api/events/get_events_data_4"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                client.newCall(request).execute()
            }

            if (response.isSuccessful) {
                val responseBody = response.body()?.string()
                val matches = parseMatchData(responseBody)
                matchesAdapter.setMatches(matches)
            } else {
                // Handle error case
            }
        }
    }

    companion object {
        fun newInstance(sportName: String): AllMatchesFragment {
            val fragment = AllMatchesFragment()
            val args = Bundle()
            args.putString("sportName", sportName)
            fragment.arguments = args
            return fragment
        }
    }

    private fun parseMatchData(response: String?): List<Match> {
        val matches = mutableListOf<Match>()
        try {
            val jsonObject = JSONObject(response)
            val jsonArray = jsonObject.getJSONArray("events")

            for (i in 0 until jsonArray.length()) {
                val matchObject = jsonArray.getJSONObject(i)
                val match = Gson().fromJson(matchObject.toString(), Match::class.java)
                matches.add(match)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return matches
    }
}