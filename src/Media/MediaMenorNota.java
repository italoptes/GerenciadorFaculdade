package Media;

import Excecoes.EntidadeNullException;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class MediaMenorNota implements MediaIF, Serializable {
    @Override
    public double calculaMedia(List<Double> valores) throws EntidadeNullException {
        if (valores == null || valores.isEmpty()) {
            throw new EntidadeNullException("NENHUMA NOTA adicionada");
        }
        if (valores.size() < 2) return valores.get(0);
        double menor = Collections.min(valores);
        double somaTotal = 0;
        for (double v : valores) {
            somaTotal += v;
        }
        return (somaTotal - menor) / (valores.size() - 1);
    }
}
