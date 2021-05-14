package pl.refactoring.interpreter.legacy.specs

import pl.refactoring.interpreter.legacy.EstateMaterial
import pl.refactoring.interpreter.legacy.EstatePlacement
import pl.refactoring.interpreter.legacy.RealEstate
import pl.refactoring.interpreter.legacy.Spec

class BelowAreaSpec(private val maxBuildingArea: Float) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.buildingArea < maxBuildingArea
}

class MaterialSpec(private val material: EstateMaterial): Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.material == this.material
}

class AndSpec(private val materialSpec: Spec, private val belowAreaSpec: Spec) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        materialSpec.isSatisfiedBy(estate) && belowAreaSpec.isSatisfiedBy(estate)
}

class PlacementSpec(private val placement: EstatePlacement) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.placement == placement
}

class NotSpec(private val placementSpec: Spec) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        placementSpec.isSatisfiedBy(estate).not()
}

class BetweenAreaSpec(private val minArea: Float, private val maxArea: Float) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.buildingArea in minArea..maxArea
}