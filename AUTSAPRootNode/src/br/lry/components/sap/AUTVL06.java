package br.lry.components.sap;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.borland.silktest.jtf.TestObject;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.xbrowser.BrowserWindow;
import com.borland.silktest.jtf.xbrowser.DomElement;
import com.microfocus.silktest.jtf.sap.SapButton;
import com.microfocus.silktest.jtf.sap.SapCheckBox;
import com.microfocus.silktest.jtf.sap.SapComponent;
import com.microfocus.silktest.jtf.sap.SapGridView;
import com.microfocus.silktest.jtf.sap.SapLabel;
import com.microfocus.silktest.jtf.sap.SapTextField;
import com.microfocus.silktest.jtf.sap.SapTree;
import com.microfocus.silktest.jtf.sap.SapWindow;
import com.microfocus.silktest.jtf.sap.common.types.VKey;
import com.microfocus.silktest.jtf.sap.SapMenu;
import com.microfocus.silktest.jtf.sap.SapMenubar;
import com.microfocus.silktest.jtf.sap.SapStatusbar;
import com.microfocus.silktest.jtf.sap.SapTable;
import com.borland.silktest.jtf.common.types.Point;

/** 
 *
 *
 * - LRFMD - ATUALIZAÇÃO DE USUÁRIO RF - PRÉ REQUISITO PARA USUÁRIOS QUE REALIZAM CONFERÊNCIA DE MERCADORIA                     
 *
 *
 *@author SOFTTEK-QA
 *
 *
 **/
public class AUTVL06 extends AUTSAPSession{	
	
	/**
	 *
	 *
	 *- Método principal de inicialização do processamento da transação
	 *
	 *
	 *
	 *
	 */
	public void autInitProcess(java.util.HashMap<String,Object> parameters){		
		autStartConf(parameters);		
	}



	/**
	 *
	 *
	 *- Método executa o fluxo de negócio principal no escopo da  transação
	 *
	 *
	 */
	public void autStartProcess(){

	}


	/**
	 * Atualização de usuário RF - para loja específica
	 * 
	 * - Pré requisito para realizar a conferência de mercadoria -
	 * 
	 * @param parametros - Parametros de configuração da transação
	 * 
	 */

	public void autInitTest() {
		java.util.HashMap<String,Object> parameters = new java.util.HashMap<String,Object>();
		parameters.put("USER_SAP", "51028487");
		parameters.put("PWD_SAP", "Auto5@2020");
		parameters.put("AUT_LOJA", "035");
		parameters.put("AUT_FILA", "ARMAZENA");
		parameters.put("AUT_FORMATO", "16X20ITS");
		parameters.put("INIT_TRANSACTION", true);
		autStartConf(parameters);		
	}


	/**
	 * 
	 * Inicia a execução dos procedimentos de pagamento
	 * 
	 * @param parametros - Parametros de configuração da transação
	 * 
	 */
	public void autStartConf(java.util.HashMap<String,Object> parametros){		
		parametros.get("USER_SAP");
		parametros.get("PWD_SAP");
		boolean init = (boolean)parametros.get("INIT_TRANSACTION");		
		if(init) {
			try {
				autSAPLogout();
				autSyncStateProcessExecution();
			}
			catch(java.lang.Exception e) {

			}
			autStartSAPECQDefault();
			autStartTransaction(this);			
		}
		else {
			autStartTransaction(this);
		}
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapButton>find("SAP.VL06ListaRemessasSaidaMercadoria").select();
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.F8);
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapLabel>find(String.format("SAP.//SapLabel[@caption='%s']",parametros.get("AUT_DOC_FORNECIMENTO").toString())).setFocus();
		AUT_AGENT_SILK4J.<SapMenu>find("SAP.VL06MenuFuncoesSubSequentes").highlightObject();
		AUT_AGENT_SILK4J.<SapMenu>find("SAP.VL06MenuFuncoesSubSequentes").select();
		AUT_AGENT_SILK4J.<SapMenu>find("SAP.VL06MenuFuncoesSubSequentes.RegistrarSaidaMercadoria").highlightObject();
		AUT_AGENT_SILK4J.<SapMenu>find("SAP.VL06MenuFuncoesSubSequentes.RegistrarSaidaMercadoria").select();
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();
		autSyncStateProcessExecution();	
		try {
			autSAPLogout();
		}
		catch(java.lang.Exception e) {
			
		}
	}


	/**
	 *
	 *
	 *- Método executa as rotinas de finalização do fluxo de negócio
	 *
	 *
	 *
	 *
	 */
	public void autEndProcess(){



	}




	/**
	 *
	 *
	 *- Método executa o fluxo de negócio End-To-End no escopo da transação
	 *
	 */
	public void autStartEndToEndProcess(){

	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "VL06";
	}


	/**
	 *
	 *
	 * - Construtor padrão da classe
	 *
	 *
	 *
	 *
	 */
	public AUTVL06(){

	}
}
