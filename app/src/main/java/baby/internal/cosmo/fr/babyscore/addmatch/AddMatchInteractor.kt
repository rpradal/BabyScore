package baby.internal.cosmo.fr.babyscore.addmatch

interface AddMatchInteractor {
    fun addMatch(inputData: AddMatchInput)
}

class AddMatchInteractorImpl(private val addMatchPresenter: AddMatchPresenter,
    private val dataSourceController: AddMatchDataSourceController) : AddMatchInteractor {

    init {
        dataSourceController.attach(this)
    }

    override fun addMatch(inputData: AddMatchInput) {

        if (inputData.firstTeamFirstPlayerName.isBlank() || inputData.secondTeamFirstPlayerName.isBlank()) {
            addMatchPresenter.presentInvalidInput()
        } else if (!inputData.scoreFirstTeam.isNumber() || !inputData.scoreSecondTeam.isNumber()) {
            addMatchPresenter.presentInvalidInput()
        } else {
            dataSourceController.addMatch(inputData)
            addMatchPresenter.dismiss()
        }
    }

    private fun String.isNumber(): Boolean {
        return try {
            toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

}
