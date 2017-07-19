package baby.internal.cosmo.fr.babyscore

interface ResultDataSource {
    fun getResults(): List<Result>
}

interface ResultDataSourceController {
    fun subscribe(resultInteractor: ResultInteractor)
    fun fetch()
}


class ResultDataSourceImpl() : ResultDataSource, ResultDataSourceController {

    var interactor: ResultInteractor? = null

    override fun getResults(): List<Result> {
        val teamScoreA = TeamScore(Team(listOf(Player("A"), Player("B"))), 0)
        val teamScoreB = TeamScore(Team(listOf(Player("C"), Player("D"))), 5)
        val teamScoreC = TeamScore(Team(listOf(Player("E"), Player("F"))), 10)
        val teamScoreD = TeamScore(Team(listOf(Player("G"), Player("H"))), 9)
        return listOf(
                Result(teamScoreA, teamScoreB),
                Result(teamScoreC, teamScoreD)
        )
    }

    override fun fetch() {
        interactor?.onNewDataFetched(getResults())
    }

    override fun subscribe(resultInteractor: ResultInteractor) {
        interactor = resultInteractor
    }
}