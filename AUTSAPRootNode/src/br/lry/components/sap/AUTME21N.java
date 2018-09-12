package br.lry.components.sap;

import com.microfocus.silktest.jtf.sap.SapTab;
import com.microfocus.silktest.jtf.sap.SapWindow;
import com.microfocus.silktest.jtf.sap.common.types.VKey;

/** 
*
*
* - ME21N - Criar pedido                          
*
*
*@author Softtek-QA
*
*
**/
public class AUTME21N extends AUTSAPBaseComponent{



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
public java.util.HashMap<String,Object> autExecME21N(java.util.HashMap<String,Object> parametros){
	
	AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.SHIFT_F2);
	AUT_AGENT_SILK4J.<SapTab>find("SAP.ME21NTabLMB").select();
	
	return parametros;
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
public AUTME21N(){

}

}
