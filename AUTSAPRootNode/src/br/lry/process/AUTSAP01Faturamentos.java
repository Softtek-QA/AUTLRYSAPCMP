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
import br.lry.components.sap.AUTVF04;
import br.lry.components.sap.AUTZOSDGCP;

/**
 * 
 * Abastecimento de estoque
 * 
 * @author Softtek-QA
 *
 */
public class AUTSAP01Faturamentos extends AUTSAPSession {
	String transacao = "ZOSD_GCP";	
	public String AUT_DOCUMENTO_REMESSA;
	public String AUT_ORDEM_TRANSPORTE;
	public String AUT_DOCUMENTO_FATURADO;
	
	
	/**
	 * 
	 * Processo de inicialização do componente
	 * 
	 */
	public void autStartProcess(java.util.HashMap<String, Object> parametros) {		
		autStartTransaction(transacao);				
	}
	
	
	public void autFaturarPedido(java.util.HashMap<String,Object> prm,IAUTSAPProcessExecution process) {
		AUTZOSDGCP transPedido  = new AUTZOSDGCP();	
		AUTVF04 transFat = new AUTVF04();
		AUTSAPAtualizacaoUsuarioRF userUpdateRF = new AUTSAPAtualizacaoUsuarioRF();
		AUTSAPConferenciaPedido confPedido = new AUTSAPConferenciaPedido();
		
		
		try {
			transPedido.autSAPLogout();
		}
		catch(java.lang.Exception e) {
			
		}
		
		transPedido.autSetExecutionMonitor(process);
		transFat.autSetExecutionMonitor(process);		
				
			
		transPedido.autStartProcessGCP(prm);
	
		AUT_DOCUMENTO_REMESSA = transPedido.AUT_NUMERO_ORDEM_REMESSA;
		AUT_ORDEM_TRANSPORTE = transPedido.AUT_NUMERO_ORDEM_TRANSPORTE;
		
		transPedido.autSAPLogout();
		
		prm.remove("AUT_DOC_FORNECIMENTO");
		prm.put("AUT_DOC_FORNECIMENTO",AUT_DOCUMENTO_REMESSA);			
		
		try {
			transPedido.autSAPLogout();
		}
		catch(java.lang.Exception e) {
			
		}
		
		userUpdateRF.autAtualizaDadosUsuarioRF(prm);
		confPedido.autStartConf(prm);
		
		try {
			transPedido.autSAPLogout();
		}
		catch(java.lang.Exception e) {
			
		}
		
		transFat.autIniFaturamento(prm);
		AUT_DOCUMENTO_FATURADO = transFat.AUT_NUMERO_FATURA;
		transFat.autSAPLogout();
		transPedido.autConsultaPedidoLoja(prm);
		autSyncStateProcessExecution();
		transPedido.autSAPLogout();	
	}

	
	
	
	/**
	 * 
	 * Altera o valor do parametro de corrente de configuração da transação de faturamento
	 * 
	 */
	public void autSetParameter(String column,Object value) {
		if(AUT_SAP_RUNTIME_PARAMETERS.containsKey(column)) {
			AUT_SAP_RUNTIME_PARAMETERS.remove(column);
			AUT_SAP_RUNTIME_PARAMETERS.put(column, value);
		}
		else {
			AUT_SAP_RUNTIME_PARAMETERS.put(column, value);
		}
	}
	
	public Object autGetParameter(String column) {
		if(AUT_SAP_RUNTIME_PARAMETERS.containsKey(column)) {
			return AUT_SAP_RUNTIME_PARAMETERS.get(column);
		}
		else {
			return "ERROGETVALUE";
		}
	}

	
	public AUTSAP01Faturamentos() {
	
	}
}
