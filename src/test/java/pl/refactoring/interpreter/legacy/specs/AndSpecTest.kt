package pl.refactoring.interpreter.legacy.specs

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import pl.refactoring.interpreter.legacy.RealEstate
import pl.refactoring.interpreter.legacy.Spec

internal class AndSpecTest {

    private val estateMock = mock<RealEstate>()

    @Test
    fun `returns true for two successful Specs`() {
        val firstSpec = SuccessfulSpec()
        val secondSpec = SuccessfulSpec()
        val underTest = AndSpec.builder()
            .withSpec(firstSpec)
            .withSpec(secondSpec)
            .build()

        val actualResult = underTest.isSatisfiedBy(estateMock)

        assertThat(firstSpec.getActuallyUsedRealEstate()).isEqualTo(estateMock)
        assertThat(secondSpec.getActuallyUsedRealEstate()).isEqualTo(estateMock)
        assertThat(actualResult).isTrue
    }

    @Test
    fun `returns true for three successful Specs`() {
        val firstSpec = SuccessfulSpec()
        val secondSpec = SuccessfulSpec()
        val thirdSpec = SuccessfulSpec()
        val underTest = AndSpec.builder()
            .withSpec(firstSpec)
            .withSpec(secondSpec)
            .withSpec(thirdSpec)
            .build()

        val actualResult = underTest.isSatisfiedBy(estateMock)

        assertThat(firstSpec.getActuallyUsedRealEstate()).isEqualTo(estateMock)
        assertThat(secondSpec.getActuallyUsedRealEstate()).isEqualTo(estateMock)
        assertThat(thirdSpec.getActuallyUsedRealEstate()).isEqualTo(estateMock)
        assertThat(actualResult).isTrue
    }

    @Test
    fun `returns false if one of the specs is unsuccessful`() {
        val firstSpec = SuccessfulSpec()
        val secondSpec = UnsuccessfulSpec()
        val thirdSpec = SuccessfulSpec()
        val underTest = AndSpec.builder()
            .withSpec(firstSpec)
            .withSpec(secondSpec)
            .withSpec(thirdSpec)
            .build()

        val actualResult = underTest.isSatisfiedBy(estateMock)

        assertThat(firstSpec.getActuallyUsedRealEstate()).isEqualTo(estateMock)
        assertThat(secondSpec.getActuallyUsedRealEstate()).isEqualTo(estateMock)
        assertThat(thirdSpec.getActuallyUsedRealEstate()).isEqualTo(estateMock)
        assertThat(actualResult).isFalse
    }
}

private class SuccessfulSpec : Spec {
    private var usedRealEstate: RealEstate? = null

    override fun isSatisfiedBy(estate: RealEstate): Boolean {
        usedRealEstate = estate
        return true
    }

    fun getActuallyUsedRealEstate() = usedRealEstate
}

private class UnsuccessfulSpec : Spec {
    private var usedRealEstate: RealEstate? = null

    override fun isSatisfiedBy(estate: RealEstate): Boolean {
        usedRealEstate = estate
        return false
    }

    fun getActuallyUsedRealEstate() = usedRealEstate
}
