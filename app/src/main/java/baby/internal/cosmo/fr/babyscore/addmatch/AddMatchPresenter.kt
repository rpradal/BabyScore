package baby.internal.cosmo.fr.babyscore.addmatch


interface AddMatchPresenter {
    fun presentInvalidInput()
    fun dismiss()

}

class AddMatchPresenterImpl(private val addMatchDisplay: AddMatchDisplay) : AddMatchPresenter {
    override fun presentInvalidInput() {
        addMatchDisplay.showInvalidInput()
    }

    override fun dismiss() {
        addMatchDisplay.dismiss()
    }

}