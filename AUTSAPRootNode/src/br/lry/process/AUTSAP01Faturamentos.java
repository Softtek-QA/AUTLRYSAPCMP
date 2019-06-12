/**
 * 
 */
package br.lry.process;

import org.junit.Assert;
import org.junit.Test;

import com.borland.silktest.jtf.xbrowser.BrowserWindow;
import com.borland.silktest.jtf.xbrowser.DomElement;
import com.microfocus.silktest.jtf.sap.SapButton;
import com.microfocus.silktest.jtf.sap.SapGridView;
import com.microfocus.silktest.jtf.sap.SapMenu;
import com.microfocus.silktest.jtf.sap.SapStatusbar;
import com.microfocus.silktest.jtf.sap.SapTextField;
import com.microfocus.silktest.jtf.sap.SapTree;
import com.microfocus.silktest.jtf.sap.SapWindow;
import com.microfocus.silktest.jtf.sap.common.types.VKey;

import br.lry.components.sap.AUTSAPBaseComponent;
import br.lry.components.sap.AUTSAPSession;
import br.lry.components.sap.AUTZOSDGCP.AUT_MENSAGENS_VALIDACAO;
import br.lry.components.sap.AUTZOSDGCP.AUT_ZOSD_GCP_MENU_LATERAL;
import br.lry.components.sap.AUTZOSDGCP.AUT_ZOSD_MENUS_COMANDO;

/**
 * 
 * Abastecimento de estoque
 * 
 * @author Softtek-QA
 *
 */
public class AUTSAP01Faturamentos extends AUTSAPSession {
//	String transacao = "ZOSD_GCP";	
//	public String AUT_DOCUMENTO_REMESSA;
//	public String AUT_ORDEM_TRANSPORTE;
//	public String AUT_DOCUMENTO_FATURADO;
//	
	
