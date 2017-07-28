package baby.internal.cosmo.fr.babyscore

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cell_result.view.*

class ResultAdapter() : RecyclerView.Adapter<ResultViewHolder>() {

    var items = emptyList<Result>()
        set (value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ResultViewHolder?, position: Int) {
        val item = items[position]

        holder?.itemView?.apply {
            scoreTextView?.text = "${item.teamScoreA.score} - ${item.teamScoreB.score}"
            firstTeamTextView?.text = item.teamScoreA.team.players.joinToString { player -> player.name }
            secondTeamTextView?.text = item.teamScoreB.team.players.joinToString { player -> player.name }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ResultViewHolder {
        val cellView = LayoutInflater.from(parent!!.context).inflate(R.layout.cell_result, parent, false)
        return ResultViewHolder(cellView)
    }

}

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
