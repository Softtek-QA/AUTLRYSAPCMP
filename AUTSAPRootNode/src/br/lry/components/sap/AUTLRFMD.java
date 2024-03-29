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
import com.microfocus.silktest.jtf.sap.SapTextField;
import com.microfocus.silktest.jtf.sap.SapTree;
import com.microfocus.silktest.jtf.sap.SapWindow;
import com.microfocus.silktest.jtf.sap.common.types.VKey;
import com.microfocus.silktest.jtf.sap.SapMenu;
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
public class AUTLRFMD extends AUTSAPSession{
	public String AUT_NUMERO_FATURA = null;


	public enum AUT_TIPO_SELECAO{
		NUMERO_DEPOSITO,
		NUMERO_DOCUMENTO,
		NUMERO_PESSOA,
		ORDENACAO_OT,
		PERFIL_PERSONALIZADO,
		STATUS_USUARIO,
		TERMINAL,
		USUARIO;		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			switch(this) {
			case NUMERO_DEPOSITO:{
				return "Nº do depósito";
			}
			case NUMERO_DOCUMENTO:{
				return "Nº documento";
			}
			case NUMERO_PESSOA:{
				return "Nº pessoal";
			}
			case ORDENACAO_OT:{
				return "Ordenação OT";
			}
			case PERFIL_PERSONALIZADO:{
				return "Perfil personaliz.";
			}
			case STATUS_USUARIO:{
				return "Status usuário";
			}
			case TERMINAL:{
				return "Terminal";
			}
			case USUARIO:{
				return "Usuário";
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
	public void autInitProcess(java.util.HashMap<String,Object> parameters){		
		autInitAtualizacao(parameters);		
		try {
			autSAPLogout();
		}
		catch(java.lang.Exception e) {
			
		}
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
	 * Atualização de usuário RF - para loja específica
	 * 
	 * - Pré requisito para realizar a conferência de mercadoria -
	 * 
	 * @param parametros - Parametros de configuração da transação
	 * 
	 */
	public void autAtualizaDadosUsuarioRF(java.util.HashMap<String,Object> parametros) {
		autSyncStateProcessExecution();

		String usuario = parametros.get("AUT_USUARIO_SAP").toString();
		String loja = parametros.get("AUT_LOJA").toString();



	}

	public void autInitTest() {
		java.util.HashMap<String,Object> parameters = new java.util.HashMap<String,Object>();

		parameters.put("USER_SAP", "51028487");
		parameters.put("PWD_SAP", "Auto5@2020");
		parameters.put("AUT_LOJA", "035");
		parameters.put("AUT_FILA", "ARMAZENA");
		parameters.put("AUT_FORMATO", "16X20ITS");

		parameters.put("INIT_TRANSACTION", true);

		autInitAtualizacao(parameters);		
	}


	/**
	 * 
	 * Inicia a execução dos procedimentos de pagamento
	 * 
	 * @param parametros - Parametros de configuração da transação
	 * 
	 */
	public void autInitAtualizacao(java.util.HashMap<String,Object> parametros){		
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
			autSyncStateProcessExecution();
			autStartTransaction(this);			
		}
		else {
			autStartTransaction(this);
		}	
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapButton>find("SAP.LRFMDBotaoEditar").select();
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapMenu>find("SAP.LRFMDMenuSelecao").select();		
		autSyncStateProcessExecution();
		AUT_AGENT_SILK4J.<SapMenu>find("SAP.LRFMDMenuSelecao.MenuPorConteudo").select();
		autSyncStateProcessExecution();
		//AUT_AGENT_SILK4J.<SapTable>find("SAP.LRFMDOpcoesSelecao.Tabela").selectRow(5);
		SapTable tabela = AUT_AGENT_SILK4J.<SapTable>find("SAP.LRFMDOpcoesSelecao.Tabela");

		tabela.selectRow(tabela.getRowCount()-2);		
		autSyncStateProcessExecution();
		
		AUT_AGENT_SILK4J.<SapButton>find("SAP.Popup.BotaoConfirmar").select();	
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.LRFMDOpcoesSelecao.CxaUsuario").setText(parametros.get("USER_SAP").toString());		
		AUT_AGENT_SILK4J.<SapWindow>find("SAP.OpcoesMultiplasConexoes").sendVKey(VKey.F8);
		autSyncStateProcessExecution();
		SapTable tabSelectUsers = AUT_AGENT_SILK4J.find("SAP.LRFMDTabelaAtribuicaoUsuarios");
		int indexEnable = -1;

		for(int c = 0 ; c < tabSelectUsers.getRowCount() ; c++) {
			try {
				if((((SapTextField)tabSelectUsers.getCell(c, 1)).getText().trim().matches(parametros.get("USER_SAP").toString()))) {
					if(((SapTextField)tabSelectUsers.getCell(c, 0)).getText().trim().matches(parametros.get("AUT_LOJA").toString())){
						indexEnable = c;
					}
					else {
						//((SapCheckBox)tabSelectUsers.getCell(c, 3)).uncheck();				
						((SapCheckBox)tabSelectUsers.getCell(c, 3)).uncheck();				
					}
				}
			}
			catch(java.lang.Exception e) {
				break;
			}
		}

		if(indexEnable != -1) {
			AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.CTRL_S);
			autSyncStateProcessExecution();
			((SapCheckBox)tabSelectUsers.getCell(indexEnable, 3)).check();			
			AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.CTRL_S);
			autSyncStateProcessExecution();
		}
		else {
			AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.CTRL_S);

			AUT_AGENT_SILK4J.<SapButton>find("SAP.LRFMDNovasEntradasUsuario").select();
			autSyncStateProcessExecution();
			SapTable tabelaUsuarios = AUT_AGENT_SILK4J.<SapTable>find("SAP.LRFMDTabelaAtribuicaoUsuarios");

			int totRows = tabelaUsuarios.getRowCount();

			for(int r = 0; r < totRows; r++) {
				SapTextField loja = (SapTextField)tabelaUsuarios.getCell(r, 0);			

				if(loja.getText().trim().isEmpty()) {
					loja.setText(parametros.get("AUT_LOJA").toString());
					((SapTextField)tabelaUsuarios.getCell(r, 1)).setText(parametros.get("USER_SAP").toString());				
					((SapTextField)tabelaUsuarios.getCell(r, 2)).setText(parametros.get("AUT_FILA").toString());				
					((SapCheckBox)tabelaUsuarios.getCell(r, 3)).check();				
					((SapTextField)tabelaUsuarios.getCell(r, 4)).setText(parametros.get("AUT_FORMATO").toString());								
					AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.CTRL_S);
					tabelaUsuarios.selectRow(r);
					autSyncStateProcessExecution();
					
					break;
				}
			}
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
		return "LRFMD";
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
	public AUTLRFMD(){

	}

}
