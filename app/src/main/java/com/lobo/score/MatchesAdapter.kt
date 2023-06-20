package com.lobo.score
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MatchesAdapter : RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>() {
    private var matches: List<Match> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.bind(match)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    fun setMatches(matches: List<Match>) {
        this.matches = matches
        notifyDataSetChanged()
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sportNameTextView: TextView = itemView.findViewById(R.id.sport_name_text_view)
        private val countryTextView: TextView = itemView.findViewById(R.id.country_text_view)
        private val tournamentNameTextView: TextView = itemView.findViewById(R.id.tournament_name_text_view)
        private val team1TextView: TextView = itemView.findViewById(R.id.team1_text_view)
        private val team2TextView: TextView = itemView.findViewById(R.id.team2_text_view)
        private val totalTextView: TextView = itemView.findViewById(R.id.total_text_view)
        private val foraTextView: TextView = itemView.findViewById(R.id.fora_text_view)

        @SuppressLint("SetTextI18n")
        fun bind(match: Match) {
            sportNameTextView.text = match.sportName
            countryTextView.text = match.country
            tournamentNameTextView.text = match.tournamentName
            team1TextView.text = match.team1
            team2TextView.text = match.team2

            if (match.total != null) {
                totalTextView.text = "Total: ${match.total.value}"
            } else {
                totalTextView.text = "Total: N/A"
            }

            if (match.fora != null) {
                foraTextView.text = "Fora: ${match.fora.value}"
            } else {
                foraTextView.text = "Fora: N/A"
            }
        }
    }
}