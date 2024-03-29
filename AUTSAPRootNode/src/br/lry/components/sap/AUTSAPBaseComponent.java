package br.lry.components.sap;

import com.borland.silktest.jtf.BaseState;
import com.borland.silktest.jtf.Desktop;
import com.microfocus.silktest.jtf.sap.SapOkCodeField;
import com.microfocus.silktest.jtf.sap.SapWindow;
import com.microfocus.silktest.jtf.sap.common.types.VKey;

import br.lry.components.sap.AUTSAPBaseComponent.IAUTSAPProcessExecution;

/** 
 *
 *
 * -  -   
 *
 *
 *@author Softtek-QA
 *
 *
 **/
public class AUTSAPBaseComponent{
	public Desktop AUT_AGENT_SILK4J = new Desktop();
	public BaseState AUT_AGENT_SILK4J_CONFIGURATION = new BaseState();
	public java.util.HashMap<String,Object> AUT_SAP_RUNTIME_PARAMETERS = new java.util.HashMap<String,Object>();
	protected IAUTSAPProcessExecution prcMonitor = null;

	/**
	 * 
	 * Interface para integração entre componentes automatizados
	 * 
	 * @author Softtek-QA
	 *
	 */
	public static interface IAUTSAPProcessExecution{
		
		/**
		 * 
		 * Método para inicialização da execução
		 * 
		 */
		public void autInitProcess();
		/**
		 * 
		 * Método para processamento - Principal no processo
		 * 
		 */
		public void autProcessExecution();
		/**
		 * 
		 * Metódo de finalização do processamento
		 * 
		 */
		public void autEndProcess();
		/**
		 * 
		 * Método para execução em paralelo - Rotinas de monitoramento dos processos
		 *
		 */
		public void autParallelProcess();
	}
	
	public static class AUTSapItemLoja{
		public String AUT_LOJA="045";
		public String AUT_FORNECEDOR="";
		public Integer AUT_QUANTIDADE=2000;
		public String AUT_GRUPO_COMPRADORES="0001";
		public String AUT_DEPOSITO_PADRAO="C050";
	}
	
	public void autSetHostExecutionService(String host) {
		AUT_AGENT_SILK4J = new Desktop(host);
	}
	
	/**
	 * Inicializa a transação especificada
	 * 
	 * @param transacao - Nome ou código da transação
	 * 
	 */
	public void autStartTransaction(String transacao) {
		System.out.println(String.format("SAP: AUT INFO: INICIALIZANDO TRANSACAO : %s",transacao));
		AUT_AGENT_SILK4J.<SapOkCodeField>find("SAP.InputTransacao").setText("/n");
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.ENTER);
		AUT_AGENT_SILK4J.<SapOkCodeField>find("SAP.InputTransacao").setText(transacao);
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.ENTER);
		
	}

	public <TObject extends Object> void autStartTransaction(TObject transacao) {
		autStartTransaction(transacao.toString());
	}


	/**
	 *
	 *
	 * - Construtor padrão da classe
	 *
	 *
	 *
	 **/
	public AUTSAPBaseComponent(){

	}

}
