package br.lry.components.sap;

import static org.junit.Assert.assertNotNull;

import com.borland.silktest.jtf.TestObject;
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
public class AUTZOSDGCP extends AUTSAPSession{

	public String AUT_NUMERO_ORDEM_REMESSA = null;
	public String AUT_NUMERO_ORDEM_TRANSPORTE = null;
	
	
	/**
	 * 
	 * Define os comandos para processos de faturamento relacionados a transação - ZOSD_GCP
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_ZOSD_MENUS_COMANDO{
	MENU_0001_CRIAR_REMESSA,
	MENU_0002_ENTREGA,
	SUB_MENU_0001_001_CRIAR_REMESSA,
	SUB_MENU_0001_002_CRIAR_OT_DE_RETORNO,
	SUB_MENU_0002_001_CRIAR_ORDEM_TRANSPORTE,
	SUB_MENU_0002_001_SAIDA_MERCADORIA_SM_MAIS_FATURAMENTO,
	SUB_MENU_0002_001_CRIAR_DOCUMENT_TRANSPORTE;	
		
		@Override
		public String toString() {
			switch(this) {
			case MENU_0001_CRIAR_REMESSA:{
				return "$MENU";
			}
			case MENU_0002_ENTREGA:{
				return "$MENU";
			}
			case SUB_MENU_0001_001_CRIAR_REMESSA:{
				return "CRIA_REMESSA";
			}
			case SUB_MENU_0001_002_CRIAR_OT_DE_RETORNO:{
				return "OT_RETORNO";
			}
			case SUB_MENU_0002_001_CRIAR_ORDEM_TRANSPORTE:{
				return "OT_REMESSA";
			}
			case SUB_MENU_0002_001_CRIAR_DOCUMENT_TRANSPORTE:{
				return "CRIA_DT";
			}
			case SUB_MENU_0002_001_SAIDA_MERCADORIA_SM_MAIS_FATURAMENTO:{
				return "SM+FT";
			}
			default:{
				return this.name();
			}
			}
		}
		
	
	}
	
	/**
	 * 
	 * 
	 * Define as opções de menus disponíveis para processos de faturamento relacionados a transação - ZOSD_GCP
	 * 
	 * 
	 * 
	 * @author Softtek-QA
	 *
	 */
	public enum AUT_ZOSD_GCP_MENU_LATERAL{
		ORDEM_DE_VEND,
		ENTREGA,
		ORDEM_DE_TRANSPORTE,
		FATURAMENTO,
		DOCUMENTO_DE_TRANSPORTE;
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			switch(this) {
			case DOCUMENTO_DE_TRANSPORTE:{
				return "DT";
			}
			case ENTREGA:{
				return "ENTREGA";
			}
			case FATURAMENTO:{
				return "FATURA";
			}
			case ORDEM_DE_TRANSPORTE:{
				return "OT";
			}
			case ORDEM_DE_VEND:{
				return "VENDA";
			}
			default:{
				return this.name();
			}
			}
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
	 * Seleciona o menu de opções lateral na transação - ZOSD_GCP
	 * 
	 * @param option - AUT_ZOSD_GCP_MENU_LATERAL
	 * 
	 */
	public<TOption extends java.lang.Enum> void autSelectMenuItem(TOption option) {
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").selectItem(option.toString(), "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").ensureVisibleHorizontalItem(option.toString(), "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").doubleSelectItem(option.toString(), "1");
		com.borland.silktest.jtf.Utils.sleep(4000);
		autSelectItemGrid(option);
				
	}
	
	public <TOption extends java.lang.Enum> void autSelectItemGrid(TOption option) {
	
		switch((AUT_ZOSD_GCP_MENU_LATERAL)option) {
		case DOCUMENTO_DE_TRANSPORTE:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
			
			break;
		}
		case ENTREGA:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
			
			break;
		}
		case FATURAMENTO:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
			
			break;
		}
		case ORDEM_DE_TRANSPORTE:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
			
