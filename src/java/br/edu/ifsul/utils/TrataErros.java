package br.edu.ifsul.utils;

import br.edu.ifsul.modelo.Pedido;
import java.io.Serializable;

/**
 *
 * @author Crisl
 */
public class TrataErros implements Serializable{
    
    private final ObjetoResultado objResultado = new ObjetoResultado();
    
    public ObjetoResultado validaInformacao(Pedido pedido){
        
        if(pedido.getCepDestino().length() != 8){
            objResultado.setDescricao("O cep de destino não é valido");
            objResultado.setCodigo(1);
            return objResultado;
        }else if(pedido.getCepOrigem().length() != 8){
            objResultado.setDescricao("O cep de origem não é valido");
            objResultado.setCodigo(2);
            return objResultado;
        }else if(pedido.getComprimento() < 16.0){
            objResultado.setDescricao("O comprimento não pode ser inferior a 16 cm");
            objResultado.setCodigo(3);
            return objResultado;
        }else if(pedido.getComprimento() > 105.0){
            objResultado.setDescricao("O comprimento nao pode ser maior que 105 cm.");
            objResultado.setCodigo(4);
            return objResultado;
        }else if( pedido.getAltura() < 2.0){
            objResultado.setDescricao("A altura não pode ser inferior a 2 cm");
            objResultado.setCodigo(5);
            return objResultado;
        }else if(pedido.getLargura() < 11.0){
            objResultado.setDescricao("A largura não pode ser inferior a 11cm");
            objResultado.setCodigo(6);
            return objResultado;
        }else if(pedido.getFormato() != 1 && pedido.getFormato() != 2 && pedido.getFormato() != 3){
            objResultado.setDescricao("O formato não é valido");
            objResultado.setCodigo(7);
            return objResultado;
        }else if(pedido.getFormato() == 1 && pedido.getComprimento() > 105.0){
            objResultado.setDescricao("O comprimento nao pode ser maior que 105 cm.");
            objResultado.setCodigo(8);
            return objResultado;
        }else if(pedido.getFormato() == 1 && pedido.getComprimento() > 60.0){
            objResultado.setDescricao("O comprimento nao pode ser maior que 60 cm.");
            objResultado.setCodigo(9);
            return objResultado;
        }else if(pedido.getFormato() == 1 && pedido.getPeso() > 30){
            objResultado.setDescricao("O peso não pode ser superior a 30 kg");
            objResultado.setCodigo(10);
            return objResultado;
        }
        else if(pedido.getFormato() == 2 && pedido.getComprimento() > 105.0){
            objResultado.setDescricao("O comprimento nao pode ser maior que 105 cm.");
            objResultado.setCodigo(11);
            return objResultado;
        }
        else if(pedido.getFormato() == 3 && pedido.getPeso() > 1.0){
            objResultado.setDescricao("O envelope não pode ter mais que 1KG");
            objResultado.setCodigo(12);
            return objResultado;
        }else if(pedido.getFrete().length() != 5){
            objResultado.setDescricao("O frete não é valido");
            objResultado.setCodigo(13);
            return objResultado;
        }else{
            objResultado.setDescricao("OK");
            objResultado.setCodigo(200);
            return objResultado;
        }
    }
    
}
