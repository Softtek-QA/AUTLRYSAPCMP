package br.lry.components.sap;

import static org.junit.Assert.assertNotNull;

import com.borland.silktest.jtf.TestObject;
import com.borland.silktest.jtf.Window;
import com.borland.silktest.jtf.xbrowser.BrowserWindow;
import com.borland.silktest.jtf.xbrowser.DomElement;
import com.microfocus.silktest.jtf.sap.SapButton;
import com.microfocus.silktest.jtf.sap.SapCheckBox;
import com.microfocus.silktest.jtf.sap.SapGridView;
import com.microfocus.silktest.jtf.sap.SapTextField;
import com.microfocus.silktest.jtf.sap.SapTree;
import com.microfocus.silktest.jtf.sap.SapWindow;
import com.microfocus.silktest.jtf.sap.common.types.VKey;
import com.microfocus.silktest.jtf.sap.SapMenu;
import com.microfocus.silktest.jtf.sap.SapStatusbar;
import com.borland.silktest.jtf.common.types.Point;

/** 
 *
 *
 * - ZOSD_GCP - TRANSAÇÃO PARA GESTÃO DA CARTEIRA DE PEDIDOS, EXECUTA PROCEDIMENTOS DE FATURAMENTO DO PEDIDO                     
 *
 *
 *@author SOFTTEK-QA
 *
 *
 **/
public class AUTVF04 extends AUTSAPSession{
	public String AUT_NUMERO_FATURA = null;

	/**
	 *
	 *
	 *- Método principal de inicialização do processamento da transação
	 *
	 *
	 *
	 *
	 */
	public void autInitProcess(){



	}



	/**
	 *
	 *
	 *- Método executa o fluxo de negócio principal no escopo da  transação
	 *
	 *
	 *
	 *
	 */
	public void autStartProcess(){



	}

	
	/**
	 * Gera fatura para documento SD
	 * 
	 * @param parametros - Parametros de configuração da transação
	 * 
	 */
	public void autFatPorDocSD(java.util.HashMap<String,Object> parametros) {
		autSyncStateProcessExecution();
		String docSD = parametros.get("AUT_DOCUMENTO_SD").toString();
		
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.VF04.DocumentoSD").setText(docSD);
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapButton>find("SAP.VF04BotaoExecutar").select();
		AUT_AGENT_SILK4J.<SapGridView>find("SAP.VF04.FaturasPedendes").setSelectedRows("0");
		AUT_AGENT_SILK4J.<SapButton>find("SAP.VF04BotaoFaturaIndividual").select();
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.CTRL_S);
		com.borland.silktest.jtf.Utils.sleep(6000);
		autSyncStateProcessExecution();
		AUT_NUMERO_FATURA = AUT_AGENT_SILK4J.<SapStatusbar>find("SAP.BarraStatus").getText();
		java.util.regex.Pattern regExp = java.util.regex.Pattern.compile("\\d+");
		java.util.regex.Matcher verifNumFat = regExp.matcher(AUT_NUMERO_FATURA);
		
		if(verifNumFat.find()) {
			AUT_NUMERO_FATURA = verifNumFat.group();
		}
		else {
			AUT_NUMERO_FATURA = "ERROFAT";
		}
		
		System.out.println(String.format("AUT INFO : SAP : FATURA GERADA : %s",AUT_NUMERO_FATURA));
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.CTRL_S);
		autSyncStateProcessExecution();
		
	}

	/**
	 * 
	 * Inicia a execução dos procedimentos de pagamento
	 * 
	 * @param parametros - Parametros de configuração da transação
	 * 
	 */
	public void autIniFaturamento(java.util.HashMap<String,Object> parametros){		
		parametros.get("USER_SAP");
		parametros.get("PWD_SAP");
		boolean init = (boolean)parametros.get("INIT_TRANSACTION");		
		if(init) {
			try {
				autSAPLogout();
			}
			catch(java.lang.Exception e) {
				
			}
			autStartSAPECQDefault();
			autStartTransaction(this);
			
		}
		else {
			autStartTransaction(this);
		}
		
		autFatPorDocSD(parametros);
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
	 *
	 *
	 *
	 */
	public void autStartEndToEndProcess(){



	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "VF04";
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
	public AUTVF04(){

	}

}
