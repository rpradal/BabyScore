package baby.internal.cosmo.fr.babyscore.addmatch


interface AddMatchPresenter {
    fun presentInvalidInput()

}

class AddMatchPresenterImpl(private val addMatchDisplay: AddMatchDisplay) : AddMatchPresenter {
    override fun presentInvalidInput() {
        addMatchDisplay.showInvalidInput()
    }

}