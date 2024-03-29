package br.lry.components.sap;

import com.microfocus.silktest.jtf.sap.SapComboBox;
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

	public enum AUT_ME21N_TIPOS_PEDIDO{
		COM_MANUAL_REVENDA,
		PEDIDO_DUMMY;
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			switch(this) {
			case COM_MANUAL_REVENDA:{
				return "ZCMA";
			}
			case PEDIDO_DUMMY:{
				return "";
			}
			}
			return super.toString();
		}
	}


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
public java.util.HashMap<String,Object> autExecME21NZCMA(java.util.HashMap<String,Object> parametros){	
	boolean init = (Boolean)parametros.get("INIT_TRANSACTION");
	
	if(init) {
		autStartTransaction(this.toString());
	}
		
	AUT_AGENT_SILK4J.<SapComboBox>find("SAP.ME21NTipoPedido").selectKey(AUT_ME21N_TIPOS_PEDIDO.COM_MANUAL_REVENDA.toString());
	AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.CTRL_F2);
	AUT_AGENT_SILK4J.<SapTab>find("SAP.ME21NTabLMB").select();
		
	return parametros;
}


/**
 * 
 * Sobrecarga do método toString padrão
 * 
 */
@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ME21N";
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
