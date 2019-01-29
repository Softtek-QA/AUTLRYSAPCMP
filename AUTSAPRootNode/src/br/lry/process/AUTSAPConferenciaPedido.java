package br.lry.process;

import br.lry.components.sap.AUTVL06;

/**
 * 
 * Gerencia a interface para procedimentos de conferências de pedido
 * 
 * @author Softtek-QA
 *
 */
public class AUTSAPConferenciaPedido extends AUTVL06 {
	public void autInitProcess(java.util.HashMap<String,Object> parameters) {
		autStartConf(parameters);
	}
	
	public AUTSAPConferenciaPedido() {
		// TODO Auto-generated constructor stub
		super();
	}

}
