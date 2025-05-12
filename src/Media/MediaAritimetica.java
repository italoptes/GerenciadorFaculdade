package Media;

import Excecoes.EntidadeNullException;

import java.io.Serializable;
import java.util.List;

public class MediaAritimetica implements MediaIF, Serializable {

    @Override
    public double calculaMedia(List<Double> valores) throws EntidadeNullException {
        if (valores == null || valores.isEmpty()) {
            throw new EntidadeNullException("NENHUMA NOTA adicionada");
        }
        double soma = 0;
        for (double v : valores) {
            soma += v;
        }
        return soma / valores.size();
    }
}
