package pl.refactoring.interpreter.legacy

interface Spec {
    fun isSatisfiedBy(estate: RealEstate): Boolean
}