	public void autFiltroInicial(java.util.HashMap<String, Object> parametros) {
		autStartTransaction("/nZOSD_GCP");
		String loja = parametros.get("AUT_LOJA").toString();
		String dataInicial = parametros.get("AUT_DATA_INICIAL").toString();
		String dataFinal = parametros.get("AUT_DATA_FINAL").toString();
		String numeroPedido = parametros.get("AUT_NUMERO_PEDIDO").toString();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Loja").setText(loja);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataInicialPedido").setText(dataInicial);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataFinalPedido").setText(dataFinal);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Pedido").setText(numeroPedido);
		//autInsertScreenByScenario();
		
		AUT_AGENT_SILK4J.<SapButton>find("SAP.ZOSDGCPBotaoExecutar").select();
	}
	
	/**
	 * 
	 * Executa procedimentos para criação de remessa do SAP para o processo de faturamento
	 * 
	 * @return String - Retorna o numero da remessa gerada
	 * 
	 */
	public String autGerarOrdemRemessa() {
		String remessa;
		
		com.borland.silktest.jtf.Utils.sleep(20 * 1000);
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").selectItem("REFRESH", "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").ensureVisibleHorizontalItem("REFRESH", "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").doubleSelectItem("REFRESH", "1");

		autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ORDEM_DE_VEND);
		autSelectMenuComando(AUT_ZOSD_MENUS_COMANDO.MENU_0001_CRIAR_REMESSA,
				AUT_ZOSD_MENUS_COMANDO.SUB_MENU_0001_001_CRIAR_REMESSA);
		boolean confirmCriarRemessa = AUT_AGENT_SILK4J.<SapWindow>find("SAP").exists("Popup", 30000);
		if (confirmCriarRemessa) {
			boolean msgRemessa = AUT_AGENT_SILK4J.<BrowserWindow>find("SAP.Popup.Mensagens").exists("CriarRemessas",
					5000);
			if (msgRemessa) {
				AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoSim").select();
			} 
		}
		//autSyncStateProcessExecution();
		java.util.regex.Pattern regExp = java.util.regex.Pattern.compile("\\d+");
		java.util.regex.Matcher verifOrderRemessa = null;

		remessa = AUT_AGENT_SILK4J.<SapGridView>find("SAP.Popup.RemessaGeradaOutput")
				.getCellValue(0, "MSG").toString();
		//autSyncStateProcessExecution();
		//autInsertScreenByScenario();
		verifOrderRemessa = regExp.matcher(remessa);

		if (verifOrderRemessa.find()) {			
			remessa = verifOrderRemessa.group();
			System.out.println(String.format("AUT INFO: NUMERO DE REMESSA GERADO: %s", remessa));
			AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();
			return remessa;
		} else {
			remessa = "ERRORGERARREMESSA";
			System.out.println("AUT ERROR: NAO FOI POSSIVEL GERAR NUMERO DE REMESSA DO PEDIDO INFORMADO");
		}

		if (AUT_AGENT_SILK4J.<SapWindow>find("SAP.Popup").exists("BotaoConfirmar", 4000)) {
			AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();
		}

		//autSyncStateProcessExecution();
		
		return remessa;
	}
	
	/**
	 * 
	 * Gera ordem de transporte relacionada o ordem de entrega do processo de faturamento
	 * 
	 * @return String - Retorna o número do Ordem de transporte gerada automaticamente pelo sistema
	 * 
	 */
	public String autGerarOrdemTransporte(String numRemessa) {
		String numeroOT;
		
		autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ENTREGA);
		autSelectMenuComando(AUT_ZOSD_MENUS_COMANDO.MENU_0002_ENTREGA, AUT_ZOSD_MENUS_COMANDO.SUB_MENU_0002_001_CRIAR_ORDEM_TRANSPORTE);		
		boolean criarOrdemTransporte = AUT_AGENT_SILK4J.<SapWindow>find("SAP").exists("Popup", 30000);		
		if(criarOrdemTransporte) {
			boolean msgCriarOT = AUT_AGENT_SILK4J.<BrowserWindow>find("SAP.Popup.Mensagens").exists("CriarOrdemTransporte", 5000);
			if(msgCriarOT) {
				AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoSim").select();
			}
			else {
				System.out.println("AUT ERROR: NAO FOI POSSIVEL CRIAR ORDEM DE TRANSPORTE : SEM MENSAGEM DE CONFIGURACAO");
			}
		}
		else {
			System.out.println("AUT ERROR: NAO FOI POSSIVEL CRIAR ORDEM DE TRANSPORTE : SEM POPUP");
		}

		numeroOT = "ERRORGERARNUMEROOT";
		//autInsertScreenByScenario();
		
		boolean outputGerado = AUT_AGENT_SILK4J.<SapWindow>find("SAP.Popup").exists("DadosSaidaOutput", 20000);
		if(outputGerado) {
			numeroOT = AUT_AGENT_SILK4J.<SapGridView>find("SAP.Popup.DadosSaidaOutput").getCellValue(0, "MSG");

			java.util.regex.Pattern regExp = java.util.regex.Pattern.compile("\\d+");
			java.util.regex.Matcher verifItens = regExp.matcher(numeroOT);
			if(verifItens.find()) {
				numeroOT = verifItens.group();
				if(verifItens.find()) {
					numRemessa = verifItens.group();
					System.out.println(String.format("AUT INFO: ORDEM DE TRANSPORTE GERADA COM SUCESSO : %s : ORDEM REMESSA: %s",numeroOT,numRemessa));
					AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();
					return numeroOT;
				}
			}
		}

		return numeroOT;
	}

	public void autConfirmaOTVisivel() {
		String numDocSD;
		
		autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ORDEM_DE_TRANSPORTE);
		Integer totRows = AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").getRowCount();
	
		for(Integer row = 0;row < totRows;row++) {
			String confirmacao = AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").getCellValue(row, "KQUIT");
			String ot = AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").getCellValue(row, "TANUM");			
			String docSD = AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").getCellValue(row, "VBELN");
	
			if(!confirmacao.matches("(?i:C|X)")) {
				numDocSD=docSD;
				AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").click(row, "TANUM");
				AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").doubleClick(row, "TANUM");
				AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows(row.toString());
				break;
			}
		}
	
		AUT_AGENT_SILK4J.<SapMenu>find("SAPMenus.ConfirmacaoVisivel").select();
		AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").sendVKey(VKey.CTRL_S);		
	
		com.borland.silktest.jtf.Utils.sleep(6000);
		//autInsertScreenByScenario();
		
		String msgConfirmOT = AUT_AGENT_SILK4J.<SapStatusbar>find("SAP.BarraStatus").getText();	
		autSyncStateProcessExecution();
		if(msgConfirmOT.matches(AUT_MENSAGENS_VALIDACAO.CONFIRMACAO_ORDEM_TRANSPORTE.toString())) {
			System.out.println("AUT INFO: CONFIRMACAO DE OT EXECUTADA COM SUCESSO");
			System.out.println(msgConfirmOT);
			//autSyncStateProcessExecution();
		}
		else {
			System.out.println("AUT INFO: CONFIRMACAO DE OT FALHOU");
			System.out.println(msgConfirmOT);
			//autSyncStateProcessExecution();
		}
		
		com.borland.silktest.jtf.Utils.sleep(5000);
		AUT_AGENT_SILK4J.<SapButton>find("SAPMenus.BotaoVoltar").select();
		com.borland.silktest.jtf.Utils.sleep(8000);
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").selectItem("REFRESH", "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").ensureVisibleHorizontalItem("REFRESH", "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").doubleSelectItem("REFRESH", "1");
		
		//autInsertScreenByScenario();
	
		//AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").closeSynchron();
	}
	
	
	public boolean autValidaFaturamentoSAP() {
		
		try {
			
			String confirmacao;
			String statusSefaz;
			
			com.borland.silktest.jtf.Utils.sleep(20 * 1000);
			AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").selectItem("REFRESH", "1");
			AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").ensureVisibleHorizontalItem("REFRESH", "1");
			AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").doubleSelectItem("REFRESH", "1");

			autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ENTREGA);
			
			confirmacao = AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").getCellValue(0, "LVSTK");
			Assert.assertTrue(confirmacao.equals("C"));
			
			confirmacao = AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").getCellValue(0, "WBSTK");
			Assert.assertTrue(confirmacao.equals("C"));
			
			confirmacao = AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").getCellValue(0, "FKSTK");		
			Assert.assertTrue(confirmacao.equals("C"));		

			autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.FATURAMENTO);
			
			//Aguarda retorno da SEFAZ
			for (int x=1;x<=10;x++) {

				statusSefaz = AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").getCellValue(0, "DOCSTAT");
				
				if(statusSefaz.equals("")) {
					com.borland.silktest.jtf.Utils.sleep(30000);
					AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").selectItem("REFRESH", "1");
					AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").ensureVisibleHorizontalItem("REFRESH", "1");
					AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").doubleSelectItem("REFRESH", "1");
				} else {
					Assert.assertTrue(statusSefaz.equals("1"));	
					x=11;
				}	
			}
			
			return true;
		
		} catch (java.lang.Exception e) {
			e.getMessage();
			return false;
		}
	}

	/**
	 * Seleciona o menu de opções lateral na transação - ZOSD_GCP
	 * 
	 * @param option - AUT_ZOSD_GCP_MENU_LATERAL
	 * 
	 */
	public<TOption extends java.lang.Enum> void autSelectMenuItem(TOption option) {
		//autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").selectItem(option.toString(), "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").ensureVisibleHorizontalItem(option.toString(), "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").doubleSelectItem(option.toString(), "1");
		com.borland.silktest.jtf.Utils.sleep(4000);
		autSelectItemGrid(option);	
	}
	
	/**
	 * 
	 * Seleciona a linha default da grid de dados no processo de faturamento para transação atual
	 * 
	 * @param option - Opção do item de configuração
	 * 
	 * 
	 */
	public <TOption extends java.lang.Enum> void autSelectItemGrid(TOption option) {
		//autSyncStateProcessExecution();
		switch((AUT_ZOSD_GCP_MENU_LATERAL)option) {
		case DOCUMENTO_DE_TRANSPORTE:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
			//autSyncStateProcessExecution();
			break;
		}
		case ENTREGA:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
			//autSyncStateProcessExecution();
			break;
		}
		case FATURAMENTO:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
			break;
		}
		case ORDEM_DE_TRANSPORTE:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
			//autSyncStateProcessExecution();
			break;
		}
		case ORDEM_DE_VEND:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");	
			//autSyncStateProcessExecution();
			break;
		}
		default:{
			System.out.println("AUT INFO : SAP : SELECT GRID MENU NOT IMPLEMENTED");
			break;
		}
		}		
	}
	
	
	/**
	 * 
	 * Seleciono o menus específicos da transação de gestão de carteira dos pedidos
	 * 
	 * 
	 * @param menu - Objeto menu
	 * @param subMenu - Objeto sub menu
	 */
	public <TOption extends java.lang.Enum> void autSelectMenuComando(TOption menu,TOption subMenu) {
		//autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").pressToolbarContextButton(menu.toString());
		com.borland.silktest.jtf.Utils.sleep(3000);
		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").selectContextMenuItem(subMenu.toString());
		//autSyncStateProcessExecution();
	}
	
