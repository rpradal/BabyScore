package baby.internal.cosmo.fr.babyscore

interface ResultPresenter {
    fun presentResults(results: List<Result>)
    fun goToAddMatchScreen()
}

class ResultPresenterImpl(private val display: ResultDisplay) : ResultPresenter {
    override fun goToAddMatchScreen() {
        display.showAddMatchScreen()
    }

    override fun presentResults(results: List<Result>) {
        display.showResults(results)
    }

}