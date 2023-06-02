package com.deveficiente.heuristicas.valueobjects.percentual.v1;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Percentual {

    private long dividendo;
    private long divisor;

    public Percentual(long dividendo, long divisor) {
        this.dividendo = dividendo;
        this.divisor = divisor;
    }

    public BigDecimal calcula(int casasDecimais, RoundingMode arredodamento) {
        return new BigDecimal(dividendo).divide(new BigDecimal(divisor), casasDecimais, arredodamento);
    }
}
