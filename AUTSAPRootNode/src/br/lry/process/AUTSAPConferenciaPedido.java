package br.lry.process;

import com.microfocus.silktest.jtf.sap.SapButton;
import com.microfocus.silktest.jtf.sap.SapTextField;
import com.microfocus.silktest.jtf.sap.SapWindow;
import com.microfocus.silktest.jtf.sap.common.types.VKey;

import br.lry.components.sap.AUTSAPBaseComponent;

/**
 * 
 * Gerencia a interface para procedimentos de conferências de pedido
 * 
 * @author Softtek-QA
 *
 */
//public class AUTSAPConferenciaPedido extends AUTVL06 {
//	public void autInitProcess(java.util.HashMap<String,Object> parameters) {
//		autStartConf(parameters);
//	}
//	
//	public AUTSAPConferenciaPedido() {
//		// TODO Auto-generated constructor stub
//		super();
//	}
//
//}

public class AUTSAPConferenciaPedido extends AUTSAPBaseComponent{
		
	public void autConferenciaPedido(java.util.HashMap<String,Object> parameters) {
		
		String pedido = parameters.get("AUT_NUMERO_PEDIDO").toString();
		autStartTransaction("/nLM01");
		
		//Opc 1.Conferência
		AUT_AGENT_SILK4J.<SapButton>find("SAP.LM01SaidaMercadoria").select();
		com.borland.silktest.jtf.Utils.sleep(5000);
		
		//Opc 1.Saida Cliente
		AUT_AGENT_SILK4J.<SapButton>find("SAP.LM01SaidaMercadoria").select();
		com.borland.silktest.jtf.Utils.sleep(3000);
		
		//Opc 1.Saida Cliente
		AUT_AGENT_SILK4J.<SapButton>find("SAP.LM01SaidaMercadoria").select();
		com.borland.silktest.jtf.Utils.sleep(3000);
		
		//Número do Pedido
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.LM01.NumPedido").setText(pedido);
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.ENTER);
		com.borland.silktest.jtf.Utils.sleep(5000);
		
		//Número do Item
		String numItem = AUT_AGENT_SILK4J.<SapTextField>find("SAP.LM01.NumItemDe").getText();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.LM01.NumItemPara").setText(numItem);
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.ENTER);
		
		//Quantidade
		String qde = AUT_AGENT_SILK4J.<SapTextField>find("SAP.LM01.QdeDe").getText();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.LM01.QdePara").setText(qde);	
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.ENTER);
		
		AUT_AGENT_SILK4J.<SapButton>find("SAP.LM01.BotaoSim").select();	
	}
	
	public void autSaidaMercadoria(java.util.HashMap<String,Object> parameters) {
		String pedido = parameters.get("AUT_NUMERO_PEDIDO").toString();
		
		AUT_AGENT_SILK4J.<SapButton>find("SAP.LM01.BotaoSim").select();	
		
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.LM01.NumPedido").setText(pedido);
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.ENTER);
		
	}
	


}
