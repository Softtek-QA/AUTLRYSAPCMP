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
public class AUTZOSDGCP extends AUTSAPSession{

	public String AUT_NUMERO_ORDEM_REMESSA = null;
	public String AUT_NUMERO_ORDEM_TRANSPORTE = null;


	/**
	 * 
	 * MENSAGENS DE VALIDAÇÃO DO FLUXO
	 * 
	 * @author SOFTTEK-QA
	 *
	 */
	public enum AUT_MENSAGENS_VALIDACAO{
		CONFIRMACAO_ORDEM_TRANSPORTE;

		@Override
		public String toString() {	
			switch(this) {
			case CONFIRMACAO_ORDEM_TRANSPORTE:{
				return "(:i?confirmada)";
			}
			default:{
				this.name();
			}
			}

			return this.name();
		}
	}

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
		ORDEM_DE_VEND_CONSULTA,
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
			case ORDEM_DE_VEND_CONSULTA:{
				return ORDEM_DE_VEND.toString();
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
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").selectItem(option.toString(), "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").ensureVisibleHorizontalItem(option.toString(), "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").doubleSelectItem(option.toString(), "1");
		com.borland.silktest.jtf.Utils.sleep(4000);
		autSyncStateProcessExecution();
		autSelectItemGrid(option);
		autSyncStateProcessExecution();		
	}

	/**
	 * 
	 * Seleciona o menu de opções lateral na transação - ZOSD_GCP
	 * 
	 * @param option - AUT_ZOSD_GCP_MENU_LATERAL
	 * 
	 */
	public<TOption extends java.lang.Enum> void autSelectMenuItem(TOption option,java.util.HashMap<String,Object> parameters) {
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").selectItem(option.toString(), "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").ensureVisibleHorizontalItem(option.toString(), "1");
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").doubleSelectItem(option.toString(), "1");
		com.borland.silktest.jtf.Utils.sleep(4000);
		autSelectItemGrid(option,parameters);
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
		autSyncStateProcessExecution();
		switch((AUT_ZOSD_GCP_MENU_LATERAL)option) {
		case DOCUMENTO_DE_TRANSPORTE:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
			autSyncStateProcessExecution();
			break;
		}
		case ENTREGA:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
			autSyncStateProcessExecution();
			break;
		}
		case FATURAMENTO:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");

