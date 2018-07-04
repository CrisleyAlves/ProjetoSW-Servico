package br.edu.ifsul.utils;

import java.io.Serializable;

/**
 *
 * @author Crisl
 */
public class ObjetoResultado implements Serializable{
    
    private Integer codigo;
    private String descricao;
    
    public  ObjetoResultado(){
        
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
