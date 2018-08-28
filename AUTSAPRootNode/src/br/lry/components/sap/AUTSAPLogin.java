package br.lry.components;

import com.borland.silktest.jtf.BaseState;
import com.borland.silktest.jtf.Control;

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

public class AUTSAPLogin extends junit.framework.TestCase{
	private Desktop desktop = new Desktop();

	@Before
	public void baseState() {
		BaseState baseState = new BaseState();
		baseState.execute(desktop);
	}

	
	public void autInitSAPApp() {		
		desktop.<Control>find("SAPConnections.ItensConfiguracao").textClick("Conexões");
		desktop.<ListView>find("SAPConnections.ListaConexoes").select("LEROY-ECQ");
		desktop.<PushButton>find("SAPConnections.Logon").click();
	}

	@Test
	public void autStartLoginDefault() {
		autInitSAPApp();
		desktop.<SapWindow>find("SAP").setActive();
		desktop.<SapTextField>find("SAP.Login.Usuario").setText("51028487");
		desktop.<SapTextField>find("SAP.Login.Senha").setText("Auto5@2020");
		desktop.<SapButton>find("SAP.Executar").select();
	}
}