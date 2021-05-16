package pl.refactoring.interpreter.legacy.specs

import pl.refactoring.interpreter.legacy.*

class BelowAreaSpec internal constructor(private val maxBuildingArea: Float) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.buildingArea < maxBuildingArea
}

class MaterialSpec internal constructor(private val material: EstateMaterial) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.material == this.material
}

// FIXME: 16/05/2021 figure out why constructor can't be private
class AndSpec(private vararg val specs: Spec) : Spec {

    companion object {
        @JvmStatic
        fun builder() = AndSpecBuilder()
    }

    class AndSpecBuilder {

        private val specs: MutableList<Spec> = mutableListOf()

        fun withSpec(spec: Spec): AndSpecBuilder {
            specs.add(spec)
            return this
        }

        fun build() = AndSpec(*specs.toTypedArray())
    }

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        specs.filterNot { it.isSatisfiedBy(estate) }.isEmpty()
}

class PlacementSpec internal constructor(private val placement: EstatePlacement) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.placement == placement
}

class NotSpec internal constructor(private val spec: Spec) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        spec.isSatisfiedBy(estate).not()
}

class BetweenAreaSpec internal constructor(private val minArea: Float, private val maxArea: Float) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.buildingArea in minArea..maxArea
}

class TypeSpec internal constructor(private val type: EstateType) : Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.type == type
}