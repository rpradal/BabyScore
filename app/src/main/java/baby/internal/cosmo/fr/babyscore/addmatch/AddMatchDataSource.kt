package baby.internal.cosmo.fr.babyscore.addmatch

import com.google.firebase.database.FirebaseDatabase

interface AddMatchDataSourceController {
    fun attach(interactor: AddMatchInteractor)
    fun addMatch(inputData: AddMatchInput)
}

class AddMatchDataSourceControllerImpl(private val firebaseDatabase: FirebaseDatabase) : AddMatchDataSourceController, AddMatchDataSource {
    lateinit var interactor: AddMatchInteractor

    override fun attach(interactor: AddMatchInteractor) {
        this.interactor = interactor
    }

    override fun addMatch(inputData: AddMatchInput) {
        val reference = firebaseDatabase.reference
        reference.database.getReference("matches").push().setValue(inputData)
    }
}

interface AddMatchDataSource {
    fun addMatch(inputData: AddMatchInput)
}