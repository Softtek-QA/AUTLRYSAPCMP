package br.lry.components.sap;

import com.borland.silktest.jtf.TestObject;
import com.microfocus.silktest.jtf.sap.SapButton;
import com.microfocus.silktest.jtf.sap.SapCheckBox;
import com.microfocus.silktest.jtf.sap.SapTextField;
import com.microfocus.silktest.jtf.sap.SapTree;
import com.microfocus.silktest.jtf.sap.SapWindow;
import com.microfocus.silktest.jtf.sap.common.types.VKey;

/** 
 *
 *
 * - MMBE - VisÆo geral de estoques               
 *
 *
 *@author Softtek-QA
 *
 *
 **/
public class AUTMMBE extends AUTSAPSession{

	String transacao = "MMBE";
	String material = "87878700";
	String centro = "0045";
	String depositoLoja = "C050 Disponivel Loja";
	
	/**
	 *
	 *
	 *- Método principal de inicialização do processamento da transação
	 *
	 *
	 *
	 *
	 */
	public void autInitProcess() {
		autStartSAPECQDefault();
		autStartTransaction(transacao);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.MMBE.Centro").setText(centro);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.MMBE.Material").setText(material);
		AUT_AGENT_SILK4J.<SapCheckBox>find("SAP.MMBE.ApenasEstoqueComSaldo").check();
		
		AUT_AGENT_SILK4J.<SapButton>find("SAP.Executar").select();
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.F8);
		for(TestObject obj : AUT_AGENT_SILK4J.<SapTree>find("SAP.MMBE.DetalhesEstoques").getChildren()) {
			System.out.println(obj.getText());obj.getText();
		}	
	}

	public java.util.HashMap<String, Object> autExecMMBE(java.util.HashMap<String, Object> parametros) {
		String estoqueKey = "AUT_ESTOQUE";
		autStartTransaction(transacao);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.MMBE.Centro").setText(parametros.get("AUT_CENTRO").toString());
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.MMBE.Material").setText(parametros.get("AUT_MATERIAL").toString());
		AUT_AGENT_SILK4J.<SapCheckBox>find("SAP.MMBE.ApenasEstoqueComSaldo").check();

		AUT_AGENT_SILK4J.<SapButton>find("SAP.Executar").select();
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.F8);
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").setActive();
		
		AUT_AGENT_SILK4J.<SapTree>find("SAP.MMBE.DetalhesEstoques").selectColumn("C          1");
		//String saldoEstoque = AUT_AGENT_SILK4J.<SapTree>find("SAP.MMBE.DetalhesEstoques").getSelectedItemColumn();
		String saldoEstoque = "0";
		System.out.println(AUT_AGENT_SILK4J.<SapTree>find("SAP.MMBE.DetalhesEstoques").getSelectedItemColumn());			
		if(parametros.containsKey(estoqueKey)) {
			parametros.remove(estoqueKey);
			parametros.put("AUT_ESTOQUE", saldoEstoque);
		}
		else {
			parametros.put("AUT_ESTOQUE", saldoEstoque);
		}
				
		return parametros;


		//end recording
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
	/**
	 *
	 *
	 * - Construtor padrão da classe
	 *
	 *
	 *
	 *
	 */
	public AUTMMBE(){

	}

}
