/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.servico;

import br.edu.ifsul.modelo.Pedido;
import br.edu.ifsul.utils.ObjetoResultado;
import br.edu.ifsul.utils.TrataErros;
import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.tempuri.CResultado;
import org.tempuri.CServico;
import org.tempuri.CalcPrecoPrazoWS;

/**
 *
 * @author Crisl
 */
@Stateless
@Path("correios")
public class ServicoPedido implements Serializable{
    
    private Gson gson;
    private final CalcPrecoPrazoWS correios;
    private final TrataErros erros;
    
    public ServicoPedido(){
        correios = new CalcPrecoPrazoWS();
        gson = new Gson();
        erros = new TrataErros();
    }
    
    @POST
    @Produces("application/json; charset=ISO-8859-1")
    @Consumes("application/json; charset=ISO-8859-1")
    public Response listar(Pedido pedido){
        
        ObjetoResultado objResultado = new ObjetoResultado();
        
        objResultado = erros.validaInformacao(pedido);

        if(objResultado.getCodigo() != 200){
            return Response.status(objResultado.getCodigo()).entity(objResultado).build();
        }
        
        try {
            CServico res = correios.getCalcPrecoPrazoWSSoap().calcPreco(
                    "",
                    "",
                    pedido.getFrete(), 
                    pedido.getCepOrigem(), 
                    pedido.getCepDestino(),
                    pedido.getPeso().toString(), //PESO MIN 20
                    pedido.getFormato(),  // 1 CAIXA, 2 ROLO PRISMA; 3 ENVELOPE
                    new BigDecimal(pedido.getComprimento()), //COMPRIMENTO MIN 16
                    new BigDecimal(pedido.getAltura()), //ALTURA MIN 20
                    new BigDecimal(pedido.getLargura()), //LARGURA MIN 11
                    new BigDecimal(pedido.getDiametro()),  //DIAMETRO MIN 20
                    pedido.getMaoPropria(),    //SERVIÇO ADICIONAL MÃO PRÓPRIA
                    new BigDecimal(pedido.getValorDeclarado()), // VALOR DECLARADO ??
                    pedido.getAvisoRecebimento()) //SERVIÇO AVISO DE RECEBIMENTO S/N
                    .getServicos().getCServico().get(0);
            
            return Response.ok().entity(res).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
    
}
