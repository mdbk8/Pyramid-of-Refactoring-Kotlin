package pl.refactoring.interpreter.legacy

/**
 * Copyright (c) 2020 IT Train Wlodzimierz Krakowski (www.refactoring.pl)
 * Sources are available only for personal usage by Udemy students of this course.
 */
data class RealEstate(
    val id: Int,
    val buildingArea: Float,
    val placement: EstatePlacement,
    val type: EstateType,
    val material: EstateMaterial
)