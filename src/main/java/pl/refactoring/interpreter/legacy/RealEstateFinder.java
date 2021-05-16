package pl.refactoring.interpreter.legacy;

import org.jetbrains.annotations.NotNull;
import pl.refactoring.interpreter.legacy.specs.AndSpec;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static pl.refactoring.interpreter.legacy.specs.Specs.ofAreaRange;
import static pl.refactoring.interpreter.legacy.specs.Specs.not;
import static pl.refactoring.interpreter.legacy.specs.Specs.placedIn;
import static pl.refactoring.interpreter.legacy.specs.Specs.belowArea;
import static pl.refactoring.interpreter.legacy.specs.Specs.ofMaterial;
import static pl.refactoring.interpreter.legacy.specs.Specs.ofType;

/**
 * Copyright (c) 2020 IT Train Wlodzimierz Krakowski (www.refactoring.pl)
 * Sources are available only for personal usage by Udemy students of this course.
 */
public class RealEstateFinder {
    private List<RealEstate> repository;

    public RealEstateFinder(List<RealEstate> repository) {
        this.repository = repository;
    }

    public List<RealEstate> byBelowArea(float maxBuildingArea) {
        return bySpec(belowArea(maxBuildingArea));
    }

    public List<RealEstate> byMaterial(EstateMaterial material) {
        return bySpec(ofMaterial(material));
    }

    public List<RealEstate> byMaterialBelowArea(EstateMaterial material, float maxBuildingArea) {
        Spec andSpec = new AndSpec(ofMaterial(material), belowArea(maxBuildingArea));
        return bySpec(andSpec);
    }

    public List<RealEstate> byPlacement(EstatePlacement placement) {
        return bySpec(placedIn(placement));
    }

    public List<RealEstate> byAvoidingPlacement(EstatePlacement placement) {
        return bySpec(not(placedIn(placement)));
    }

    public List<RealEstate> byAreaRange(float minArea, float maxArea) {
        return bySpec(ofAreaRange(minArea, maxArea));
    }

    public List<RealEstate> byType(EstateType type) {
        return bySpec(ofType(type));
    }

    public List<RealEstate> byTypePlacementAndMaterial(EstateType type, EstatePlacement placement, EstateMaterial material) {
        return bySpec(new AndSpec(
                ofType(type),
                placedIn(placement),
                ofMaterial(material))
        );
    }

    @NotNull
    private List<RealEstate> bySpec(Spec spec) {
        return repository.stream()
                .filter(spec::isSatisfiedBy)
                .collect(toList());
    }
}
