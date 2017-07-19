package baby.internal.cosmo.fr.babyscore

interface ResultController {
    fun fetch()
}

class ResultControllerImpl(val interactor: ResultInteractor) : ResultController {

    override fun fetch() {
        interactor.fetch()
    }

}