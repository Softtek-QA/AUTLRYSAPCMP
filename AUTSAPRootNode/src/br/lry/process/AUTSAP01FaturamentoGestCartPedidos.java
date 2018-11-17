/**
 * 
 */
package br.lry.process;

import org.junit.Test;

import com.microfocus.silktest.jtf.sap.SapLabel;
import com.microfocus.silktest.jtf.sap.SapWindow;

import br.lry.components.sap.AUTME21N;
import br.lry.components.sap.AUTSAPBaseComponent;
import br.lry.components.sap.AUTSAPSession;
import br.lry.components.sap.AUTZOSDGCP;

/**
 * 
 * Abastecimento de estoque
 * 
 * @author Softtek-QA
 *
 */
public class AUTSAP01FaturamentoGestCartPedidos extends AUTSAPSession {
	String transacao = "ZOSD_GCP";
	
	/**
	 * 
	 * Processo de inicialização do componente
	 * 
	 */
	public void autStartProcess(java.util.HashMap<String, Object> parametros) {
		
		autStartTransaction(transacao);				
	}
	
	
	@Test
	public void autStartProcessZCMA() {		
		java.util.HashMap<String,Object> parametros = new java.util.HashMap<String,Object>();
		AUTZOSDGCP transPedido  = new AUTZOSDGCP();				
		parametros.put("USER_SAP", "51028487");
		parametros.put("PWD_SAP", "Auto5@2020");
		parametros.put("INIT_TRANSACTION", true);
		parametros.put("AUT_PEDIDO", "939318");
		parametros.put("AUT_LOJA", "0035");
		parametros.put("AUT_DATA_INICIAL", "12.11.2018");
		parametros.put("AUT_DATA_FINAL", "16.11.2018");
		parametros.put("AUT_TIPO_PEDIDO", "ZVAS");
		
		transPedido.autStartProcessGCP(parametros);
	}	
	
	
	public AUTSAP01FaturamentoGestCartPedidos() {
	
	}
}
