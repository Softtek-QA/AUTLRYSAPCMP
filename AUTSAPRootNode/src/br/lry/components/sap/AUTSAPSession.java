package br.lry.components.sap;

import com.borland.silktest.jtf.BaseState;
import com.borland.silktest.jtf.Control;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import com.borland.silktest.jtf.win32.ListView;
import com.borland.silktest.jtf.common.types.ClickType;
import com.microfocus.silktest.jtf.sap.SapWindow;
import com.microfocus.silktest.jtf.sap.SapButton;
import com.microfocus.silktest.jtf.sap.SapRadioButton;
import com.microfocus.silktest.jtf.sap.SapTextField;
import com.microfocus.silktest.jtf.sap.SapTree;
import com.microfocus.silktest.jtf.sap.SapUserArea;
import com.microfocus.silktest.jtf.sap.common.types.VKey;

import junit.framework.TestResult;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.PushButton;
import com.borland.silktest.jtf.Window;

public class AUTSAPSession extends AUTSAPBaseComponent{
	String usuario = "51024057";
	String senha = "Prince@10";

	public void autSyncStateProcessExecution() {
		if(autGetExecutionMonitor()!=null) {
			System.out.println("AUT INFO : SAP SYNC STATE PROCESS EXECUTION");
			autGetExecutionMonitor().autProcessExecution();
		}
	}

	public void autSetExecutionMonitor(IAUTSAPProcessExecution excMonitor) {
		prcMonitor = excMonitor;
	}

	public IAUTSAPProcessExecution autGetExecutionMonitor() {
		return prcMonitor;
	}
	
	public enum AUT_SAP_SESSIONS_ACTIVE_LOCATIONS_SLK{
		SESSION1,
		SESSION2,
		SESSION3,
		VL03N_BT_EDITAR,
		VL03N_BT_REGISTRAR_SM;
		@Override
		public String toString() {
			
			switch(this) {
			case SESSION1:{
				return "/SapWindow[@automationId='/app/con[0]/ses[0]/wnd[0]']";
			}	
			case SESSION2:{
				return "/SapWindow[@automationId='/app/con[0]/ses[1]/wnd[0]']";
			}
			case SESSION3:{
				return "/SapWindow[@automationId='/app/con[0]/ses[2]/wnd[0]']";
			}
			case VL03N_BT_EDITAR:{
				return String.format("%s//SapButton[@automationId='tbar[1]/btn[25]']", SESSION3.toString());
			}
			case VL03N_BT_REGISTRAR_SM:{
				return String.format("%s//SapButton[@automationId='tbar[1]/btn[20]']", SESSION3.toString());
			}
			default:{
				return this.name();
			}
			}
		}
	}
	
	public void baseState() {
		try {
			autSAPLogout();
		}
		catch(java.lang.Exception e) {

		}
		AUT_AGENT_SILK4J_CONFIGURATION.execute(AUT_AGENT_SILK4J);
	}


	public void autInitSAPApp() {				
		AUT_AGENT_SILK4J.<Control>find("SAPConnections.ItensConfiguracao").textClick("Conexões");
		AUT_AGENT_SILK4J.<ListView>find("SAPConnections.ListaConexoes").select("LEROY-ECQ");
		AUT_AGENT_SILK4J.<PushButton>find("SAPConnections.Logon").select();			
	}

	public void autStartLoginDefault(Boolean restartApp) {
		if(restartApp) {
			autInitSAPApp();
		}
		AUT_AGENT_SILK4J.exists("SAP", 1000 * 180);
		
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").setActive();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Usuario").setText(usuario);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Senha").setText(senha);
		
		AUT_AGENT_SILK4J.<SapButton>find("SAP.Executar").select();
		
