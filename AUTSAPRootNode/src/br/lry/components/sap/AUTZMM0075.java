package br.lry.components.sap;

import org.junit.Test;

import com.microfocus.silktest.jtf.sap.SapGridView;
import com.microfocus.silktest.jtf.sap.SapRadioButton;
import com.microfocus.silktest.jtf.sap.SapStatusbar;
import com.microfocus.silktest.jtf.sap.SapTextField;
import com.microfocus.silktest.jtf.sap.SapWindow;
import com.microfocus.silktest.jtf.sap.common.types.VKey;

/** 
 *
 *
 * - ZMM0075 - Monitor Consultas Gerias - CAR        
 *
 *
 *@author Softtek-QA
 *
 *
 **/
public class AUTZMM0075 extends AUTSAPSession{
	String transacao = "ZMM0075";
	String material = "87878700";
	String centro = "0045";
	int tentativas = 1;
	

	public enum AUT_ZMM0075_TIPO_CONSULTA{
		ESTOQUE_ON_HAND_CAR,
		ESTOQUE_TOTAL_CAR,
		ESTOQUE_ON__COM_NET_CAR;
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
		AUTMMBE mmbe = null;
		String estoqueItem = "";
		Object estoqueTotal;
		
		autStartSAPECQDefault();
		autStartTransaction(transacao);
		AUT_AGENT_SILK4J.<SapRadioButton>find("SAP.ZMM0075.EstoqueOnCarComNetCar").select();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZMM0075.Centro").setText(centro);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZMM0075.Material").setText(material);
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.F8);
		com.borland.silktest.jtf.Utils.sleep(3000);
		
		for(int i = 1;i<= tentativas;i++) {
			if(!AUT_AGENT_SILK4J.<SapStatusbar>find("SAP.BarraStatus").getText().matches(".{0,}erro.{0,}")) {
				SapGridView gridEstoques = AUT_AGENT_SILK4J.<SapGridView>find("SAP.MMBEDetalhesEstoque");
				estoqueTotal = gridEstoques.getCellValue(0, "ZZQTDNET");
				
				
				System.out.println(String.format("SAP : AUT INFO: SALDO ESTOQUE ITEM: %s : SALDO: %s : TIPO: %s",material,estoqueTotal.toString(),estoqueTotal.getClass().getSimpleName()));
				break;
			}
			else {
				AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.F8);	
				if(i==tentativas) {
					java.util.HashMap<String,Object> parametros = new java.util.HashMap<String,Object>();
					parametros.put("AUT_MATERIAL", material);
					parametros.put("AUT_CENTRO",centro);
					mmbe = new AUTMMBE();
					mmbe.autExecMMBE(parametros);
				}
			}
		}
	}

	public java.util.HashMap<String,Object> autInitProcess(java.util.HashMap<String,Object> parametros){
		AUTMMBE mmbe = null;
		String estoqueItem = "";
		Object estoqueTotal;
		String estoqueKey = "AUT_ESTOQUE";
		autStartSAPECQDefault();
		autStartTransaction(transacao);
		AUT_AGENT_SILK4J.<SapRadioButton>find("SAP.ZMM0075.EstoqueOnCarComNetCar").select();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZMM0075.Centro").setText(parametros.get("AUT_CENTRO").toString());
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZMM0075.Material").setText(parametros.get("AUT_MATERIAL").toString());
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.F8);
		com.borland.silktest.jtf.Utils.sleep(3000);
		
		for(int i = 1;i<= tentativas;i++) {
			if(!AUT_AGENT_SILK4J.<SapStatusbar>find("SAP.BarraStatus").getText().matches(".{0,}erro.{0,}")) {
				SapGridView gridEstoques = AUT_AGENT_SILK4J.<SapGridView>find("SAP.MMBEDetalhesEstoque");
				estoqueTotal = gridEstoques.getCellValue(0, "ZZQTDNET");	
				if(parametros.containsKey(estoqueKey)) {
					parametros.remove(estoqueKey);
					parametros.put("AUT_ESTOQUE", estoqueTotal);
				}
				else {
					parametros.put("AUT_ESTOQUE", estoqueTotal);
				}
				System.out.println(String.format("SAP : AUT INFO: SALDO ESTOQUE ITEM: %s : SALDO: %s : TIPO: %s",material,estoqueTotal.toString(),estoqueTotal.getClass().getSimpleName()));
				break;
			}
			else {
				AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.F8);	
				if(i==tentativas) {
					mmbe = new AUTMMBE();
					parametros = mmbe.autExecMMBE(parametros);
				}
			}
		}
		
		return parametros;
	}

	public <IAUTSAPInterface extends IAUTSAPProcessExecution> java.util.HashMap<String,Object> autInitProcess(java.util.HashMap<String,Object> parametros,IAUTSAPInterface processMonitor){
		processMonitor.autInitProcess();
		AUTMMBE mmbe = null;
		String estoqueItem = "";
		Object estoqueTotal;
		String estoqueKey = "AUT_ESTOQUE";
		autStartSAPECQDefault();
		processMonitor.autParallelProcess();
		autStartTransaction(transacao);
		processMonitor.autProcessExecution();
		AUT_AGENT_SILK4J.<SapRadioButton>find("SAP.ZMM0075.EstoqueOnCarComNetCar").select();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZMM0075.Centro").setText(parametros.get("AUT_CENTRO").toString());
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.ZMM0075.Material").setText(parametros.get("AUT_MATERIAL").toString());
		processMonitor.autProcessExecution();
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.F8);
		com.borland.silktest.jtf.Utils.sleep(3000);
		processMonitor.autProcessExecution();
		for(int i = 1;i<= tentativas;i++) {
			if(!AUT_AGENT_SILK4J.<SapStatusbar>find("SAP.BarraStatus").getText().matches(".{0,}erro.{0,}")) {
				SapGridView gridEstoques = AUT_AGENT_SILK4J.<SapGridView>find("SAP.MMBEDetalhesEstoque");
				processMonitor.autProcessExecution();
				gridEstoques.setSelectedRows("0");
				gridEstoques.setCurrentCell(0, "ZZQTDNET");
				processMonitor.autProcessExecution();
				estoqueTotal = gridEstoques.getCellValue(0, "ZZQTDNET");
				
				
				if(parametros.containsKey(estoqueKey)) {
					parametros.remove(estoqueKey);
					parametros.put("AUT_ESTOQUE", estoqueTotal);
					
					processMonitor.autProcessExecution();
				}
				else {
					parametros.put("AUT_ESTOQUE", estoqueTotal);
				}
				System.out.println(String.format("SAP : AUT INFO: SALDO ESTOQUE ITEM: %s : SALDO: %s : TIPO: %s",material,estoqueTotal.toString(),estoqueTotal.getClass().getSimpleName()));
				break;
			}
			else {
				AUT_AGENT_SILK4J.<SapWindow>find("SAP").sendVKey(VKey.F8);	
				processMonitor.autProcessExecution();
				if(i==tentativas) {
					mmbe = new AUTMMBE();
					parametros = mmbe.autExecMMBE(parametros,processMonitor);
				}
			}
		}
		
		return parametros;
	}
	
	
	
	
	
	public java.util.HashMap<String,Object> autExecZMM0075(java.util.HashMap<String,Object> parametros,IAUTSAPProcessExecution processMonitor){
		return autInitProcess(parametros,processMonitor);		
	}
	
	
	public java.util.HashMap<String,Object> autExecZMM0075(java.util.HashMap<String,Object> parametros){
		return autInitProcess(parametros);		
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

		autInitProcess();


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
	public AUTZMM0075(){
		super();
	}

}
