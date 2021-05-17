package pl.refactoring.interpreter.legacy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import pl.refactoring.interpreter.legacy.EstateMaterial.*
import pl.refactoring.interpreter.legacy.EstatePlacement.*
import pl.refactoring.interpreter.legacy.EstateType.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RealEstateFinderTest {

    private lateinit var underTest: RealEstateFinder

    @BeforeEach
    internal fun setUp() {
        underTest = RealEstateFinder(productsRepository)
    }

    @Test
    fun `finds small real estates`() {
        val actualResults = underTest.byBelowArea(70F)

        assertThat(actualResults)
            .hasSize(2)
            .containsExactlyInAnyOrder(ferroconcreteCityFlat, brickCityFlat)
    }

    @Test
    fun `finds wooden real estates`() {
        val actualResults = underTest.byMaterial(WOOD)

        assertThat(actualResults)
            .hasSize(2)
            .containsExactlyInAnyOrder(woodenVillageBungallow, woodenVillageHouse)
    }

    @Test
    fun `finds small wooden property`() {
        val actualResults = underTest.byMaterialBelowArea(WOOD, 150F)

        assertThat(actualResults).containsExactly(woodenVillageBungallow)
    }

    @Test
    fun `finds real estates in town`() {
        val actualResults = underTest.byPlacement(TOWN)

        assertThat(actualResults)
            .hasSize(2)
            .containsExactlyInAnyOrder(brickTownHouse, stoneTownCastle)
    }

    @Test
    fun `finds non-village real estates`() {
        val actualResults = underTest.byAvoidingPlacement(VILLAGE)

        assertThat(actualResults)
            .hasSize(4)
            .containsExactlyInAnyOrder(
                brickTownHouse,
                stoneTownCastle,
                brickCityFlat,
                ferroconcreteCityFlat
            )
    }

    @Test
    fun `finds real estates by area range`() {
        val actualResults = underTest.byAreaRange(130F, 140F)

        assertThat(actualResults)
            .hasSize(2)
            .containsExactlyInAnyOrder(brickVillageBungallow, woodenVillageBungallow)
    }

    @Test
    fun `finds all houses`() {
        val realEstates = underTest.byType(HOUSE)

        assertThat(realEstates)
            .hasSize(2)
            .containsExactlyInAnyOrder(brickTownHouse, woodenVillageHouse)
    }

    @Test
    fun `finds stone catles in towns`() {
        val actualResults = underTest.byTypePlacementAndMaterial(
            CASTLE,
            TOWN,
            STONE
        )

        assertThat(actualResults)
            .containsExactly(stoneTownCastle)
    }

    private val woodenVillageBungallow =
        RealEstate(
            id = 1,
            buildingArea = 140F,
            placement = VILLAGE,
            type = BUNGALLOW,
            material = WOOD
        )

    private val woodenVillageHouse =
        RealEstate(
            id = 2,
            buildingArea = 210F,
            placement = VILLAGE,
            type = HOUSE,
            material = WOOD
        )

    private val brickTownHouse =
        RealEstate(
            id = 3,
            buildingArea = 230F,
            placement = TOWN,
            type = HOUSE,
            material = BRICK
        )

    private val brickVillageBungallow =
        RealEstate(
            id = 4,
            buildingArea = 130F,
            placement = VILLAGE,
            type = BUNGALLOW,
            material = BRICK
        )

    private val stoneTownCastle =
        RealEstate(
            id = 5,
            buildingArea = 3900F,
            placement = TOWN,
            type = CASTLE,
            material = STONE
        )

    private val ferroconcreteCityFlat =
        RealEstate(
            id = 6,
            buildingArea = 40F,
            placement = CITY,
            type = FLAT,
            material = FERROCONCRETE
        )

    private val brickCityFlat =
        RealEstate(
            id = 7,
            buildingArea = 69F,
            placement = CITY,
            type = FLAT,
            material = BRICK
        )

    private val productsRepository = listOf(
        woodenVillageBungallow,
        woodenVillageHouse,
        brickTownHouse,
        brickVillageBungallow,
        stoneTownCastle,
        ferroconcreteCityFlat,
        brickCityFlat
    )

}