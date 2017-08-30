package baby.internal.cosmo.fr.babyscore.addmatch

import android.util.Log

interface AddMatchDataSourceController {
    fun attach(interactor: AddMatchInteractor)
    fun addMatch(inputData: AddMatchInput)
}

class AddMatchDataSourceControllerImpl : AddMatchDataSourceController, AddMatchDataSource {
    lateinit var interactor : AddMatchInteractor

    override fun attach(interactor: AddMatchInteractor) {
        this.interactor = interactor
    }

    override fun addMatch(inputData: AddMatchInput) {
            Log.d("AddMatchDSC", "AddMatch $inputData")
    }
}

interface AddMatchDataSource {
    fun addMatch(inputData: AddMatchInput)
}