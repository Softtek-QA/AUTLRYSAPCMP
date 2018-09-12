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
public class AUTSAP01AbastecimentoEstoque extends AUTSAPBaseComponent {
	String transacao = "ME21N";
	/**
	 * 
	 * Processo de inicialização do componente
	 * 
	 */
	@Test
	public void autStartProcess(java.util.HashMap<String, Object> parametros) {
		AUTSAPSession login = new AUTSAPSession();
		AUTME21N criarPedido = new AUTME21N();
		
		login.autStartSAPECQDefault();
		login.autStartTransaction(transacao);
		criarPedido.autExecME21N(parametros);
		
	}
	
	public AUTSAP01AbastecimentoEstoque() {
	
	}
}
