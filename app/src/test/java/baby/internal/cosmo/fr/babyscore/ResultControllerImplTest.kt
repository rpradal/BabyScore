package baby.internal.cosmo.fr.babyscore

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by remi on 19/07/2017.
 */
class ResultControllerImplTest {
    lateinit var interactor: ResultInteractor
    lateinit var controller: ResultController

    @Before
    fun setUp() {
        interactor = mock()
        controller = ResultControllerImpl(interactor)
    }

    @Test
    fun fetch_shouldCallInteractor() {
        controller.fetch()
        verify(interactor).fetch()
    }

}