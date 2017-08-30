package baby.internal.cosmo.fr.babyscore.addmatch

interface AddMatchController {
    fun addMatch(inputData: AddMatchInput)
}


class AddMatchControllerImpl(val interactor: AddMatchInteractor) : AddMatchController {
    override fun addMatch(inputData: AddMatchInput) {
        interactor.addMatch(inputData)
    }

}

interface AddMatchInteractor {
    fun addMatch(inputData: AddMatchInput)

}
