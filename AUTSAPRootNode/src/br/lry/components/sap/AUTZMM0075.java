package br.lry.components.sap;

import org.junit.Test;

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
public class AUTZMM0075 extends AUTSAPBaseComponent{
	String transacao = "ZMM0075";


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
		AUTSAPLogin login = new AUTSAPLogin();
		login.autStartSAPECQDefault();
		login.autStartTransaction(transacao);

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
