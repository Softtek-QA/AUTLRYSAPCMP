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

	String usuario = "51028487";
	String senha = "Auto5@2020";



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
		AUT_AGENT_SILK4J.<PushButton>find("SAPConnections.Logon").click();				
	}


	public void autStartLoginDefault() {
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
		autStartLoginDefault();
	}

	public void autSAPLogout() {
		try {

			boolean sapVisible = AUT_AGENT_SILK4J.exists("SAP",8000);
			
			
			if(sapVisible) {
				System.out.println("AUT SAP : LOGOUT SENDO EXECUTADO : EXISTE UMA  INSTANCIA DO SAP EM EXECUCAO");
				
				autStartTransaction("/n");

				/**
				if(sapVisible) {
					AUT_AGENT_SILK4J.<SapWindow>find("SAP").closeSynchron();
				}
				
				if(sapConsole) {
					AUT_AGENT_SILK4J.<Window>find("SAPConnections").closeSynchron();
				}
				**/
				
				java.lang.Runtime.getRuntime().exec("taskkill /f /t /im sap*");
				com.borland.silktest.jtf.Utils.sleep(8000);
			}
			else {
				System.out.println("AUT SAP : LOGOUT NAO EXECUTADO : NAO HA INSTANCIAS DO SAP EM EXECUCAO");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}