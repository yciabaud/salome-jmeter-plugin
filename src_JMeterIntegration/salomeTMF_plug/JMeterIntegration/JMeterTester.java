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

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;

import org.objectweb.salome_tmf.data.Attachment;
import org.objectweb.salome_tmf.data.AutomaticTest;
import org.objectweb.salome_tmf.data.FileAttachment;
import org.objectweb.salome_tmf.data.Project;
import org.objectweb.salome_tmf.data.UrlAttachment;
import org.objectweb.salome_tmf.ihm.admin.AskName;
import org.objectweb.salome_tmf.ihm.main.SalomeTMFContext;
import org.objectweb.salome_tmf.ihm.main.datawrapper.DataModel;
import org.objectweb.salome_tmf.ihm.models.ScriptFileFilter;

import salomeTMF_plug.JMeterIntegration.languages.Language;

/**
 * 
 * @author marchemi
 */
public class JMeterTester extends javax.swing.JDialog {

	boolean okPressed = false;
	Attachment pNewAttch = null;
	Attachment pSelectedAttch = null;

	static final String jMeterDescAttach = "[JMETER_CLASSFILE]";
	static JFileChooser fileChooser = new JFileChooser();
	static ScriptFileFilter pJarFileFilter = new ScriptFileFilter(Language
			.getInstance().getText("Fichier_Jar"), ".jar");
	static ScriptFileFilter pZipFileFilter = new ScriptFileFilter(Language
			.getInstance().getText("Fichier_Zip"), ".zip");

	static {
		fileChooser.addChoosableFileFilter(pZipFileFilter);
		fileChooser.addChoosableFileFilter(pJarFileFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
	}

	/** Creates new form JMeterTester */
	public JMeterTester(java.awt.Frame parent, boolean modal,
			AutomaticTest pTest, boolean newtest, String strID,
			String strAttachment, String strClass, String meth, String version) {
		super(parent, modal);
		initComponents();
		// pTest.getTestList();
		jTextFieldTestMethod.setEditable(true);
		jLabelTestMethod.setEnabled(true);
		// initData(pTest.getTestListFromModel(), newtest, strID, strAttachment,
		// strClass, meth);
		initData(DataModel.getCurrentProject(), newtest, strID, strAttachment,
				strClass, meth, version);

	}

	// public JMeterTester(java.awt.Frame parent, boolean modal, TestList
	// pSuite, boolean newtest, String strID, String strAttachment, String
	// strClass) {
	public JMeterTester(java.awt.Frame parent, boolean modal, Project pProject,
			boolean newtest, String strID, String strAttachment,
			String strClass, String version) {
		super(parent, modal);
		initComponents();
		// initData(pSuite, newtest, strID, strAttachment, strClass, "");
		initData(pProject, newtest, strID, strAttachment, strClass, "", version);

	}

	// public void initData(TestList pSuite, boolean newtest, String strID,
	// String strAttachment, String strClass, String meth) {
	public void initData(Project pProject, boolean newtest, String strID,
			String strAttachment, String strClass, String meth, String version) {
		// Hashtable tabAttach = new
		// Hashtable(pSuite.getAttachmentMapFromModel());
		Hashtable tabAttach = new Hashtable(
				pProject.getAttachmentMapFromModel());
		Enumeration e = tabAttach.elements();
		Vector pListJunitClass = new Vector();
		Attachment pAtiveAttach = null;
		jTextFieldTesterClass.setText(strClass);
		jTextFieldTestMethod.setText(meth);
		while (e.hasMoreElements()) {
			Attachment pAttach = (Attachment) e.nextElement();
			// System.out.println("Attach desc = " +
			// pAttach.getDescriptionFromModel());
			if (pAttach.getDescriptionFromModel().equals(jMeterDescAttach)) {
				pListJunitClass.add(pAttach);
				jComboBoxAttachList.addItem(pAttach);
			}
			String id = "" + pAttach.getIdBdd();
			// System.out.println("Test if " + id + " == " + strID);
			if (!newtest && pAtiveAttach == null) {
				if (id.trim().equals(strID.trim())) {
					pAtiveAttach = pAttach;
				}
			}
		}
		if (pAtiveAttach != null) {
			jComboBoxAttachList.setSelectedItem(pAtiveAttach);
		}
		if (version != null) {
			if (version.equals("3.x")) {
				v3.setSelected(true);
			} else {
				v4.setSelected(true);
			}
		} else {
			v3.setSelected(true);
		}
	}

	Attachment getNewAttachment() {
		return pNewAttch;
	}

	Attachment getSelectedAttachment() {
		return pSelectedAttch;
	}

	String getJunitClass() {
		return jTextFieldTesterClass.getText();
	}

	String getJunitMeth() {
		return jTextFieldTestMethod.getText();
	}

	boolean execute() {
		show();
		return okPressed;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {// GEN-BEGIN:initComponents
		jPanelTitle = new javax.swing.JPanel();
		jLabelJunit = new javax.swing.JLabel();
		jPanelAsk = new javax.swing.JPanel();
		jLabelClassPath = new javax.swing.JLabel();
		jPanelVide = new javax.swing.JPanel();
		jComboBoxAttachList = new javax.swing.JComboBox();
		jPanelNewAttach = new javax.swing.JPanel();
		jButtonNewFile = new javax.swing.JButton();
		jButtonNewUrl = new javax.swing.JButton();
		jLabelTesterClass = new javax.swing.JLabel();
		jTextFieldTesterClass = new javax.swing.JTextField();
		jLabelTestMethod = new javax.swing.JLabel();
		jTextFieldTestMethod = new javax.swing.JTextField();
		jPanelReturn = new javax.swing.JPanel();
		jButtonOK = new javax.swing.JButton();
		jButtonCancel = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		jLabelJunit.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/salomeTMF_plug/simpleJunit/resources/junitlogo.gif")));
		jPanelTitle.add(jLabelJunit);

		
		ButtonGroup group = new ButtonGroup();
		group.add(v3);
		group.add(v4);
		jPanelTitle.add(jVersionPanel);
		getContentPane().add(jPanelTitle, java.awt.BorderLayout.NORTH);

		jPanelAsk.setLayout(new java.awt.GridLayout(4, 4, 5, 2));

		jLabelClassPath.setText(Language.getInstance().getText(
				"Chemin_d_acces_a_la_classe__jar_"));
		jPanelAsk.add(jLabelClassPath);

		jPanelAsk.add(jPanelVide);

		jComboBoxAttachList.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBoxAttachListItemStateChanged(evt);
			}
		});

