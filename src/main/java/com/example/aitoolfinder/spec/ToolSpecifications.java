package com.example.aitoolfinder.spec;

import com.example.aitoolfinder.model.PricingType;
import com.example.aitoolfinder.model.Tool;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public class ToolSpecifications {
    public static Specification<Tool> build(String category, String pricing, String ratingExpr, String q) {
        Specification<Tool> spec = Specification.where(null);

        if (category != null && !category.isBlank()) {
            String c = category.trim().toLowerCase(Locale.ROOT);
            spec = spec.and((root, query, cb) -> cb.equal(cb.lower(root.get("category")), c));
        }
        if (pricing != null && !pricing.isBlank()) {
            try {
                PricingType pt = PricingType.valueOf(pricing.trim().toUpperCase(Locale.ROOT));
                PricingType finalPt = pt;
                spec = spec.and((root, query, cb) -> cb.equal(root.get("pricingType"), finalPt));
            } catch (IllegalArgumentException ignored) {
                // invalid pricing type -> no match
                spec = spec.and((root, query, cb) -> cb.disjunction());
            }
        }
        if (ratingExpr != null && !ratingExpr.isBlank()) {
            RatingFilter rf = parseRatingExpr(ratingExpr.trim());
            if (rf != null) {
                switch (rf.op) {
                    case GE -> spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("averageRating"), rf.value));
                    case LE -> spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("averageRating"), rf.value));
                    case EQ -> spec = spec.and((root, query, cb) -> cb.equal(root.get("averageRating"), rf.value));
                }
            }
        }
        if (q != null && !q.isBlank()) {
            String term = "%" + q.trim().toLowerCase(Locale.ROOT) + "%";
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("name")), term),
                    cb.like(cb.lower(root.get("useCase")), term)
            ));
        }
        return spec;
    }

    enum Op { GE, LE, EQ }
    static class RatingFilter { Op op; double value; RatingFilter(Op op, double value){this.op=op;this.value=value;} }

    static RatingFilter parseRatingExpr(String expr) {
        String e = expr.replaceAll("\\s+", "");
        if (e.startsWith(">=")) {
            return parseValue(e.substring(2), Op.GE);
        } else if (e.startsWith("<=")) {
            return parseValue(e.substring(2), Op.LE);
        } else if (e.startsWith("=")) {
            return parseValue(e.substring(1), Op.EQ);
        } else if (e.matches("[0-9]+(\\.[0-9]+)?")) {
            return parseValue(e, Op.GE); // default to >= if only a number
        }
        return null;
    }

    private static RatingFilter parseValue(String v, Op op) {
        try {
            double val = Double.parseDouble(v);
            return new RatingFilter(op, val);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
