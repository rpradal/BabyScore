package baby.internal.cosmo.fr.babyscore

interface ResultInteractor {
    fun fetch()
    fun onNewDataFetched(results: List<Result>)
    fun addMatch()
}

class ResultInteractorImpl(val presenter: ResultPresenter, val controller: ResultDataSourceController) : ResultInteractor {



    init {
        controller.subscribe(this)
    }

    override fun fetch() {
        controller.fetch()
    }

    override fun onNewDataFetched(results: List<Result>) {
        presenter.presentResults(results)
    }

    override fun addMatch() {
        presenter.goToAddMatchScreen()
    }


}
