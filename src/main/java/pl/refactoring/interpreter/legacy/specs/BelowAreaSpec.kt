package pl.refactoring.interpreter.legacy.specs

import pl.refactoring.interpreter.legacy.RealEstate
import pl.refactoring.interpreter.legacy.Spec

class BelowAreaSpec(private val maxBuildingArea: Float) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.buildingArea < maxBuildingArea
}