/**
 * 
 */
package br.lry.process;

import org.junit.Test;

import br.lry.components.sap.AUTSAPBaseComponent;
import br.lry.components.sap.AUTSAPLogin;

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
	public void autStartProcess() {
		AUTSAPLogin login = new AUTSAPLogin();
		login.autStartSAPECQDefault();
		login.autStartTransaction(transacao);		
	}
	
	public AUTSAP01AbastecimentoEstoque() {
	
	}
}