			break;
		}
		case ORDEM_DE_VEND:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");	
			
			break;
		}
		default:{
			
			System.out.println("AUT INFO : SAP : SELECT GRID MENU NOT IMPLEMENTED");
			
			break;
		}
		}		
	}
	
	
	public <TOption extends java.lang.Enum> void autSelectMenuComando(TOption menu,TOption subMenu) {
		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").pressToolbarContextButton(menu.toString());
		com.borland.silktest.jtf.Utils.sleep(3000);
		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").selectContextMenuItem(subMenu.toString());
	}
	
	public String autGerarOrdemRemessa() {
		autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ORDEM_DE_VEND);		
		autSelectMenuComando(AUT_ZOSD_MENUS_COMANDO.MENU_0001_CRIAR_REMESSA,AUT_ZOSD_MENUS_COMANDO.SUB_MENU_0001_001_CRIAR_REMESSA);	
		boolean confirmCriarRemessa = AUT_AGENT_SILK4J.<SapWindow>find("SAP").exists("Popup",30000);
		if(confirmCriarRemessa) {
			boolean msgRemessa = AUT_AGENT_SILK4J.<BrowserWindow>find("SAP.Popup.Mensagens").exists("CriarRemessas", 5000);
			if(msgRemessa) {
				AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoSim").select();
			}
			else {		
				
			}
		}
		else {	
			
		}
		
		java.util.regex.Pattern regExp = java.util.regex.Pattern.compile("\\d+");
		java.util.regex.Matcher verifOrderRemessa = null;
		
		AUT_NUMERO_ORDEM_REMESSA = AUT_AGENT_SILK4J.<SapGridView>find("SAP.Popup.RemessaGeradaOutput").getCellValue(0, "MSG").toString();
		
		verifOrderRemessa = regExp.matcher(AUT_NUMERO_ORDEM_REMESSA);
		
		if(verifOrderRemessa.find()) {
			AUT_NUMERO_ORDEM_REMESSA = verifOrderRemessa.group();
			System.out.println(String.format("AUT INFO: NUMERO DE REMESSA GERADO: %s", AUT_NUMERO_ORDEM_REMESSA));
			AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();
			return AUT_NUMERO_ORDEM_REMESSA;
		}
		else {
			AUT_NUMERO_ORDEM_REMESSA = "ERRORGERARREMESSA";
			System.out.println("AUT ERROR: NAO FOI POSSIVEL GERAR NUMERO DE REMESSA DO PEDIDO INFORMADO");
		}
		
		if(AUT_AGENT_SILK4J.<SapWindow>find("SAP.Popup").exists("BotaoConfirmar", 4000)) {
			AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();			
		}
				
		return AUT_NUMERO_ORDEM_REMESSA;
	}

	
	public String autGerarOrdemTransporte() {
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

		AUT_NUMERO_ORDEM_TRANSPORTE = "ERRORGERARNUMEROOT";
		
		boolean outputGerado = AUT_AGENT_SILK4J.<SapWindow>find("SAP.Popup").exists("DadosSaidaOutput", 20000);
		if(outputGerado) {
			AUT_NUMERO_ORDEM_TRANSPORTE = AUT_AGENT_SILK4J.<SapGridView>find("SAP.Popup.DadosSaidaOutput").getCellValue(0, "MSG");
			
			java.util.regex.Pattern regExp = java.util.regex.Pattern.compile("\\d+");
			java.util.regex.Matcher verifItens = regExp.matcher(AUT_NUMERO_ORDEM_TRANSPORTE);
			if(verifItens.find()) {
				AUT_NUMERO_ORDEM_TRANSPORTE = verifItens.group();
				if(verifItens.find()) {
					AUT_NUMERO_ORDEM_REMESSA = verifItens.group();
					System.out.println(String.format("AUT INFO: ORDEM DE TRANSPORTE GERADA COM SUCESSO : %s : ORDEM REMESSA: %s",AUT_NUMERO_ORDEM_TRANSPORTE,AUT_NUMERO_ORDEM_REMESSA));
					AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();
					return AUT_NUMERO_ORDEM_TRANSPORTE;
				}
			}
		}

		return AUT_NUMERO_ORDEM_TRANSPORTE;
	}
	
	public void autFatPedidoFluxoPadrao(java.util.HashMap<String, Object> parametros) {
		String loja = parametros.get("AUT_LOJA").toString();
		String dataInicial = parametros.get("AUT_DATA_INICIAL").toString();
		String dataFinal = parametros.get("AUT_DATA_FINAL").toString();
		String numeroPedido = parametros.get("AUT_PEDIDO").toString();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Loja").setText(loja);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataInicialPedido").setText(dataInicial);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataFinalPedido").setText(dataFinal);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Pedido").setText(numeroPedido);
		AUT_AGENT_SILK4J.<SapButton>find("SAP.ZOSDGCPBotaoExecutar").select();
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").setActive();

		//String remessa = autGerarOrdemRemessa();
		//String numeroOT = autGerarOrdemTransporte();

		autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ORDEM_DE_TRANSPORTE);

		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").click(0, "TANUM");
		AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").setActive();
		AUT_AGENT_SILK4J.<SapMenu>find("SAPMenus.0001MenuOrdemTransporte").select();
		AUT_AGENT_SILK4J.<SapButton>find("SAPMenus.001SubMenu0001ConfirmarBackground").select();
		AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").sendVKey(VKey.F6);
		AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").sendVKey(VKey.CTRL_F3);
		
		
		
/**		
		//AUT_AGENT_SILK4J.<SapMenu>find("SAPMenus.ConfirmacaoVisivel").select();
		//AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").sendVKey(VKey.CTRL_S);
		//AUT_AGENT_SILK4J.<SapCheckBox>find("SAPMenus.CheckConfirmarOrdem").check();
		//end recording

		//start recording 
		AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").setActive();
		AUT_AGENT_SILK4J.<SapMenu>find("SAPMenus.0001MenuOrdemTransporte").select();
		AUT_AGENT_SILK4J.<SapButton>find("SAPMenus.001SubMenu0001ConfirmarBackground").select();
		AUT_AGENT_SILK4J.<SapCheckBox>find("SAPMenus.CheckConfirmarOrdem").check();
		AUT_AGENT_SILK4J.<SapCheckBox>find("SAPMenus.CheckConfirmarOrdem").setFocus();
		AUT_AGENT_SILK4J.<SapButton>find("SAPMenus.BotaoSalvar").select();
		//AUT_AGENT_SILK4J.<SapButton>find("SAPMenus.wnd 1.btnSPOP-OPTION1").select();
		//end recording
		 * 
		 */
		
	}
	
	
	/**
	 * 
	 * Inicia a execução dos procedimentos de pagamento
	 * 
	 * @param parametros - Parametros de configuração da transação
	 * 
	 */
	public void autStartProcessGCP(java.util.HashMap<String,Object> parametros){		
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
			autFatPedidoFluxoPadrao(parametros);
		}
		else {
			autStartTransaction(this);
			autFatPedidoFluxoPadrao(parametros);
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
	 *
	 *
	 *
	 */
	public void autStartEndToEndProcess(){



	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ZOSD_GCP";
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
	public AUTZOSDGCP(){

	}

}
