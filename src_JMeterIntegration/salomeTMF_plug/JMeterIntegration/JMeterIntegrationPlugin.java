/*
 * SalomeTMF is a Test Management Framework
 * Copyright (C) 2005 France Telecom R&D
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * @author Fayçal SOUGRATI, Vincent Pautret, Marche Mikael
 *
 * Contact: mikael.marche@rd.francetelecom.com
 */

package salomeTMF_plug.JMeterIntegration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;

import org.java.plugin.ExtensionPoint;
import org.java.plugin.Plugin;
import org.java.plugin.PluginDescriptor;
import org.java.plugin.PluginManager;
import org.objectweb.salome_tmf.api.Api;
import org.objectweb.salome_tmf.api.ApiConstants;
import org.objectweb.salome_tmf.api.Util;
import org.objectweb.salome_tmf.data.Attachment;
import org.objectweb.salome_tmf.data.AutomaticTest;
import org.objectweb.salome_tmf.data.Family;
import org.objectweb.salome_tmf.data.Project;
import org.objectweb.salome_tmf.data.Script;
import org.objectweb.salome_tmf.data.SimpleData;
import org.objectweb.salome_tmf.data.Test;
import org.objectweb.salome_tmf.data.TestList;
import org.objectweb.salome_tmf.ihm.main.SalomeTMFPanels;
import org.objectweb.salome_tmf.ihm.main.datawrapper.DataModel;
import org.objectweb.salome_tmf.ihm.tools.Tools;
import org.objectweb.salome_tmf.plugins.IPlugObject;
import org.objectweb.salome_tmf.plugins.core.Common;
import org.objectweb.salome_tmf.plugins.core.ReqManager;
import org.objectweb.salome_tmf.plugins.core.TestDriver;

import salomeTMF_plug.JMeterIntegration.languages.Language;

