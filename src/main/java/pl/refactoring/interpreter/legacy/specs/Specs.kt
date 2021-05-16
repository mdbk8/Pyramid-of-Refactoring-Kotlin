package pl.refactoring.interpreter.legacy.specs

import pl.refactoring.interpreter.legacy.EstateMaterial
import pl.refactoring.interpreter.legacy.EstatePlacement
import pl.refactoring.interpreter.legacy.EstateType
import pl.refactoring.interpreter.legacy.Spec

object Specs {

    @JvmStatic
    fun belowArea(maxBuildingArea: Float): Spec = BelowAreaSpec(maxBuildingArea)

    @JvmStatic
    fun ofMaterial(material: EstateMaterial): Spec = MaterialSpec(material)

    @JvmStatic
    fun placedIn(placement: EstatePlacement): Spec = PlacementSpec(placement)

    @JvmStatic
    fun not(spec: Spec): Spec = NotSpec(spec)

    @JvmStatic
    fun ofAreaRange(minArea: Float, maxArea: Float): Spec = BetweenAreaSpec(minArea, maxArea)

    @JvmStatic
    fun ofType(type: EstateType): Spec = TypeSpec(type)
}