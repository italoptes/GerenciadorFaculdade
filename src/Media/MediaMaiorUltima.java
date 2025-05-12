package Media;

import Excecoes.EntidadeNullException;

import java.io.Serializable;
import java.util.List;

public class MediaMaiorUltima implements MediaIF, Serializable {
    public double calculaMedia(List<Double> valores) throws EntidadeNullException {
        if (valores == null || valores.isEmpty()) {
            throw new EntidadeNullException("NENHUMA NOTA adicionada");
        }
        if (valores.size() < 2) {
            return valores.get(valores.size() - 1);
        }
        double ultima = valores.get(valores.size() - 1);
        double soma = 0;
        for (int i = 0; i < valores.size() - 1; i++) {
            soma += valores.get(i);
        }
        double mediaAnterior = soma / (valores.size() - 1);
        return 0.5 * ultima + 0.5 * mediaAnterior;
    }
}
