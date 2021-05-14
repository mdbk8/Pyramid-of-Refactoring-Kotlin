package pl.refactoring.interpreter.legacy

class MaterialSpec(private val material: EstateMaterial): Spec {

    override fun isSatisfiedBy(estate: RealEstate): Boolean =
        estate.material == this.material
}