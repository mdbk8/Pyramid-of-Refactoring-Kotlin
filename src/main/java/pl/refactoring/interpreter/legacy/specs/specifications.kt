package pl.refactoring.interpreter.legacy.specs

import pl.refactoring.interpreter.legacy.*

class BelowAreaSpec private constructor(private val maxBuildingArea: Float) : Spec {

    companion object {
        @JvmStatic
        fun belowArea(maxBuildingArea: Float) = BelowAreaSpec(maxBuildingArea)
    }

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.buildingArea < maxBuildingArea
}

class MaterialSpec private constructor(private val material: EstateMaterial) : Spec {

    companion object {

        @JvmStatic
        fun ofMaterial(material: EstateMaterial) = MaterialSpec(material)
    }

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.material == this.material
}

class AndSpec(private vararg val specs: Spec) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        specs.filterNot { it.isSatisfiedBy(estate) }.isEmpty()
}

class PlacementSpec private constructor(private val placement: EstatePlacement) : Spec {

    companion object {
        @JvmStatic
        fun placedIn(placement: EstatePlacement) = PlacementSpec(placement)
    }

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.placement == placement
}

class NotSpec private constructor(private val spec: Spec) : Spec {

    companion object {
        @JvmStatic
        fun not(spec: Spec) = NotSpec(spec)
    }

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        spec.isSatisfiedBy(estate).not()
}

class BetweenAreaSpec private constructor(private val minArea: Float, private val maxArea: Float) : Spec {

    companion object {
        @JvmStatic
        fun ofAreaRange(minArea: Float, maxArea: Float) = BetweenAreaSpec(minArea, maxArea)
    }

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.buildingArea in minArea..maxArea
}

class TypeSpec private constructor(private val type: EstateType) : Spec {

    companion object {
        @JvmStatic
        fun ofType(type: EstateType) = TypeSpec(type)
    }

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.type == type
}