		jPanelAsk.add(jComboBoxAttachList);

		jPanelNewAttach.setLayout(new javax.swing.BoxLayout(jPanelNewAttach,
				javax.swing.BoxLayout.X_AXIS));

		jButtonNewFile.setText(Language.getInstance()
				.getText("Nouveau_Fichier"));
		jButtonNewFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				NewFilePerformed(evt);
			}
		});

		jPanelNewAttach.add(jButtonNewFile);

		jButtonNewUrl.setText(Language.getInstance().getText("Nouvelle_Url"));
		jButtonNewUrl.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				NewUrlPerformed(evt);
			}
		});

		jPanelNewAttach.add(jButtonNewUrl);

		// jPanelAsk.add(jVersionPanel);
		jPanelAsk.add(jPanelNewAttach);

		jLabelTesterClass.setText(Language.getInstance().getText(
				"Classe_Tester__heritant_de_Test_"));
		jPanelAsk.add(jLabelTesterClass);

		jPanelAsk.add(jTextFieldTesterClass);

		jLabelTestMethod.setText(Language.getInstance().getText(
				"Methode_de_test"));
		jLabelTestMethod.setEnabled(false);
		jPanelAsk.add(jLabelTestMethod);

		jTextFieldTestMethod.setEditable(false);
		jPanelAsk.add(jTextFieldTestMethod);

		getContentPane().add(jPanelAsk, java.awt.BorderLayout.CENTER);

		jPanelReturn.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

		jButtonOK.setText(Language.getInstance().getText("OK"));
		jButtonOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				OkPerfomed(evt);
			}
		});

		jPanelReturn.add(jButtonOK);

		jButtonCancel.setText(Language.getInstance().getText("Annuler"));
		jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CancelPerformed(evt);
			}
		});

		jPanelReturn.add(jButtonCancel);

		getContentPane().add(jPanelReturn, java.awt.BorderLayout.SOUTH);

		pack();
	}// GEN-END:initComponents

	private void jComboBoxAttachListItemStateChanged(
			java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBoxAttachListItemStateChanged
		// TODO add your handling code here:
		pSelectedAttch = (Attachment) jComboBoxAttachList.getSelectedItem();
	}// GEN-LAST:event_jComboBoxAttachListItemStateChanged

	private void NewUrlPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_NewUrlPerformed
		// TODO add your handling code here:
		AskName askName = new AskName(Language.getInstance().getText(
				"Entrez_une_URL_"), Language.getInstance().getText(
				"Attacher_une_URL"),
				org.objectweb.salome_tmf.ihm.languages.Language.getInstance()
						.getText("url"), null, SalomeTMFContext.getInstance()
						.getSalomeFrame());
		String res = askName.getResult();
		// Attachment pOldAttch = null;
		Attachment pOldAttch = pNewAttch;
		if (res != null) {
			if (pNewAttch != null) {
				jComboBoxAttachList.removeItem(pNewAttch);
			}
			try {
				pNewAttch = new UrlAttachment(res, jMeterDescAttach);
				URL url = new URL(res);
				// ((UrlAttachment)pNewAttch).setName(res);
				((UrlAttachment) pNewAttch).setUrl(url);
				// pNewAttch.setDescription(jMeterDescAttach);
				jComboBoxAttachList.addItem(pNewAttch);
				jComboBoxAttachList.setSelectedItem(pNewAttch);
			} catch (Exception e) {
				if (pOldAttch != null) {
					jComboBoxAttachList.addItem(pOldAttch);
				}
				pNewAttch = pOldAttch;
			}
		}
	}// GEN-LAST:event_NewUrlPerformed

	private void NewFilePerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_NewFilePerformed
		// TODO add your handling code here:
		int returnVal = fileChooser.showOpenDialog(this);
		Attachment pOldAttch = pNewAttch;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (pNewAttch != null) {
				jComboBoxAttachList.removeItem(pNewAttch);
			}
			try {
				File file = fileChooser.getSelectedFile();
				if (!file.exists()) {
					if (pOldAttch != null) {
						jComboBoxAttachList.addItem(pOldAttch);
					}
					return;
				}
				// Date dateOfFile = new Date(file.lastModified());
				// pNewAttch = new FileAttachment(file.getName(),
				// jMeterDescAttach);
				pNewAttch = new FileAttachment(file, jMeterDescAttach);
				// ((FileAttachment)pNewAttch).setName(file.getName());
				// ((FileAttachment)pNewAttch).setLocalisation(file.getAbsolutePath());
				// ((FileAttachment)pNewAttch).setSize(new Long(file.length()));
				// ((FileAttachment)pNewAttch).setDate(new
				// Date(file.lastModified()));
				// pNewAttch.setDescription(jMeterDescAttach);
				jComboBoxAttachList.addItem(pNewAttch);
				jComboBoxAttachList.setSelectedItem(pNewAttch);
			} catch (Exception e) {
				if (pOldAttch != null) {
					jComboBoxAttachList.addItem(pOldAttch);
				}
				pNewAttch = pOldAttch;
			}
		}
	}// GEN-LAST:event_NewFilePerformed

	private void CancelPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CancelPerformed
		// TODO add your handling code here:
		okPressed = false;
		dispose();
	}// GEN-LAST:event_CancelPerformed

	private void OkPerfomed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_OkPerfomed
		// TODO add your handling code here:
		okPressed = true;
		dispose();
	}// GEN-LAST:event_OkPerfomed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		// new JMeterTester(new javax.swing.JFrame(), true, null, true, "", "",
		// "").show();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonCancel;
	private javax.swing.JButton jButtonNewFile;
	private javax.swing.JButton jButtonNewUrl;
	private javax.swing.JButton jButtonOK;
	private javax.swing.JComboBox jComboBoxAttachList;
	private javax.swing.JLabel jLabelClassPath;
	private javax.swing.JLabel jLabelJunit;
	private javax.swing.JLabel jLabelTestMethod;
	private javax.swing.JLabel jLabelTesterClass;
	private javax.swing.JPanel jPanelAsk;
	private javax.swing.JPanel jPanelNewAttach;
	private javax.swing.JPanel jPanelReturn;
	private javax.swing.JPanel jPanelTitle;
	private javax.swing.JPanel jPanelVide;
	private JRadioButton v3;
	private JRadioButton v4;
	private javax.swing.JPanel jVersionPanel;
	private javax.swing.JTextField jTextFieldTestMethod;
	private javax.swing.JTextField jTextFieldTesterClass;
	// End of variables declaration//GEN-END:variables

}