public class JMeterIntegrationPlugin extends Plugin implements TestDriver,
		Common {

	String url_txt = null;

	String id_plugin;

	String MSG_ERR_EDIT = Language.getInstance().getText(
			"Erreur_lors_de_l_ouverture_du_fichier_jMeter_de_tests");
	String MSG_ERR_WRITE = Language.getInstance().getText(
			"Erreur_lors_de_l_ecriture_du_fichier_de_tests_jMeter");
	String MSG_ERR_CREATE = Language.getInstance().getText(
			"Erreur_lors_de_la_recherche_des_methodes_de_tests");

	/**** CLASS UTIL ****/
	// IJunitAnalyser currentJunitAnalyser;

	private IPlugObject pIPlugObject;

	/** Creates a new instance of JMeterIntegrationPlugin */
	public JMeterIntegrationPlugin(PluginManager manager, PluginDescriptor descr) {
		super(manager, descr);
		id_plugin = descr.getId() + ".TestDriver";
	}

	/********************* extends Plugin **********************************/

	/**
	 * @see org.java.plugin.Plugin()
	 */
	protected void doStart() throws Exception {
		Util.log("[JMeterIntegrationPlugin:doStart] chargement du plugin");
	}

	/**
	 * @see org.java.plugin.Plugin()
	 */
	protected void doStop() throws Exception {
		// no-op
	}

	/*********************** Interface TestDriver ****************************/

	/**
	 * @see salome.plugins.core.TestDriver
	 */
	public void initTestDriver(URL urlSalome) {
		String _urlSalome = urlSalome.toString();
		url_txt = _urlSalome.substring(0, _urlSalome.lastIndexOf("/"));
	}

	/**
	 * @see salome.plugins.core.TestDriver
	 */
	public void getHelp() {
		Util.log("JMeter TestDriver");
	}

	/**
	 * @see salome.plugins.core.TestDriverpTestResult
	 */
	public String getDefaultTestDiverAgument() {
		return "";
	}

	/**
	 * @see salome.plugins.core.TestDriver
	 */
	public String modifyTestDiverAgument(String oldArg) {
		return "";
	}

	/**
	 * @see salome.plugins.core.TestDriver
	 */
	public int runTest(String testScript,
			org.objectweb.salome_tmf.data.AutomaticTest pTest,
			Hashtable argTest, String plugArg) throws Exception {
		File pFile = new File(testScript);
		FileInputStream pFileInputStream = new FileInputStream(pFile);
		Properties test_prop = new Properties();
		test_prop.load(pFileInputStream);
		String strID = test_prop.getProperty("attachment_id");
		String strAttachment = test_prop.getProperty("attachment_name");
		String strClass = test_prop.getProperty("junit_class");
		String meth = test_prop.getProperty("test_meth");
		String version = test_prop.getProperty("junit_version");
		pFileInputStream.close();
		Util.log("[JMeterIntegrationPlugin:runTest] attachment_id = " + strID);
		Util.log("[JMeterIntegrationPlugin:runTest] attachment_name = "
				+ strAttachment);
		Util.log("[JMeterIntegrationPlugin:runTest] junit_class = " + strClass);
		Util.log("[JMeterIntegrationPlugin:runTest] test_meth = " + meth);
		Util.log("[JMeterIntegrationPlugin:runTest] junit_version = " + version);

		return 0; // currentJunitAnalyser.runTest(strID, strAttachment,
					// strClass, meth, testScript, pTest, argTest, plugArg);
	}

	/**
	 * @see salome.plugins.core.TestDriver
	 */
	public String getTestLog() {
		/*
		 * if (currentJunitAnalyser != null){ return
		 * currentJunitAnalyser.getTestLog(); }
		 */
		return "";
	}

	/**
	 * @see salome.plugins.core.TestDriver
	 */
	public void stopTest() throws Exception {
		/*
		 * if (currentJunitAnalyser != null){ currentJunitAnalyser.stopTest(); }
		 */

	}

	/**
	 * Odre d'edition du test
	 */
	public void editTest(String testScript,
			org.objectweb.salome_tmf.data.AutomaticTest pTest, Hashtable arg,
			String plugParam) throws Exception {
		try {
			File pFile = new File(testScript);
			FileInputStream pFileInputStream = new FileInputStream(pFile);
			Properties test_prop = new Properties();
			test_prop.load(pFileInputStream);
			// Utile.getPropertiesFile(testScript);
			String strID = test_prop.getProperty("attachment_id");
			String strAttachment = test_prop.getProperty("attachment_name");
			String strClass = test_prop.getProperty("junit_class");
			String meth = test_prop.getProperty("test_meth");
			String version = test_prop.getProperty("junit_version");
			pFileInputStream.close();

			File script = ChoiceOrEdit(pTest, false, pFile, strID,
					strAttachment, strClass, meth, version);
			if (script != null) {
				Script pScript = pTest.getScriptFromModel();
				pScript.updateInDB(script);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Tools.ihmExceptionView(e);
		}
	}

	public void updateTestScriptFromImport(String testScript,
			org.objectweb.salome_tmf.data.AutomaticTest pTest) {
		updateTestScript(testScript, pTest);
	}

	public String updateTestScript(String testScript,
			org.objectweb.salome_tmf.data.AutomaticTest pTest) {
		String strNewId = "";
		try {

			File pFile = new File(testScript);
			FileInputStream pFileInputStream = new FileInputStream(pFile);
			Properties test_prop = new Properties();
			test_prop.load(pFileInputStream);
			// Utile.getPropertiesFile(testScript);
			String strID = test_prop.getProperty("attachment_id");
			String strAttachment = test_prop.getProperty("attachment_name");
			String strClass = test_prop.getProperty("junit_class");
			String meth = test_prop.getProperty("test_meth");
			String version = test_prop.getProperty("junit_version");
			pFileInputStream.close();

			Hashtable tabAttach = new Hashtable(DataModel.getCurrentProject()
					.getAttachmentMapFromModel());
			Enumeration e = tabAttach.elements();
			boolean trouve = false;
			while (e.hasMoreElements() && !trouve) {
				Attachment pAttach = (Attachment) e.nextElement();
				if (pAttach.getDescriptionFromModel().equals(
						JMeterTester.jMeterDescAttach)
						&& pAttach.getNameFromModel().equals(strAttachment)) {
					strNewId = "" + pAttach.getIdBdd();
					trouve = true;
				}
			}
			// ADD PROJECT PATH

			String toWrite = "attachment_id = " + strNewId + "\n";
			toWrite += "attachment_name = " + strAttachment + "\n";
			toWrite += "junit_class = " + strClass + "\n";
			toWrite += "test_meth = " + meth + "\n";
			toWrite += "junit_version = " + version + "\n";
			File newFile = writeJunitFile(pFile, pTest, toWrite);
			Script pScript = pTest.getScriptFromModel();
			pScript.updateInDB(newFile);

		} catch (Exception e) {

		}
		return strNewId;
	}

	/**
	 * Choix d'un test
	 * 
	 * @return le test choisi
	 */
	public java.io.File choiceTest(
			org.objectweb.salome_tmf.data.AutomaticTest pTest) {
		return ChoiceOrEdit(pTest, true, null, "", "", "", "", "");
	}

	public void onDeleteTestScript(
			org.objectweb.salome_tmf.data.AutomaticTest pTest) {

	}

	java.io.File ChoiceOrEdit(AutomaticTest pTest, boolean choice, File pFile,
			String strID, String strAttachment, String strClass, String meth,
			String version) {
		JMeterTester pJMeterTester = new JMeterTester(null, true, pTest, choice,
				strID, strAttachment, strClass, meth, version);
		if (pJMeterTester.execute()) {
			Util.log("[JMeterIntegrationPlugin:editTest] ok pressed");
			Attachment pNewattach = pJMeterTester.getNewAttachment();
			Attachment pSelectedattach = pJMeterTester.getSelectedAttachment();
			String toWrite = "attachment_id = ";
			if (pSelectedattach != null) {
				if (pSelectedattach.equals(pNewattach)) {
					if (!addAttachmentToProject(pNewattach,
							DataModel.getCurrentProject()))
						return null;
				}
				toWrite += pSelectedattach.getIdBdd() + "\n";
				toWrite += "attachment_name = "
						+ pSelectedattach.getNameFromModel() + "\n";
			} else {
				toWrite += "\nattachment_name = \n";
			}
			toWrite += "junit_class = " + pJMeterTester.getJunitClass() + "\n";
			toWrite += "test_meth = " + pJMeterTester.getJunitMeth() + "\n";
			if (version != null) {
				toWrite += "junit_version = " + version + "\n";
				version = version.trim();

			}
			try {
				File f = writeJunitFile(pFile, pTest, toWrite);
				return f;
			} catch (Exception e) {
				Tools.ihmExceptionView(e);
				e.printStackTrace();
				return null;
			}
		} else {
			Util.log("[JMeterIntegrationPlugin:editTest] cancel pressed");
			return null;
		}
	}

	File writeJunitFile(File pFile,
			org.objectweb.salome_tmf.data.AutomaticTest pTest, String toWtrite)
			throws Exception {
		String fileName;
		if (pFile == null) {
			Properties sys = System.getProperties();
			String tmpDir = sys.getProperty("java.io.tmpdir");
			String fs = sys.getProperty("file.separator");
			fileName = tmpDir + fs + pTest.getNameFromModel() + ".junit";
			pFile = new File(fileName);
		} else {
			fileName = pFile.getAbsolutePath();
			pFile.delete();
			pFile.createNewFile();
		}
		Util.log("[JMeterIntegrationPlugin:writeJunitFile] write " + toWtrite
				+ ", in " + fileName);
		FileOutputStream fos = new FileOutputStream(pFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		byte[] b = toWtrite.getBytes();
		bos.write(b, 0, b.length);
		bos.flush();
		bos.close();
		// return null;
		return pFile;
	}

	boolean addAttachmentToProject(Attachment pAttach, Project pProject) {
		if (Api.isConnected()) {
			try {
				pProject.addAttachementInDBAndModel(pAttach);
				Util.log("[JMeterIntegrationPlugin:addAttachmentToSuite] add attach in DB & model : "
						+ pAttach);

			} catch (Exception exception) {
				Tools.ihmExceptionView(exception);
				return false;
			}
		}

		return true;
	}

	void createJMeterSuitePerformed() {
		Project pProject = DataModel.getCurrentProject();
		TestList pTestList = null;
		Family pFamily = null;
		Test pTest = null;
		DefaultMutableTreeNode pNode = SalomeTMFPanels.getTestDynamicTree()
				.getSelectedNode();
		if (pNode == null) {
			return;
		} else {
			SimpleData pData = (SimpleData) pNode.getUserObject();
			if (pData instanceof Family) {
				pFamily = (Family) pData;
			} else if (pData instanceof TestList) {
				pTestList = (TestList) pData;
			} else if (pData instanceof Test) {
				pTest = (Test) pData;
			}
		}

		if (pTestList != null) {
			Util.log("Test List selected = " + pTestList.getNameFromModel());
			JMeterTester pMeterTester = new JMeterTester(null, true, pProject,
					true, "", "", "", "");
			if (pMeterTester.execute()) {
				Util.log("[JMeterIntegrationPlugin:editTest] ok pressed");
				Attachment pNewattach = pMeterTester.getNewAttachment();
				Attachment pSelectedattach = pMeterTester
						.getSelectedAttachment();
				String toWrite = "attachment_id = ";
				if (pSelectedattach != null) {
					if (pSelectedattach.equals(pNewattach)) {
						if (!addAttachmentToProject(pNewattach,
								DataModel.getCurrentProject()))
							return;
					}
					toWrite += pSelectedattach.getIdBdd() + "\n";
					toWrite += "attachment_name = "
							+ pSelectedattach.getNameFromModel() + "\n";
				} else {
					toWrite += "\nattachment_name = \n";
				}
				toWrite += "junit_class = " + pMeterTester.getJunitClass()
						+ "\n";
				
				try {
					if (!pTestList.isInBase()) {
						pTestList.updateInModel(pMeterTester.getJunitClass(),
								"JMETER TEST SUITE");
						pFamily.addTestListInDB(pTestList);
					}
					//currentJunitAnalyser.createJunitTest(pSelectedattach,
					//		pJunitTester.getJunitClass(), pTestList, toWrite);
				} catch (Exception e1) {
					Tools.ihmExceptionView(e1);
				}

			}
		}
	}

	void createBeanshellJunitSuitePerformed() {
		Project pProject = DataModel.getCurrentProject();
		TestList pTestList = null;
		Family pFamily = null;
		Test pTest = null;
		DefaultMutableTreeNode pNode = SalomeTMFPanels.getTestDynamicTree()
				.getSelectedNode();
		if (pNode == null) {
			return;
		} else {
			SimpleData pData = (SimpleData) pNode.getUserObject();
			if (pData instanceof Family) {
				pFamily = (Family) pData;
			} else if (pData instanceof TestList) {
				pTestList = (TestList) pData;
			} else if (pData instanceof Test) {
				pTest = (Test) pData;
			}
		}

		if (pTestList == null) {
			if (pTest != null) {
				pTestList = pTest.getTestListFromModel();
			}
			if (pTestList == null && pFamily != null) {
				pTestList = new TestList("tmp", "JUNIT TEST SUITE");
			}
		}

		if (pTestList != null) {
			Util.log("Test List selected = " + pTestList.getNameFromModel());
			JMeterTester pJunitTester = new JMeterTester(null, true, pProject,
					true, "", "", "", "");
			if (pJunitTester.execute()) {
				Util.log("[JMeterIntegrationPlugin:editTest] ok pressed");
				Attachment pNewattach = pJunitTester.getNewAttachment();
				Attachment pSelectedattach = pJunitTester
						.getSelectedAttachment();
				if (pSelectedattach != null) {
					if (pSelectedattach.equals(pNewattach)) {
						if (!addAttachmentToProject(pNewattach,
								DataModel.getCurrentProject()))
							return;

					}
				}
				
				try {
					if (!pTestList.isInBase()) {
						pTestList.updateInModel(pJunitTester.getJunitClass(),
								"JMETER TEST SUITE");
						pFamily.addTestListInDB(pTestList);
					}
					/*currentJunitAnalyser.createBeanShellJunitTest(
							pSelectedattach, pJunitTester.getJunitClass(),
							pTestList);*/
				} catch (Exception e) {
					Tools.ihmExceptionView(e);
				}
			}
		}
	}

	/********************* interface common ********************/

	String getTestName(String name, TestList pSuite, int index) {
		if (pSuite.getTestFromModel(name) != null) {
			index++;
			name = name + index;
			return getTestName(name, pSuite, index);
		} else {
			return name;
		}
	}

	void addTest(String name, TestList pSuite, String toWrite) {
		String[] idReqs = {};
		addTest(name, "Junit TestCase", idReqs, pSuite, toWrite);
	}

	void addTest(String name, String description, String[] idReqs,
			TestList pSuite, String toWrite) {
		name = getTestName(name, pSuite, 0);
		AutomaticTest pTest = new AutomaticTest(name, description, id_plugin);
		pTest.setConceptorLoginInModel(DataModel.getCurrentUser()
				.getLoginFromModel());
		if (Api.isConnected()) {
			try {
				pSuite.addTestInDBAndModel(pTest);
			} catch (Exception e1) {
				Tools.ihmExceptionView(e1);
				return;
			}
		}
		File file = null;
		try {
			Util.log("[JMeterIntegrationPlugin:AddTest] Create test Script file");
			file = writeJunitFile(null, pTest, toWrite);
		} catch (Exception e2) {
			Tools.ihmExceptionView(e2);
			e2.printStackTrace();
			return;
		}
		Script pScript = new Script(file.getName(), "Junit test script");
		pScript.setLocalisation(file.getAbsolutePath());
		pScript.setTypeInModel(ApiConstants.TEST_SCRIPT);
		pScript.setScriptExtensionInModel(pTest.getExtensionFromModel());
		// pScript.setPlugArg("");

		if (Api.isConnected()) {
			try {
				pTest.addScriptInDBAndModel(pScript, file);
				Util.log("[JMeterIntegrationPlugin:AddTest] Add Script : "
						+ pScript.getNameFromModel() + " to DB & Model");
			} catch (Exception e3) {
				Tools.ihmExceptionView(e3);
				return;
			}
		}

		// add associated requirements
		Vector<ReqManager> reqManagers = pIPlugObject.getReqManagers();
		for (ReqManager reqManager : reqManagers) {
			for (String idReqString : idReqs) {
				int idReq = new Integer(idReqString).intValue();
				reqManager.addReqLinkWithTest(pTest, idReq);
			}
		}
	}

	public void activatePluginInCampToolsMenu(javax.swing.JMenu jMenu) {
	}

	public void activatePluginInDataToolsMenu(javax.swing.JMenu jMenu) {
	}

	public void activatePluginInDynamicComponent(Integer integer) {
	}

	public void activatePluginInStaticComponent(Integer integer) {
	}

	public void allPluginActived(ExtensionPoint commonExtensions,
			ExtensionPoint testDriverExtensions,
			ExtensionPoint scriptEngineExtensions,
			ExtensionPoint bugTrackerExtensions) {

	}

	public void activatePluginInTestToolsMenu(javax.swing.JMenu jMenu) {

		JMenuItem jMeterItem = new JMenuItem(Language.getInstance()
				.getText("Creer_une_suite_de_tests_JMeter_"));
		jMeterItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createJMeterSuitePerformed();
			}
		});
		jMenu.add(jMeterItem);
	}

	public void freeze() {
	}

	public java.util.Vector getUsedUIComponents() {
		return null;
	}

	public void init(Object pIPlugObject) {
		this.pIPlugObject = (IPlugObject) pIPlugObject;
	}

	public boolean isActivableInCampToolsMenu() {
		return false;
	}

	public boolean isActivableInDataToolsMenu() {
		return false;
	}

	public boolean isActivableInTestToolsMenu() {
		return true;
	}

	public boolean isFreezable() {
		return false;
	}

	public boolean isFreezed() {
		return false;
	}

	public void unFreeze() {
	}

	public boolean usesOtherUIComponents() {
		return false;
	}

	public IPlugObject getPIPlugObject() {
		return pIPlugObject;
	}

}
