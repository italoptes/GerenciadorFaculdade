package Media;

import Excecoes.EntidadeNullException;

import java.io.Serializable;
import java.util.List;

public interface MediaIF extends Serializable {
    double calculaMedia(List<Double> valores) throws EntidadeNullException;
}
