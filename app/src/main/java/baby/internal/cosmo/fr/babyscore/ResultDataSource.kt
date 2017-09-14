package baby.internal.cosmo.fr.babyscore

import baby.internal.cosmo.fr.babyscore.addmatch.AddMatchInput
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

interface ResultDataSource {
    fun getResults(): List<Result>
}

interface ResultDataSourceController {
    fun subscribe(resultInteractor: ResultInteractor)
    fun fetch()
}


class ResultDataSourceImpl(private val firebaseDatabase: FirebaseDatabase) : ResultDataSourceController, ValueEventListener {
    override fun onDataChange(postSnapshot: DataSnapshot) {
        val results = postSnapshot.children.map {
            it.getValue(AddMatchInput::class.java)
        }
            .filterNotNull()
            .map {
                Result(
                    TeamScore(
                        Team(listOf(Player(it.firstTeamFirstPlayerName), Player(it.firstTeamSecondPlayerName))),
                        it.scoreFirstTeam.toInt()),
                    TeamScore(
                        Team(listOf(Player(it.secondTeamFirstPlayerName), Player(it.secondTeamSecondPlayerName))),
                        it.scoreSecondTeam.toInt())
                )
            }
        interactor?.onNewDataFetched(results)
    }

    override fun onCancelled(p0: DatabaseError?) {
    }

    var interactor: ResultInteractor? = null

    override fun fetch() {
        val reference = firebaseDatabase.reference
        val matchesDatabaseReference = reference.database.getReference("matches")
        matchesDatabaseReference.addValueEventListener(this)
    }

    override fun subscribe(resultInteractor: ResultInteractor) {
        interactor = resultInteractor
    }
}