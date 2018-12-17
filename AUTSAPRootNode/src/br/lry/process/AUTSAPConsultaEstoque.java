/**
 * 
 */
package br.lry.process;

import br.lry.components.sap.AUTMMBE;
import br.lry.components.sap.AUTSAPSession;
import br.lry.components.sap.AUTZMM0001;
import br.lry.components.sap.AUTZMM0075;

/**
 * 
 * Consulta estoque de material
 * 
 * @author Softtek-QA
 *
 */
public class AUTSAPConsultaEstoque extends AUTSAPSession {

	/**
	 * 
	 * Transação para consulta de material
	 * 
	 * 
	 * @param loja - Número da loja
	 * @param material - Material
	 * @return AUTMMBE - Transação de consulta do estoque
	 */
	public AUTMMBE autGetTransactionMMBE(String loja,String material) {
		try {
			System.out.println("SAP : PROCESS : ERRO CONSULTA DE ESTOQUE DO MATERIAL");

			return new AUTMMBE();
		}
		catch(java.lang.Exception e) {
			System.out.println("SAP : PROCESS : ERRO CONSULTA DE ESTOQUE DO MATERIAL");
			System.out.println(e.getMessage());
			e.printStackTrace();			
			return null;
		}
	}

	/**
	 * Retorna transação ZMM0075 para consulta de estoque
	 * 
	 * @param loja - Número da loja
	 * @param material - Código do material
	 * @return AUTZMM0075 - Transação para consulta de estoque do material
	 */
	public AUTZMM0075 autGetTransactionZMM0075(String loja,String material) {		
		try {
			System.out.println("SAP: PROCESS: INFO: CONSULTA DE ESTOQUE DO MATERIAL");		
			return new AUTZMM0075();
		}
		catch(java.lang.Exception e) {
			System.out.println("SAP: PROCESS: ERROR: CONSULTA DE ESTOQUE DO MATERIAL");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 
	 * Executa procedimentos para consulta de estoque
	 * 
	 * @param loja - Número da Loja
	 * @param material - Código do material
	 * 
	 * @return Integer - Saldo em estoque
	 */
	public Integer autGetStorageMaterial(String loja,String material) {
		try {
			AUT_AGENT_SILK4J = new com.borland.silktest.jtf.Desktop();
			AUT_AGENT_SILK4J_CONFIGURATION = new com.borland.silktest.jtf.BaseState("sap.settings");
			AUT_AGENT_SILK4J.executeBaseState(AUT_AGENT_SILK4J_CONFIGURATION);
			//autSAPLogout();
			autStartLoginDefault();

			java.util.HashMap<String,Object> parametros = new java.util.HashMap<String,Object>();
			parametros.put("AUT_CENTRO", loja);
			parametros.put("AUT_MATERIAL",material);
			Integer totalMMBE = 0;
			Integer totalZMM0075 = 0;

			if(prcMonitor!=null) {
				totalZMM0075 = Integer.parseInt(autGetTransactionZMM0075(loja, material).autExecZMM0075(parametros,autGetExecutionMonitor()).get("AUT_ESTOQUE").toString());
			}
			else {
				totalZMM0075 = Integer.parseInt(autGetTransactionZMM0075(loja, material).autExecZMM0075(parametros).get("AUT_ESTOQUE").toString());	
			}


			if(totalZMM0075 == 0) {
				if(prcMonitor!=null) {
					totalMMBE = Integer.parseInt(autGetTransactionMMBE(loja, material).autExecMMBE(parametros,prcMonitor).get("AUT_ESTOQUE").toString());				
				}
				else {
					totalMMBE = Integer.parseInt(autGetTransactionMMBE(loja, material).autExecMMBE(parametros).get("AUT_ESTOQUE").toString());									
				}
			}		
			autSAPLogout();
			return (totalZMM0075 > 0 ? totalZMM0075 : (totalMMBE > 0 ? totalMMBE : 0));
		}
		catch(java.lang.Exception e) {
			System.out.println("SAP: PROCESSO: ERRO: CONSULTA QUANTIDADE EM ESTOQUE");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return 0;
		}
	}
}
