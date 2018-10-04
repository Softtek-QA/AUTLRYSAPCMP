/**
 * 
 */
package br.lry.process;

import org.junit.Test;

import br.lry.components.sap.AUTME21N;
import br.lry.components.sap.AUTSAPBaseComponent;
import br.lry.components.sap.AUTSAPSession;

/**
 * 
 * Abastecimento de estoque
 * 
 * @author Softtek-QA
 *
 */
public class AUTSAP01AbastecimentoEstoque extends AUTSAPSession {
	String transacao = "ME21N";
	/**
	 * 
	 * Processo de inicialização do componente
	 * 
	 */
	//@Test
	public void autStartProcess(java.util.HashMap<String, Object> parametros) {
		AUTSAPSession login = new AUTSAPSession();
		AUTME21N criarPedido = new AUTME21N();
		
		login.autStartSAPECQDefault();
		login.autStartTransaction(transacao);
		criarPedido.autExecME21NZCMA(parametros);
		
	}
	
	
	@Test
	public void autStartProcess() {
		
		
		autStartSAPECQDefault();
		
		java.util.HashMap<String,Object> parametros = new java.util.HashMap<String,Object>();
		java.util.List<AUTSapItemLoja> itemLoja = new java.util.ArrayList<AUTSapItemLoja>();
		AUTME21N transPedido  = new AUTME21N();				
		parametros.put("USER_SAP", "51028487");
		parametros.put("PWD_SAP", "Auto5@2020");
		parametros.put("INIT_TRANSACTION", true);
		parametros.put("ITEMS", itemLoja);
		
		transPedido.autExecME21NZCMA(parametros);
		
		
		com.borland.silktest.jtf.Utils.sleep(10000);

		autSAPLogout();
		
	}
	
	
	public AUTSAP01AbastecimentoEstoque() {
	
	}
}