//	/**
//	 * 
//	 * Processo de inicialização do componente
//	 * 
//	 */
//	public void autStartProcess(java.util.HashMap<String, Object> parametros) {		
//		autStartTransaction(transacao);				
//	}
	
	
	public void autFaturarPedido(java.util.HashMap<String,Object> prm,IAUTSAPProcessExecution process) {
//		AUTZOSDGCP transPedido  = new AUTZOSDGCP();	
//		AUTVF04 transFat = new AUTVF04();
//		AUTSAPAtualizacaoUsuarioRF userUpdateRF = new AUTSAPAtualizacaoUsuarioRF();
//		AUTSAPConferenciaPedido confPedido = new AUTSAPConferenciaPedido();
//		
//		
////		try {
////			transPedido.autSAPLogout();
////		}
////		catch(java.lang.Exception e) {
////			
////		}
////		
////		transPedido.autSetExecutionMonitor(process);
////		transFat.autSetExecutionMonitor(process);		
//				
//			
//		transPedido.autStartProcessGCP(prm);
//	
//		AUT_DOCUMENTO_REMESSA = transPedido.AUT_NUMERO_ORDEM_REMESSA;
//		AUT_ORDEM_TRANSPORTE = transPedido.AUT_NUMERO_ORDEM_TRANSPORTE;
//		
//		transPedido.autSAPLogout();
//		
//		prm.remove("AUT_DOC_FORNECIMENTO");
//		prm.put("AUT_DOC_FORNECIMENTO",AUT_DOCUMENTO_REMESSA);			
//		
////		try {
////			transPedido.autSAPLogout();
////		}
////		catch(java.lang.Exception e) {
////			
////		}
//
////		userUpdateRF.autSetExecutionMonitor(process);
////		confPedido.autSetExecutionMonitor(process);
//
////		userUpdateRF.autInitAtualizacao(prm);
//		confPedido.autStartConf(prm);
//		
////		try {
////			transPedido.autSAPLogout();
////		}
////		catch(java.lang.Exception e) {
////			
////		}
//		
////		transFat.autIniFaturamento(prm);
//		AUT_DOCUMENTO_FATURADO = transFat.AUT_NUMERO_FATURA;
////		transFat.autSAPLogout();
//		transPedido.autConsultaPedidoLoja(prm);
////		autSyncStateProcessExecution();
//		transPedido.autSAPLogout();	
	}
//
//	
//	
//	
//	/**
//	 * 
//	 * Altera o valor do parametro de corrente de configuração da transação de faturamento
//	 * 
//	 */
//	public void autSetParameter(String column,Object value) {
//		if(AUT_SAP_RUNTIME_PARAMETERS.containsKey(column)) {
//			AUT_SAP_RUNTIME_PARAMETERS.remove(column);
//			AUT_SAP_RUNTIME_PARAMETERS.put(column, value);
//		}
//		else {
//			AUT_SAP_RUNTIME_PARAMETERS.put(column, value);
//		}
//	}
//	
//	public Object autGetParameter(String column) {
//		if(AUT_SAP_RUNTIME_PARAMETERS.containsKey(column)) {
//			return AUT_SAP_RUNTIME_PARAMETERS.get(column);
//		}
//		else {
//			return "ERROGETVALUE";
//		}
//	}
//
//	
//	public AUTSAP01Faturamentos() {
//	
//	}
}
