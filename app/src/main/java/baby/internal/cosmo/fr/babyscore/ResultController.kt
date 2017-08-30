package baby.internal.cosmo.fr.babyscore

interface ResultController {
    fun fetch()
    fun addMatch()
}

class ResultControllerImpl(val interactor: ResultInteractor) : ResultController {
    override fun addMatch() {
        interactor.addMatch()
    }

    override fun fetch() {
        interactor.fetch()
    }

}