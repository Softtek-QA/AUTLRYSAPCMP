package br.lry.components.sap;

import com.borland.silktest.jtf.BaseState;
import com.borland.silktest.jtf.Desktop;
import com.microfocus.silktest.jtf.sap.SapOkCodeField;
import com.microfocus.silktest.jtf.sap.SapWindow;
import com.microfocus.silktest.jtf.sap.common.types.VKey;

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