			break;
		}
		case ORDEM_DE_TRANSPORTE:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
			autSyncStateProcessExecution();
			break;
		}
		case ORDEM_DE_VEND:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");	
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").doubleClick(0, "BSTNK");
			Integer row = AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").getRowCount();
			String rowsSelect ="";
			for(Integer r = 0 ; r < row ;r++) {
				String conteudo = AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").getCellValue(r, "CHARG");
				if(conteudo.matches("\\w+")) {
					AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").doubleClick(r, "CHARG");		
					rowsSelect += String.format("  %s  ",r);					
				}
			}

			rowsSelect = rowsSelect.trim();
			rowsSelect = rowsSelect.replaceAll("  ", ",");
			
			for(Integer r = 0 ; r < row ;r++) {
				String conteudo = AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").getCellValue(r, "CHARG");
				if(conteudo.matches("\\w+")) {
					AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").setSelectedRows(r.toString());			
				}
			}

			AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").setSelectedRows(rowsSelect);
			
			//
			
			
			autSyncStateProcessExecution();
			break;
		}
		default:{

			System.out.println("AUT INFO : SAP : SELECT GRID MENU NOT IMPLEMENTED");

			break;
		}
		}		
	}


	public <TOption extends java.lang.Enum> void autSelectItemGrid(TOption option,java.util.HashMap<String,Object> parameters) {
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
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").doubleClick(0, "BSTNK");
			Integer row = AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").getRowCount();
			String rowsSelect ="";
			if(parameters.containsKey("AUT_FATURAR_ITENS_COM_LOTE")) {
				for(Integer r = 0 ; r < row ;r++) {
					String conteudo = AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").getCellValue(r, "CHARG");
					if(conteudo.matches("\\w+")) {
						AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").doubleClick(r, "CHARG");		
						rowsSelect += String.format("  %s  ",r);					
					}
				}
				
				rowsSelect = rowsSelect.trim();
				rowsSelect = rowsSelect.replaceAll("  ", ",");
				
				for(Integer r = 0 ; r < row ;r++) {
					String conteudo = AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").getCellValue(r, "CHARG");
					if(conteudo.matches("\\w+")) {
						AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").setSelectedRows(r.toString());			
					}
				}

				AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").setSelectedRows(rowsSelect);
				
			}
			else {
				AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").selectAll();
			}
			break;
		}
		case ORDEM_DE_VEND_CONSULTA:{
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");	
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").doubleClick(0, "BSTNK");
			Integer row = AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").getRowCount();
			String rowsSelect ="";
			if(parameters.containsKey("AUT_FATURAR_ITENS_COM_LOTE")) {
				for(Integer r = 0 ; r < row ;r++) {
					String conteudo = AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").getCellValue(r, "CHARG");
					if(conteudo.matches("\\w+")) {
						AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").doubleClick(r, "CHARG");		
						rowsSelect += String.format("  %s  ",r);					
					}
				}
				
				rowsSelect = rowsSelect.trim();
				rowsSelect = rowsSelect.replaceAll("  ", ",");
				
				for(Integer r = 0 ; r < row ;r++) {
					String conteudo = AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").getCellValue(r, "CHARG");
					if(conteudo.matches("\\w+")) {
						AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").setSelectedRows(r.toString());			
					}
				}

				AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").setSelectedRows(rowsSelect);
				
			}
			else {
				AUT_AGENT_SILK4J.<SapGridView>find("SAP.//SapGridView[@automationId='usr/cntlTREE_CONTAINER/shellcont/shell/shellcont[1]/shell/shellcont[1]/shell']").selectAll();
			}
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
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").pressToolbarContextButton(menu.toString());
		com.borland.silktest.jtf.Utils.sleep(3000);
		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").selectContextMenuItem(subMenu.toString());
		autSyncStateProcessExecution();
	}



	/**
	 * 
	 * Executa procedimentos para criação de remessa do SAP para o processo de faturamento
	 * 
	 * @return String - Retorna o numero da remessa gerada
	 * 
	 */
	public String autGerarOrdemRemessa() {
		com.borland.silktest.jtf.Utils.sleep(30 * 1000);
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
			} else {

			}
		} else {

		}
		autSyncStateProcessExecution();
		java.util.regex.Pattern regExp = java.util.regex.Pattern.compile("\\d+");
		java.util.regex.Matcher verifOrderRemessa = null;

		AUT_NUMERO_ORDEM_REMESSA = AUT_AGENT_SILK4J.<SapGridView>find("SAP.Popup.RemessaGeradaOutput")
				.getCellValue(0, "MSG").toString();
		autSyncStateProcessExecution();
		verifOrderRemessa = regExp.matcher(AUT_NUMERO_ORDEM_REMESSA);

		if (verifOrderRemessa.find()) {
			
			AUT_NUMERO_ORDEM_REMESSA = verifOrderRemessa.group();
			System.out.println(String.format("AUT INFO: NUMERO DE REMESSA GERADO: %s", AUT_NUMERO_ORDEM_REMESSA));
			AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();
			return AUT_NUMERO_ORDEM_REMESSA;
		} else {
			AUT_NUMERO_ORDEM_REMESSA = "ERRORGERARREMESSA";
			System.out.println("AUT ERROR: NAO FOI POSSIVEL GERAR NUMERO DE REMESSA DO PEDIDO INFORMADO");
		}

		if (AUT_AGENT_SILK4J.<SapWindow>find("SAP.Popup").exists("BotaoConfirmar", 4000)) {
			AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();
		}

		autSyncStateProcessExecution();

		
		return AUT_NUMERO_ORDEM_REMESSA;
	}

	
	
	public String autGerarOrdemRemessa(java.util.HashMap<String,Object> parameters) {
		if(parameters.containsKey("AUT_TEMPO_ESPERA_CRIACAO_REMESSA")) {
			Integer tempo = Integer.parseInt(parameters.get("AUT_TEMPO_ESPERA_CRIACAO_REMESSA").toString());
			com.borland.silktest.jtf.Utils.sleep(tempo * 1000);

		}
		
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").selectItem("REFRESH", "1");		

		autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ORDEM_DE_VEND,parameters);
		
		
		autSelectMenuComando(AUT_ZOSD_MENUS_COMANDO.MENU_0001_CRIAR_REMESSA,
				AUT_ZOSD_MENUS_COMANDO.SUB_MENU_0001_001_CRIAR_REMESSA);
		boolean confirmCriarRemessa = AUT_AGENT_SILK4J.<SapWindow>find("SAP").exists("Popup", 30000);
		if (confirmCriarRemessa) {
			boolean msgRemessa = AUT_AGENT_SILK4J.<BrowserWindow>find("SAP.Popup.Mensagens").exists("CriarRemessas",
					5000);
			if (msgRemessa) {
				AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoSim").select();
			} else {

			}
		} else {

		}
		autSyncStateProcessExecution();
		java.util.regex.Pattern regExp = java.util.regex.Pattern.compile("\\d+");
		java.util.regex.Matcher verifOrderRemessa = null;

		AUT_NUMERO_ORDEM_REMESSA = AUT_AGENT_SILK4J.<SapGridView>find("SAP.Popup.RemessaGeradaOutput")
				.getCellValue(0, "MSG").toString();
		autSyncStateProcessExecution();
		verifOrderRemessa = regExp.matcher(AUT_NUMERO_ORDEM_REMESSA);

		if (verifOrderRemessa.find()) {
			
			AUT_NUMERO_ORDEM_REMESSA = verifOrderRemessa.group();
			System.out.println(String.format("AUT INFO: NUMERO DE REMESSA GERADO: %s", AUT_NUMERO_ORDEM_REMESSA));
			AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();
			return AUT_NUMERO_ORDEM_REMESSA;
		} else {
			AUT_NUMERO_ORDEM_REMESSA = "ERRORGERARREMESSA";
			System.out.println("AUT ERROR: NAO FOI POSSIVEL GERAR NUMERO DE REMESSA DO PEDIDO INFORMADO");
		}

		if (AUT_AGENT_SILK4J.<SapWindow>find("SAP.Popup").exists("BotaoConfirmar", 4000)) {
			AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();
		}

		autSyncStateProcessExecution();

		
		return AUT_NUMERO_ORDEM_REMESSA;
		
	}

	public String autConsultarOVPedido(java.util.HashMap<String,Object> parameters) {
		if(parameters.containsKey("AUT_TEMPO_ESPERA_CRIACAO_REMESSA")) {
			Integer tempo = Integer.parseInt(parameters.get("AUT_TEMPO_ESPERA_CRIACAO_REMESSA").toString());
			com.borland.silktest.jtf.Utils.sleep(tempo * 1000);
		}
		
		AUT_AGENT_SILK4J.<SapTree>find("SAP.ZOSDGCP.MenusLaterais").selectItem("REFRESH", "1");		

		autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ORDEM_DE_VEND_CONSULTA,parameters);

		return "";
	}
	

	/**
	 * 
	 * Gera ordem de transporte relacionada o ordem de entrega do processo de faturamento
	 * 
	 * @return String - Retorna o número do Ordem de transporte gerada automaticamente pelo sistema
	 * 
	 */
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






	public void autConfirmaOTVisivel() {
		autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ORDEM_DE_TRANSPORTE);

		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").click(0, "TANUM");
		AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").setActive();
		AUT_AGENT_SILK4J.<SapMenu>find("SAPMenus.ConfirmacaoVisivel").select();
		AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").sendVKey(VKey.CTRL_S);		

		String msgConfirmOT = AUT_AGENT_SILK4J.<SapStatusbar>find("SAP.BarraStatus").getText();	
		autSyncStateProcessExecution();
		if(msgConfirmOT.matches(AUT_MENSAGENS_VALIDACAO.CONFIRMACAO_ORDEM_TRANSPORTE.toString())) {
			System.out.println("AUT INFO: CONFIRMACAO DE OT EXECUTADA COM SUCESSO");
			System.out.println(msgConfirmOT);
			autSyncStateProcessExecution();
		}
		else {
			System.out.println("AUT INFO: CONFIRMACAO DE OT FALHOU");
			System.out.println(msgConfirmOT);
			autSyncStateProcessExecution();
		}

		AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").closeSynchron();
	}

	public void autConfirmaOTVisivel(java.util.HashMap<String,Object> parameters) {
		autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ORDEM_DE_TRANSPORTE);
		if(parameters.containsKey("AUT_NUMERO_ORDEM_TRANSPORTE_SAP")) {
			String ot = parameters.get("AUT_NUMERO_ORDEM_TRANSPORTE_SAP").toString();
			Integer totItens = AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").getRowCount();
			for(Integer r = 0; r < totItens;r++) {
				String conteudo = AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").getCellValue(r, "TANUM");
				if(conteudo.matches(ot)) {
					AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").click(r, "TANUM");
				}
			}
		}
		else {			
			AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").click(0, "TANUM");
		}
		
		AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").setActive();
		AUT_AGENT_SILK4J.<SapMenu>find("SAPMenus.ConfirmacaoVisivel").select();
		AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").sendVKey(VKey.CTRL_S);		

		String msgConfirmOT = AUT_AGENT_SILK4J.<SapStatusbar>find("SAP.BarraStatus").getText();	
		autSyncStateProcessExecution();
		if(msgConfirmOT.matches(AUT_MENSAGENS_VALIDACAO.CONFIRMACAO_ORDEM_TRANSPORTE.toString())) {
			System.out.println("AUT INFO: CONFIRMACAO DE OT EXECUTADA COM SUCESSO");
			System.out.println(msgConfirmOT);
			autSyncStateProcessExecution();
		}
		else {
			System.out.println("AUT INFO: CONFIRMACAO DE OT FALHOU");
			System.out.println(msgConfirmOT);
			autSyncStateProcessExecution();
		}

		AUT_AGENT_SILK4J.<SapWindow>find("SAPMenus").closeSynchron();
	}


	/**
	 * 
	 * Faz o registro documento SD relaciondo ao faturamento em processamento do SAP
	 * 
	 * 
	 */
	public void autRegistrarSMDocSD() {
		autSyncStateProcessExecution();
		autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ORDEM_DE_TRANSPORTE);
		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").click(0, "VBELN");
		AUT_AGENT_SILK4J.exists(AUT_SAP_SESSIONS_ACTIVE_LOCATIONS_SLK.SESSION3.toString(),1000 * 170);
		AUT_AGENT_SILK4J.<SapButton>find(AUT_SAP_SESSIONS_ACTIVE_LOCATIONS_SLK.VL03N_BT_EDITAR.toString()).select();
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapButton>find(AUT_SAP_SESSIONS_ACTIVE_LOCATIONS_SLK.VL03N_BT_REGISTRAR_SM.toString()).select();
		autSyncStateProcessExecution();

	}


	public void autFatPedidoFluxoPadrao(java.util.HashMap<String, Object> parameters) {		
		String loja = parameters.get("AUT_LOJA").toString();
		String dataInicial = parameters.get("AUT_DATA_INICIAL").toString();
		String dataFinal = parameters.get("AUT_DATA_FINAL").toString();
		String numeroPedido = parameters.get("AUT_PEDIDO").toString();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Loja").setText(loja);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataInicialPedido").setText(dataInicial);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataFinalPedido").setText(dataFinal);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Pedido").setText(numeroPedido);
		AUT_AGENT_SILK4J.<SapButton>find("SAP.ZOSDGCPBotaoExecutar").select();
		autGerarOrdemRemessa(parameters);
		
		/*
		String remessa = autGerarOrdemRemessa();
		String numeroOT = autGerarOrdemTransporte();
		autConfirmaOTVisivel();		
		//autRegistrarSMDocSD();
		parametros.remove("AUT_DOCUMENTO_SD");
		parametros.put("AUT_DOCUMENTO_SD", remessa);
		*/
	}

	public void autIniciarConsultasGCP(java.util.HashMap<String, Object> parameters) {		
		String loja = parameters.get("AUT_LOJA").toString();
		String dataInicial = parameters.get("AUT_DATA_INICIAL").toString();
		String dataFinal = parameters.get("AUT_DATA_FINAL").toString();
		String numeroPedido = parameters.get("AUT_PEDIDO").toString();		
		autStartTransaction(this);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Loja").setText(loja);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataInicialPedido").setText(dataInicial);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataFinalPedido").setText(dataFinal);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Pedido").setText(numeroPedido);
		AUT_AGENT_SILK4J.<SapButton>find("SAP.ZOSDGCPBotaoExecutar").select();
		autConsultarOVPedido(parameters);
		
	}
	
	public void autIniciarFaturamentoGCP(java.util.HashMap<String, Object> parameters) {		
		String loja = parameters.get("AUT_LOJA").toString();
		String dataInicial = parameters.get("AUT_DATA_INICIAL").toString();
		String dataFinal = parameters.get("AUT_DATA_FINAL").toString();
		String numeroPedido = parameters.get("AUT_PEDIDO").toString();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Loja").setText(loja);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataInicialPedido").setText(dataInicial);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataFinalPedido").setText(dataFinal);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Pedido").setText(numeroPedido);
		AUT_AGENT_SILK4J.<SapButton>find("SAP.ZOSDGCPBotaoExecutar").select();
		String remessa = autGerarOrdemRemessa(parameters);
		if(remessa!=null && remessa.matches("\\d+")) {
			if(parameters.containsKey("AUT_NUMERO_REMESSA_SAP")) {
				parameters.remove("AUT_NUMERO_REMESSA_SAP");
				parameters.put("AUT_NUMERO_REMESSA_SAP", remessa);
			}
			else {
				parameters.put("AUT_NUMERO_REMESSA_SAP", remessa);			
			}
			
			if(parameters.containsKey("AUT_NUMERO_DOCUMENTO_FORNECIMENTO_SAP")) {
				parameters.remove("AUT_NUMERO_DOCUMENTO_FORNECIMENTO_SAP");
				parameters.put("AUT_NUMERO_DOCUMENTO_FORNECIMENTO_SAP", remessa);
			}
			else {
				parameters.put("AUT_NUMERO_DOCUMENTO_FORNECIMENTO_SAP", remessa);				
			}
		}
		
		String numeroOT = autGerarOrdemTransporte();
		
		if(numeroOT!=null && numeroOT.matches("\\d+")) {
			if(parameters.containsKey("AUT_NUMERO_ORDEM_TRANSPORTE_SAP")) {
				parameters.remove("AUT_NUMERO_ORDEM_TRANSPORTE_SAP");
				parameters.put("AUT_NUMERO_ORDEM_TRANSPORTE_SAP", numeroOT);
			}
			else {
				parameters.put("AUT_NUMERO_ORDEM_TRANSPORTE_SAP", numeroOT);
			}
		}
		autConfirmaOTVisivel();	
	
		
		
			
		//autRegistrarSMDocSD();
		
	}
	

	public void autConsultaStatusPedidoFaturado(java.util.HashMap<String, Object> parametros) {
		String loja = parametros.get("AUT_LOJA").toString();
		String dataInicial = parametros.get("AUT_DATA_INICIAL").toString();
		String dataFinal = parametros.get("AUT_DATA_FINAL").toString();
		String numeroPedido = parametros.get("AUT_PEDIDO").toString();
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Loja").setText(loja);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataInicialPedido").setText(dataInicial);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.DataFinalPedido").setText(dataFinal);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZOSDGCP.Pedido").setText(numeroPedido);
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapButton>find("SAP.ZOSDGCPBotaoExecutar").select();

		autSelectMenuItem(AUT_ZOSD_GCP_MENU_LATERAL.ORDEM_DE_VEND);
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setSelectedRows("0");
		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").selectColumn("FKSTK_TEXT");
		AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").setCurrentCellColumn("FKSTK_TEXT");
		String statusPedido = AUT_AGENT_SILK4J.<SapGridView>find("SAP.ZOSDGCP.TabelasRegistros").getCellValue(0, "FKSTK_TEXT");
		autSyncStateProcessExecution();
		System.out.println(String.format("AUT INFO : STATUS DO PEDIDO : SAP : %s",statusPedido));

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
		Boolean init = (parametros.get("INIT_TRANSACTION").toString()!="0" ? true : false);		
		if(init) {
			try {
				autSAPLogout();
			}
			catch(java.lang.Exception e) {

			}
			autSyncStateProcessExecution();
			autStartSAPECQDefault(parametros);
			autSyncStateProcessExecution();
			autStartTransaction(this);

			autFatPedidoFluxoPadrao(parametros);
		}
		else {
			autStartTransaction(this);
			autFatPedidoFluxoPadrao(parametros);
		}
	}

	public void autIniciarGestaoCarteiraPedidos(java.util.HashMap<String,Object> parametros){				
		autStartTransaction(this);
		autIniciarFaturamentoGCP(parametros);		
	}

	public void autConsultaItensOrdemVendaGCP(java.util.HashMap<String,Object> parametros){				
		autStartTransaction(this);
		autIniciarFaturamentoGCP(parametros);		
	}


	/**
	 * 
	 * Inicia a execução dos procedimentos de pagamento
	 * 
	 * @param parametros - Parametros de configuração da transação
	 * 
	 */
	public void autConsultaPedidoLoja(java.util.HashMap<String,Object> parametros){		
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
			autConsultaStatusPedidoFaturado(parametros);
		}
		else {
			autStartTransaction(this);
			autConsultaStatusPedidoFaturado(parametros);
		}
		
		com.borland.silktest.jtf.Utils.sleep(1000 * 40);
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
