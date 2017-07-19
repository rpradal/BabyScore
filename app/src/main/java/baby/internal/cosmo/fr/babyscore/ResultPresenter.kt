package baby.internal.cosmo.fr.babyscore

interface ResultPresenter {
    fun presentResults(results: List<Result>)
}

class ResultPresenterImpl(private val display: ResultDisplay) : ResultPresenter {

    override fun presentResults(results: List<Result>) {
        display.showResults(results)
    }

}