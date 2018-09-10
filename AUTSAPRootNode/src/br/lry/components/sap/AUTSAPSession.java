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
import com.microfocus.silktest.jtf.sap.SapTextField;
import com.microfocus.silktest.jtf.sap.SapTree;
import com.microfocus.silktest.jtf.sap.common.types.VKey;

import junit.framework.TestResult;

import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.PushButton;

public class AUTSAPSession extends AUTSAPBaseComponent{
	
	String usuario = "51028487";
	String senha = "Auto5@2020";
	
	
	
	@Before
	public void baseState() {
		AUT_AGENT_SILK4J_CONFIGURATION.execute(AUT_AGENT_SILK4J);
	}

	
	public void autInitSAPApp() {		
		AUT_AGENT_SILK4J.<Control>find("SAPConnections.ItensConfiguracao").textClick("Conexões");
		AUT_AGENT_SILK4J.<ListView>find("SAPConnections.ListaConexoes").select("LEROY-ECQ");
		AUT_AGENT_SILK4J.<PushButton>find("SAPConnections.Logon").click();
	}

	@Test
	public void autStartLoginDefault() {
		AUT_AGENT_SILK4J.<SapWindow>find("SAP").setActive();
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Usuario").setText(usuario);
		AUT_AGENT_SILK4J.<SapTextField>find("SAP.Login.Senha").setText(senha);
		AUT_AGENT_SILK4J.<SapButton>find("SAP.Executar").select();
	}
	
	public void autStartSAPECQDefault() {
		baseState();
		autInitSAPApp();
		autStartLoginDefault();
	}
	
	public void autSAPLogout() {
		try {
			java.lang.Runtime.getRuntime().exec("taskkill /f /t im sap*");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}