package pl.refactoring.interpreter.legacy;

import org.jetbrains.annotations.NotNull;
import pl.refactoring.interpreter.legacy.specs.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
        return bySpec(new BelowAreaSpec(maxBuildingArea));
    }

    public List<RealEstate> byMaterial(EstateMaterial material) {
        return bySpec(new MaterialSpec(material));
    }

    public List<RealEstate> byMaterialBelowArea(EstateMaterial material, float maxBuildingArea) {
        Spec andSpec = new AndSpec(new MaterialSpec(material), new BelowAreaSpec(maxBuildingArea));
        return bySpec(andSpec);
    }

    public List<RealEstate> byPlacement(EstatePlacement placement) {
        return bySpec(new PlacementSpec(placement));
    }

    public List<RealEstate> byAvoidingPlacement(EstatePlacement placement) {
        Spec notSpec = new NotSpec(new PlacementSpec(placement));

        return bySpec(notSpec);
    }

    public List<RealEstate> byAreaRange(float minArea, float maxArea) {
        return bySpec(new BetweenAreaSpec(minArea, maxArea));
    }

    public List<RealEstate> byType(EstateType type) {
        return bySpec(new TypeSpec(type));
    }

    public List<RealEstate> byTypePlacementAndMaterial(EstateType type, EstatePlacement placement, EstateMaterial material) {
        Spec typeSpec = new TypeSpec(type);
        Spec placementSpec = new PlacementSpec(placement);
        Spec materialSpec = new MaterialSpec(material);

        return bySpec(new AndSpec(typeSpec, placementSpec, materialSpec));
    }

    @NotNull
    private List<RealEstate> bySpec(Spec spec) {
        return repository.stream()
                .filter(spec::isSatisfiedBy)
                .collect(toList());
    }
}
