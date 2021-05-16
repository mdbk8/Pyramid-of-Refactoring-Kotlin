package pl.refactoring.interpreter.legacy.specs

import pl.refactoring.interpreter.legacy.*

class BelowAreaSpec(private val maxBuildingArea: Float) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.buildingArea < maxBuildingArea
}

class MaterialSpec(private val material: EstateMaterial) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.material == this.material
}

class AndSpec(private vararg val specs: Spec) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        specs.filterNot { it.isSatisfiedBy(estate) }.isEmpty()
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

class TypeSpec(private val type: EstateType) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.type == type
}