		if(AUT_AGENT_SILK4J.<SapWindow>find("SAP").exists("OpcoesMultiplasConexoes",6000)) {
			AUT_AGENT_SILK4J.<SapRadioButton>find("SAP.OpcoesMultiplasConexoes.Multiconexao").select();
			AUT_AGENT_SILK4J.<SapWindow>find("SAP.OpcoesMultiplasConexoes").sendVKey(VKey.ENTER);
		}	
	}
	
	
	public void autStartLoginDefault() {
		autInitSAPApp();
		AUT_AGENT_SILK4J.exists("SAP", 1000 * 180);
		
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").setActive();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Usuario").setText(usuario);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Senha").setText(senha);
		
		AUT_AGENT_SILK4J.<SapButton>find("SAP.Executar").select();
		
		if(AUT_AGENT_SILK4J.<SapWindow>find("SAP").exists("OpcoesMultiplasConexoes",6000)) {
			AUT_AGENT_SILK4J.<SapRadioButton>find("SAP.OpcoesMultiplasConexoes.Multiconexao").select();
			AUT_AGENT_SILK4J.<SapWindow>find("SAP.OpcoesMultiplasConexoes").sendVKey(VKey.ENTER);
		}	
	}

	public void autStartLoginDefault(java.util.HashMap<String,Object> parameters) {
		AUT_AGENT_SILK4J.exists("SAP", 1000 * 180);
		
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").setActive();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Usuario").setText(parameters.get("USER_SAP").toString());
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Senha").setText(parameters.get("PWD_SAP").toString());
		
		AUT_AGENT_SILK4J.<SapButton>find("SAP.Executar").select();
		
		if(AUT_AGENT_SILK4J.<SapWindow>find("SAP").exists("OpcoesMultiplasConexoes",6000)) {
			AUT_AGENT_SILK4J.<SapRadioButton>find("SAP.OpcoesMultiplasConexoes.Multiconexao").select();
			AUT_AGENT_SILK4J.<SapWindow>find("SAP.OpcoesMultiplasConexoes").sendVKey(VKey.ENTER);
		}
	
	}
	public void autStartLoginDefault(IAUTSAPProcessExecution processoMonitor) {
		processoMonitor.autInitProcess();
		AUT_AGENT_SILK4J.exists("SAP", 1000 * 180);
		processoMonitor.autParallelProcess();
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").setActive();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Usuario").setText(usuario);
		processoMonitor.autProcessExecution();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Senha").setText(senha);
		AUT_AGENT_SILK4J.<SapButton>find("SAP.Executar").select();
		processoMonitor.autProcessExecution();
		
		if(AUT_AGENT_SILK4J.<SapWindow>find("SAP").exists("OpcoesMultiplasConexoes",6000)) {
			processoMonitor.autProcessExecution();
			AUT_AGENT_SILK4J.<SapRadioButton>find("SAP.OpcoesMultiplasConexoes.Multiconexao").select();
			processoMonitor.autProcessExecution();
			AUT_AGENT_SILK4J.<SapWindow>find("SAP.OpcoesMultiplasConexoes").sendVKey(VKey.ENTER);
			processoMonitor.autProcessExecution();
		}
	
		processoMonitor.autEndProcess();
	}
	
	public void autStartLogin(String usuario,String senha) {
		//AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Usuario").waitForProperty("Enabled", true,40000);
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").setActive();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Usuario").setText(usuario);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Senha").setText(senha);
		AUT_AGENT_SILK4J.<SapButton>find("SAP.Executar").select();

		if(AUT_AGENT_SILK4J.<SapWindow>find("SAP").exists("OpcoesMultiplasConexoes",6000)) {
			AUT_AGENT_SILK4J.<SapRadioButton>find("SAP.OpcoesMultiplasConexoes.Multiconexao").select();
			AUT_AGENT_SILK4J.<SapWindow>find("SAP.OpcoesMultiplasConexoes").sendVKey(VKey.ENTER);
		}
		
	}

	public void autStartSAPECQDefault() {
		baseState();
		autInitSAPApp();
		autStartLoginDefault(false);
	}

	public void autStartSAPECQDefault(java.util.HashMap<String,Object> parameters) {
		baseState();
		autInitSAPApp();
		autStartLoginDefault(parameters);
	}
	
	public void autSAPLogout() {
		try {
			java.lang.Runtime.getRuntime().exec("cmd /c taskkill /f /t /im sap*");
			com.borland.silktest.jtf.Utils.sleep(15000);
		} catch (IOException e) {
			com.borland.silktest.jtf.Utils.sleep(15000);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}