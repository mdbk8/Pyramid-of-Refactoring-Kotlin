package pl.refactoring.interpreter.legacy

import pl.refactoring.interpreter.legacy.specs.AndSpec
import pl.refactoring.interpreter.legacy.specs.Specs.belowArea
import pl.refactoring.interpreter.legacy.specs.Specs.not
import pl.refactoring.interpreter.legacy.specs.Specs.ofAreaRange
import pl.refactoring.interpreter.legacy.specs.Specs.ofMaterial
import pl.refactoring.interpreter.legacy.specs.Specs.ofType
import pl.refactoring.interpreter.legacy.specs.Specs.placedIn

/**
 * Copyright (c) 2020 IT Train Wlodzimierz Krakowski (www.refactoring.pl)
 * Sources are available only for personal usage by Udemy students of this course.
 */
class RealEstateFinder(private val repository: List<RealEstate>) {

    fun byBelowArea(maxBuildingArea: Float): List<RealEstate> =
        bySpec(belowArea(maxBuildingArea))

    fun byMaterial(material: EstateMaterial): List<RealEstate> =
        bySpec(ofMaterial(material))

    fun byMaterialBelowArea(material: EstateMaterial, maxBuildingArea: Float): List<RealEstate> =
        AndSpec.Builder()
            .withSpec(ofMaterial(material))
            .withSpec(belowArea(maxBuildingArea))
            .build()
            .let { bySpec(it) }

    fun byPlacement(placement: EstatePlacement): List<RealEstate> =
        bySpec(placedIn(placement))

    fun byAvoidingPlacement(placement: EstatePlacement): List<RealEstate> =
        bySpec(not(placedIn(placement)))

    fun byAreaRange(minArea: Float, maxArea: Float): List<RealEstate> =
        bySpec(ofAreaRange(minArea, maxArea))

    fun byType(type: EstateType): List<RealEstate> =
        bySpec(ofType(type))

    fun byTypePlacementAndMaterial(
        type: EstateType,
        placement: EstatePlacement,
        material: EstateMaterial
    ): List<RealEstate> =
        AndSpec.Builder()
            .withSpec(ofType(type))
            .withSpec(placedIn(placement))
            .withSpec(ofMaterial(material))
            .build()
            .let { bySpec(it) }

    private fun bySpec(spec: Spec): List<RealEstate> =
        repository.filter { spec.isSatisfiedBy(it) }
}