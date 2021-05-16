package pl.refactoring.interpreter.legacy.specs

import pl.refactoring.interpreter.legacy.EstateMaterial
import pl.refactoring.interpreter.legacy.EstatePlacement
import pl.refactoring.interpreter.legacy.EstateType
import pl.refactoring.interpreter.legacy.Spec

object Specs {

    fun belowArea(maxBuildingArea: Float): Spec = BelowAreaSpec(maxBuildingArea)

    fun ofMaterial(material: EstateMaterial): Spec = MaterialSpec(material)

    fun placedIn(placement: EstatePlacement): Spec = PlacementSpec(placement)

    fun not(spec: Spec): Spec = NotSpec(spec)

    fun ofAreaRange(minArea: Float, maxArea: Float): Spec = BetweenAreaSpec(minArea, maxArea)

    fun ofType(type: EstateType): Spec = TypeSpec(type)
}